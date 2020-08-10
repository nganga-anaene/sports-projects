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
@Table(name = "competition")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competition.findAll", query = "SELECT c FROM Competition c")
    , @NamedQuery(name = "Competition.findById", query = "SELECT c FROM Competition c WHERE c.id = :id")
    , @NamedQuery(name = "Competition.findByName", query = "SELECT c FROM Competition c WHERE c.name = :name")
    , @NamedQuery(name = "Competition.findByWinPoints", query = "SELECT c FROM Competition c WHERE c.winPoints = :winPoints")
    , @NamedQuery(name = "Competition.findByDrawPoints", query = "SELECT c FROM Competition c WHERE c.drawPoints = :drawPoints")})
public class Competition implements Serializable {

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
    @Column(name = "win_points")
    private int winPoints;
    @Basic(optional = false)
    @Column(name = "draw_points")
    private int drawPoints;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "competition")
    private League league;
    @JoinColumn(name = "association_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Associations associationId;

    public Competition() {
    }

    public Competition(Integer id) {
        this.id = id;
    }

    public Competition(Integer id, String name, int winPoints, int drawPoints) {
        this.id = id;
        this.name = name;
        this.winPoints = winPoints;
        this.drawPoints = drawPoints;
    }
    
    public Competition(String name, Associations associationId) {
        this.name = name;
        this.associationId = associationId;
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

    public int getWinPoints() {
        return winPoints;
    }

    public void setWinPoints(int winPoints) {
        this.winPoints = winPoints;
    }

    public int getDrawPoints() {
        return drawPoints;
    }

    public void setDrawPoints(int drawPoints) {
        this.drawPoints = drawPoints;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Associations getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Associations associationId) {
        this.associationId = associationId;
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
        if (!(object instanceof Competition)) {
            return false;
        }
        Competition other = (Competition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sports.football.entities.Competition[ id=" + id + " ]";
    }
    
}
