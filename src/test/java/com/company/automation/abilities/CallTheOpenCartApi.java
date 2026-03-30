package com.company.automation.abilities;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

/**
 * Ability: CallTheOpenCartApi
 *
 * Encapsula la capacidad del actor de invocar la API REST de OpenCart.
 * Delega en la ability nativa de Serenity Screenplay REST: CallAnApi.
 *
 * Principio SOLID aplicado: Open/Closed – se puede extender para nuevas
 * API base urls sin modificar las tasks existentes.
 */
public class CallTheOpenCartApi {

    private final String baseUrl;

    private CallTheOpenCartApi(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public static CallTheOpenCartApi at(String baseUrl) {
        return new CallTheOpenCartApi(baseUrl);
    }

    /**
     * Registra la ability CallAnApi en el actor con la base URL configurada.
     *
     * @param actor el actor que recibirá la ability
     */
    public static void enable(Actor actor, String baseUrl) {
        actor.can(CallAnApi.at(baseUrl));
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
