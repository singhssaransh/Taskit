package com.example.mydell.taskit;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class CustomAdapter extends ArrayAdapter<String > {

    int[] images;
    public CustomAdapter(Context context, String[] classes,int[] images) {
        super(context, R.layout.custom_row, classes);
        this.images=images;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater taskInflater = LayoutInflater.from(getContext());
        View customView = taskInflater.inflate(R.layout.custom_row, parent, false);

        String triggerName = getItem(position);
        TextView tv1 = (TextView) customView.findViewById(R.id.textView);
        ImageView iv1 = (ImageView) customView.findViewById(R.id.imageView);
        tv1.setText(triggerName);
        tv1.setTypeface(null, Typeface.BOLD);
        iv1.setImageResource(images[position]);
        return customView;
    }
}
