package sports.football.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the result database table.
 * 
 */
@Entity
@NamedQuery(name = "Result.findAll", query = "SELECT r FROM Result r")
public class Result implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "away_goals")
	private int awayGoals;

	@Column(name = "home_goals")
	private int homeGoals;

	// bi-directional many-to-one association to Fixture
	@ManyToOne
	private Fixture fixture;

	public Result() {
	}

	public Result(Fixture fixture, int homeGoals, int awayGoals) {
		this.fixture = fixture;
		this.homeGoals = homeGoals;
		this.awayGoals = awayGoals;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAwayGoals() {
		return this.awayGoals;
	}

	public void setAwayGoals(int awayGoals) {
		this.awayGoals = awayGoals;
	}

	public int getHomeGoals() {
		return this.homeGoals;
	}

	public void setHomeGoals(int homeGoals) {
		this.homeGoals = homeGoals;
	}

	public Fixture getFixture() {
		return this.fixture;
	}

	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}

	@Override
	public String toString() {
		return String.format("Result[id=%d, fixture=%s, home goals=%d, away goals=%d]", id, fixture, homeGoals,
				awayGoals);
	}
}