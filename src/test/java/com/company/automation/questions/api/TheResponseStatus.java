package com.company.automation.questions.api;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

/**
 * Question: TheResponseStatus
 *
 * Recupera el código de estado HTTP de la última respuesta API recibida.
 *
 * Principio Screenplay: Question que abstrae la extracción del status code
 * del objeto de respuesta de REST Assured.
 */
@Subject("the HTTP response status code")
public class TheResponseStatus implements Question<Integer> {

    private TheResponseStatus() {}

    public static TheResponseStatus received() {
        return new TheResponseStatus();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        return LastResponse.received()
                           .answeredBy(actor)
                           .statusCode();
    }
}
