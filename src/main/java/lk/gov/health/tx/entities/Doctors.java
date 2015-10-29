/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.tx.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author buddhika
 */
@Entity
@Table(name = "doctors")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Doctors.findAll", query = "SELECT d FROM Doctors d"),
    @NamedQuery(name = "Doctors.findByNic", query = "SELECT d FROM Doctors d WHERE d.nic = :nic"),
    @NamedQuery(name = "Doctors.findByName", query = "SELECT d FROM Doctors d WHERE d.name = :name"),
    @NamedQuery(name = "Doctors.findByDatereported", query = "SELECT d FROM Doctors d WHERE d.datereported = :datereported"),
    @NamedQuery(name = "Doctors.findByReportedordername", query = "SELECT d FROM Doctors d WHERE d.reportedordername = :reportedordername"),
    @NamedQuery(name = "Doctors.findByUnit", query = "SELECT d FROM Doctors d WHERE d.unitid = :unit"),
    @NamedQuery(name = "Doctors.findByRemarks", query = "SELECT d FROM Doctors d WHERE d.remarks = :remarks")})
public class Doctors implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "NIC")
    private String nic;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_reported")
    @Temporal(TemporalType.DATE)
    private Date datereported;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Reported_order_name")
    private int reportedordername;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "Remarks")
    private String remarks;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nic")
    private Collection<Transfers> transfersCollection;
    @JoinColumn(name = "Unit_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Units unitid;

    public Doctors() {
    }

    public Doctors(String nic) {
        this.nic = nic;
    }

    public Doctors(String nic, String name, Date datereported, int reportedordername, String remarks) {
        this.nic = nic;
        this.name = name;
        this.datereported = datereported;
        this.reportedordername = reportedordername;
        this.remarks = remarks;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDatereported() {
        return datereported;
    }

    public void setDatereported(Date datereported) {
        this.datereported = datereported;
    }

    public int getReportedordername() {
        return reportedordername;
    }

    public void setReportedordername(int reportedordername) {
        this.reportedordername = reportedordername;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @XmlTransient
    public Collection<Transfers> getTransfersCollection() {
        return transfersCollection;
    }

    public void setTransfersCollection(Collection<Transfers> transfersCollection) {
        this.transfersCollection = transfersCollection;
    }

    public Units getUnitid() {
        return unitid;
    }

    public void setUnitid(Units unitid) {
        this.unitid = unitid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nic != null ? nic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctors)) {
            return false;
        }
        Doctors other = (Doctors) object;
        if ((this.nic == null && other.nic != null) || (this.nic != null && !this.nic.equals(other.nic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
