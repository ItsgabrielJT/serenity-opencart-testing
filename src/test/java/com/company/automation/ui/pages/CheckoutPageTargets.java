package com.company.automation.ui.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * CheckoutPageTargets
 *
 * Locators de todas las secciones del flujo de checkout de OpenCart.
 * El checkout es multi-paso; cada sección está agrupada con comentarios.
 * Completar los selectores después de inspeccionar el HTML.
 */
public class CheckoutPageTargets {

    private CheckoutPageTargets() {}

    // ─── Sección 1: Checkout Options ─────────────────────────────────────────

    public static final Target GUEST_CHECKOUT_RADIO =
            Target.the("guest checkout radio button")
                  .located(By.cssSelector("input[name='account'][value='guest']"));

    public static final Target REGISTERED_ACCOUNT_RADIO =
            Target.the("existing account radio button")
                  .located(By.cssSelector("input[name='account'][value='register']"));

    public static final Target CONTINUE_CHECKOUT_BUTTON =
            Target.the("continue checkout button (step 1)")
                  .located(By.id("button-account"));

    // ─── Sección 2: Billing Details ───────────────────────────────────────────

    public static final Target BILLING_FIRSTNAME =
            Target.the("billing first name field")
                  .located(By.id("input-payment-firstname"));

    public static final Target BILLING_LASTNAME =
            Target.the("billing last name field")
                  .located(By.id("input-payment-lastname"));

    public static final Target BILLING_EMAIL =
            Target.the("billing email field")
                  .located(By.id("input-payment-email"));

    public static final Target BILLING_TELEPHONE =
            Target.the("billing telephone field")
                  .located(By.id("input-payment-telephone"));

    public static final Target BILLING_ADDRESS1 =
            Target.the("billing address line 1 field")
                  .located(By.id("input-payment-address-1"));

    public static final Target BILLING_CITY =
            Target.the("billing city field")
                  .located(By.id("input-payment-city"));

    public static final Target BILLING_POSTCODE =
            Target.the("billing postcode field")
                  .located(By.id("input-payment-postcode"));

    public static final Target BILLING_COUNTRY_SELECT =
            Target.the("billing country select")
                  .located(By.id("input-payment-country"));

    public static final Target BILLING_REGION_SELECT =
            Target.the("billing region/state select")
                  .located(By.id("input-payment-zone"));

    // Para guest checkout el botón de continuar billing es #button-guest
    public static final Target BILLING_CONTINUE_BUTTON =
            Target.the("billing details continue button")
                  .located(By.id("button-guest"));

    // ─── Sección 3: Delivery Details ─────────────────────────────────────────

    public static final Target DELIVERY_CONTINUE_BUTTON =
            Target.the("delivery details continue button")
                  .located(By.id("button-guest-shipping"));

    // ─── Sección 4: Delivery Method ───────────────────────────────────────────

    public static final Target DELIVERY_METHOD_CONTINUE_BUTTON =
            Target.the("delivery method continue button")
                  .located(By.id("button-shipping-method"));

    // ─── Sección 5: Payment Method ────────────────────────────────────────────

    public static final Target AGREE_TERMS_CHECKBOX =
            Target.the("agree to terms checkbox")
                  .located(By.cssSelector("input[name='agree']"));

    public static final Target PAYMENT_CONTINUE_BUTTON =
            Target.the("payment method continue button")
                  .located(By.id("button-payment-method"));

    // ─── Sección 6: Order Confirmation ───────────────────────────────────────

    public static final Target CONFIRM_ORDER_BUTTON =
            Target.the("confirm order button")
                  .located(By.id("button-confirm"));
}
