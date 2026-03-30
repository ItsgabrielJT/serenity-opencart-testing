package com.company.automation.ui.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * SearchResultsPageTargets
 *
 * Locators de la página de resultados de búsqueda.
 * Completar los selectores después de inspeccionar el HTML.
 */
public class SearchResultsPageTargets {

    private SearchResultsPageTargets() {}

    public static final Target RESULTS_CONTAINER =
            Target.the("search results container")
                  .located(By.id("content"));

    public static final Target PRODUCT_NAMES =
            Target.the("product name list")
                  .locatedBy("//div[contains(@class,'product-layout')]//h4/a");

    /**
     * Target parametrizado: localiza el botón "Add to Cart" de un producto
     * por su nombre visible.
     *
     * @param productName nombre del producto tal como aparece en la UI
     */
    public static Target addToCartButtonFor(String productName) {
        String xpath = "//*[contains(text(),'" + productName + "')]/ancestor::div[contains(@class,'product-layout')]//button[contains(@onclick,'cart.add')]";
        return Target.the("add to cart button for " + productName)
                     .locatedBy(xpath);
    }

    /**
     * Target parametrizado: localiza el enlace de un producto por nombre.
     */
    public static Target productLinkFor(String productName) {
        return Target.the("product link for " + productName)
                     .locatedBy("//div[contains(@class,'product-layout')]//h4/a[normalize-space(.)='" + productName + "']");
    }

    public static final Target NO_RESULTS_MESSAGE =
            Target.the("no results message")
                  .located(By.cssSelector("#content p"));
}
