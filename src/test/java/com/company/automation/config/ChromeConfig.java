package com.company.automation.config;

import org.openqa.selenium.chrome.ChromeOptions;

/**
 * ChromeConfig
 *
 * Configuración centralizada para opciones de Chrome.
 * Ignora advertencias de certificados SSL y otros problemas de seguridad.
 */
public class ChromeConfig {

    /**
     * Obtiene ChromeOptions configuradas para ignorar errores de certificado SSL
     * y otras advertencias de seguridad que pueden bloquear la automatización.
     */
    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        
        // Ignorar errores de certificado (para soportar HTTP y certificados inseguros)
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--ignore-certificate-errors-spki-list");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        
        // Permitir conexiones inseguras (HTTP)
        options.addArguments("--allow-insecure-localhost");
        
        // Disable warnings and popups
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        
        // Aceptar certificados inseguros y trabajar con HTTP sin problemas
        options.setAcceptInsecureCerts(true);
        
        return options;
    }
}
