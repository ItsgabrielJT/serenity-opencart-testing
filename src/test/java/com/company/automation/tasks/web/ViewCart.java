package com.company.automation.tasks.web;

import com.company.automation.ui.pages.CartPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.Subject;

@Subject("view the shopping cart")
public class ViewCart implements Task {

    private ViewCart() {}

    public static ViewCart now() {
        return new ViewCart();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(CartPageTargets.CART_TABLE)
        );
    }
}
