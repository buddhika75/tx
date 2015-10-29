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
import lk.gov.health.tx.entities.Doctors;
import lk.gov.health.tx.entities.Units;

/**
 *
 * @author buddhika
 */
@Stateless
public class DoctorsFacade extends AbstractFacade<Doctors> {

    @PersistenceContext(unitName = "lk.gov.health_tx_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DoctorsFacade() {
        super(Doctors.class);
    }

    public List<Doctors> getDoctorsByUnit(Units unit) {
        return em.createNamedQuery("Doctors.findByUnit")
                .setParameter("unit", unit)
                .getResultList();
    }

}
