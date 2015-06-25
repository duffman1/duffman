package com.nbcuni.test.publisher.bo;

/**
 * Created by kiryl_zayets on 6/25/15.
 */
public class Metadata {

    private String title;
    private String alt;
    private String source;
    private String credit;
    private String copyright;
    private String keywords;
    private String mediatags;

    public Metadata(String title, String alt,  String source, String credit, String copyright, String keywords, String mediatags){

        this.title = title;
        this.alt = alt;
        this.source = source;
        this.credit = credit;
        this.copyright = copyright;
        this.keywords = keywords;
        this.mediatags = mediatags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Metadata metadata = (Metadata) o;

        if (!alt.equals(metadata.alt)) return false;
        if (!copyright.equals(metadata.copyright)) return false;
        if (!credit.equals(metadata.credit)) return false;
        if (!keywords.equals(metadata.keywords)) return false;
        if (!mediatags.equals(metadata.mediatags)) return false;
        if (!source.equals(metadata.source)) return false;
        if (!title.equals(metadata.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + alt.hashCode();
        result = 31 * result + source.hashCode();
        result = 31 * result + credit.hashCode();
        result = 31 * result + copyright.hashCode();
        result = 31 * result + keywords.hashCode();
        result = 31 * result + mediatags.hashCode();
        return result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getMediatags() {
        return mediatags;
    }

    public void setMediatags(String mediatags) {
        this.mediatags = mediatags;
    }
}
