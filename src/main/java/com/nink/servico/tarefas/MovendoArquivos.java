/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nink.servico.tarefas;

import com.nink.servico.ferramentas.FileUtils;
import com.nink.servico.ferramentas.Propriedades;
import com.nink.servico.ferramentas.XmlUtils;
import com.nink.servico.threads.ThreadXml;
import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author jefferson
 */
@Stateless
public class MovendoArquivos {

    private static boolean already;

    private static final XmlUtils XML_UTILS = new XmlUtils();

    private static String pasta_web;

    private static String pasta_xml_nao_processado;

    //private static String pasta_zip_nao_processado;
    public MovendoArquivos() {

        if (InitServico.INIT_SERVICO) {

            if (!already) {

                pasta_web = Propriedades.MAP_PASTAS.get("PASTA_WEB");

                pasta_xml_nao_processado = Propriedades.MAP_PASTAS.get("XML_NAO_PROCESSADO");

                already = true;
            }

        } else {
            System.out.println(Thread.currentThread().getId() + ": [MovendoArquivos] Esperando início do sistema ...");
        }
    }

    @Schedule(hour = "*", minute = "*", second = "*/25", persistent = false)
    public void tarefa() throws AccessDeniedException {
        if (InitServico.INIT_SERVICO) {

            System.out.println(Thread.currentThread().getId() + ": [MovendoArquivos:tarefa] buscando novos arquivos na pasta <" + pasta_web + ">");

            File[] files = new File(pasta_web).listFiles();

            for (File file : files) {

                if (file.isFile()) {

                    try {

                        if (XML_UTILS.extensaoCorreta(file)) {
                            FileUtils.moveFile_s(file, pasta_xml_nao_processado);
                        } //TODO: Caso não seja um arquivo xml ,aqui verificará se o arquivo é do tipo zip
                        // .
                        // .
                        // .
                        // .
                        else {
                            throw new IOException("Error : arquivo <" + file + "> não reconhecido pelo sistema");
                        }
                    } catch (IOException e) {

                        Logger.getLogger(ThreadXml.class.getName()).log(Level.SEVERE, null, e);
                        System.out.println(Thread.currentThread().getId() + ": [MovendoArquivos:tarefa] Movendo o arquivo : <" + file.getName()
                                + "> para pasta de lixo");

                        FileUtils.moveFile_s(file,
                                Propriedades.MAP_PASTAS.get("PASTA_LIXO"));
                    }
                }

            }

        } else {
            System.out.println(Thread.currentThread().getId() + ": [MovendoArquivos:tarefa] Esperando início do sistema ...");
        }
    }
}
