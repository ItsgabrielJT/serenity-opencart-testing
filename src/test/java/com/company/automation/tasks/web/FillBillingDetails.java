package com.company.automation.tasks.web;

import com.company.automation.models.CustomerData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.annotations.Subject;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

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
            Thread.sleep(2000);
            
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

