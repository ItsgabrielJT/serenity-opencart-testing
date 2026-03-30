package com.company.automation.tasks.web;

import com.company.automation.ui.pages.CartPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.Subject;

/**
 * Task: ProceedToCheckout
 *
 * Hace clic en el botón "Proceed to Checkout" desde la página del carrito.
 */
@Subject("proceed to checkout")
public class ProceedToCheckout implements Task {

    private ProceedToCheckout() {}

    public static ProceedToCheckout now() {
        return new ProceedToCheckout();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(CartPageTargets.CHECKOUT_BUTTON)
        );
    }
}
