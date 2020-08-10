/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lee
 */
@Component
public class Conditions {

    
    public Conditions() {
    }
    
    public ExpectedCondition pageHasTitle(){
        return (ExpectedCondition<Boolean>) (WebDriver driver) -> !driver.getTitle().isEmpty();
    }
    
    public ExpectedCondition pageHasId(String id) {
        return ExpectedConditions.presenceOfElementLocated(By.id(id));
    }
}
