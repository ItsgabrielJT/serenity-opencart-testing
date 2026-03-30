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
            actor.attemptsTo(
                    Click.on(CartPageTargets.CART_ICON_BUTTON)
            );
            System.out.println("✓ Cart icon button clicked to open popover");
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            By checkoutButtonLocator = By.cssSelector("#cart .dropdown-menu a");
            wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButtonLocator));
            System.out.println("✓ Cart popover is visible");
            
            Thread.sleep(500);
            
            actor.attemptsTo(
                    Click.on(CartPageTargets.CHECKOUT_BUTTON_IN_POPOVER)
            );
            System.out.println("✓ Checkout button in popover clicked");
            
            Thread.sleep(2000);
            wait.until(ExpectedConditions.urlContains("checkout"));
            System.out.println("✓ Navigated to checkout page");
            
        } catch (Exception e) {
            System.err.println("✗ Error clicking checkout from popover: " + e.getMessage());
            throw new RuntimeException("Failed to click checkout from cart popover", e);
        }
    }
}
