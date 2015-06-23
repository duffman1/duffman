package com.nbcuni.test.publisher.bo;

/**
 * Created by kiryl_zayets on 6/22/15.
 */
public class SimpleCustomContent {

    private String contentName;
    private String field;
    private String fieldType;
    private String widget;
    private String image;

    public SimpleCustomContent(String contentName, String field, String fieldType, String widget, String image){

        this.contentName = contentName;
        this.field = field;
        this.fieldType = fieldType;
        this.widget = widget;
        this.image = image;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getWidget() {
        return widget;
    }

    public void setWidget(String widget) {
        this.widget = widget;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
