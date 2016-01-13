/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nink.servico.ferramentas;

import com.nink.servico.exceptions.VoltarParaParseException;
import com.nink.servico.sax.ClassesSax;
import com.nink.servico.sax.SaxFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author jefferson
 */
public class XmlUtils extends SaxFactory {

    private static final String REGULAR_EXPRESSION_XML = "^([a-zA-Z0-9_-]+)\\.(xml)$";

    private StringBuffer stringBuffer;

    private File xml = null;

    private String classtagPrincipal;

    public XmlUtils(String xml) {
        this.xml = new File(xml);
    }

    public XmlUtils(File xml) {
        this.xml = xml;
    }

    public XmlUtils() {

    }

    /**
     * Verifica se a extensão do arquivo condiz com a expressão regular.
     *
     * @param arquivo
     * @return
     */
    public boolean extensaoCorreta(File arquivo) {
        return extensaoCorreta(arquivo.getName());
    }

    /**
     * Verifica se a extensão do arquivo condiz com a expressão regular. Deve
     * ser passado o nome do arquivo.
     *
     * @param arquivo
     * @return
     */
    public boolean extensaoCorreta(String arquivo) {
        return arquivo.matches(REGULAR_EXPRESSION_XML);
    }
    
    public boolean extensaoCorreta() {
        return extensaoCorreta(this.xml);
    }

    private void construtor() {

        this.stringBuffer = new StringBuffer(50);
        this.classtagPrincipal = null;

        if (!extensaoCorreta(this.xml.getName())) {
            throw new IllegalArgumentException("A nomenclatura do arquivo <"
                    + this.xml + "> esta incorreta");
        }
    }

    /**
     * Retorna a classe tag principal do arquivo xml
     *
     * @return
     */
    public String getClasstagPrincipal() {
        return classtagPrincipal;
    }

    public void validar() throws ParserConfigurationException, SAXException,
            IOException {
        construtor();

        try {
            processar();
        } catch (VoltarParaParseException e) {
            System.out.println(Thread.currentThread().getId() + "<> [XmlUtils] Iniciando validação do arquivo :" + this.xml + " conforme seu 'Schema' ");
            validarSchema();
        }
    }

    private void validarSchema() throws SAXException, IOException {

        SchemaFactory factory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        Schema schema = factory.newSchema(ClassesSax.MAP_CLASSES_FILE_XSD
                .get(this.classtagPrincipal));

        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(this.xml));

    }

    @Override
    public void processar() throws ParserConfigurationException, SAXException,
            IOException {

        if (this.xml != null) {

            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

            saxParserFactory.setValidating(true);
            saxParserFactory.setNamespaceAware(true);

            SAXParser saxParser = saxParserFactory.newSAXParser();

            saxParser.parse(this.xml, this);
        } else {
            throw new NullPointerException("Arquivo xml não pode ser nulo");
        }

    }

    @Override
    public void startDocument() {
    }

    @Override
    public void startElement(String uri, String localName, String tag,
            Attributes atributos) throws SAXException {

        this.classtagPrincipal = tag;

        if (ClassesSax.MAP_CLASSES_SAX.containsValue(tag)) {

            System.out.println(Thread.currentThread().getId() + "<> [XmlUtils] Sucesso : A classe Tag <" + tag + "> do arquivo <"
                    + this.xml + "> foi reconhecido no sistema");

            throw new VoltarParaParseException();

        } else {

            throw new SAXException("Error: A classe Tag <" + tag
                    + "> do arquivo <" + this.xml
                    + "> não foi reconhecido no sistema");
        }

    }

    @Override
    public void endElement(String uri, String localName, String tag) {
        this.stringBuffer.delete(0, this.stringBuffer.length());

    }

    @Override
    public void characters(char[] ch, int start, int length) {
        this.stringBuffer.append(ch, start, length);
    }

    @Override
    public ArrayList<Object> getListModelo() {
        return null;
    }

    public static String getPathSchema() {
        return null;
    }
}
