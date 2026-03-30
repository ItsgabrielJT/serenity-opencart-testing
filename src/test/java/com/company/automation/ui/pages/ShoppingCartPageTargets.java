package com.company.automation.ui.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ShoppingCartPageTargets {

    private ShoppingCartPageTargets() {}

    public static final Target SHOPPING_CART_LINK =
            Target.the("shopping cart link in the top navigation")
                  .located(By.cssSelector("#top-links a[title='Shopping Cart']"));

    public static final Target CHECKOUT_BUTTON =
            Target.the("checkout button in the shopping cart page")
                  .located(By.cssSelector(".buttons .pull-right a.btn.btn-primary"));

    public static final Target CART_PRODUCT_NAMES =
            Target.the("product names in the shopping cart table")
                  .located(By.cssSelector("#content table.table-bordered .text-left a"));
}