/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.tx.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author buddhika
 */
@Entity
@Table(name = "transfers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transfers.findAll", query = "SELECT t FROM Transfers t"),
    @NamedQuery(name = "Transfers.findById", query = "SELECT t FROM Transfers t WHERE t.id = :id"),
    @NamedQuery(name = "Transfers.findByNic", query = "SELECT t FROM Transfers t WHERE t.nic.nic like :nic"),
    @NamedQuery(name = "Transfers.findByTransferOrderName", query = "SELECT t FROM Transfers t WHERE t.transferordernameid = :order"),
    @NamedQuery(name = "Transfers.findByFromUnit", query = "SELECT t FROM Transfers t WHERE t.fromunitid = :unit"),
    @NamedQuery(name = "Transfers.findByToUnit", query = "SELECT t FROM Transfers t WHERE t.tounitid = :unit"),
    @NamedQuery(name = "Transfers.findBySenioritygrade", query = "SELECT t FROM Transfers t WHERE t.senioritygrade = :senioritygrade"),
    @NamedQuery(name = "Transfers.findBySenioritydate", query = "SELECT t FROM Transfers t WHERE t.senioritydate = :senioritydate"),
    @NamedQuery(name = "Transfers.findBySeniorityPF", query = "SELECT t FROM Transfers t WHERE t.seniorityPF = :seniorityPF"),
    @NamedQuery(name = "Transfers.findByRemarks", query = "SELECT t FROM Transfers t WHERE t.remarks = :remarks")})
public class Transfers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Seniority_grade")
    private int senioritygrade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Seniority_date")
    @Temporal(TemporalType.DATE)
    private Date senioritydate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Seniority_PF")
    private int seniorityPF;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "Remarks")
    private String remarks;
    @JoinColumn(name = "NIC", referencedColumnName = "NIC")
    @ManyToOne(optional = false)
    private Doctors nic;
    @JoinColumn(name = "To_unit_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Units tounitid;
    @JoinColumn(name = "From_unit_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Units fromunitid;
    @JoinColumn(name = "Transfer_order_name_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private OrderNames transferordernameid;

    public Transfers() {
    }

    public Transfers(Integer id) {
        this.id = id;
    }

    public Transfers(Integer id, int senioritygrade, Date senioritydate, int seniorityPF, String remarks) {
        this.id = id;
        this.senioritygrade = senioritygrade;
        this.senioritydate = senioritydate;
        this.seniorityPF = seniorityPF;
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSenioritygrade() {
        return senioritygrade;
    }

    public void setSenioritygrade(int senioritygrade) {
        this.senioritygrade = senioritygrade;
    }

    public Date getSenioritydate() {
        return senioritydate;
    }

    public void setSenioritydate(Date senioritydate) {
        this.senioritydate = senioritydate;
    }

    public int getSeniorityPF() {
        return seniorityPF;
    }

    public void setSeniorityPF(int seniorityPF) {
        this.seniorityPF = seniorityPF;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Doctors getNic() {
        return nic;
    }

    public void setNic(Doctors nic) {
        this.nic = nic;
    }

    public Units getTounitid() {
        return tounitid;
    }

    public void setTounitid(Units tounitid) {
        this.tounitid = tounitid;
    }

    public Units getFromunitid() {
        return fromunitid;
    }

    public void setFromunitid(Units fromunitid) {
        this.fromunitid = fromunitid;
    }

    public OrderNames getTransferordernameid() {
        return transferordernameid;
    }

    public void setTransferordernameid(OrderNames transferordernameid) {
        this.transferordernameid = transferordernameid;
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
        if (!(object instanceof Transfers)) {
            return false;
        }
        Transfers other = (Transfers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + "";
    }

}
