/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.tx.facelets;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lk.gov.health.tx.entities.Institutions;

/**
 *
 * @author buddhika
 */
@Stateless
public class InstitutionsFacade extends AbstractFacade<Institutions> {
    @PersistenceContext(unitName = "lk.gov.health_tx_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InstitutionsFacade() {
        super(Institutions.class);
    }
    
}
