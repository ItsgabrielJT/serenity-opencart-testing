package com.company.automation.abilities;

import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Actor;
import org.openqa.selenium.WebDriver;

public class BrowseTheWebWithSerenity {

    private BrowseTheWebWithSerenity() {
        // utility wrapper – no instanciar directamente
    }

    
    public static BrowseTheWeb asConfiguredIn(WebDriver driver) {
        return BrowseTheWeb.with(driver);
    }
}
