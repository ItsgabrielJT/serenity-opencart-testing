package com.company.automation.tasks.web;

import com.company.automation.ui.pages.HomePageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.annotations.Subject;
import org.openqa.selenium.Keys;

@Subject("search for '#product'")
public class SearchForProduct implements Task {

    private final String product;

    private SearchForProduct(String product) {
        this.product = product;
    }

    public static SearchForProduct called(String product) {
        return new SearchForProduct(product);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(product)
                     .into(HomePageTargets.SEARCH_INPUT),
                Click.on(HomePageTargets.SEARCH_BUTTON)
        );
    }
}
