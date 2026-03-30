package com.company.automation.tasks.web;

import com.company.automation.ui.pages.HomePageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.annotations.Subject;

/**
 * Task: NavigateToHomePage
 *
 * Representa la acción de abrir la página principal de OpenCart.
 *
 * Principio Screenplay: Las Tasks orquestan Interactions de bajo nivel.
 * Principio SOLID: Single Responsibility – esta task solo abre la home.
 */
@Subject("navigate to the OpenCart home page")
public class NavigateToHomePage implements Task {

    private final String homeUrl;

    private NavigateToHomePage(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    public static NavigateToHomePage now() {
        return new NavigateToHomePage("http://opencart.abstracta.us/");
    }

    public static NavigateToHomePage at(String url) {
        return new NavigateToHomePage(url);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(homeUrl)
        );
    }
}
