package com.company.automation.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * TestSuiteRunner
 *
 * Runner principal de Serenity que ejecuta TODOS los escenarios.
 * Utilizado en pipeline CI/CD para la suite de regresión completa.
 *
 * Principio Screenplay: Serenity gestiona el ciclo de vida del Actor
 * Stage automáticamente cuando se usa CucumberWithSerenity.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue     = "com.company.automation.stepdefinitions",
        plugin   = {
                "pretty",
                "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/report.html"
        },
        tags     = "not @wip and not @ui"
)
public class TestSuiteRunner {
    // Runner vacío — Serenity gestiona la ejecución.
}
