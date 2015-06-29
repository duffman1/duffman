package com.nbcuni.test.publisher.pageobjects.Configuration;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

/**
 * ******************************************
 * publisher.nbcuni.com MPS Configuration Library. Copyright
 *
 * @author Brandon Clark
 * @version 1.1 Date: July 7, 2014
 *          *******************************************
 */

public class MPSConfiguration {

    private WebDriver webWebWebDriver;
    private WaitFor waitFor;
    private Config config;
    private Integer timeout;
    private Interact interact;

    //PAGE OBJECT CONSTRUCTOR    
    public MPSConfiguration(WebDriver webWebWebDriver) {
        this.webWebWebDriver = webWebWebDriver;
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }

    //PAGE OBJECT IDENTIFIERS    
    private By MPSHost_Txb = By.id("edit-mps-host");

    private By IntegrationMethod_Rdb(String integrationMethod) {
        return By.xpath("//label[text()='" + integrationMethod + " ']/..//input");
    }

    private By SiteInstanceOverride_Txb = By.id("edit-mps-site-override");

    private By SendQueryStrings_Cbx = By.id("edit-mps-query");

    private By Name_Txb(String index) {
        return By.xpath("(//input[contains(@id, 'name')])[" + index + "]");
    }

    private By AllName_Txbs = By.xpath("//input[contains(@id, 'name')]");

    private By Value_Txb(String index) {
        return By.xpath("(//input[contains(@id, 'value')])[" + index + "]");
    }

    private By AllValue_Txbs = By.xpath("//input[contains(@id, 'value')]");

    private By JSON_Cbx(String index) {
        return By.xpath("(//input[contains(@id, 'json')])[" + index + "]");
    }

    private By AllJSON_Cbxs = By.xpath("//input[contains(@id, 'json')]");

    private By AddAnotherOpt_Btn = By.id("edit-add-another-opt");

    private By PatternForCategoryField_Txb = By.id("edit-mps-cat-pattern");

    private By BrowseAvailableTokens_Lnk = By.linkText("Browse available tokens.");

    private By MPSExpander_Lnk = By.xpath("//td[contains(text(), 'MPS')][1]/span[@class='expander']");

    private By MPSCatProperty_Lnk = By.xpath("//a[text()='[mps:cat-pattern:?]']");

    private By SyncAdBlocks_Btn = By.xpath("//input[@value='Sync Ad Blocks']");

    private By SaveConfiguration_Btn = By.id("edit-submit");

    private By MPSCall_Scr = By.xpath("//script[contains(text(), 'mpscall')]");


    //PAGE OBJECT METHODS
    public void VerifyMPSCallParameters(List<String> parameters) throws Exception {

        String mpsCallParams = waitFor.ElementPresent(MPSCall_Scr).getAttribute("innerHTML");

        for (String parameter : parameters) {
            Reporter.log("Verify the mps call parameter '" + parameter + "' is present in the page source.");
            Assert.assertTrue(mpsCallParams.contains(parameter), "MPS Call parameter '" + parameter + "' is not present in page source as expected.");
        }

    }

    public Boolean IsMPSEnabled() throws Exception {

        Boolean mpsEnabled = false;
        if (webWebWebDriver.getPageSource().contains("mpscall")) {
            mpsEnabled = true;
        }

        return mpsEnabled;
    }

    public void VerifyNoMPSCallsMade() throws Exception {

        Reporter.log("Verify that no MPS calls were made on the page.");
        Assert.assertFalse(this.IsMPSEnabled());

    }

    public void EnterMPSHost(String host) throws Exception {

        Reporter.log("Enter '" + host + "' in the 'MPS Host' text box.");
        interact.Type(waitFor.ElementVisible(MPSHost_Txb), host);

    }

    public void ClickIntegrationMethod(String label) throws Exception {

        Reporter.log("Click the '" + label + "' radio button.");
        interact.Click(waitFor.ElementVisible(IntegrationMethod_Rdb(label)));

    }

    public void EnterSiteInstanceOverride(String override) throws Exception {

        Reporter.log("Enter '" + override + "' in the 'Site Instance Override' text box.");
        interact.Type(waitFor.ElementVisible(SiteInstanceOverride_Txb), override);

    }

    public void CheckSendQueryStringsCbx() throws Exception {

        WebElement ele = waitFor.ElementVisible(SendQueryStrings_Cbx);

        if (ele.isSelected() == false) {
            Reporter.log("Check the 'Send Query Strings' check box.");
            interact.Click(ele);
        }

    }

    public void UnCheckSendQueryStringsCbx() throws Exception {

        WebElement ele = waitFor.ElementVisible(SendQueryStrings_Cbx);

        if (ele.isSelected() == true) {
            Reporter.log("Un-check the 'Send Query Strings' check box.");
            interact.Click(ele);
        }

    }

    public void EnterName(String nameTxt, String index) throws Exception {

        Reporter.log("Enter '" + nameTxt + "' in the 'Name' text box with index '" + index + "'.");
        interact.Type(waitFor.ElementVisible(Name_Txb(index)), nameTxt);

    }

    public void EnterValue(String valueTxt, String index) throws Exception {

        Reporter.log("Enter '" + valueTxt + "' in the 'Value' text box with index '" + index + "'.");
        interact.Type(waitFor.ElementVisible(Value_Txb(index)), valueTxt);

    }

    public void CheckJSONCbx(String index) throws Exception {

        Reporter.log("Check the 'JSON' check box with index '" + index + "'.");
        interact.Click(waitFor.ElementVisible(JSON_Cbx(index)));

    }

    public void ClickAddAnotherOptBtn() throws Exception {

        Reporter.log("Click the 'Add another opt' button.");
        interact.Click(waitFor.ElementVisible(AddAnotherOpt_Btn));

    }

    public void ClickBrowseAvailableTokensLnk() throws Exception {

        Reporter.log("Click the 'Browse available tokens' link.");
        interact.Click(waitFor.ElementVisible(BrowseAvailableTokens_Lnk));

    }

    public void ClickMPSExpanderLnk() throws Exception {

        Reporter.log("Click the 'MPS' link.");
        interact.Click(waitFor.ElementVisible(MPSExpander_Lnk));

    }

    public void VerifyMPSCatPropertyLnkPresent() throws Exception {

        Reporter.log("Verify the 'MPS CAT Property' link with text '[mps:cat-pattern:?]' is present.");
        waitFor.ElementVisible(MPSCatProperty_Lnk);

    }

    public void EnterPatternForCategoryField(String pattern) throws Exception {

        Reporter.log("Enter '" + pattern + "' in the 'Pattern for Category Field' text box.");
        interact.Type(waitFor.ElementVisible(PatternForCategoryField_Txb), pattern);

    }

    public void ClickSyncAdBlocksBtn() throws Exception {

        Reporter.log("Click the 'Sync Ad Blocks' button.");
        interact.Click(waitFor.ElementVisible(SyncAdBlocks_Btn));

    }

    public void ClickSaveConfigurationBtn() throws Exception {

        Reporter.log("Click the 'Save configuration' button.");
        interact.Click(waitFor.ElementVisible(SaveConfiguration_Btn));

    }

    public void CleanAllMPSOptions() throws Exception {

        Reporter.log("Clear all 'Name' text box values.");
        for (WebElement Name_Txb : waitFor.ElementsVisible(AllName_Txbs)) {
            Name_Txb.clear();
        }

        Reporter.log("Clear all 'Value' text box values.");
        for (WebElement Value_Txb : waitFor.ElementsVisible(AllValue_Txbs)) {
            Value_Txb.clear();
        }

        Reporter.log("Uncheck all 'JSON' check boxes.");
        for (WebElement JSON_Cbx : waitFor.ElementsVisible(AllJSON_Cbxs)) {
            if (JSON_Cbx.isSelected() == true) {
                interact.Click(JSON_Cbx);
            }
        }
    }


}
