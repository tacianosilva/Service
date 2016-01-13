/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nink.servico.ferramentas;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jefferson
 */
@Stateless
public class Persistencia {

    @PersistenceContext(unitName = "persistencia")
    private EntityManager em;

    public void insert(Object object) {
        System.out.println(Thread.currentThread().getId() + ": Iniciando persistência do objeto : " + object.toString());
        em.persist(object);
    }

}
