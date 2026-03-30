package com.company.automation.tasks.web;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

/**
 * Task: ClickContinuePaymentMethod
 *
 * Hace clic en el botón "Continue" de la sección Payment Method (Step 5).
 */
@Subject("click continue button from payment method")
public class ClickContinuePaymentMethod implements Task {

    private ClickContinuePaymentMethod() {}

    public static ClickContinuePaymentMethod now() {
        return new ClickContinuePaymentMethod();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = actor.abilityTo(BrowseTheWeb.class).getDriver();
        
        try {
            Thread.sleep(1000);
            
            String clickScript = 
                "var button = document.getElementById('button-payment-method');" +
                "if (button) { button.click(); return true; } " +
                "else { return false; }";
            
            Object result = ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(clickScript);
            if (result != null && (boolean) result) {
                System.out.println("✓ Payment Method continue button clicked");
            } else {
                System.err.println("⚠ Payment Method continue button not found");
            }
            
            Thread.sleep(2000);
            
        } catch (Exception e) {
            System.err.println("✗ Error clicking continue from payment method: " + e.getMessage());
            throw new RuntimeException("Failed to click continue from payment method", e);
        }
    }
}
