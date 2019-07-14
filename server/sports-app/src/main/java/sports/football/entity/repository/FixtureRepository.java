package sports.football.entity.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sports.football.entity.CompetitionTeam;
import sports.football.entity.Fixture;

public interface FixtureRepository extends CrudRepository<Fixture, Integer> {

	public Fixture findByCompetitionTeam1AndCompetitionTeam2AndMatchDay(CompetitionTeam competitionTeam1,
			CompetitionTeam competitionTeam2, Calendar matchDay);
	
	public List<Fixture> findByMatchDay(Calendar matchDay);
}
