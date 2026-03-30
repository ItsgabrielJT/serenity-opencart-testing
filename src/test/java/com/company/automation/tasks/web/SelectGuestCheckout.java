package com.company.automation.tasks.web;

import com.company.automation.ui.pages.CheckoutPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.Subject;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

@Subject("select guest checkout option")
public class SelectGuestCheckout implements Task {

    private SelectGuestCheckout() {}

    public static SelectGuestCheckout now() {
        return new SelectGuestCheckout();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = actor.abilityTo(BrowseTheWeb.class).getDriver();
        
        try {
            Thread.sleep(1000);
            
            String scriptClickRadio = 
                "var guestRadio = document.querySelector(\"input[name='account'][value='guest']\");" +
                "if (guestRadio) { guestRadio.click(); } " +
                "else { alert('Guest radio not found!'); }";
            
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(scriptClickRadio);
            
            System.out.println("✓ Guest checkout radio clicked with JavaScript");
            Thread.sleep(500);
            
            String scriptClickButton = 
                "var continueBtn = document.getElementById('button-account');" +
                "if (continueBtn) { continueBtn.click(); } " +
                "else { alert('Continue button not found!'); }";
            
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(scriptClickButton);
            
            System.out.println("✓ Continue button clicked with JavaScript");
            
            Thread.sleep(3000);
            
        } catch (Exception e) {
            System.err.println("✗ Error selecting guest checkout: " + e.getMessage());
            throw new RuntimeException("Failed to select guest checkout", e);
        }
    }
}

