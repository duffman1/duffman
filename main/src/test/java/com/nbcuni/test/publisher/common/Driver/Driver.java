package com.nbcuni.test.publisher.common.Driver;

import java.net.URL;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;

/*********************************************
 * publisher.nbcuni.com Driver Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 27, 2014
 *********************************************/

public class Driver extends RemoteWebDriver implements TakesScreenshot {

    public Driver(URL url, DesiredCapabilities capabilities) {

        super(url, capabilities);

    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {

        if ((Boolean) getCapabilities().getCapability(CapabilityType.TAKES_SCREENSHOT)) {

            return target.convertFromBase64Png(execute(DriverCommand.SCREENSHOT).getValue().toString());

        }

        return null;
    }

}

