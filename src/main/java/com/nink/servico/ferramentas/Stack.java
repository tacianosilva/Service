/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nink.servico.ferramentas;

/**
 *
 * @author jefferson
 */
public interface Stack {
    
    boolean empty();
    Object pop();
    Object top();
    void push(Object object);
}
