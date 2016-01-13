/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nink.servico.threads;

import com.nink.servico.ferramentas.FileUtils;
import com.nink.servico.ferramentas.Propriedades;
import com.nink.servico.ferramentas.StackInsert;
import com.nink.servico.ferramentas.XmlUtils;
import com.nink.servico.sax.ClassesSax;
import com.nink.servico.sax.SaxFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author jefferson
 */
public class ThreadXml implements Runnable {

    private static final String FOLDER_PROCESSADO = Propriedades.MAP_PASTAS
            .get("XML_PROCESSADO");

    private static final String FOLDER_EM_PROCESSAMENTO = Propriedades.MAP_PASTAS
            .get("XML_EM_PROCESSAMENTO");

    private final File file;

    private final StackInsert stackInsert;

    private final XmlUtils xmlUtil;

    /**
     * Nome do arquivo xml na pasta XML_EM_PROCESSAMENTO
     *
     * @param xml
     */
    public ThreadXml(String xml) {

        this.file = new File(FOLDER_EM_PROCESSAMENTO + xml);

        this.stackInsert = StackInsert.getInstance();

        this.xmlUtil = new XmlUtils(this.file);

    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + "<> [ThreadXml] Iniciando processamento do arquivo <" + this.file.getName() + ">");

        try {

            if (this.xmlUtil.extensaoCorreta()) {
                System.out.println(Thread.currentThread().getId() + "<> [ThreadXml] Nomenclatura do arquivo ...");
            } else {
                throw new IllegalArgumentException("<> [ThreadXml] Error : A  nomenclatura do arquivo é incorreta");
            }

            System.out.print(Thread.currentThread().getId() + "");

            this.xmlUtil.validar();

            System.out.println(Thread.currentThread().getId() + "<> [ThreadXml] O arquivo <" + this.file + " > é valido");

            System.out.println(Thread.currentThread().getId() + "<> [ThreadXml] Capturando o elemento classe do arquivo xml");

            String classe = this.xmlUtil.getClasstagPrincipal();

            System.out.println(Thread.currentThread().getId() + "<> [ThreadXml] Construindo objetos para leitura do arquivo ...");

            Class<?> clasz = ClassesSax.MAP_CLASSES_MODELOS.get(classe);

            Constructor<?> constructor = clasz.getConstructor(String.class);

            Object instance = constructor
                    .newInstance(this.file.getPath());

            SaxFactory sax = (SaxFactory) instance;

            System.out.println(Thread.currentThread().getId() + "<> [ThreadXml] construção realizada com sucesso.");

            System.out.println(Thread.currentThread().getId() + "<> [ThreadXml] Iniciando parse do arquivo com a classe <" + instance.getClass().getName() + ">");

            sax.processar();

            System.out.println(Thread.currentThread().getId() + "<> [ThreadXml] parse realizado com sucesso.");

            System.out.println(Thread.currentThread().getId() + "<> [ThreadXml] recebendo a refêrencia da lista de objetos do modelo ... ");

            ArrayList<Object> list = sax.getListModelo();

            System.out.println(Thread.currentThread().getId() + "<> [ThreadXml] Inserindo objetos na fila para persistir ...");

            for (Object object : list) {
                this.stackInsert.push(object);
            }

            System.out.print(Thread.currentThread().getId() + "<> [ThreadXml] Sucesso : objetos inserido com sucesso");

            FileUtils.moveFile_s(this.file, FOLDER_PROCESSADO);

        } catch (ParserConfigurationException | SAXException | IOException | IllegalArgumentException ex) {
            Logger.getLogger(ThreadXml.class.getName()).log(Level.SEVERE, null, ex);
            try {
                error();
            } catch (AccessDeniedException ex1) {
                Logger.getLogger(ThreadXml.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (NoSuchMethodException | SecurityException | InstantiationException |
                IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(ThreadXml.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            System.out.println("<> [ThreadXml] finalizando a thread" + Thread.currentThread().getId());
            
            Thread.currentThread().interrupt();
        }
    }

    private void error() throws AccessDeniedException {

        System.out.println(Thread.currentThread().getId() + "<> [ThreadXml] Movendo o arquivo : <" + this.file.getName()
                + "> para pasta de lixo");

        FileUtils.moveFile_s(this.file,
                Propriedades.MAP_PASTAS.get("PASTA_LIXO"));
    }

}
