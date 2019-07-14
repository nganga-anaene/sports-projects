package sports.football.entity.repository;

import org.springframework.data.repository.CrudRepository;

import sports.football.entity.Association;
import sports.football.entity.Confederation;

public interface AssociationRepository extends CrudRepository<Association, Integer> {
	
	public Association findAssociationByNameAndAssociationAbbAndConfederation(String name, String associationAbb, Confederation confederation);

	public Association findAssociationByName(String name);
}
