package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsData>> {
    private static final String URL = "https://content.guardianapis.com/search?q=india&show-tags=contributor&api-key=b9b34771-9f62-4477-a614-2ae64747a090";
    private static final int LOADER_ID = 1;
    NewsAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected){
            ListView listView = findViewById(R.id.list);
            adapter = new NewsAdapter(this,new ArrayList<NewsData>());
            listView.setAdapter(adapter);
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(LOADER_ID,null,this);
            listView.setEmptyView(findViewById(R.id.emptyview));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    NewsData data = adapter.getItem(position);
                    Uri newsUri = Uri.parse(data.getmUrl());
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                    startActivity(websiteIntent);
                }
            });
        }
        else{
            View spinningView = findViewById(R.id.loading_spinner);
            spinningView.setVisibility(View.GONE);
            TextView message=(TextView)findViewById(R.id.emptyview);
            message.setText(R.string.no_internet);
        }
    }
    @Override
    public Loader<List<NewsData>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this,URL);
    }
    @Override
    public void onLoadFinished(Loader<List<NewsData>> loader, List<NewsData> data) {
        adapter.clear();
        View spinningView = findViewById(R.id.loading_spinner);
        spinningView.setVisibility(View.GONE);
        if (data != null && !data.isEmpty()) {
            adapter.addAll(data);
        }
        else{
            TextView noDataView = findViewById(R.id.emptyview);
            noDataView.setText(R.string.no_list_items);
        }
        TextView noListItemView = (TextView)findViewById(R.id.emptyview);
        noListItemView.setText(R.string.nodata);
    }
    @Override
    public void onLoaderReset(Loader<List<NewsData>> loader) {
        adapter.clear();
    }
}
