package com.company.automation.ui.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartPageTargets {

    private CartPageTargets() {}

    public static final Target CART_ICON_BUTTON =
            Target.the("cart icon button in header")
                  .located(By.cssSelector("#cart button[data-toggle='dropdown']"));


    public static final Target CHECKOUT_BUTTON_IN_POPOVER =
            Target.the("checkout button in cart popover")
                  .located(By.xpath("//div[@id='cart']//ul[@class='dropdown-menu']//a[contains(@href, 'checkout')]"));

    public static final Target VIEW_CART_BUTTON_IN_POPOVER =
            Target.the("view cart button in cart popover")
                  .located(By.xpath("//div[@id='cart']//ul[@class='dropdown-menu']//a[contains(@href, '/cart')]"));

    public static final Target CART_TABLE =
            Target.the("shopping cart navigation link")
                  .located(By.xpath("//a[@title='Shopping Cart']"));

    public static final Target PRODUCT_ROWS =
            Target.the("cart product name links")
                  .located(By.cssSelector("#content table .text-left a"));

    
    public static Target rowForProduct(String productName) {
        return Target.the("cart row for " + productName)
                     .locatedBy("//td[contains(@class,'text-left')]//a[normalize-space(.)='" + productName + "']");
    }

    public static final Target CHECKOUT_BUTTON =
            Target.the("proceed to checkout button")
                  .located(By.cssSelector("#content a.btn-primary"));

    public static final Target CHECKOUT_BUTTON_IN_CART_PAGE =
            Target.the("checkout button in cart page")
                  .located(By.xpath("//div[@class='pull-right']//a[contains(@href, 'checkout/checkout') and contains(@class, 'btn-primary')]"));

    public static final Target CONTINUE_SHOPPING_BUTTON =
            Target.the("continue shopping button")
                  .located(By.cssSelector("#content a.btn-default"));

    public static final Target CART_TOTAL =
            Target.the("cart total amount")
                  .located(By.cssSelector(".table-bordered tr:last-child td:last-child"));

    public static final Target REMOVE_ITEM_BUTTON =
            Target.the("remove item button")
                  .located(By.cssSelector("#content .btn-danger"));

    public static final Target EMPTY_CART_MESSAGE =
            Target.the("empty cart message")
                  .located(By.cssSelector("#content p.text-center"));
}
