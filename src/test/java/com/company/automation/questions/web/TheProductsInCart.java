package com.company.automation.questions.web;

import com.company.automation.ui.pages.CartPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Subject("the products currently in the cart")
public class TheProductsInCart implements Question<List<String>> {

    private TheProductsInCart() {}

    public static TheProductsInCart now() {
        return new TheProductsInCart();
    }

    @Override
    public List<String> answeredBy(Actor actor) {
        Collection<String> texts = Text.ofEach(CartPageTargets.PRODUCT_ROWS)
                                       .answeredBy(actor);
        return new ArrayList<>(texts);
    }
}
