package com.nbcuni.test.publisher.common.Driver.configuration;

import com.nbcuni.test.publisher.common.Driver.component.annotations.CustomCapabilities;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.webdriver.CustomWebDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by kiryl_zayets on 6/29/15.
 */

@Configuration
public class SeleniumContext {

    @Autowired
    @Value("${webdriver.hub}")
    URL url;

    @CustomCapabilities
    Capabilities capabilities;

    @Bean
    public DesiredCapabilities capabilities() {
        return new DesiredCapabilities();
    }

    @Bean(name = "firefox")
    public Capabilities firefox() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("browserName", "firefox");
        return desiredCapabilities;
    }

    @Bean(name = "chrome")
    public Capabilities chrome() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("browserName", "chrome");
        return desiredCapabilities;
    }

    @Bean
    @Scope("thread")
    public WebDriver webDriver() {
        CustomWebDriver webDriver = new CustomWebDriver(url, capabilities);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        return webDriver;
    }

}
