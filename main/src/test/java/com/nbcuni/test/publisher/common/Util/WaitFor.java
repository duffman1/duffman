package com.nbcuni.test.publisher.common.Util;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.google.common.base.Function;

public class WaitFor {

	private Driver webDriver;
	private Integer timeout = 0;
	private Config config;
    
    public WaitFor(Driver webDriver, Integer waitTime) {
        this.webDriver = webDriver;
        timeout = waitTime;
        config = new Config();
    }
    
    private FluentWait<WebElement> elementWait(final WebElement element) throws Exception {
    	
    	return new FluentWait<WebElement>(element)
    			.withTimeout(timeout, TimeUnit.SECONDS)
    			.pollingEvery(config.getConfigValueInt("PollingTime"), TimeUnit.MILLISECONDS);
    			
    }
    
    private FluentWait<Driver> driverWait() throws Exception {
    	
    	return new FluentWait<Driver>(webDriver)
    			.withTimeout(timeout, TimeUnit.SECONDS)
    			.pollingEvery(config.getConfigValueInt("PollingTime"), TimeUnit.MILLISECONDS);
    			
    }
    
    public WebElement ElementPresent(final WebElement element) throws Exception {
    	
    	this.elementWait(element)
    		.ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class))
    		.withMessage("Element not present.")
    		.until(new Function<WebElement, Boolean>() {
    			@Override
    			public Boolean apply(WebElement ele) {
    				return !ele.getLocation().equals(0);	
    			}
    		});
    	
    	return element;
    }
    
    public void ElementNotPresent(WebElement element) throws Exception {

    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    	this.elementWait(element)
    		.ignoring(StaleElementReferenceException.class)
			.withMessage("Element still present.")
			.until(new Function<WebElement, Boolean>() {
				@Override
				public Boolean apply(WebElement ele) {
					Boolean elementPresent = false;
					try {
						ele.getLocation();
						elementPresent = true;
					}
					catch (NoSuchElementException e) { }
					return !elementPresent;	
				}
			});
    	
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    }
    
    public WebElement ElementVisible(final WebElement element) throws Exception {
    	
    	this.elementWait(element)
    		.ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class, 
    				ElementNotVisibleException.class))
    		.withMessage("Element not visible.")
    		.until(new Function<WebElement, Boolean>() {
    			@Override
    			public Boolean apply(WebElement ele) {
    				return ele.isDisplayed();	
    			}
    		});
    	
    	return element;
    }
    
    public WebElement ElementNotVisible(final WebElement element) throws Exception {
    	
    	this.elementWait(element)
    		.ignoreAll(Arrays.asList(StaleElementReferenceException.class, ElementNotVisibleException.class))
    		.withMessage("Element still visible.")
    		.until(new Function<WebElement, Boolean>() {
    			@Override
    			public Boolean apply(WebElement ele) {
    				return !ele.isDisplayed();	
    			}
    		});
    	
    	return element;
    }
    
    public void MultipleWindowsPresent() throws Exception {
    	
    	this.driverWait()
    		.ignoring(Exception.class)
    		.withMessage("Multiple windows not present.")
    		.until(new Function<Driver, Boolean>() {
    			@Override
    			public Boolean apply(Driver drv) {
    				return !drv.getWindowHandles().equals(1);
    			}
    		});
    	
    }

    public WebElement ElementContainsText(final WebElement element, final String text) throws Exception {
    	
    	this.elementWait(element)
    		.ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class, 
    				ElementNotVisibleException.class))
    		.withMessage("Text '" + text + "' not present in element.")
    		.until(new Function<WebElement, Boolean>() {
    			@Override
    			public Boolean apply(WebElement ele) {
    				return ele.getText().contains(text);
    			}
    		});
    	
    	return element;
    }
    
    public WebElement ElementNotContainsText(final WebElement element, final String text) throws Exception {
    	
    	this.elementWait(element)
    		.ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class, 
    				ElementNotVisibleException.class))
    		.withMessage("Text '" + text + "' is still present in element.")
    		.until(new Function<WebElement, Boolean>() {
    			@Override
    			public Boolean apply(WebElement ele) {
    				return !ele.getText().contains(text);
    			}
    		});
    	
    	return element;
    }
    
    public void TitleContains(final String text) throws Exception {
    	
    	this.driverWait()
    		.withMessage("Title does not contain text '" + text + "'.")
    		.until(new Function<Driver, Boolean>() {
    			@Override
    			public Boolean apply(Driver drv) {
    				return drv.getTitle().contains(text);
    			}
    		});
    	
    }
    
    public void PageSourceContainsText(final String text) throws Exception {
    	
    	this.driverWait()
    		.withMessage("Page source does not contain text '" + text + "'.")
    		.until(new Function<Driver, Boolean>() {
    			@Override
    			public Boolean apply(Driver drv) {
    				return drv.getPageSource().contains(text);
    			}
    		});
    	
    }
    
    public void PageSourceNotContainsText(final String text) throws Exception {
    	
    	this.driverWait()
    		.withMessage("Page source still contains text '" + text + "'.")
    		.until(new Function<Driver, Boolean>() {
    			@Override
    			public Boolean apply(Driver drv) {
    				return !drv.getPageSource().contains(text);
    			}
    		});
    	
    }
    
    public void ImageVisible(final WebElement image) throws Exception {
    	
    	this.elementWait(image)
    		.ignoring(NoSuchElementException.class)
    		.withMessage("Image not visible.")
    		.until(new Function<WebElement, Boolean>() {
    			@Override
    			public Boolean apply(WebElement ele) {
    				return (Boolean) webDriver.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", image);
    			}
    		});
    	
    }

}