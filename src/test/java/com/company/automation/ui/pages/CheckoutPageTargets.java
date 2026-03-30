package com.company.automation.ui.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutPageTargets {

    private CheckoutPageTargets() {}

    public static final Target GUEST_CHECKOUT_RADIO =
            Target.the("guest checkout radio button")
                  .located(By.cssSelector("input[name='account'][value='guest']"));

    public static final Target BILLING_FIRSTNAME =
            Target.the("billing first name field")
                  .located(By.id("input-payment-firstname"));

    public static final Target DELIVERY_METHOD_CONTINUE_BUTTON =
            Target.the("delivery method continue button")
                  .located(By.id("button-shipping-method"));

    public static final Target TERMS_AND_CONDITIONS_CHECKBOX =
            Target.the("terms and conditions checkbox")
                  .located(By.cssSelector("input[name='agree']"));

    public static final Target CONFIRM_ORDER_BUTTON =
            Target.the("confirm order button")
                  .located(By.id("button-confirm"));
}