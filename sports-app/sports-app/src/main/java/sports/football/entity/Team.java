package sports.football.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the team database table.
 * 
 */
@Entity
@NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t")
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private int points;

	// bi-directional many-to-one association to CompetitionTeam
	@OneToMany(mappedBy = "team")
	private List<CompetitionTeam> competitionTeams;

	// bi-directional many-to-one association to Association
	@ManyToOne
	private Association association;

	public Team() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return this.points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public List<CompetitionTeam> getCompetitionTeams() {
		return this.competitionTeams;
	}

	public void setCompetitionTeams(List<CompetitionTeam> competitionTeams) {
		this.competitionTeams = competitionTeams;
	}

	public CompetitionTeam addCompetitionTeam(CompetitionTeam competitionTeam) {
		getCompetitionTeams().add(competitionTeam);
		competitionTeam.setTeam(this);

		return competitionTeam;
	}

	public CompetitionTeam removeCompetitionTeam(CompetitionTeam competitionTeam) {
		getCompetitionTeams().remove(competitionTeam);
		competitionTeam.setTeam(null);

		return competitionTeam;
	}

	public Association getAssociation() {
		return this.association;
	}

	public void setAssociation(Association association) {
		this.association = association;
	}

	public Team(String name, Association association) {
		this.association = association;
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Team[id=%d,name=%s,association=%s]", id, name, association);
	}
}