package com.company.automation.tasks.web;

import com.company.automation.ui.pages.CheckoutPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.Subject;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

@Subject("complete the remaining checkout steps")
public class CompleteCheckoutSteps implements Task {

    private CompleteCheckoutSteps() {}

    public static CompleteCheckoutSteps now() {
        return new CompleteCheckoutSteps();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = actor.abilityTo(BrowseTheWeb.class).getDriver();
        
        try {
            Thread.sleep(1000);
            clickButtonWithId(driver, "button-guest-shipping", "Delivery Details continue");
            
            Thread.sleep(1500);
            clickButtonWithId(driver, "button-shipping-method", "Delivery Method continue");
            
            Thread.sleep(1500);
            
            String agreeScript = 
                "var agreeCheckbox = document.querySelector(\"input[name='agree']\");" +
                "if (agreeCheckbox && !agreeCheckbox.checked) { agreeCheckbox.click(); }";
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(agreeScript);
            System.out.println("✓ Terms & Conditions agreed");
            
            Thread.sleep(500);
            clickButtonWithId(driver, "button-payment-method", "Payment Method continue");
            
            Thread.sleep(2000);
            
        } catch (Exception e) {
            System.err.println("✗ Error completing checkout steps: " + e.getMessage());
            throw new RuntimeException("Failed to complete checkout steps", e);
        }
    }
    
    private void clickButtonWithId(WebDriver driver, String buttonId, String description) throws InterruptedException {
        String clickScript = 
            "var button = document.getElementById('" + buttonId + "');" +
            "if (button) { button.click(); return true; } " +
            "else { return false; }";
        
        try {
            Object result = ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(clickScript);
            if (result != null && (boolean) result) {
                System.out.println("✓ " + description + " button clicked");
            } else {
                System.err.println("⚠ " + description + " button not found");
            }
        } catch (Exception e) {
            System.err.println("⚠ Failed to click " + description + " button: " + e.getMessage());
        }
        Thread.sleep(500);
    }
}
