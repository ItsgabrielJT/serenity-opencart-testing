package com.company.automation.questions.web;

import com.company.automation.ui.pages.OrderConfirmationPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;

@Subject("the order confirmation message displayed")
public class TheOrderConfirmationMessage implements Question<String> {

    private TheOrderConfirmationMessage() {}

    public static TheOrderConfirmationMessage displayed() {
        return new TheOrderConfirmationMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(OrderConfirmationPageTargets.CONFIRMATION_HEADING)
                   .answeredBy(actor);
    }
}
