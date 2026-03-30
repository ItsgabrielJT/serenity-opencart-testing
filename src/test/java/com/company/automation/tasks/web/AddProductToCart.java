package com.company.automation.tasks.web;

import com.company.automation.ui.pages.ProductDetailPageTargets;
import com.company.automation.ui.pages.SearchResultsPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.Subject;

@Subject("add '#productName' to the cart")
public class AddProductToCart implements Task {

    private final String productName;
    private boolean fromSearchResults;

    private AddProductToCart(String productName) {
        this.productName = productName;
        this.fromSearchResults = true;
    }

    public static AddProductToCart named(String productName) {
        return new AddProductToCart(productName);
    }

    
    public AddProductToCart fromDetailPage() {
        this.fromSearchResults = false;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (fromSearchResults) {
            actor.attemptsTo(
                    Click.on(SearchResultsPageTargets.addToCartButtonFor(productName))
            );
        } else {
            actor.attemptsTo(
                    Click.on(ProductDetailPageTargets.ADD_TO_CART_BUTTON)
            );
        }
    }
}
