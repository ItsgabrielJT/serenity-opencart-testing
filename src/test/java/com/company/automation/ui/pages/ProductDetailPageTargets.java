package com.company.automation.ui.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * ProductDetailPageTargets
 *
 * Locators de la página de detalle de un producto.
 * Completar los selectores después de inspeccionar el HTML.
 */
public class ProductDetailPageTargets {

    private ProductDetailPageTargets() {}

    public static final Target PRODUCT_NAME =
            Target.the("product name heading")
                  .located(By.xpath(""));

    public static final Target PRODUCT_PRICE =
            Target.the("product price")
                  .located(By.xpath(""));

    public static final Target QUANTITY_INPUT =
            Target.the("quantity input field")
                  .located(By.xpath(""));

    public static final Target ADD_TO_CART_BUTTON =
            Target.the("add to cart button")
                  .located(By.xpath(""));

    public static final Target SUCCESS_ALERT =
            Target.the("add to cart success alert")
                  .located(By.xpath(""));

    public static final Target PRODUCT_DESCRIPTION =
            Target.the("product description tab")
                  .located(By.xpath(""));
}
