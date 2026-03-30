package com.company.automation.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * SmokeTestRunner
 *
 * Runner dedicado para la suite de humo (@smoke).
 * Ejecuta solo los escenarios marcados con @smoke para validación rápida.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue     = "com.company.automation.stepdefinitions",
        plugin   = {
                "pretty",
                "json:target/cucumber-reports/smoke-cucumber.json",
                "html:target/cucumber-reports/smoke-report.html"
        },
        tags     = "@smoke and not @ui"
)
public class SmokeTestRunner {
    // Runner vacío — Serenity gestiona la ejecución.
}
