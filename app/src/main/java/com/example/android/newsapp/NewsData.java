package com.example.android.newsapp;

public class NewsData {
    private String mSection;
    private String mTitle;
    private String mDate;
    private String mUrl;
    public String getmSection() {
        return mSection;
    }
    public String getmTitle() {
        return mTitle;
    }
    public String getmDate() {
        return mDate;
    }
    public String getmUrl() {
        return mUrl;
    }
    public NewsData(String section, String title, String date, String url){
        mSection = section;
        mTitle = title;
        mDate = date;
        mUrl = url;
    }
}
