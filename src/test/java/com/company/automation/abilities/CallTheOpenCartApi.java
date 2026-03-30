package com.company.automation.abilities;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

public class CallTheOpenCartApi {

    private final String baseUrl;

    private CallTheOpenCartApi(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public static CallTheOpenCartApi at(String baseUrl) {
        return new CallTheOpenCartApi(baseUrl);
    }

    
    public static void enable(Actor actor, String baseUrl) {
        actor.can(CallAnApi.at(baseUrl));
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
