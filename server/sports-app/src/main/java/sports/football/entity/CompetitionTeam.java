package sports.football.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the competition_team database table.
 * 
 */
@Entity
@Table(name="competition_team")
@NamedQuery(name="CompetitionTeam.findAll", query="SELECT c FROM CompetitionTeam c")
public class CompetitionTeam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="season_id")
	private String seasonId;

	//bi-directional many-to-one association to Competition
	@ManyToOne
	private Competition competition;

	//bi-directional many-to-one association to Team
	@ManyToOne
	private Team team;

	//bi-directional many-to-one association to Fixture
	@OneToMany(mappedBy="competitionTeam1")
	private List<Fixture> fixtures1;

	//bi-directional many-to-one association to Fixture
	@OneToMany(mappedBy="competitionTeam2")
	private List<Fixture> fixtures2;

	public CompetitionTeam() {
	}

	public CompetitionTeam(Team team, Competition competition, String seasonId) {
		this.competition = competition;
		this.team = team;
		this.seasonId = seasonId;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSeasonId() {
		return this.seasonId;
	}

	public void setSeasonId(String seasonId) {
		this.seasonId = seasonId;
	}

	public Competition getCompetition() {
		return this.competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<Fixture> getFixtures1() {
		return this.fixtures1;
	}

	public void setFixtures1(List<Fixture> fixtures1) {
		this.fixtures1 = fixtures1;
	}

	public Fixture addFixtures1(Fixture fixtures1) {
		getFixtures1().add(fixtures1);
		fixtures1.setCompetitionTeam1(this);

		return fixtures1;
	}

	public Fixture removeFixtures1(Fixture fixtures1) {
		getFixtures1().remove(fixtures1);
		fixtures1.setCompetitionTeam1(null);

		return fixtures1;
	}

	public List<Fixture> getFixtures2() {
		return this.fixtures2;
	}

	public void setFixtures2(List<Fixture> fixtures2) {
		this.fixtures2 = fixtures2;
	}

	public Fixture addFixtures2(Fixture fixtures2) {
		getFixtures2().add(fixtures2);
		fixtures2.setCompetitionTeam2(this);

		return fixtures2;
	}

	public Fixture removeFixtures2(Fixture fixtures2) {
		getFixtures2().remove(fixtures2);
		fixtures2.setCompetitionTeam2(null);

		return fixtures2;
	}

}