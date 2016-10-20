package com.example.mydell.taskit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class customadapter2 extends ArrayAdapter<String > {

    int[] images2;
    public customadapter2(Context context, String[] classes2,int[] images2) {
        super(context, R.layout.custom_row, classes2);
        this.images2=images2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater taskInflater = LayoutInflater.from(getContext());
        View customView = taskInflater.inflate(R.layout.custom_row2, parent, false);

        String triggerName = getItem(position);
        TextView tv1 = (TextView) customView.findViewById(R.id.textView);
        ImageView iv1 = (ImageView) customView.findViewById(R.id.imageView);
        tv1.setText(triggerName);
        iv1.setImageResource(images2[position]);
        return customView;
    }
}
