/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.football.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import sports.football.entities.Associations;
import sports.football.entities.Team;

/**
 *
 * @author Lee
 */
public interface TeamRepository extends CrudRepository<Team, Integer> {

    public List<Team> findTeamByAssociationId(Associations associationId);
    
    public Team findTeamByAssociationIdAndName(Associations associationId, String name);
}
