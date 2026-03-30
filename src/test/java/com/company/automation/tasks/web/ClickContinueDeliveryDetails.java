package com.company.automation.tasks.web;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

/**
 * Task: ClickContinueDeliveryDetails
 *
 * Hace clic en el botón "Continue" de la sección Delivery Details (Step 3).
 */
@Subject("click continue button from delivery details")
public class ClickContinueDeliveryDetails implements Task {

    private ClickContinueDeliveryDetails() {}

    public static ClickContinueDeliveryDetails now() {
        return new ClickContinueDeliveryDetails();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = actor.abilityTo(BrowseTheWeb.class).getDriver();
        
        try {
            Thread.sleep(1000);
            
            String clickScript = 
                "var button = document.getElementById('button-guest-shipping');" +
                "if (button) { button.click(); return true; } " +
                "else { return false; }";
            
            Object result = ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(clickScript);
            if (result != null && (boolean) result) {
                System.out.println("✓ Delivery Details continue button clicked");
            } else {
                System.err.println("⚠ Delivery Details continue button not found");
            }
            
            Thread.sleep(2000);
            
        } catch (Exception e) {
            System.err.println("✗ Error clicking continue from delivery details: " + e.getMessage());
            throw new RuntimeException("Failed to click continue from delivery details", e);
        }
    }
}
