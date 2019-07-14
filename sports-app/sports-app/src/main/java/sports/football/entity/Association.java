package sports.football.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the association database table.
 * 
 */
@Entity
@NamedQuery(name = "Association.findAll", query = "SELECT a FROM Association a")
public class Association implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "association_abb")
	private String associationAbb;

	private String name;

	// bi-directional many-to-one association to Confederation
	@ManyToOne
	private Confederation confederation;

	// bi-directional many-to-one association to Competition
	@OneToMany(mappedBy = "association")
	private List<Competition> competitions;

	// bi-directional many-to-one association to Team
	@OneToMany(mappedBy = "association")
	private List<Team> teams;

	public Association() {
	}

	public Association(String associationAbb, String name, Confederation confederation) {
		this.name = name;
		this.associationAbb = associationAbb;
		this.confederation = confederation;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAssociationAbb() {
		return this.associationAbb;
	}

	public void setAssociationAbb(String associationAbb) {
		this.associationAbb = associationAbb;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Confederation getConfederation() {
		return this.confederation;
	}

	public void setConfederation(Confederation confederation) {
		this.confederation = confederation;
	}

	public List<Competition> getCompetitions() {
		return this.competitions;
	}

	public void setCompetitions(List<Competition> competitions) {
		this.competitions = competitions;
	}

	public Competition addCompetition(Competition competition) {
		getCompetitions().add(competition);
		competition.setAssociation(this);

		return competition;
	}

	public Competition removeCompetition(Competition competition) {
		getCompetitions().remove(competition);
		competition.setAssociation(null);

		return competition;
	}

	public List<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public Team addTeam(Team team) {
		getTeams().add(team);
		team.setAssociation(this);

		return team;
	}

	public Team removeTeam(Team team) {
		getTeams().remove(team);
		team.setAssociation(null);

		return team;
	}

	@Override
	public String toString() {
		return String.format("Association[id=%d, name=%s, association abb=%s, confederation=%s]", id, name,
				associationAbb, confederation);
	}
}