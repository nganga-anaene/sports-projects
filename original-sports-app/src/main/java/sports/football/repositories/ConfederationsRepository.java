/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.football.repositories;

import java.util.List;
import sports.football.entities.Confederations;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Lee
 */
public interface ConfederationsRepository extends JpaRepository<Confederations, Integer> {
    
    public Confederations findConfederationsByName(String name);
    public Confederations findConfederationsByAbb(String abb);
}
