/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nink.servico.tarefas;

import com.nink.servico.ferramentas.Persistencia;
import com.nink.servico.ferramentas.StackInsert;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author jefferson
 */
@Stateless
public class InsertBancoDeDados {

    @EJB
    Persistencia persistencia;

    private static final StackInsert STACK_INSERT = StackInsert
            .getInstance();

    public InsertBancoDeDados() {
    }

    @Schedule(hour = "*", minute = "*", second = "*/20", persistent = false)
    public void tarefa() {
        
        System.out.println(Thread.currentThread().getId() + ": [InsertBancoDeDados:tarefa] Verificando a existência de objetos para persistir ... ");
        
        while (!STACK_INSERT.empty()) {
            persistencia.insert(STACK_INSERT.pop());
        }
    }
}
