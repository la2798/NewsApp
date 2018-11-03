package com.example.android.newsapp;
import android.content.AsyncTaskLoader;
import android.content.Context;
import java.util.List;
public class NewsLoader extends AsyncTaskLoader<List<NewsData>> {
    private String mUrl;
    public NewsLoader(Context context,String url) {
        super(context);
        mUrl = url;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    @Override
    public List<NewsData> loadInBackground() {
        if ( mUrl == null) {
            return null;
        }
        List<NewsData> news = QueryUtil.fetchNewsData(mUrl);
        return news;
    }
}
