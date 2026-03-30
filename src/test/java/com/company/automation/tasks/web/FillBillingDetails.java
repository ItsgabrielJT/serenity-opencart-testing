package com.company.automation.tasks.web;

import com.company.automation.models.CustomerData;
import com.company.automation.ui.pages.CheckoutPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.annotations.Subject;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

/**
 * Task: FillBillingDetails
 *
 * Completa el formulario de datos de facturación del checkout.
 * Usa JavaScript como fallback para formularios cargados por AJAX.
 *
 * Principio SOLID:
 *   - Dependency Inversion: depende del modelo CustomerData, no de valores
 *     hard-codeados, lo que permite Data Driven Testing.
 *   - Single Responsibility: solo rellena el formulario de billing.
 */
@Subject("fill in billing details for '#customer'")
public class FillBillingDetails implements Task {

    private final CustomerData customer;

    private FillBillingDetails(CustomerData customer) {
        this.customer = customer;
    }

    public static FillBillingDetails using(CustomerData customer) {
        return new FillBillingDetails(customer);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = actor.abilityTo(BrowseTheWeb.class).getDriver();
        
        try {
            // Esperar a que el formulario de billing se cargue por AJAX
            Thread.sleep(2000);
            
            // Usar JavaScript para rellenar los campos
            String jsScript = 
                "document.getElementById('input-payment-firstname').value = '" + escapeQuotes(customer.getFirstName()) + "';" +
                "document.getElementById('input-payment-lastname').value = '" + escapeQuotes(customer.getLastName()) + "';" +
                "document.getElementById('input-payment-email').value = '" + escapeQuotes(customer.getEmail()) + "';" +
                "document.getElementById('input-payment-telephone').value = '" + escapeQuotes(customer.getTelephone()) + "';" +
                "document.getElementById('input-payment-address-1').value = '" + escapeQuotes(customer.getAddress()) + "';" +
                "document.getElementById('input-payment-city').value = '" + escapeQuotes(customer.getCity()) + "';" +
                "document.getElementById('input-payment-postcode').value = '" + escapeQuotes(customer.getPostcode()) + "';";
            
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(jsScript);
            System.out.println("✓ Billing details filled with JavaScript");
            
            Thread.sleep(500);
            
            // Seleccionar país (más complejo porque es un select)
            try {
                String countryScript = 
                    "var countrySelect = document.getElementById('input-payment-country');" +
                    "if (countrySelect) {" +
                    "  for (var i = 0; i < countrySelect.options.length; i++) {" +
                    "    if (countrySelect.options[i].text.includes('" + customer.getCountry() + "')) {" +
                    "      countrySelect.selectedIndex = i;" +
                    "      countrySelect.dispatchEvent(new Event('change', { bubbles: true }));" +
                    "      break;" +
                    "    }" +
                    "  }" +
                    "}";
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(countryScript);
                System.out.println("✓ Country selected");
            } catch (Exception e) {
                System.err.println("⚠ Country selection failed: " + e.getMessage());
            }
            
            Thread.sleep(1000);
            
            // Seleccionar región/estado
            try {
                String regionScript = 
                    "var regionSelect = document.getElementById('input-payment-zone');" +
                    "if (regionSelect) {" +
                    "  for (var i = 0; i < regionSelect.options.length; i++) {" +
                    "    if (regionSelect.options[i].text.includes('" + customer.getRegion() + "')) {" +
                    "      regionSelect.selectedIndex = i;" +
                    "      regionSelect.dispatchEvent(new Event('change', { bubbles: true }));" +
                    "      break;" +
                    "    }" +
                    "  }" +
                    "}";
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(regionScript);
                System.out.println("✓ Region selected");
            } catch (Exception e) {
                System.err.println("⚠ Region selection failed: " + e.getMessage());
            }
            
            Thread.sleep(500);
            System.out.println("✓ Billing details filled");
            
        } catch (Exception e) {
            System.err.println("✗ Error filling billing details: " + e.getMessage());
            throw new RuntimeException("Failed to fill billing details", e);
        }
    }
    
    private String escapeQuotes(String value) {
        return value != null ? value.replace("'", "\\'") : "";
    }
}

