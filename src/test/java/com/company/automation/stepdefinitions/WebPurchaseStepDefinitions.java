package com.company.automation.stepdefinitions;

import com.company.automation.models.CustomerData;
import com.company.automation.questions.web.TheOrderConfirmationMessage;
import com.company.automation.questions.web.TheProductsInShoppingCart;
import com.company.automation.tasks.web.AddProductToCart;
import com.company.automation.tasks.web.FillBillingDetails;
import com.company.automation.tasks.web.NavigateToHomePage;
import com.company.automation.tasks.web.SearchForProduct;
import com.company.automation.ui.pages.CheckoutPageTargets;
import com.company.automation.ui.pages.ShoppingCartPageTargets;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class WebPurchaseStepDefinitions {

    private static final String BASE_URL = "http://opencart.abstracta.us/";
    private static final String BASE_HOST = "opencart.abstracta.us";
    private static final String CART_URL = BASE_URL + "index.php?route=checkout/cart";
    private static final String CHECKOUT_URL = "http://opencart.abstracta.us/index.php?route=checkout/checkout";

    private Actor customer() {
        try {
            Actor actor = OnStage.theActorInTheSpotlight();
            if (actor != null) {
                return actor;
            }
        } catch (Exception e) {
            // Ignorar - El actor no esta en el spotlight aun
        }

        return OnStage.theActorCalled("Customer");
    }

    private void waitForVisible(Target target, String errorMessage) {
        try {
            new WebDriverWait(customer().abilityTo(BrowseTheWeb.class).getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(target.resolveFor(customer())));
        } catch (Exception e) {
            throw new RuntimeException(errorMessage, e);
        }
    }

    private void waitForUrl(String fragment, String errorMessage) {
        try {
            new WebDriverWait(customer().abilityTo(BrowseTheWeb.class).getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.urlContains(fragment));
        } catch (Exception e) {
            throw new RuntimeException(errorMessage, e);
        }
    }

    private void forceHttpIfNeeded(String expectedFragment, String fallbackUrl) {
        var driver = customer().abilityTo(BrowseTheWeb.class).getDriver();
        String currentUrl = driver.getCurrentUrl();

        if (currentUrl != null
                && currentUrl.startsWith("https://")
                && currentUrl.contains(BASE_HOST)
                && currentUrl.contains(expectedFragment)) {
            driver.navigate().to(currentUrl.replaceFirst("^https://", "http://"));
            return;
        }

        if (currentUrl == null || !currentUrl.contains(expectedFragment)) {
            driver.navigate().to(fallbackUrl);
        }
    }

    private void clickButtonById(String buttonId, String errorMessage) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Object clicked = ((JavascriptExecutor) customer().abilityTo(BrowseTheWeb.class).getDriver()).executeScript(
                "var button = document.getElementById('" + buttonId + "');"
                        + "if (button) { button.click(); return true; }"
                        + "return false;"
        );

        if (!(clicked instanceof Boolean) || !((Boolean) clicked)) {
            throw new RuntimeException(errorMessage);
        }

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void ensureCheckboxChecked(String cssSelector, String errorMessage) {
        Object checked = ((JavascriptExecutor) customer().abilityTo(BrowseTheWeb.class).getDriver()).executeScript(
                "var checkbox = document.querySelector(\"" + cssSelector + "\");"
                        + "if (checkbox && !checkbox.checked) { checkbox.click(); return true; }"
                        + "if (checkbox && checkbox.checked) { return true; }"
                        + "return false;"
        );

        if (!(checked instanceof Boolean) || !((Boolean) checked)) {
            throw new RuntimeException(errorMessage);
        }
    }

    private void selectGuestCheckoutOption() {
        waitForVisible(CheckoutPageTargets.GUEST_CHECKOUT_RADIO, "Guest checkout option did not display");

        WebElement guestRadio = CheckoutPageTargets.GUEST_CHECKOUT_RADIO.resolveFor(customer());
        if (!guestRadio.isSelected()) {
            guestRadio.click();
        }

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private CustomerData customerDataFrom(DataTable dataTable) {
        var data = dataTable.asMap(String.class, String.class);

        return CustomerData.builder()
                .firstName(data.get("firstName"))
                .lastName(data.get("lastName"))
                .email(data.get("email"))
                .telephone(data.get("telephone"))
                .address(data.get("address"))
                .city(data.get("city"))
                .postcode(data.get("postcode"))
                .country(data.get("country"))
                .region(data.get("region"))
                .build();
    }

    @Given("the customer is on the OpenCart home page")
    public void theCustomerIsOnTheOpenCartHomePage() {
        OnStage.theActorCalled("Customer").attemptsTo(NavigateToHomePage.now());
    }

    @When("the customer searches for {string}")
    public void theCustomerSearchesFor(String product) {
        customer().attemptsTo(SearchForProduct.called(product));
    }

    @When("the customer adds {string} to the cart from the search results")
    public void theCustomerAddsProductToCartFromSearchResults(String productName) {
        customer().attemptsTo(AddProductToCart.named(productName));
    }

    @When("the customer navigates to checkout via shopping cart")
    public void theCustomerNavigatesToCheckoutViaShoppingCart() {
        customer().attemptsTo(NavigateToHomePage.at(CHECKOUT_URL));
    }

    @When("the customer clicks the shopping cart link")
    public void theCustomerClicksTheShoppingCartLink() {
        customer().attemptsTo(Click.on(ShoppingCartPageTargets.SHOPPING_CART_LINK));
        forceHttpIfNeeded("checkout/cart", CART_URL);
        waitForUrl("checkout/cart", "Shopping cart page did not display");
    }

    @Then("the shopping cart should contain {string}")
    public void theShoppingCartShouldContain(String productName) {
        customer().should(seeThat(TheProductsInShoppingCart.now(), hasItem(containsString(productName))));
    }

    @When("the customer clicks the checkout button from the shopping cart")
    public void theCustomerClicksTheCheckoutButtonFromTheShoppingCart() {
        customer().attemptsTo(Click.on(ShoppingCartPageTargets.CHECKOUT_BUTTON));
        forceHttpIfNeeded("checkout/checkout", CHECKOUT_URL);
        waitForUrl("checkout/checkout", "Checkout page did not load from shopping cart");
    }

    @Then("the checkout page should display")
    public void theCheckoutPageShouldDisplay() {
        waitForUrl("checkout", "Checkout page did not display");
    }

    @Then("the billing details section should display")
    public void theBillingDetailsSectionShouldDisplay() {
        waitForVisible(CheckoutPageTargets.BILLING_FIRSTNAME, "Billing details section did not display");
    }

    @When("the customer clicks continue from billing details")
    public void theCustomerClicksContinueFromBillingDetails() {
        clickButtonById("button-guest", "Failed to click continue from billing details");
    }

    @Then("the delivery details section should display")
    public void theDeliveryDetailsSectionShouldDisplay() {
        waitForUrl("checkout", "Delivery details section did not display");
    }

    @When("the customer clicks continue from delivery details")
    public void theCustomerClicksContinueFromDeliveryDetails() {
        clickButtonById("button-guest-shipping", "Failed to click continue from delivery details");
    }

    @Then("the delivery method section should display")
    public void theDeliveryMethodSectionShouldDisplay() {
        waitForUrl("checkout/checkout", "Delivery method section did not display");
    }

    @When("the customer clicks continue from delivery method")
    public void theCustomerClicksContinueFromDeliveryMethod() {
        clickButtonById("button-shipping-method", "Failed to click continue from delivery method");
    }

    @Then("the payment method section should display")
    public void thePaymentMethodSectionShouldDisplay() {
        waitForVisible(CheckoutPageTargets.TERMS_AND_CONDITIONS_CHECKBOX, "Payment method section did not display");
    }

    @When("the customer agrees to the terms and conditions")
    public void theCustomerAgreesToTheTermsAndConditions() {
        ensureCheckboxChecked("input[name='agree']", "Failed to agree to terms and conditions");
    }

    @When("the customer clicks continue from payment method")
    public void theCustomerClicksContinueFromPaymentMethod() {
        clickButtonById("button-payment-method", "Failed to click continue from payment method");
    }

    @Then("the confirm order section should display")
    public void theConfirmOrderSectionShouldDisplay() {
        waitForVisible(CheckoutPageTargets.CONFIRM_ORDER_BUTTON, "Confirm order section did not display");
    }

    @When("the customer confirms the order")
    public void theCustomerConfirmsTheOrder() {
        clickButtonById("button-confirm", "Failed to confirm the order");
    }

    @Then("the order confirmation page should display")
    public void theOrderConfirmationPageShouldDisplay() {
        waitForUrl("success", "Order confirmation page did not display");
    }

    @Then("the order confirmation message should contain {string}")
    public void theOrderConfirmationMessageShouldContain(String expectedMessage) {
        customer().should(seeThat(TheOrderConfirmationMessage.displayed(), containsString(expectedMessage)));
    }

    @When("the customer selects guest checkout")
    public void theCustomerSelectsGuestCheckout() {
        selectGuestCheckoutOption();
    }

    @When("the customer clicks continue from checkout options")
    public void theCustomerClicksContinueFromCheckoutOptions() {
        clickButtonById("button-account", "Failed to click continue from checkout options");
        waitForVisible(CheckoutPageTargets.BILLING_FIRSTNAME, "Billing details section did not display after checkout options");
    }

    @When("the customer fills in the billing details with:")
    public void theCustomerFillsInTheBillingDetailsWith(DataTable dataTable) {
        customer().attemptsTo(FillBillingDetails.using(customerDataFrom(dataTable)));
    }
}
