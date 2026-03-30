package com.company.automation.tasks.web;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

@Subject("click continue button from guest checkout options")
public class ClickContinueGuestCheckout implements Task {

    private ClickContinueGuestCheckout() {}

    public static ClickContinueGuestCheckout now() {
        return new ClickContinueGuestCheckout();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = actor.abilityTo(BrowseTheWeb.class).getDriver();
        
        try {
            Thread.sleep(1000);
            
            String clickScript = 
                "var button = document.getElementById('button-account');" +
                "if (button) { button.click(); return true; } " +
                "else { return false; }";
            
            Object result = ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(clickScript);
            if (result != null && (boolean) result) {
                System.out.println("✓ Guest Checkout continue button clicked");
            } else {
                System.err.println("⚠ Guest Checkout continue button not found");
            }
            
            Thread.sleep(3000);
            
        } catch (Exception e) {
            System.err.println("✗ Error clicking continue from guest checkout: " + e.getMessage());
            throw new RuntimeException("Failed to click continue from guest checkout", e);
        }
    }
}
