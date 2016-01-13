/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nink.servico.ferramentas;

import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author jefferson
 */
public class FileUtils {

    private FileUtils() {
    }

    public static boolean moveFile(File file, String folder) {

        File destinoFile = new File(folder + file.getName());

        return file.renameTo(destinoFile);

    }

    public static void moveFile_s(File file, String folder) throws AccessDeniedException {

        if (!moveFile(file, folder)) {
            
            throw new AccessDeniedException("Error : não foi possível mover o arquivo  "
                    + "<" + file.getName() + "> para a pasta <" + folder + ">");
            
        }else{
            System.out.println(Thread.currentThread().getId() + "<> [FileUtils] O arquivo <" + file.getName() + "> foi movido para a pasta <" + folder + ">");
        }
    }
    
    public static void copyFile(UploadedFile uploadedFile, String folder) throws IOException {

        Path path = Paths.get(folder + uploadedFile.getFileName());

        Files.copy(uploadedFile.getInputstream(), path, StandardCopyOption.REPLACE_EXISTING);
    }
}
