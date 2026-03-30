package com.company.automation.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/web",
        glue     = "com.company.automation.stepdefinitions",
        plugin   = {
                "pretty",
                "json:target/cucumber-reports/ui-cucumber.json",
                "html:target/cucumber-reports/ui-report.html"
        },
        tags     = "@ui"
)
public class WebUIRunner {
    // Runner vacío — Serenity gestiona la ejecución.
}
