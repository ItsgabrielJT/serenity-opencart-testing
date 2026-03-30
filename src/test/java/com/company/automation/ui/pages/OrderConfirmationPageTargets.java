package com.company.automation.ui.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class OrderConfirmationPageTargets {

    private OrderConfirmationPageTargets() {}

    public static final Target CONFIRMATION_HEADING =
            Target.the("order confirmation heading")
                  .located(By.cssSelector("#content h1"));

    public static final Target SUCCESS_MESSAGE =
            Target.the("order success message")
                  .located(By.cssSelector("#content p"));

    public static final Target ORDER_ID =
            Target.the("order ID reference")
                  .located(By.cssSelector("#content p strong"));

    public static final Target CONTINUE_BUTTON =
            Target.the("continue button after order")
                  .located(By.cssSelector("#content a.btn-primary"));
}
