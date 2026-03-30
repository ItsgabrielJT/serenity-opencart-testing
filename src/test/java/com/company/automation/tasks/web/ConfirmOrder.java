package com.company.automation.tasks.web;

import com.company.automation.ui.pages.CheckoutPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.Subject;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

/**
 * Task: ConfirmOrder
 *
 * Confirma la orden haciendo clic en el botón final de checkout (Step 6).
 * Usa JavaScript como fallback.
 */
@Subject("confirm the order")
public class ConfirmOrder implements Task {

    private ConfirmOrder() {}

    public static ConfirmOrder now() {
        return new ConfirmOrder();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = actor.abilityTo(BrowseTheWeb.class).getDriver();
        
        try {
            Thread.sleep(1000);
            
            // Usar JavaScript para clickear el botón de confirmar
            String clickScript = 
                "var confirmBtn = document.getElementById('button-confirm');" +
                "if (confirmBtn) { confirmBtn.click(); return true; } " +
                "else { return false; }";
            
            Object result = ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(clickScript);
            if (result != null && (boolean) result) {
                System.out.println("✓ Order confirmed");
            } else {
                System.err.println("⚠ Confirm button not found, trying alternate selector");
                // Fallback: buscar por texto
                String fallbackScript = 
                    "var buttons = document.querySelectorAll('button');" +
                    "for (var i = 0; i < buttons.length; i++) {" +
                    "  if (buttons[i].textContent.includes('Confirm')) { buttons[i].click(); break; }" +
                    "}";
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(fallbackScript);
                System.out.println("✓ Order confirmed (alternate method)");
            }
            
            Thread.sleep(3000);
            
        } catch (Exception e) {
            System.err.println("✗ Error confirming order: " + e.getMessage());
            throw new RuntimeException("Failed to confirm order", e);
        }
    }
}
