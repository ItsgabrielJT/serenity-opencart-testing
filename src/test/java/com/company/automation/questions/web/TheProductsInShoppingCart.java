package com.company.automation.questions.web;

import com.company.automation.ui.pages.ShoppingCartPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Subject("the products currently listed in the shopping cart")
public class TheProductsInShoppingCart implements Question<List<String>> {

    private TheProductsInShoppingCart() {}

    public static TheProductsInShoppingCart now() {
        return new TheProductsInShoppingCart();
    }

    @Override
    public List<String> answeredBy(Actor actor) {
        Collection<String> texts = Text.ofEach(ShoppingCartPageTargets.CART_PRODUCT_NAMES)
                .answeredBy(actor);
        return new ArrayList<>(texts);
    }
}