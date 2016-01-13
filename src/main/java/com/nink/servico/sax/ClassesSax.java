/**
 *
 */
package com.nink.servico.sax;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.nink.servico.modelos.Cliente;
import com.nink.servico.sax.cliente.ClientesSax;

/**
 * @author jefferson
 *
 */
//@Singleton
public class ClassesSax {

    public static Map<Integer, String> MAP_CLASSES_SAX;

    public static Map<String, File> MAP_CLASSES_FILE_XSD;

    public static Map<String, Class<?>> MAP_CLASSES_MODELOS;

    private static boolean construido = false;

    private static final ClassesSax INSTANCE = new ClassesSax();

    public static synchronized ClassesSax getInstance() {
        return INSTANCE;
    }

    private ClassesSax() {
    }

    public synchronized void construir() {

        if (!construido) {

            System.out.println(Thread.currentThread().getId() + ": Construindo ClassesSax ...");
            
            MAP_CLASSES_SAX = new HashMap<>();

            MAP_CLASSES_FILE_XSD = new HashMap<>();

            MAP_CLASSES_MODELOS = new HashMap<>();

            MAP_CLASSES_SAX.put(0, Cliente.getNameEntity());

            MAP_CLASSES_FILE_XSD.put(MAP_CLASSES_SAX.get(0), new File(
                    ClientesSax.getPathSchema()));

            MAP_CLASSES_MODELOS.put(MAP_CLASSES_SAX.get(0), ClientesSax.class);

            construido = true;
        }
    }
    
    public static synchronized boolean foiConstruido() {
        return construido;
    }
}
