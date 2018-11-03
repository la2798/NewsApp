package com.example.android.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<NewsData> {
    private static final String DATE_SEPARATOR = "T";
    public NewsAdapter(@NonNull Context context, ArrayList<NewsData> objects) {
        super(context, 0, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        NewsData data = getItem(position);
        String section = data.getmSection();
        TextView sectionView = listItemView.findViewById(R.id.section);
        sectionView.setText(section);
        String title = data.getmTitle();
        TextView titleView = listItemView.findViewById(R.id.title);
        titleView.setText(title);
        String date;
        String time;
        String completeDate = data.getmDate();
        String[] parts = completeDate.split(DATE_SEPARATOR);
        date = parts[0] ;
        time = parts[1];
        TextView dateView = listItemView.findViewById(R.id.date);
        dateView.setText(date);
        TextView timeView = listItemView.findViewById(R.id.time);
        timeView.setText(time);
        return listItemView;
    }
}
