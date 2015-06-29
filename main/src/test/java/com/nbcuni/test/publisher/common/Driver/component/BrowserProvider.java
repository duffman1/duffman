package com.nbcuni.test.publisher.common.Driver.component;

import com.nbcuni.test.publisher.common.Driver.component.annotations.XmlBasedCapabilities;
import com.nbcuni.test.webdriver.CustomWebDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Entry for browser initialization; Each time new instance is being created;
 */

@Configuration
public class BrowserProvider {

    @Autowired @Value("${webdriver.hub}")  URL url;

    @XmlBasedCapabilities Capabilities capabilities;

    @Bean
    public WebDriver getDriver() {
        CustomWebDriver webDriver = new CustomWebDriver(url, capabilities);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return webDriver;
    }

}
