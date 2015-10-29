/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.tx.facelets;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lk.gov.health.tx.entities.OrderNames;

/**
 *
 * @author buddhika
 */
@Stateless
public class OrderNamesFacade extends AbstractFacade<OrderNames> {
    @PersistenceContext(unitName = "lk.gov.health_tx_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderNamesFacade() {
        super(OrderNames.class);
    }
    
    public List<OrderNames> getOrderNamesByOrderName(String str){
        return em.createNamedQuery("OrderNames.findByOrdername").setParameter("ordername", str.toLowerCase().trim()).getResultList();
    }
    
}
