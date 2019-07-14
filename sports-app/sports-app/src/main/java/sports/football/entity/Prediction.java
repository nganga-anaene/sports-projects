package sports.football.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the prediction database table.
 * 
 */
@Entity
@NamedQuery(name="Prediction.findAll", query="SELECT p FROM Prediction p")
public class Prediction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="away_win")
	private byte awayWin;

	private byte draw;

	@Column(name="home_win")
	private byte homeWin;

	@Column(name="over_one_goal")
	private byte overOneGoal;

	@Column(name="over_two_goals")
	private byte overTwoGoals;

	//bi-directional many-to-one association to Fixture
	@ManyToOne
	private Fixture fixture;

	public Prediction() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getAwayWin() {
		return this.awayWin;
	}

	public void setAwayWin(byte awayWin) {
		this.awayWin = awayWin;
	}

	public byte getDraw() {
		return this.draw;
	}

	public void setDraw(byte draw) {
		this.draw = draw;
	}

	public byte getHomeWin() {
		return this.homeWin;
	}

	public void setHomeWin(byte homeWin) {
		this.homeWin = homeWin;
	}

	public byte getOverOneGoal() {
		return this.overOneGoal;
	}

	public void setOverOneGoal(byte overOneGoal) {
		this.overOneGoal = overOneGoal;
	}

	public byte getOverTwoGoals() {
		return this.overTwoGoals;
	}

	public void setOverTwoGoals(byte overTwoGoals) {
		this.overTwoGoals = overTwoGoals;
	}

	public Fixture getFixture() {
		return this.fixture;
	}

	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}

}