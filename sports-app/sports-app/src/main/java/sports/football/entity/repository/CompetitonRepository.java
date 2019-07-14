package sports.football.entity.repository;

import org.springframework.data.repository.CrudRepository;

import sports.football.entity.Association;
import sports.football.entity.Competition;

public interface CompetitonRepository extends CrudRepository<Competition, Integer> {

	public Competition findCompetitionByNameAndAssociation(String name, Association association);

	public Competition findCompetitionByName(String name);
}
