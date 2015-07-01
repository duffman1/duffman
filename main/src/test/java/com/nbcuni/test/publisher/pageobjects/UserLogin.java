package com.nbcuni.test.publisher.pageobjects;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.component.annotations.*;
import com.nbcuni.test.publisher.common.Driver.configuration.SeleniumContext;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.testng.Reporter;

import javax.annotation.PostConstruct;

/**
 * ******************************************
 * publisher.nbcuni.com UserLogin Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *          *******************************************
 */

@Configuration
@Scope("prototype")
@com.nbcuni.test.publisher.common.Driver.component.annotations.Page
public class UserLogin {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    private EmberNav navigation;
    WebDriver webWebDriver;

    //PAGE OBJECT CONSTRUCTOR

    @Autowired
    SeleniumContext context;

    public UserLogin() {};

    @PostConstruct
    public void init() {
        webWebDriver = context.webDriver();
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebDriver, timeout);
        interact = new Interact(webWebDriver, timeout);
        navigation = new EmberNav(webWebDriver);
    }

    public UserLogin(WebDriver webDriver) {
//        super(webDriver);

        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
        navigation = new EmberNav(webDriver);
    }

    //PAGE OBJECT IDENTIFIERS
    private By EmailAddress_Txb = By.xpath("//input[@id='edit-name']");

    private By Password_Txb = By.xpath("//input[@id='edit-pass']");

    private By LogIn_Btn = By.cssSelector("input[value='Log in']");

    private By FederatedLogIn_Lnk = By.xpath("//a[text()='Federated Log In']");

    public UserLogin navigate(String url) {
        webWebDriver.get(url);
        return this;
    }

    //PAGE OBJECT METHODS
    public void EnterEmailAddress(String emailAddress) throws Exception {

        Reporter.log("Enter '" + emailAddress + "' in the 'Email Address' text box.");
        interact.Type(waitFor.ElementVisible(EmailAddress_Txb), emailAddress);

    }

    public void EnterPassword(String password) throws Exception {

        Reporter.log("Enter '" + password + "' in the 'Password' text box.");
        interact.Type(waitFor.ElementVisible(Password_Txb), password);

    }

    public void ClickLoginBtn() throws Exception {

        Reporter.log("Click the 'Login' button.");
        interact.Click(waitFor.ElementVisible(LogIn_Btn));

    }

    public void VerifyFederatedLoginLnkPresent() throws Exception {

        Reporter.log("Verify the 'Federated Login' link is present.");
        waitFor.ElementVisible(FederatedLogIn_Lnk);

    }

    public void VerifyFederatedLoginLnkNotPresent() throws Exception {

        Reporter.log("Verify the 'Federated Login' link is not present.");
        waitFor.ElementNotPresent(FederatedLogIn_Lnk);

    }

    public void Login(String emailAddress, String password) throws Exception {

        this.EnterEmailAddress(emailAddress);
        this.EnterPassword(password);
        this.ClickLoginBtn();
        Thread.sleep(1000);

        navigation.ShowMenu();
        navigation.ClickMenuHorizontalBtn();
    }


}

