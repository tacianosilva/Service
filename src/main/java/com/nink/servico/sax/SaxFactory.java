/**
 *
 */
package com.nink.servico.sax;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author jefferson
 *
 */
public abstract class SaxFactory extends DefaultHandler {

    /**
     * Construtor que inicializa os objetos necessarios para fazer o parse do
     * arquivo contato.xml
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public abstract void processar() throws ParserConfigurationException,
            SAXException, IOException;

    /**
     * Indica que o parser achou o inicio do documento XML. Este evento nao lhe
     * passa qualquer informacao, apenas indica que o parser vai comecar a
     * escanear o arquivo XML.
     *
     */
    @Override
    public abstract void startDocument();

    /**
     * /** Indica que o parser achou o inicio de uma tag (tag de
     * abertura/inicio). Este evento fornece o nome do elemento, o nome e valor
     * dos atributos deste elemento, e tambem pode fornecer as informacoes sobre
     * o namespace.
     *
     * @param uri
     * @param localName
     * @param tag
     * @param atributos
     * @throws SAXException
     */
    @Override
    public abstract void startElement(String uri, String localName, String tag,
            Attributes atributos) throws SAXException;

    /**
     *
     * Indica que o parser achou o fim de uma tag/elemento. Este evento fornece
     * o nome do elemento, e tambem pode fornecer as informacoes sobre o
     * namespace.
     *
     * @param uri
     * @param localName
     * @param tag
     */
    @Override
    public abstract void endElement(String uri, String localName, String tag);

    /**
     * Indica que o parser achou algum Texto (Informacao).
     *
     * @param ch
     * @param start
     * @param length
     */
    @Override
    public abstract void characters(char[] ch, int start, int length);

    /**
     * Esse método retorna uma lista contendo os modelos
     *
     * @return
     */
    public abstract ArrayList<Object> getListModelo();

    /**
     * Retorna o caminho de Schema do leitor de XML.
     *
     * @return
     */
    public static String getPathSchema() {
        return null;
    }

}
