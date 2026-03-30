package com.company.automation.tasks.web;

import com.company.automation.ui.pages.SearchResultsPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.Subject;

@Subject("add '#productName' to the cart")
public class AddProductToCart implements Task {

    private final String productName;

    private AddProductToCart(String productName) {
        this.productName = productName;
    }

    public static AddProductToCart named(String productName) {
        return new AddProductToCart(productName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(SearchResultsPageTargets.addToCartButtonFor(productName))
        );
    }
}
