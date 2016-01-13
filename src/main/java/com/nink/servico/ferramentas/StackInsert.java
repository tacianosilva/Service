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
public class StackInsert implements Stack {

    private class Node {

        private final Object object;

        public Node next;

        public Node(Object object, Node node) {
            this.object = object;
            this.next = node;
        }
    }

    private static Node node = null;

    private static final StackInsert INSTANCE = new StackInsert();

    public static synchronized StackInsert getInstance() {
        return INSTANCE;
    }

    private StackInsert() {
    }

    @Override
    public synchronized boolean empty() {
        return node == null;
    }

    @Override
    public Object pop() {

        Object object = node.object;

        node = node.next;

        return object;
    }

    @Override
    public Object top() {
        return node.object;
    }

    @Override
    public void push(Object object) {
        node = new Node(object, node);
    }
    
}
