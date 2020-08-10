/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.utilities;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sports.football.entities.Associations;

/**
 *
 * @author Lee
 */
@Component
public class UniqueAssociations {
    
    @Autowired
    private SportsUtilities sportsUtilities;
    private final String path = "unique-associations";

    public UniqueAssociations() {
    }
    
    public List<Associations> getAssociations() {
        sportsUtilities.readJsonFile(path);
        return sportsUtilities.getJsonObjectMap()
                .entrySet()
                .stream().map(key -> {
                    Associations associations = new Associations(key.getKey(), key.getValue().toString());
                    return associations;
                }).collect(Collectors.toList());
    }
}
