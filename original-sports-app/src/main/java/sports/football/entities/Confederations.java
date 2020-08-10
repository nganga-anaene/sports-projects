/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.football.entities;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lee
 */
@Entity
@Table(name = "confederations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Confederations.findAll", query = "SELECT c FROM Confederations c")
    , @NamedQuery(name = "Confederations.findById", query = "SELECT c FROM Confederations c WHERE c.id = :id")
    , @NamedQuery(name = "Confederations.findByName", query = "SELECT c FROM Confederations c WHERE c.name = :name")
    , @NamedQuery(name = "Confederations.findByRegionName", query = "SELECT c FROM Confederations c WHERE c.regionName = :regionName")
    , @NamedQuery(name = "Confederations.findByAbb", query = "SELECT c FROM Confederations c WHERE c.abb = :abb")})
public class Confederations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "region_name")
    private String regionName;
    @Basic(optional = false)
    @Column(name = "abb")
    private String abb;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "confederationId")
    private Collection<Associations> associationsCollection;

    public Confederations() {
    }

    public Confederations(Integer id) {
        this.id = id;
    }

    public Confederations(Integer id, String name, String regionName, String abb) {
        this.id = id;
        this.name = name;
        this.regionName = regionName;
        this.abb = abb;
    }
    
    public Confederations(String name, String regionName, String abb) {
        this.name = name;
        this.regionName = regionName;
        this.abb = abb;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getAbb() {
        return abb;
    }

    public void setAbb(String abb) {
        this.abb = abb;
    }

    @XmlTransient
    public Collection<Associations> getAssociationsCollection() {
        return associationsCollection;
    }

    public void setAssociationsCollection(Collection<Associations> associationsCollection) {
        this.associationsCollection = associationsCollection;
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
        if (!(object instanceof Confederations)) {
            return false;
        }
        Confederations other = (Confederations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sports.football.entities.Confederations[ id=" + id + " ]";
    }
    
}
