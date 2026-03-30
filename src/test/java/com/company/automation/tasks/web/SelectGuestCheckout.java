package com.company.automation.tasks.web;

import com.company.automation.ui.pages.CheckoutPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.Subject;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

/**
 * Task: SelectGuestCheckout
 *
 * Selecciona la opción "Guest Checkout" en OpenCart.
 * Usa JavaScript injection como método principal para evitar problemas de timing.
 */
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
            // Esperar a que el radio button esté presente
            Thread.sleep(1000);
            
            // Usar JavaScript para clickear el radio button de guest
            String scriptClickRadio = 
                "var guestRadio = document.querySelector(\"input[name='account'][value='guest']\");" +
                "if (guestRadio) { guestRadio.click(); } " +
                "else { alert('Guest radio not found!'); }";
            
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(scriptClickRadio);
            
            System.out.println("✓ Guest checkout radio clicked with JavaScript");
            Thread.sleep(500);
            
            // Clickear el botón continue
            String scriptClickButton = 
                "var continueBtn = document.getElementById('button-account');" +
                "if (continueBtn) { continueBtn.click(); } " +
                "else { alert('Continue button not found!'); }";
            
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(scriptClickButton);
            
            System.out.println("✓ Continue button clicked with JavaScript");
            
            // Esperar a que se cargue el formulario de billing (AJAX)
            Thread.sleep(3000);
            
        } catch (Exception e) {
            System.err.println("✗ Error selecting guest checkout: " + e.getMessage());
            throw new RuntimeException("Failed to select guest checkout", e);
        }
    }
}

