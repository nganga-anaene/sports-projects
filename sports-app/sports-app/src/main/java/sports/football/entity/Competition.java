package sports.football.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the competition database table.
 * 
 */
@Entity
@NamedQuery(name = "Competition.findAll", query = "SELECT c FROM Competition c")
public class Competition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "draw_points")
	private int drawPoints;

	private String name;

	@Column(name = "win_points")
	private int winPoints;

	// bi-directional many-to-one association to Association
	@ManyToOne
	private Association association;

	// bi-directional many-to-one association to CompetitionTeam
	@OneToMany(mappedBy = "competition")
	private List<CompetitionTeam> competitionTeams;

	public Competition() {
	}

	public Competition(String name, Association association) {
		this.name = name;
		this.association = association;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDrawPoints() {
		return this.drawPoints;
	}

	public void setDrawPoints(int drawPoints) {
		this.drawPoints = drawPoints;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWinPoints() {
		return this.winPoints;
	}

	public void setWinPoints(int winPoints) {
		this.winPoints = winPoints;
	}

	public Association getAssociation() {
		return this.association;
	}

	public void setAssociation(Association association) {
		this.association = association;
	}

	public List<CompetitionTeam> getCompetitionTeams() {
		return this.competitionTeams;
	}

	public void setCompetitionTeams(List<CompetitionTeam> competitionTeams) {
		this.competitionTeams = competitionTeams;
	}

	public CompetitionTeam addCompetitionTeam(CompetitionTeam competitionTeam) {
		getCompetitionTeams().add(competitionTeam);
		competitionTeam.setCompetition(this);

		return competitionTeam;
	}

	public CompetitionTeam removeCompetitionTeam(CompetitionTeam competitionTeam) {
		getCompetitionTeams().remove(competitionTeam);
		competitionTeam.setCompetition(null);

		return competitionTeam;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Competition[id=%d, name=%s, association=%s]", id, name, association);
	}
}