package com.example.mydell.taskit;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by MY DELL on 26-08-2016.
 */
public class Main extends ListActivity {

    String classes[]={"example1","example2","example3","example4","example5","example6","example7","example8","example9"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(Main.this,android.R.layout.simple_list_item_1,classes));
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String ch=classes[position];
       try {
           Class ourclass = Class.forName("com.example.mydell."+ch);
           Intent ourintent = new Intent(Main.this, ourclass);
           startActivity(ourintent);
       }catch (ClassNotFoundException e){e.printStackTrace();}
    }


}
