/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.football.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Lee
 */
@Embeddable
public class LeaguePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "competition_id")
    private int competitionId;
    @Basic(optional = false)
    @Column(name = "team_id")
    private int teamId;

    public LeaguePK() {
    }

    public LeaguePK(int id, int competitionId, int teamId) {
        this.id = id;
        this.competitionId = competitionId;
        this.teamId = teamId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) competitionId;
        hash += (int) teamId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeaguePK)) {
            return false;
        }
        LeaguePK other = (LeaguePK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.competitionId != other.competitionId) {
            return false;
        }
        if (this.teamId != other.teamId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sports.football.entities.LeaguePK[ id=" + id + ", competitionId=" + competitionId + ", teamId=" + teamId + " ]";
    }

}
