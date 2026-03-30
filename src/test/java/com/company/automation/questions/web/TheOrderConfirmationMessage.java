package com.company.automation.questions.web;

import com.company.automation.ui.pages.OrderConfirmationPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;

/**
 * Question: TheOrderConfirmationMessage
 *
 * Recupera el texto del mensaje de confirmación de la orden.
 *
 * Principio Screenplay: Las Questions son la única forma de obtener
 * información del estado del sistema bajo prueba.
 *
 * Uso:
 *   actor.should(seeThat(TheOrderConfirmationMessage.displayed(),
 *                containsString("Your order has been placed")));
 */
@Subject("the order confirmation message displayed")
public class TheOrderConfirmationMessage implements Question<String> {

    private TheOrderConfirmationMessage() {}

    public static TheOrderConfirmationMessage displayed() {
        return new TheOrderConfirmationMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(OrderConfirmationPageTargets.SUCCESS_MESSAGE)
                   .answeredBy(actor);
    }
}
