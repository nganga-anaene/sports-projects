package sports.football.entity.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sports.football.entity.Competition;
import sports.football.entity.CompetitionTeam;
import sports.football.entity.Team;

public interface CompetitionTeamRepository extends CrudRepository<CompetitionTeam, Integer> {

	public CompetitionTeam findCompetitionTeamByCompetitionAndTeamAndSeasonId(Competition competition, Team team, String seasonId);

	public List<CompetitionTeam> findCompetitionTeamsByCompetition(Competition competition);

	public List<CompetitionTeam> findCompetitionTeamsByTeam(Team team);

	public List<CompetitionTeam> findCompetitionTeamsByCompetitionAndSeasonId(Competition competition, String seasonId);

}
