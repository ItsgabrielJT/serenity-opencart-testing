package com.company.automation.ui.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * CartPageTargets
 *
 * Locators de la página del carrito de compras y el popover del carrito.
 * Completar los selectores después de inspeccionar el HTML.
 */
public class CartPageTargets {

    private CartPageTargets() {}

    // ─── Popover del Carrito (Header) ──────────────────────────────────────

    // El botón del carrito en el header (icono con cantidad y precio) que abre el popover
    public static final Target CART_ICON_BUTTON =
            Target.the("cart icon button in header")
                  .located(By.cssSelector("#cart button[data-toggle='dropdown']"));

    // El botón "Checkout" dentro del popover del carrito
    // Selectores alternativos probados:
    // - #cart .dropdown-menu a[href*='checkout'] (más específico)
    // - a[href*='checkout/checkout'] (por URL completa)
    public static final Target CHECKOUT_BUTTON_IN_POPOVER =
            Target.the("checkout button in cart popover")
                  .located(By.xpath("//div[@id='cart']//ul[@class='dropdown-menu']//a[contains(@href, 'checkout')]"));

    // El botón "View Cart" dentro del popover del carrito
    public static final Target VIEW_CART_BUTTON_IN_POPOVER =
            Target.the("view cart button in cart popover")
                  .located(By.xpath("//div[@id='cart']//ul[@class='dropdown-menu']//a[contains(@href, '/cart')]"));

    // ─── Página del Carrito (checkout/cart) ──────────────────────────────────

    // Enlace "Shopping Cart" en la barra de navegación superior
    public static final Target CART_TABLE =
            Target.the("shopping cart navigation link")
                  .located(By.xpath("//a[@title='Shopping Cart']"));

    // Nombres de los productos en la tabla del carrito (página checkout/cart)
    public static final Target PRODUCT_ROWS =
            Target.the("cart product name links")
                  .located(By.cssSelector("#content table .text-left a"));

    /**
     * Target parametrizado: localiza la fila del carrito por nombre de producto.
     */
    public static Target rowForProduct(String productName) {
        return Target.the("cart row for " + productName)
                     .locatedBy("//td[contains(@class,'text-left')]//a[normalize-space(.)='" + productName + "']");
    }

    public static final Target CHECKOUT_BUTTON =
            Target.the("proceed to checkout button")
                  .located(By.cssSelector("#content a.btn-primary"));

    // Botón "Checkout" específico en la página del carrito (dentro de div.pull-right)
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
