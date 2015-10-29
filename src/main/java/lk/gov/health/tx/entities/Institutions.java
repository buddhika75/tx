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
@Table(name = "institutions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Institutions.findAll", query = "SELECT i FROM Institutions i"),
    @NamedQuery(name = "Institutions.findById", query = "SELECT i FROM Institutions i WHERE i.id = :id"),
    @NamedQuery(name = "Institutions.findByInstname", query = "SELECT i FROM Institutions i WHERE i.instname = :instname"),
    @NamedQuery(name = "Institutions.findByCarder", query = "SELECT i FROM Institutions i WHERE i.carder = :carder")})
public class Institutions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Inst_name")
    private String instname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Carder")
    private int carder;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institutionID")
    private Collection<Units> unitsCollection;

    public Institutions() {
    }

    public Institutions(Integer id) {
        this.id = id;
    }

    public Institutions(Integer id, String instname, int carder) {
        this.id = id;
        this.instname = instname;
        this.carder = carder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstname() {
        return instname;
    }

    public void setInstname(String instname) {
        this.instname = instname;
    }

    public int getCarder() {
        return carder;
    }

    public void setCarder(int carder) {
        this.carder = carder;
    }

    @XmlTransient
    public Collection<Units> getUnitsCollection() {
        return unitsCollection;
    }

    public void setUnitsCollection(Collection<Units> unitsCollection) {
        this.unitsCollection = unitsCollection;
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
        if (!(object instanceof Institutions)) {
            return false;
        }
        Institutions other = (Institutions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return instname;
    }
    
}
