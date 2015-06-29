package com.nbcuni.test.publisher.common.Driver.component;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kiryl_zayets on 6/28/15.
 */

@Configuration
public class CapabilityComponent {

    @Bean
    public DesiredCapabilities capabilities(){
        return new DesiredCapabilities();
    }

    @Bean(name = "firefox")
    public Capabilities firefox(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("browserName", "firefox");
        return desiredCapabilities;
    }

    @Bean(name = "chrome")
    public Capabilities chrome(){
         DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("browserName", "chrome");
        return desiredCapabilities;
    }

}
