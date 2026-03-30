package com.company.automation.tasks.api;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.rest.interactions.Get;

/**
 * Task: GetOrderStatus
 *
 * Recupera el estado de una orden existente a través de la API.
 *
 * Principio SOLID: Single Responsibility – solo consulta el estado,
 * no modifica ni elimina.
 */
@Subject("get the status of order '#orderId'")
public class GetOrderStatus implements Task {

    private final String orderId;

    private GetOrderStatus(String orderId) {
        this.orderId = orderId;
    }

    public static GetOrderStatus forOrderId(String orderId) {
        return new GetOrderStatus(orderId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/order/" + orderId)
        );
    }
}
