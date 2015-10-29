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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "order_names")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderNames.findAll", query = "SELECT o FROM OrderNames o"),
    @NamedQuery(name = "OrderNames.findById", query = "SELECT o FROM OrderNames o WHERE o.id = :id"),
    @NamedQuery(name = "OrderNames.findByOrdername", query = "SELECT o FROM OrderNames o WHERE lower(o.ordername) like :ordername"),
    @NamedQuery(name = "OrderNames.findByOrdereffectivedate", query = "SELECT o FROM OrderNames o WHERE o.ordereffectivedate = :ordereffectivedate")})
public class OrderNames implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Order_name")
    private String ordername;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Order_effective_date")
    @Temporal(TemporalType.DATE)
    private Date ordereffectivedate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transferordernameid")
    private Collection<Transfers> transfersCollection;
    @JoinColumn(name = "Order_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private OrderTypes ordertypeid;

    public OrderNames() {
    }

    public OrderNames(Integer id) {
        this.id = id;
    }

    public OrderNames(Integer id, String ordername, Date ordereffectivedate) {
        this.id = id;
        this.ordername = ordername;
        this.ordereffectivedate = ordereffectivedate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public Date getOrdereffectivedate() {
        return ordereffectivedate;
    }

    public void setOrdereffectivedate(Date ordereffectivedate) {
        this.ordereffectivedate = ordereffectivedate;
    }

    @XmlTransient
    public Collection<Transfers> getTransfersCollection() {
        return transfersCollection;
    }

    public void setTransfersCollection(Collection<Transfers> transfersCollection) {
        this.transfersCollection = transfersCollection;
    }

    public OrderTypes getOrdertypeid() {
        return ordertypeid;
    }

    public void setOrdertypeid(OrderTypes ordertypeid) {
        this.ordertypeid = ordertypeid;
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
        if (!(object instanceof OrderNames)) {
            return false;
        }
        OrderNames other = (OrderNames) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ordername;
    }
    
}
