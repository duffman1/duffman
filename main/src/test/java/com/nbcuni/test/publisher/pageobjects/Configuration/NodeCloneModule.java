package com.nbcuni.test.publisher.pageobjects.Configuration;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

/**
 * ******************************************
 * publisher.nbcuni.com Node Clone Module Library. Copyright
 *
 * @author Mohd Faizan Khan
 * @version 1.0 Date: Feb 18, 2014
 *          *******************************************
 */

public class NodeCloneModule {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;

    //PAGE OBJECT CONSTRUCTOR
    public NodeCloneModule(WebDriver webWebWebDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }

    //PAGE OBJECT IDENTIFIERS
    private By PrePopulateNodeFormFields_Rdb = By.id("edit-clone-method-prepopulate");

    private By BypassConfirmation_Rdb = By.id("edit-clone-nodes-without-confirm-1");

    private By No_Rdb = By.id("edit-clone-menu-links-0");

    private By UseNodeTypeNameInCloneLink_Cbx = By.id("edit-clone-use-node-type-name");

    private By SaveConfiguration_Btn = By.id("edit-submit");

    private By AllPublishingOptionsResetDefault_Cbxs = By.cssSelector("input[id*='edit-clone-reset']");

    private By AllOmittedContentTypes_Cbxs = By.cssSelector("input[id*='edit-clone-omitted']");


    //PAGE OBJECT METHODS
    public void ClickPrePopulateNodeFormFieldsRdb() throws Exception {

        WebElement ele = waitFor.ElementVisible(PrePopulateNodeFormFields_Rdb);
        if (ele.isSelected() == false) {

            Reporter.log("Click the 'Pre-populate the node form fields' radio button.");
            interact.Click(ele);

        }

    }

    public void ClickBypassConfirmationRdb() throws Exception {

        WebElement ele = waitFor.ElementVisible(BypassConfirmation_Rdb);
        if (ele.isSelected() == false) {

            Reporter.log("Click the 'Bypass confirmation' radio button.");
            interact.Click(ele);

        }
    }

    public void ClickCloneMenuLnksNoRdb() throws Exception {

        WebElement ele = waitFor.ElementVisible(No_Rdb);
        if (ele.isSelected() == false) {

            Reporter.log("Click the 'No' radio button in under the 'Clone menu links' label.");
            interact.Click(ele);
        }
    }

    public void ClickUseNodeTypeNameInCloneLnkCbx() throws Exception {

        WebElement ele = waitFor.ElementVisible(UseNodeTypeNameInCloneLink_Cbx);
        if (ele.isSelected() == false) {

            Reporter.log("Check the 'Use node type name in clone link' check box.");
            interact.Click(ele);
        }

    }

    public void UncheckAllPublishingOptionResetDefaultCbxs() throws Exception {

        List<WebElement> allCbxs = waitFor.ElementsPresent(AllPublishingOptionsResetDefault_Cbxs);

        for (WebElement ele : allCbxs) {
            if (ele.isSelected() == true) {
                Reporter.log("Uncheck the check box with id '" + ele.getAttribute("id") + "' under the 'Should the publishing options be reset...' label.");
                interact.Click(ele);
            }
        }

    }

    public void UncheckAllOmittedContentTypesCbxs() throws Exception {

        List<WebElement> allCbxs = waitFor.ElementsPresent(AllOmittedContentTypes_Cbxs);

        for (WebElement ele : allCbxs) {
            if (ele.isSelected() == true) {
                Reporter.log("Uncheck the check box with id '" + ele.getAttribute("id") + "' under the 'Omitted content types' label.");
                interact.Click(ele);
            }
        }
    }

    public void ClickSaveConfigurationBtn() throws Exception {

        Reporter.log("Click the 'Save configuration' button.");
        interact.Click(waitFor.ElementVisible(SaveConfiguration_Btn));

    }

    public void EnableNodeCloneModuleSetting() throws Exception {

        this.ClickPrePopulateNodeFormFieldsRdb();
        this.ClickBypassConfirmationRdb();
        this.ClickCloneMenuLnksNoRdb();
        this.ClickUseNodeTypeNameInCloneLnkCbx();
        this.UncheckAllPublishingOptionResetDefaultCbxs();
        this.UncheckAllOmittedContentTypesCbxs();
        this.ClickSaveConfigurationBtn();

    }
}