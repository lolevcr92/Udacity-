package com.example.android.guardianews;

public class Article {
    /*Defining object fields*/
    private String mTitle;
    private String mDate;
    private String mSectionName;
    private String mPillar;
    private String mUrl;
    private String mImageUrl;
    private String mAuthor;

    /*Defining constructor*/
    public Article(String title, String date, String pillar, String sectionName, String author, String url, String imageUrl) {
        mTitle = title;
        mDate = date;
        mPillar = pillar;
        mSectionName = sectionName;
        mAuthor = author;
        mUrl = url;
        mImageUrl = imageUrl;
    }

    /*Defining getters for objects*/
    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public String getPillar() {
        return mPillar;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}
