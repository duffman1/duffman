package com.nbcuni.test.publisher.common.Util;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.google.common.base.Function;

@SuppressWarnings("unused")
public class Interact {

	private Driver webDriver;
	private Integer timeout = 0;
	private Config config;
    
    public Interact(Driver webDriver, Integer waitTime) {
        this.webDriver = webDriver;
        timeout = waitTime;
        config = new Config();
    }
    
    private By GetByLocator(WebElement ele) {
    	
    	By locator = null;
    	
    	System.out.println(ele.toString());
    	String elementObject[] = ele.toString().split("->");
    	System.out.println("Element object zero = " + elementObject[0]);
    	System.out.println("Element object one = " + elementObject[1]);
    	String element[] = elementObject[1].replace("]]", "]").split(": ");
    	
    	System.out.println("Element zero = " + element[0]);
    	System.out.println("Elmenet one = " + element[1]);
    	String locatorType = element[0].trim();
    	String elementID = element[1];
    	
    	switch (locatorType) {
    		case "xpath": locator = By.xpath(elementID); break;
    		case "cssSelector": locator = By.cssSelector(elementID); break;
    		case "linkText": locator = By.linkText(elementID); break;
    		case "id": locator = By.id(elementID); break;
    	}
    	
    	System.out.println(locator.toString());
    	return locator;
    	
    }
    
    private FluentWait<By> byWait(final By locator) throws Exception {
    	
    	return new FluentWait<By>(locator)
    			.withTimeout(timeout, TimeUnit.SECONDS)
    			.pollingEvery(config.getConfigValueInt("PollingTime"), TimeUnit.MILLISECONDS);
    			
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
    
    public void Click(final WebElement element) throws Exception {
    	
    	this.elementWait(element)
    		.ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class, WebDriverException.class))
    		.withMessage("Element not clickable.")
    		.until(new Function<WebElement, Boolean>() {
    			@Override
    			public Boolean apply(WebElement ele) {
    				Boolean elementClicked = false;
    				try {
    					ele.click();
    					elementClicked = true;
    				}
    				catch (WebDriverException e) {
    					if (e.getMessage().toString().contains("stale element"))
    					{
    						System.out.println("click element is stale.");
    						webDriver.findElement(GetByLocator(ele)).click();
    						elementClicked = true;
    					}
    				}
    				return elementClicked;	
    			}
    		});
    }
    
    public void Type(final WebElement element, final String text) throws Exception {
    	
    	this.elementWait(element)
    		.ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class, WebDriverException.class))
    		.withMessage("Element cannot be typed into.")
    		.until(new Function<WebElement, Boolean>() {
    			@Override
    			public Boolean apply(WebElement ele) {
    				Boolean elementTyped = false;
    				try {
    					ele.clear();
    					ele.sendKeys(text);
    					elementTyped = true;
    				}
    				catch (WebDriverException e) { 
    					if (e.getMessage().toString().contains("stale element"))
    					{
    						System.out.println("typed element is stale.");
    						webDriver.findElement(GetByLocator(ele)).clear();
    						webDriver.findElement(GetByLocator(ele)).sendKeys(text);
    						elementTyped = true;
    					}
    				}
    				return elementTyped;	
    			}
    		});
    }
    
    public void Select(final WebElement element, final String option) throws Exception {
    	
    	this.elementWait(element)
    		.ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class, WebDriverException.class))
    		.withMessage("Element cannot be selected with option '" + option + "'.")
    		.until(new Function<WebElement, Boolean>() {
    			@Override
    			public Boolean apply(WebElement ele) {
    				Boolean elementSelected = false;
    				try {
    					new Select(ele).selectByVisibleText(option);
    					elementSelected = true;
    				}
    				catch (WebDriverException e) { 
    					if (e.getMessage().toString().contains("stale element"))
    					{
    						System.out.println("select element is stale.");
    						new Select(webDriver.findElement(GetByLocator(ele))).selectByVisibleText(option);
    						elementSelected = true;
    					}
    				}
    				return elementSelected;	
    			}
    		});
    }
    
    public void Scroll(String scrollCount) throws Exception {
    	
    	webDriver.executeScript("window.scrollBy(0," + scrollCount + ");"); 
    	
    }
    
    public void ScrollToTop() throws Exception {
    	
    	webDriver.executeScript("window.scrollTo(0, 0);");
    	
    }
    
    

}
