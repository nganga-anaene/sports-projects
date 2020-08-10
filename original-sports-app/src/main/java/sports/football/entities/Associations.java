/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.football.entities;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lee
 */
@Entity
@Table(name = "associations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Associations.findAll", query = "SELECT a FROM Associations a")
    , @NamedQuery(name = "Associations.findById", query = "SELECT a FROM Associations a WHERE a.id = :id")
    , @NamedQuery(name = "Associations.findByName", query = "SELECT a FROM Associations a WHERE a.name = :name")
    , @NamedQuery(name = "Associations.findByAssociationAbb", query = "SELECT a FROM Associations a WHERE a.associationAbb = :associationAbb")})
public class Associations implements Serializable {

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
    @Column(name = "association_abb")
    private String associationAbb;
    @JoinColumn(name = "confederation_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Confederations confederationId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "associationId")
    private Competition competition;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "associationId")
    private Team team;

    public Associations() {
    }

    public Associations(Integer id) {
        this.id = id;
    }

    public Associations(Integer id, String name, String associationAbb) {
        this.id = id;
        this.name = name;
        this.associationAbb = associationAbb;
    }
    
    public Associations(String name, String associationAbb) {
        this.name = name;
        this.associationAbb = associationAbb;
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

    public String getAssociationAbb() {
        return associationAbb;
    }

    public void setAssociationAbb(String associationAbb) {
        this.associationAbb = associationAbb;
    }

    public Confederations getConfederationId() {
        return confederationId;
    }

    public void setConfederationId(Confederations confederationId) {
        this.confederationId = confederationId;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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
        if (!(object instanceof Associations)) {
            return false;
        }
        Associations other = (Associations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sports.football.entities.Associations[ id=" + id + " ]";
    }
    
}
