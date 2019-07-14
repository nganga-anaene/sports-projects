package sports.football.entity.repository;

import org.springframework.data.repository.CrudRepository;

import sports.football.entity.Fixture;
import sports.football.entity.Result;

public interface ResultRepository extends CrudRepository<Result, Integer> {

	public Result findResultByFixture(Fixture fixture);
}
