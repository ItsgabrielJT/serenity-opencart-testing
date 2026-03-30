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
 * Task: ViewCartThenCheckout
 *
 * Navega a la página de carrito (Shopping Cart) desde el navbar
 * y luego hace clic en el botón "Checkout" de esa página.
 *
 * Flujo:
 *   1. Click en "Shopping Cart" del navbar (#top-links)
 *   2. Espera a que la página del carrito cargue
 *   3. Click en el botón "Checkout" de la página del carrito
 *   4. Navega a la página de checkout
 */
@Subject("view shopping cart and click checkout button")
public class ViewCartThenCheckout implements Task {

    private ViewCartThenCheckout() {}

    public static ViewCartThenCheckout now() {
        return new ViewCartThenCheckout();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = actor.abilityTo(BrowseTheWeb.class).getDriver();
        
        try {
            // Step 1: Click en "Shopping Cart" del navbar
            actor.attemptsTo(
                    Click.on(CartPageTargets.CART_TABLE)
            );
            System.out.println("✓ Shopping Cart link clicked");
            
            // Step 2: Esperar a que la página del carrito cargue
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.urlContains("checkout/cart"));
            System.out.println("✓ Shopping cart page loaded");
            
            Thread.sleep(500);
            
            // Step 3: Click en el botón "Checkout" de la página del carrito
            actor.attemptsTo(
                    Click.on(CartPageTargets.CHECKOUT_BUTTON_IN_CART_PAGE)
            );
            System.out.println("✓ Checkout button clicked from cart page");
            
            // Step 4: Esperar a que la página de checkout cargue
            Thread.sleep(2000);
            wait.until(ExpectedConditions.urlContains("checkout/checkout"));
            System.out.println("✓ Navigated to checkout page");
            
        } catch (Exception e) {
            System.err.println("✗ Error: " + e.getMessage());
            throw new RuntimeException("Failed to navigate to cart and checkout", e);
        }
    }
}
