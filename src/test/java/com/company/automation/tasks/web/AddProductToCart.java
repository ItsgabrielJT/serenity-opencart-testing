package com.company.automation.tasks.web;

import com.company.automation.ui.pages.ProductDetailPageTargets;
import com.company.automation.ui.pages.SearchResultsPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.Subject;

/**
 * Task: AddProductToCart
 *
 * Localiza un producto en los resultados de búsqueda y lo agrega al carrito.
 * Si el producto requiere ser abierto en su página de detalle primero,
 * se puede utilizar la variante withDetailPage().
 *
 * Principio SOLID:
 *   - Single Responsibility: solo agrega un producto al carrito.
 *   - Open/Closed: se puede extender para seleccionar variantes (talla, color)
 *     sin modificar la lógica base.
 */
@Subject("add '#productName' to the cart")
public class AddProductToCart implements Task {

    private final String productName;
    private boolean fromSearchResults;

    private AddProductToCart(String productName) {
        this.productName = productName;
        this.fromSearchResults = true;
    }

    public static AddProductToCart named(String productName) {
        return new AddProductToCart(productName);
    }

    /**
     * Indica que el producto se agrega desde la página de detalle
     * (ya se navegó a ella previamente).
     */
    public AddProductToCart fromDetailPage() {
        this.fromSearchResults = false;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (fromSearchResults) {
            actor.attemptsTo(
                    Click.on(SearchResultsPageTargets.addToCartButtonFor(productName))
            );
        } else {
            actor.attemptsTo(
                    Click.on(ProductDetailPageTargets.ADD_TO_CART_BUTTON)
            );
        }
    }
}
