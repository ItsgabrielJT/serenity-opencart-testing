package com.company.automation.questions.api;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

/**
 * Question: TheResponseBody
 *
 * Recupera el body de la última respuesta API como String.
 * Para extracciones específicas de JSON paths, crear Questions dedicadas
 * usando LastResponse.received().answeredBy(actor).path("your.json.path").
 *
 * Principio SOLID: Open/Closed – se puede extender con métodos factory
 * especializados sin modificar esta clase base.
 */
@Subject("the response body content")
public class TheResponseBody implements Question<String> {

    private TheResponseBody() {}

    public static TheResponseBody asBodyString() {
        return new TheResponseBody();
    }

    @Override
    public String answeredBy(Actor actor) {
        return LastResponse.received()
                           .answeredBy(actor)
                           .body()
                           .asString();
    }

    /**
     * Factory especializado: extrae un valor usando JSON path.
     */
    public static Question<String> atPath(String jsonPath) {
        return actor -> LastResponse.received()
                                    .answeredBy(actor)
                                    .path(jsonPath)
                                    .toString();
    }
}
