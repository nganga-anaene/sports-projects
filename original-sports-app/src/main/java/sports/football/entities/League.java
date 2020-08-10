/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.football.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "league")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "League.findAll", query = "SELECT l FROM League l")
    , @NamedQuery(name = "League.findById", query = "SELECT l FROM League l WHERE l.leaguePK.id = :id")
    , @NamedQuery(name = "League.findByCompetitionId", query = "SELECT l FROM League l WHERE l.leaguePK.competitionId = :competitionId")
    , @NamedQuery(name = "League.findByTeamId", query = "SELECT l FROM League l WHERE l.leaguePK.teamId = :teamId")
    , @NamedQuery(name = "League.findBySeasonId", query = "SELECT l FROM League l WHERE l.seasonId = :seasonId")})
public class League implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LeaguePK leaguePK;
    @Basic(optional = false)
    @Column(name = "season_id")
    private String seasonId;
    @JoinColumn(name = "competition_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Competition competition;
    @JoinColumn(name = "team_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Team team;

    public League() {
    }

    public League(LeaguePK leaguePK) {
        this.leaguePK = leaguePK;
    }

    public League(LeaguePK leaguePK, String seasonId) {
        this.leaguePK = leaguePK;
        this.seasonId = seasonId;
    }

    public League(int id, int competitionId, int teamId) {
        this.leaguePK = new LeaguePK(id, competitionId, teamId);
    }

    public LeaguePK getLeaguePK() {
        return leaguePK;
    }

    public void setLeaguePK(LeaguePK leaguePK) {
        this.leaguePK = leaguePK;
    }

    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
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
        hash += (leaguePK != null ? leaguePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof League)) {
            return false;
        }
        League other = (League) object;
        if ((this.leaguePK == null && other.leaguePK != null) || (this.leaguePK != null && !this.leaguePK.equals(other.leaguePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sports.football.entities.League[ leaguePK=" + leaguePK + " ]";
    }
    
}
