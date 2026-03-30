package com.company.automation.questions.web;

import com.company.automation.ui.pages.SearchResultsPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Subject("the search results displayed on the page")
public class TheSearchResults implements Question<List<String>> {

    private TheSearchResults() {}

    public static TheSearchResults displayed() {
        return new TheSearchResults();
    }

    @Override
    public List<String> answeredBy(Actor actor) {
        Collection<String> texts = Text.ofEach(SearchResultsPageTargets.PRODUCT_NAMES)
                                        .answeredBy(actor);
        return new ArrayList<>(texts);
    }
}
