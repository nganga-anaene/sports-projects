package sports.football.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Calendar;
import java.util.List;

/**
 * The persistent class for the fixture database table.
 * 
 */
@Entity
@NamedQuery(name = "Fixture.findAll", query = "SELECT f FROM Fixture f")
public class Fixture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "match_day")
	private Calendar matchDay;

	// bi-directional many-to-one association to CompetitionTeam
	@ManyToOne
	@JoinColumn(name = "home_team")
	private CompetitionTeam competitionTeam1;

	// bi-directional many-to-one association to CompetitionTeam
	@ManyToOne
	@JoinColumn(name = "away_team")
	private CompetitionTeam competitionTeam2;

	// bi-directional many-to-one association to Prediction
	@OneToMany(mappedBy = "fixture")
	private List<Prediction> predictions;

	// bi-directional many-to-one association to Result
	@OneToMany(mappedBy = "fixture")
	private List<Result> results;

	public Fixture() {
	}

	public Fixture(CompetitionTeam competitionTeam1, CompetitionTeam competitionTeam2, Calendar matchday) {
		this.competitionTeam1 = competitionTeam1;
		this.competitionTeam2 = competitionTeam2;
		this.matchDay = matchday;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getMatchDay() {
		return this.matchDay;
	}

	public void setMatchDay(Calendar matchDay) {
		this.matchDay = matchDay;
	}

	public CompetitionTeam getCompetitionTeam1() {
		return this.competitionTeam1;
	}

	public void setCompetitionTeam1(CompetitionTeam competitionTeam1) {
		this.competitionTeam1 = competitionTeam1;
	}

	public CompetitionTeam getCompetitionTeam2() {
		return this.competitionTeam2;
	}

	public void setCompetitionTeam2(CompetitionTeam competitionTeam2) {
		this.competitionTeam2 = competitionTeam2;
	}

	public List<Prediction> getPredictions() {
		return this.predictions;
	}

	public void setPredictions(List<Prediction> predictions) {
		this.predictions = predictions;
	}

	public Prediction addPrediction(Prediction prediction) {
		getPredictions().add(prediction);
		prediction.setFixture(this);

		return prediction;
	}

	public Prediction removePrediction(Prediction prediction) {
		getPredictions().remove(prediction);
		prediction.setFixture(null);

		return prediction;
	}

	public List<Result> getResults() {
		return this.results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public Result addResult(Result result) {
		getResults().add(result);
		result.setFixture(this);

		return result;
	}

	public Result removeResult(Result result) {
		getResults().remove(result);
		result.setFixture(null);

		return result;
	}

	@Override
	public String toString() {
		return String.format("Fixture[id=%d, home team=%s, away team=%s, matchday=%s]", id, competitionTeam1,
				competitionTeam2, matchDay);
	}

}