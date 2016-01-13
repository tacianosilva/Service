/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nink.servico.ferramentas;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jefferson
 */
public class Propriedades {

    public static Map<String, String> MAP_PASTAS;

    private static boolean construido = false;

    private static final Propriedades INSTANCE = new Propriedades();

    public static synchronized Propriedades getInstance() {
        return INSTANCE;
    }

    private Propriedades() {
    }
    
    public synchronized void construir() throws IOException {

        if (!construido) {

            System.out.println("Construindo Propriedades ...");
            
            MAP_PASTAS = new HashMap<>();

            File pasta;

            MAP_PASTAS.put("PASTA_PADRAO", "/tmp/servicoBr/");

            pasta = new File(MAP_PASTAS.get("PASTA_PADRAO"));

            pasta.mkdir();
            
            MAP_PASTAS.put("PASTA_WEB", MAP_PASTAS.get("PASTA_PADRAO")
                    + "web/");
            
            pasta = new File(MAP_PASTAS.get("PASTA_WEB"));

            pasta.mkdir();
            
            MAP_PASTAS.put("PASTA_LIXO", MAP_PASTAS.get("PASTA_PADRAO")
                    + "lixo/");

            pasta = new File(MAP_PASTAS.get("PASTA_LIXO"));

            pasta.mkdir();

            MAP_PASTAS.put("PASTA_SCHEMA", MAP_PASTAS.get("PASTA_PADRAO")
                    + "schemas/");

            pasta = new File(MAP_PASTAS.get("PASTA_SCHEMA"));

            pasta.mkdir();

            MAP_PASTAS
                    .put("PASTA_ZIP", MAP_PASTAS.get("PASTA_PADRAO") + "zip/");

            pasta = new File(MAP_PASTAS.get("PASTA_ZIP"));

            pasta.mkdir();

            MAP_PASTAS.put("ZIP_NAO_PROCESSADO", MAP_PASTAS.get("PASTA_ZIP")
                    + "NaoProcessado/");

            pasta = new File(MAP_PASTAS.get("ZIP_NAO_PROCESSADO"));

            pasta.mkdir();

            MAP_PASTAS.put("ZIP_EM_PROCESSAMENTO", MAP_PASTAS.get("PASTA_ZIP")
                    + "EmProcessamento/");

            pasta = new File(MAP_PASTAS.get("ZIP_EM_PROCESSAMENTO"));

            pasta.mkdir();

            MAP_PASTAS.put("ZIP_PROCESSADO", MAP_PASTAS.get("PASTA_ZIP")
                    + "Processada/");

            pasta = new File(MAP_PASTAS.get("ZIP_PROCESSADO"));

            pasta.mkdir();

            MAP_PASTAS
                    .put("PASTA_XML", MAP_PASTAS.get("PASTA_PADRAO") + "xml/");

            pasta = new File(MAP_PASTAS.get("PASTA_XML"));

            pasta.mkdir();

            MAP_PASTAS.put("XML_NAO_PROCESSADO", MAP_PASTAS.get("PASTA_XML")
                    + "NaoProcessado/");

            pasta = new File(MAP_PASTAS.get("XML_NAO_PROCESSADO"));

            pasta.mkdir();

            MAP_PASTAS.put("XML_EM_PROCESSAMENTO", MAP_PASTAS.get("PASTA_XML")
                    + "EmProcessamento/");

            pasta = new File(MAP_PASTAS.get("XML_EM_PROCESSAMENTO"));

            pasta.mkdir();

            MAP_PASTAS
                    .put("XML_PROCESSADO",
                            MAP_PASTAS.get("PASTA_XML")
                            + "Processada/");

            pasta = new File(MAP_PASTAS.get("XML_PROCESSADO"));

            pasta.mkdir();

            construido = true;
        }
    }

    public static synchronized boolean foiConstruido() {
        return construido;
    }
}
