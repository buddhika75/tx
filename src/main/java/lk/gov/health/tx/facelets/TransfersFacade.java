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
import lk.gov.health.tx.entities.Transfers;
import lk.gov.health.tx.entities.Units;

/**
 *
 * @author buddhika
 */
@Stateless
public class TransfersFacade extends AbstractFacade<Transfers> {

    @PersistenceContext(unitName = "lk.gov.health_tx_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransfersFacade() {
        super(Transfers.class);
    }

    public List<Transfers> getTransfersByNic(String nic){
        return em.createNamedQuery("Transfers.findByNic").setParameter("nic", nic).getResultList();
    }
    
    public List<Transfers> getTransfersByOrderName(OrderNames orderName){
        return em.createNamedQuery("Transfers.findByTransferOrderName").setParameter("order", orderName).getResultList();
    }
    
    public List<Transfers> getTransfersByFromUnit(Units units){
        return em.createNamedQuery("Transfers.findByFromUnit").setParameter("unit", units).getResultList();
    }
    
    public List<Transfers> getTransfersByToUnit(Units units){
        return em.createNamedQuery("Transfers.findByToUnit").setParameter("unit", units).getResultList();
    }
    
    /**
     *
     * @NamedQuery(name = "Transfers.findByNic", query = "SELECT t FROM
     * Transfers t WHERE t.nic = :nic"),
     * @NamedQuery(name = "Transfers.findByTransferOrderName", query = "SELECT t
     * FROM Transfers t WHERE t.transferordernameid = :order"),
     * @NamedQuery(name = "Transfers.findByFromUnit", query = "SELECT t FROM
     * Transfers t WHERE t.fromunitid = :unit"),
     * @NamedQuery(name = "Transfers.findByToUnit", query = "SELECT t FROM
     * Transfers t WHERE t.tounitid = :unit"),
     *
     */
}
