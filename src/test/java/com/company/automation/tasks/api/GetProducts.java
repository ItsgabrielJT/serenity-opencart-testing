package com.company.automation.tasks.api;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.rest.interactions.Get;

/**
 * Task: GetProducts
 *
 * Realiza una petición GET al endpoint de productos de la API de OpenCart.
 *
 * Principio Screenplay: Las Tasks API encapsulan la lógica de construcción
 * de request sin exponer detalles HTTP al step definition.
 *
 * Principio SOLID: Single Responsibility – solo recupera la lista de productos.
 */
@Subject("get the list of products from the API")
public class GetProducts implements Task {

    private final String endpoint;

    private GetProducts(String endpoint) {
        this.endpoint = endpoint;
    }

    public static GetProducts fromEndpoint(String endpoint) {
        return new GetProducts(endpoint);
    }

    public static GetProducts now() {
        return new GetProducts("/product");
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(endpoint)
        );
    }
}
