package com.company.automation.tasks.web;

import com.company.automation.ui.pages.CartPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.Subject;

@Subject("click the cart button in the header")
public class ClickCartButton implements Task {

    private ClickCartButton() {}

    public static ClickCartButton now() {
        return new ClickCartButton();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(CartPageTargets.CART_ICON_BUTTON)
        );
        System.out.println("✓ Cart button clicked to open popover");
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
