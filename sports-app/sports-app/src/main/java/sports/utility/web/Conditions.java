package sports.utility.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

@Component
public class Conditions {

	public Conditions() {
	}

	public ExpectedCondition<Boolean> pageHasTitle() {
		return (ExpectedCondition<Boolean>) (WebDriver driver) -> !driver.getTitle().isEmpty();
	}

	public ExpectedCondition<?> pageHasId(String id) {
		return ExpectedConditions.presenceOfElementLocated(By.id(id));
	}
}
