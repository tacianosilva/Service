/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nink.servico.beans;

import com.nink.servico.ferramentas.FileUtils;
import com.nink.servico.ferramentas.Propriedades;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author jefferson
 */
@ManagedBean
public class FileUploadView {

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesMessage messageError = new FacesMessage("Error", "Não foi possível carregar o arquivo. Contate o administrador");

        UploadedFile uploadedFile = event.getFile();

        try {

            FileUtils.copyFile(uploadedFile, Propriedades.MAP_PASTAS.get("PASTA_WEB"));
            uploadedFile.getInputstream().close();
            FacesContext.getCurrentInstance().addMessage(null, message);

            System.out.println(Thread.currentThread().getId() + ": Arquivo <" + event.getFile().getFileName() + "> carregado com sucesso");

        } catch (IOException ex) {
            Logger.getLogger(FileUploadView.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, messageError);
        }
    }
}
