package com.company.automation.abilities;

import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Actor;
import org.openqa.selenium.WebDriver;

/**
 * Ability: BrowseTheWebWithSerenity
 *
 * Wrapper sobre BrowseTheWeb de Serenity para centralizar
 * la inicialización del driver y facilitar extensión futura.
 *
 * Principio SOLID aplicado: Single Responsibility – esta clase
 * solo gestiona la capacidad del actor de navegar la web.
 */
public class BrowseTheWebWithSerenity {

    private BrowseTheWebWithSerenity() {
        // utility wrapper – no instanciar directamente
    }

    /**
     * Otorga al actor la capacidad de navegar con el driver gestionado por Serenity.
     *
     * @return la Ability de Serenity BrowseTheWeb
     */
    public static BrowseTheWeb asConfiguredIn(WebDriver driver) {
        return BrowseTheWeb.with(driver);
    }
}
