/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.web;

import java.util.List;
import java.util.Optional;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lee
 */
@Component
public class SeleniumBrowser {

    private WebDriver driver;
    private WebDriverWait wait;
    private List<WebPages> pages;

    public SeleniumBrowser() {
    }

    public void initBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    public void setPages(List<WebPages> pages) {
        this.pages = pages;
    }
    
    public void processWebPage(ExpectedCondition condition) {
        pages.forEach(page -> {
            page.getUrls().forEach(url-> {
                String webpage = getWebPage(url, condition).orElse("");
                page.addWebPage(webpage);
            });
        });
    }
    
    public Optional<String> getWebPage(String url, ExpectedCondition condition){
        try {
            driver.get(url);
            wait.until(condition);
        } catch(Exception e){
            System.out.println(e);
        }
        return Optional.of(driver.getPageSource());
    }

    public List<WebPages> getPages() {
        return pages;
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
