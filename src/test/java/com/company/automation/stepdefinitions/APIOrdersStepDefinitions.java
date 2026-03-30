package com.company.automation.stepdefinitions;

import com.company.automation.models.OrderRequest;
import com.company.automation.questions.api.TheResponseBody;
import com.company.automation.questions.api.TheResponseStatus;
import com.company.automation.tasks.api.CreateOrder;
import com.company.automation.tasks.api.GetOrderStatus;
import com.company.automation.tasks.api.GetProducts;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.Collections;
import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

/**
 * APIOrdersStepDefinitions
 *
 * Step definitions para los escenarios de pruebas API.
 * Delega toda la lógica HTTP a Tasks y Questions Screenplay.
 */
public class APIOrdersStepDefinitions {

    private Actor consumer() {
        return OnStage.theActorInTheSpotlight();
    }

    @Given("an API consumer is connected to the OpenCart API")
    public void anApiConsumerIsConnectedToTheOpenCartApi() {
        // El actor ya fue configurado en los hooks con la ability CallAnApi
    }

    @When("the consumer requests the list of products")
    public void theConsumerRequestsTheListOfProducts() {
        consumer().attemptsTo(
                GetProducts.now()
        );
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatus) {
        consumer().should(
                seeThat(TheResponseStatus.received(), equalTo(expectedStatus))
        );
    }

    @Then("the response body should not be empty")
    public void theResponseBodyShouldNotBeEmpty() {
        consumer().should(
                seeThat(TheResponseBody.asBodyString(), not(emptyOrNullString()))
        );
    }

    @Given("the consumer has valid order data for customer {string}")
    public void theConsumerHasValidOrderDataForCustomer(String email) {
        OrderRequest order = OrderRequest.builder()
                .firstName("API")
                .lastName("Test")
                .email(email)
                .telephone("5551234567")
                .products(List.of(new OrderRequest.OrderProduct(40, 1)))
                .paymentAddress(OrderRequest.AddressData.of(
                        "API", "Test", "123 Main St", "Austin", "78701", 223, 3635))
                .shippingAddress(OrderRequest.AddressData.of(
                        "API", "Test", "123 Main St", "Austin", "78701", 223, 3635))
                .build();

        consumer().remember("orderRequest", order);
    }

    @When("the consumer submits the order creation request")
    public void theConsumerSubmitsTheOrderCreationRequest() {
        OrderRequest order = (OrderRequest) consumer().recall("orderRequest");
        consumer().attemptsTo(
                CreateOrder.with(order)
        );
    }

    @Then("the response body should contain an order reference")
    public void theResponseBodyShouldContainAnOrderReference() {
        consumer().should(
                seeThat(TheResponseBody.asBodyString(), not(emptyOrNullString()))
        );
    }

    @Given("an order with id {string} exists in the system")
    public void anOrderWithIdExistsInTheSystem(String orderId) {
        consumer().remember("orderId", orderId);
    }

    @When("the consumer requests the status of order {string}")
    public void theConsumerRequestsTheStatusOfOrder(String orderId) {
        consumer().attemptsTo(
                GetOrderStatus.forOrderId(orderId)
        );
    }

    @Then("the response body should contain a {string} field")
    public void theResponseBodyShouldContainAField(String fieldName) {
        consumer().should(
                seeThat(TheResponseBody.asBodyString(), containsString(fieldName))
        );
    }

    @When("the consumer requests resource from {string}")
    public void theConsumerRequestsResourceFrom(String endpoint) {
        consumer().attemptsTo(
                GetProducts.fromEndpoint(endpoint)
        );
    }
}
