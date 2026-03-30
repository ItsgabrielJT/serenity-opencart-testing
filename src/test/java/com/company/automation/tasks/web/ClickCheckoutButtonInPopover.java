package com.company.automation.tasks.web;

import com.company.automation.ui.pages.CartPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

import java.time.Duration;

/**
 * Task: ClickCheckoutButtonInPopover
 *
 * Hace clic en el botón "Checkout" dentro del popover del carrito.
 * Asume que el popover ya está abierto.
 */
@Subject("click the checkout button in the cart popover")
public class ClickCheckoutButtonInPopover implements Task {

    private ClickCheckoutButtonInPopover() {}

    public static ClickCheckoutButtonInPopover now() {
        return new ClickCheckoutButtonInPopover();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            // Esperar a que el popover esté completamente visible
            WebDriverWait wait = new WebDriverWait(
                    actor.abilityTo(BrowseTheWeb.class).getDriver(),
                    Duration.ofSeconds(5)
            );
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("#cart .dropdown-menu a")
            ));
            
            Thread.sleep(500);
            
            // Click en el botón Checkout
            actor.attemptsTo(
                    Click.on(CartPageTargets.CHECKOUT_BUTTON_IN_POPOVER)
            );
            System.out.println("✓ Checkout button in popover clicked");
            
            // Esperar a que la página de checkout cargue
            Thread.sleep(2000);
            wait.until(ExpectedConditions.urlContains("checkout"));
            System.out.println("✓ Navigated to checkout page");
            
        } catch (Exception e) {
            System.err.println("✗ Error clicking checkout button in popover: " + e.getMessage());
            throw new RuntimeException("Failed to click checkout button in cart popover", e);
        }
    }
}
