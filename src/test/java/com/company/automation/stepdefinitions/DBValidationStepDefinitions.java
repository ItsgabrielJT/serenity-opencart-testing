package com.company.automation.stepdefinitions;

import com.company.automation.questions.db.TheDatabaseResult;
import com.company.automation.tasks.db.ExecuteQuery;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

/**
 * DBValidationStepDefinitions
 *
 * Step definitions para escenarios de validación de base de datos.
 * Usa la ability QueryDatabase y las Tasks/Questions DB correspondientes.
 */
public class DBValidationStepDefinitions {

    private Actor qaEngineer() {
        return OnStage.theActorInTheSpotlight();
    }

    @Given("the QA engineer has database access configured")
    public void theQaEngineerHasDatabaseAccessConfigured() {
        // El actor ya fue configurado con QueryDatabase ability en los hooks
    }

    @Given("an order with id {string} was placed in the system")
    public void anOrderWithIdWasPlacedInTheSystem(String orderId) {
        qaEngineer().remember("targetOrderId", orderId);
    }

    @When("the QA engineer queries the database for order {string}")
    public void theQaEngineerQueriesTheDatabaseForOrder(String orderId) {
        qaEngineer().attemptsTo(
                ExecuteQuery.sql(
                        "SELECT * FROM oc_order WHERE order_id = ?",
                        Integer.parseInt(orderId)
                )
        );
    }

    @Then("the database should return at least {int} row")
    public void theDatabaseShouldReturnAtLeastRow(int minRows) {
        qaEngineer().should(
                seeThat(TheDatabaseResult.rowCount(), greaterThanOrEqualTo(minRows))
        );
    }

    @Then("the order status should be {string}")
    public void theOrderStatusShouldBe(String expectedStatus) {
        qaEngineer().should(
                seeThat(TheDatabaseResult.firstRowColumn("order_status_id"),
                        equalTo(Integer.parseInt(expectedStatus)))
        );
    }

    @Given("a customer with email {string} completed a purchase")
    public void aCustomerWithEmailCompletedAPurchase(String email) {
        qaEngineer().remember("targetEmail", email);
    }

    @When("the QA engineer queries customer data by email {string}")
    public void theQaEngineerQueriesCustomerDataByEmail(String email) {
        qaEngineer().attemptsTo(
                ExecuteQuery.sql(
                        "SELECT * FROM oc_customer WHERE email = ?", email
                )
        );
    }

    @Then("the customer firstname should be {string}")
    public void theCustomerFirstnameShouldBe(String expectedFirstname) {
        qaEngineer().should(
                seeThat(TheDatabaseResult.firstRowColumn("firstname"),
                        equalTo(expectedFirstname))
        );
    }
}
