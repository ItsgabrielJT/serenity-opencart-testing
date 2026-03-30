package com.company.automation.tasks.web;

import com.company.automation.ui.pages.CartPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

import java.time.Duration;

/**
 * Task: ClickCheckoutFromPopover
 *
 * Hace clic en el icono del carrito en la cabecera para abrir el popover,
 * y luego en el botón "Checkout" dentro del popover.
 *
 * Flujo:
 *   1. Click en el icono del carrito (abre el dropdown)
 *   2. Espera a que el popover sea visible
 *   3. Click en el botón "Checkout" dentro del popover
 *   4. Navega a la página de checkout
 */
@Subject("click the checkout button in the cart popover")
public class ClickCheckoutFromPopover implements Task {

    private ClickCheckoutFromPopover() {}

    public static ClickCheckoutFromPopover now() {
        return new ClickCheckoutFromPopover();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = actor.abilityTo(BrowseTheWeb.class).getDriver();
        
        try {
            // Step 1: Click en el icono del carrito para abrir el popover
            actor.attemptsTo(
                    Click.on(CartPageTargets.CART_ICON_BUTTON)
            );
            System.out.println("✓ Cart icon button clicked to open popover");
            
            // Step 2: Esperar a que el popover se vuelva visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            By checkoutButtonLocator = By.cssSelector("#cart .dropdown-menu a");
            wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButtonLocator));
            System.out.println("✓ Cart popover is visible");
            
            Thread.sleep(500);
            
            // Step 3: Click en el botón Checkout del popover
            actor.attemptsTo(
                    Click.on(CartPageTargets.CHECKOUT_BUTTON_IN_POPOVER)
            );
            System.out.println("✓ Checkout button in popover clicked");
            
            // Step 4: Esperar a que la página de checkout cargue
            Thread.sleep(2000);
            wait.until(ExpectedConditions.urlContains("checkout"));
            System.out.println("✓ Navigated to checkout page");
            
        } catch (Exception e) {
            System.err.println("✗ Error clicking checkout from popover: " + e.getMessage());
            throw new RuntimeException("Failed to click checkout from cart popover", e);
        }
    }
}
