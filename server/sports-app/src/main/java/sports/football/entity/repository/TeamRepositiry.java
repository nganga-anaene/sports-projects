package sports.football.entity.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sports.football.entity.Association;
import sports.football.entity.Team;

public interface TeamRepositiry extends CrudRepository<Team, Integer> {

	public List<Team> findTeamByName(String name);

	public List<Team> findTeamsByAssociation(Association association);

	public Team findTeamByNameAndAssociation(String name, Association association);
}
