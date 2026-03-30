package com.company.automation.tasks.web;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

@Subject("agree to the terms and conditions")
public class AgreeToTermsAndConditions implements Task {

    private AgreeToTermsAndConditions() {}

    public static AgreeToTermsAndConditions now() {
        return new AgreeToTermsAndConditions();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = actor.abilityTo(BrowseTheWeb.class).getDriver();
        
        try {
            Thread.sleep(1000);
            
            String clickScript = 
                "var agreeCheckbox = document.querySelector(\"input[name='agree']\");" +
                "if (agreeCheckbox && !agreeCheckbox.checked) { agreeCheckbox.click(); return true; } " +
                "else if (agreeCheckbox && agreeCheckbox.checked) { return true; } " +
                "else { return false; }";
            
            Object result = ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(clickScript);
            if (result != null && (boolean) result) {
                System.out.println("✓ Terms & Conditions checkbox checked");
            } else {
                System.err.println("⚠ Terms & Conditions checkbox not found");
            }
            
            Thread.sleep(500);
            
        } catch (Exception e) {
            System.err.println("✗ Error agreeing to terms and conditions: " + e.getMessage());
            throw new RuntimeException("Failed to agree to terms and conditions", e);
        }
    }
}
