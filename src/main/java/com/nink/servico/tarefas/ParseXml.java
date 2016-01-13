/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nink.servico.tarefas;

import com.nink.servico.ferramentas.FileUtils;
import com.nink.servico.ferramentas.Propriedades;
import com.nink.servico.threads.ThreadXml;
import java.io.File;
import java.nio.file.AccessDeniedException;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author jefferson
 */
@Stateless
public class ParseXml {

    private static String pasta_nao_processado;

    private static String pasta_em_processamento;

    private static boolean already = false;

    public ParseXml() {

        if (InitServico.INIT_SERVICO) {

            if (!already) {

                pasta_nao_processado = Propriedades.MAP_PASTAS.get("XML_NAO_PROCESSADO");
                pasta_em_processamento = Propriedades.MAP_PASTAS.get("XML_EM_PROCESSAMENTO");

                already = true;
            }

        } else {
            System.out.println(Thread.currentThread().getId() + ": [ParseXml] Esperando início do sistema ...");
        }

    }

    @Schedule(hour = "*", minute = "*", second = "*/25", persistent = false)
    public void tarefa() throws AccessDeniedException {

        if (InitServico.INIT_SERVICO) {

            System.out.println(Thread.currentThread().getId() + ": [ParseXml:tarefa] Verificando a existência de arquivos na pasta <" + pasta_nao_processado + ">");

            File[] files = new File(pasta_nao_processado).listFiles();

            if (files.length > 0) {

                //ArrayList<Thread> threads = new ArrayList<>();
                for (File file : files) {

                    if (file.isFile()) {

                        FileUtils.moveFile_s(file, pasta_em_processamento);

                        ThreadXml threadXml = new ThreadXml(file.getName());

                        Thread thread = new Thread(threadXml);

                        //threads.add(thread);
                        thread.start();
                    }
                }

                System.out.println(Thread.currentThread().getId() + ": [ParseXml:tarefa] fim dos processos ...");

                //TODO: Talvez seja interessante colocar join aqui
                /*for (Thread thread : threads) {
					try {
						thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println("Fim das threads");*/
            } else {
                System.out.println(Thread.currentThread().getId() + ": [ParseXml:tarefa] nenhum arquivo novo na pasta <" + pasta_nao_processado + ">");
            }
        } else {
            System.out.println(Thread.currentThread().getId() + ": [ParseXml:tarefa] Esperando início do sistema ...");
        }
    }
}
