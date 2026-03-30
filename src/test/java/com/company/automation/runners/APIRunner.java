package com.company.automation.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * APIRunner
 *
 * Runner específico para pruebas de API REST (@api).
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/api",
        glue     = "com.company.automation.stepdefinitions",
        plugin   = {
                "pretty",
                "json:target/cucumber-reports/api-cucumber.json",
                "html:target/cucumber-reports/api-report.html"
        },
        tags     = "@api"
)
public class APIRunner {
    // Runner vacío — Serenity gestiona la ejecución.
}
