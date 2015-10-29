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
@Table(name = "order_types")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderTypes.findAll", query = "SELECT o FROM OrderTypes o"),
    @NamedQuery(name = "OrderTypes.findById", query = "SELECT o FROM OrderTypes o WHERE o.id = :id"),
    @NamedQuery(name = "OrderTypes.findByOrdertype", query = "SELECT o FROM OrderTypes o WHERE o.ordertype = :ordertype")})
public class OrderTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Order_type")
    private String ordertype;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordertypeid")
    private Collection<OrderNames> orderNamesCollection;

    public OrderTypes() {
    }

    public OrderTypes(Integer id) {
        this.id = id;
    }

    public OrderTypes(Integer id, String ordertype) {
        this.id = id;
        this.ordertype = ordertype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    @XmlTransient
    public Collection<OrderNames> getOrderNamesCollection() {
        return orderNamesCollection;
    }

    public void setOrderNamesCollection(Collection<OrderNames> orderNamesCollection) {
        this.orderNamesCollection = orderNamesCollection;
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
        if (!(object instanceof OrderTypes)) {
            return false;
        }
        OrderTypes other = (OrderTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ordertype;
    }
    
}
