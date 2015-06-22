package com.nbcuni.test.publisher.pageobjects.Configuration;

import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.pageobjects.Page;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Created by kiryl_zayets on 6/17/15.
 */
public class Amazons3 extends Page {

    public Amazons3(Driver webDriver) {
        super(webDriver);
    }

    @FindBy(id = "edit-amazons3-key")
    private TextInput apiKey;

    @FindBy(id = "edit-amazons3-secret")
    private TextInput apiSecret;

    @FindBy(id = "edit-amazons3-bucket")
    private TextInput bucketName;

    @FindBy(id="edit-submit")
    private Button save;

    @FindBy(id="edit-amazons3-cache")
    private CheckBox metadata;

    @FindBy(css="div#console div.messages")
    private HtmlElement status;

    public void setApiSecret(String secret){
        apiSecret.clear();
        apiSecret.sendKeys(secret);
    }

    public void setApiKey(String key){
        apiKey.clear();
        apiKey.sendKeys(key);
    }

    public void setApiBucket(String bucket){
        bucketName.clear();
        bucketName.sendKeys(bucket);
    }

    public void activateMetadata(){
        metadata.select();
    }

    public void setRequiredFields(String key, String secret, String bucket){
        setApiKey(key);
        setApiSecret(secret);
        setApiBucket(bucket);
        activateMetadata();
        save.click();
    }

    public String getStatus(){
        return status.getText();

    }
}