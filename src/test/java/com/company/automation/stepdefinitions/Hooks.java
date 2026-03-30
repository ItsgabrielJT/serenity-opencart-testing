package com.company.automation.stepdefinitions;

import com.company.automation.abilities.QueryDatabase;
import com.company.automation.utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

/**
 * Hooks
 *
 * Configuración global de Cucumber para el framework Screenplay.
 *
 * Usa OnlineCast que integra automáticamente BrowseTheWeb con el WebDriver
 * gestionado por Serenity, sin necesidad de configurarlo manualmente.
 */
public class Hooks {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Before("@api")
    public void giveApiAbility() {
        String apiBaseUrl = ConfigReader.getApiBaseUrl();
        OnStage.theActorCalled("APIConsumer")
               .can(CallAnApi.at(apiBaseUrl));
    }

    @Before("@db")
    public void giveDatabaseAbility() {
        OnStage.theActorCalled("QAEngineer")
               .can(QueryDatabase.usingDefaultConfiguration());
    }

    @After
    public void tidyUpTheStage() {
        OnStage.drawTheCurtain();
    }
}
