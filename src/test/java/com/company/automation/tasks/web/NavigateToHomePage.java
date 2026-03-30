package com.company.automation.tasks.web;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.annotations.Subject;

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
