package com.company.automation.ui.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePageTargets {

    private HomePageTargets() {}

    public static final Target SEARCH_INPUT =
            Target.the("search input field")
                  .located(By.name("search"));

    public static final Target SEARCH_BUTTON =
            Target.the("search button")
                  .located(By.cssSelector("#search button.btn-default"));

    public static final Target NAVIGATION_MENU =
            Target.the("navigation menu")
                  .located(By.id("menu"));

    public static final Target CART_ICON =
            Target.the("cart icon")
                  .located(By.cssSelector("#cart button.btn-inverse"));

    public static final Target FEATURED_PRODUCTS =
            Target.the("featured products section")
                  .located(By.cssSelector(".product-layout"));
}
