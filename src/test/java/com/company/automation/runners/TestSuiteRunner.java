package com.company.automation.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue     = "com.company.automation.stepdefinitions",
        tags     = "not @wip and not @ui"
)
public class TestSuiteRunner {
    // Runner vacío — Serenity gestiona la ejecución.
}
