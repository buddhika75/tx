/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.tx.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author buddhika
 */
@Entity
@Table(name = "units")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Units.findAll", query = "SELECT u FROM Units u"),
    @NamedQuery(name = "Units.findById", query = "SELECT u FROM Units u WHERE u.id = :id"),
    @NamedQuery(name = "Units.findByUnitName", query = "SELECT u FROM Units u WHERE u.unitName like :unitName"),
    @NamedQuery(name = "Units.findByUnitOrInstituteName", query = "SELECT u FROM Units u WHERE lower(u.unitName) like :unitName or lower(u.institutionID.instname) like :unitName"),
    @NamedQuery(name = "Units.findByCarder", query = "SELECT u FROM Units u WHERE u.carder = :carder")})
public class Units implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Unit_Name")
    private String unitName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Carder")
    private int carder;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tounitid")
    private Collection<Transfers> transfersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromunitid")
    private Collection<Transfers> transfersCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitid")
    private Collection<Doctors> doctorsCollection;
    @JoinColumn(name = "Institution_ID", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Institutions institutionID;

    public Units() {
    }

    public Units(Integer id) {
        this.id = id;
    }

    public Units(Integer id, String unitName, int carder) {
        this.id = id;
        this.unitName = unitName;
        this.carder = carder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getCarder() {
        return carder;
    }

    public void setCarder(int carder) {
        this.carder = carder;
    }

    @XmlTransient
    public Collection<Transfers> getTransfersCollection() {
        return transfersCollection;
    }

    public void setTransfersCollection(Collection<Transfers> transfersCollection) {
        this.transfersCollection = transfersCollection;
    }

    @XmlTransient
    public Collection<Transfers> getTransfersCollection1() {
        return transfersCollection1;
    }

    public void setTransfersCollection1(Collection<Transfers> transfersCollection1) {
        this.transfersCollection1 = transfersCollection1;
    }

    @XmlTransient
    public Collection<Doctors> getDoctorsCollection() {
        return doctorsCollection;
    }

    public void setDoctorsCollection(Collection<Doctors> doctorsCollection) {
        this.doctorsCollection = doctorsCollection;
    }

    public Institutions getInstitutionID() {
        return institutionID;
    }

    public void setInstitutionID(Institutions institutionID) {
        this.institutionID = institutionID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Units)) {
            return false;
        }
        Units other = (Units) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return unitName;
    }
    
}
