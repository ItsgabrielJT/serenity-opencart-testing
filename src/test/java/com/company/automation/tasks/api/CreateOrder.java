package com.company.automation.tasks.api;

import com.company.automation.models.OrderRequest;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.rest.interactions.Post;

/**
 * Task: CreateOrder
 *
 * Realiza una petición POST para crear una nueva orden a través de la API.
 *
 * Principio SOLID:
 *   - Dependency Inversion: recibe un modelo OrderRequest desacoplado
 *     del formato JSON real.
 *   - Open/Closed: se puede extender para añadir auth headers sin modificar
 *     la lógica de negocio.
 */
@Subject("create a new order via the API")
public class CreateOrder implements Task {

    private final String endpoint;
    private final OrderRequest orderRequest;

    private CreateOrder(String endpoint, OrderRequest orderRequest) {
        this.endpoint = endpoint;
        this.orderRequest = orderRequest;
    }

    public static CreateOrder with(OrderRequest orderRequest) {
        return new CreateOrder("/order", orderRequest);
    }

    public static CreateOrder at(String endpoint, OrderRequest orderRequest) {
        return new CreateOrder(endpoint, orderRequest);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(endpoint)
                    .with(requestSpec ->
                            requestSpec
                                    .contentType(ContentType.JSON)
                                    .body(orderRequest)
                    )
        );
    }
}
