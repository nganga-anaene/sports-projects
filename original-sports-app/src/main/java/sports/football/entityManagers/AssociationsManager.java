/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.football.entityManagers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sports.football.web.AssociationCollector;

/**
 *
 * @author Lee
 */
@Component
public class AssociationsManager {

    @Autowired
    private AssociationCollector associationCollector;

    public AssociationsManager() {
    }

    public void saveAssociations() {
        //using selenium
        //associationCollector.setSeleniumAssociationWebPages();
        //using jsoup
        associationCollector.setSeleniumAssociationWebPages();
        associationCollector.saveUniqueAssociations();
    }

}
