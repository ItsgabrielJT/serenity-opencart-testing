package com.company.automation.config;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeConfig {

    
    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--ignore-certificate-errors-spki-list");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        
        options.addArguments("--allow-insecure-localhost");
        
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        
        options.setAcceptInsecureCerts(true);
        
        return options;
    }
}
