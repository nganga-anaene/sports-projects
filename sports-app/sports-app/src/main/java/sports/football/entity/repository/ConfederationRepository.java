package sports.football.entity.repository;

import org.springframework.data.repository.CrudRepository;

import sports.football.entity.Confederation;

public interface ConfederationRepository extends CrudRepository<Confederation, Integer> {
	public Confederation findConfederationByNameAndRegionNameAndAbb(String name, String regionName, String abb);
	public Confederation findConfederationByAbb(String abb);
}
