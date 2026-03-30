package com.company.automation.tasks.web;

import com.company.automation.ui.pages.CheckoutPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.Subject;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

@Subject("select guest checkout option")
public class SelectGuestCheckoutOption implements Task {

    private SelectGuestCheckoutOption() {}

    public static SelectGuestCheckoutOption now() {
        return new SelectGuestCheckoutOption();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = actor.abilityTo(BrowseTheWeb.class).getDriver();
        
        try {
            Thread.sleep(1000);
            
            String scriptClickRadio = 
                "var guestRadio = document.querySelector(\"input[name='account'][value='guest']\");" +
                "if (guestRadio) { guestRadio.click(); return true; } " +
                "else { return false; }";
            
            Object result = ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(scriptClickRadio);
            if (result != null && (boolean) result) {
                System.out.println("✓ Guest checkout radio button selected");
            } else {
                System.err.println("⚠ Guest checkout radio button not found");
            }
            
            Thread.sleep(500);
            
        } catch (Exception e) {
            System.err.println("✗ Error selecting guest checkout option: " + e.getMessage());
            throw new RuntimeException("Failed to select guest checkout option", e);
        }
    }
}
