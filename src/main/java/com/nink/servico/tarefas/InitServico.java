/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nink.servico.tarefas;

import com.nink.servico.ferramentas.Propriedades;
import com.nink.servico.ferramentas.StackInsert;
import com.nink.servico.sax.ClassesSax;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author jefferson
 */
@Startup
@Singleton
public class InitServico {

    public static boolean INIT_SERVICO = false;

    public InitServico() {
        
        if (!INIT_SERVICO) {
            System.out.println(Thread.currentThread().getId() + ": Iniciando sistema .... \n");
        }
    }

    @PostConstruct
    void atStartup() {

        if (!INIT_SERVICO) {

            INIT_SERVICO = true;

            if (!ClassesSax.foiConstruido()) {
                ClassesSax.getInstance().construir();
            }

            try {

                if (!Propriedades.foiConstruido()) {
                    Propriedades.getInstance().construir();
                }

            } catch (IOException ex) {
                Logger.getLogger(InitServico.class.getName()).log(Level.SEVERE, null, ex);
            }

            StackInsert.getInstance();

        }
    }
}
