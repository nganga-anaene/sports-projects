/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.football.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lee
 */
@Entity
@Table(name = "fixture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fixture.findAll", query = "SELECT f FROM Fixture f")
    , @NamedQuery(name = "Fixture.findById", query = "SELECT f FROM Fixture f WHERE f.id = :id")
    , @NamedQuery(name = "Fixture.findByMatchDay", query = "SELECT f FROM Fixture f WHERE f.matchDay = :matchDay")})
public class Fixture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "match_day")
    @Temporal(TemporalType.DATE)
    private Date matchDay;
    @JoinColumn(name = "league_id", referencedColumnName = "id", unique = true)
    @OneToOne(optional = false)
    private League leagueId;
    @JoinColumn(name = "home_team_id", referencedColumnName = "team_id", unique = true)
    @OneToOne(optional = false)
    private League homeTeamId;
    @JoinColumn(name = "away_team_id", referencedColumnName = "team_id", unique = true)
    @OneToOne(optional = false)
    private League awayTeamId;

    public Fixture() {
    }

    public Fixture(Integer id) {
        this.id = id;
    }

    public Fixture(Integer id, Date matchDay) {
        this.id = id;
        this.matchDay = matchDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(Date matchDay) {
        this.matchDay = matchDay;
    }

    public League getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(League leagueId) {
        this.leagueId = leagueId;
    }

    public League getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(League homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public League getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(League awayTeamId) {
        this.awayTeamId = awayTeamId;
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
        if (!(object instanceof Fixture)) {
            return false;
        }
        Fixture other = (Fixture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sports.football.entities.Fixture[ id=" + id + " ]";
    }
    
}
