package com.nink.servico.sax.cliente;

import com.nink.servico.ferramentas.Propriedades;
import com.nink.servico.ferramentas.XmlUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.nink.servico.modelos.Cliente;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.nink.servico.sax.SaxFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Created by jefferson on 04/01/16.
 */
public class ClientesSax extends SaxFactory {

    private final StringBuffer stringBuffer = new StringBuffer(50);

    private final File fileXML;

    private final ArrayList<Object> clientes = new ArrayList<>();

    private static final XmlUtils XML_UTILS = new XmlUtils();

    private Cliente clienteTemporario;
    
    public ClientesSax(String fileXmlPath) {
        this.fileXML = new File(fileXmlPath);

        if (!XML_UTILS.extensaoCorreta(this.fileXML)) {
            throw new IllegalArgumentException("Error : extensão do arquivo <" + fileXmlPath + "> é incorreta");
        }
        
    }

    public Cliente getClienteTemporario() {
        return clienteTemporario;
    }

    public String getStringBufferForStringType() {
        return stringBuffer.toString().trim();
    }

    public Integer getStringBufferForIntegerType() {
        return Integer.parseInt(getStringBufferForStringType());
    }

    @Override
    public void processar() throws ParserConfigurationException, SAXException,
            IOException {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();

        saxParser.parse(this.fileXML, this);
    }

    @Override
    public void startDocument() {

        System.out.println(Thread.currentThread().getId() + ": Iniciando leitura do arquivo XML : "
                + this.fileXML.getName());

    }

    @Override
    public void startElement(String uri, String localName, String tag,
            Attributes atributos) {

        if (tag.equalsIgnoreCase(ClientesTagSax.CLIENTE.getTag())) {
            this.clienteTemporario = new Cliente();
        }

    }

    @Override
    public void endElement(String uri, String localName, String tag) {

        if (tag.equalsIgnoreCase(ClientesTagSax.CLIENTE.getTag())) {
            this.clientes.add(this.clienteTemporario);
        } else if (tag.equalsIgnoreCase(ClientesTagSax.NOME.getTag())) {
            this.clienteTemporario.setNome(getStringBufferForStringType());
        } else if (tag.equalsIgnoreCase(ClientesTagSax.IDADE.getTag())) {
            this.clienteTemporario.setIdade(getStringBufferForIntegerType());
        } else if (tag.equalsIgnoreCase(ClientesTagSax.PROFISSAO.getTag())) {
            this.clienteTemporario.setProfissao(getStringBufferForStringType());
        } else if (tag.equalsIgnoreCase(ClientesTagSax.SEXO.getTag())) {
            this.clienteTemporario.setSexo(getStringBufferForStringType());
        }

        // Limpeza
        this.stringBuffer.delete(0, this.stringBuffer.length());

    }

    @Override
    public void characters(char[] ch, int start, int length) {
        this.stringBuffer.append(ch, start, length);
    }

    @Override
    public ArrayList<Object> getListModelo() {
        return this.clientes;
    }

    public static String getPathSchema() {

        if (!Propriedades.foiConstruido()) {
            try {
                Propriedades.getInstance().construir();
            } catch (IOException ex) {
                Logger.getLogger(ClientesSax.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return Propriedades.MAP_PASTAS.get("PASTA_SCHEMA")
                + "ClienteSchema.xsd";
    }
}
