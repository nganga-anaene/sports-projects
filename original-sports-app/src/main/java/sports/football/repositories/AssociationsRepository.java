/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.football.repositories;

import org.springframework.data.repository.CrudRepository;
import sports.football.entities.Associations;

/**
 *
 * @author Lee
 */
public interface AssociationsRepository extends CrudRepository<Associations, Integer> {
    
    public Associations findAssociationsByName(String name);
}
