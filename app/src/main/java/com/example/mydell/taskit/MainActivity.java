package com.example.mydell.taskit;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ListAdapter;
import  android.widget.*;


public class MainActivity extends AppCompatActivity {

    String classes[]={"Airplane Mode Changed","Application Installed/Removed","Application Launched/Closed","Battery Level","Bluetooth Event","Calender Event",
            "Call Active","Call Ended","Call Incoming","Call Missed","Call Outgoing","Data Connectivity Change"
            ,"Day of Week/Month","Day/Time Trigger","Daydream On/Off","Dial Phone Number","Flip Device","GPS Enabled/Disabled","Headphones Insert/Remove",
            "Hotspot Enabled/Disabled","Light Sensor","Power Button Toggle","Power Connected/Removed","Proximity Sensor","Regular Interval"
            ,"Roaming Started/Stopped","Screen On/Off","Screen Unlocked","Shake Device","Silent Mode Enabled/Disabled","SMS Received","SMS Sent"
            ,"Swipe Screen","Volume Button Pressed","Wifi State Change"};
    int[] images={R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,R.drawable.e,R.drawable.f,
            R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j,R.drawable.k,R.drawable.l,R.drawable.m,R.drawable.n,
            R.drawable.o,R.drawable.p,R.drawable.q,R.drawable.r,R.drawable.s,R.drawable.t,R.drawable.u,R.drawable.v,
            R.drawable.w,R.drawable.x,R.drawable.y,R.drawable.z,R.drawable.aa,R.drawable.ab,R.drawable.ac,R.drawable.ad,
            R.drawable.ae,R.drawable.af,R.drawable.ag,R.drawable.ah,R.drawable.ai};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getApplicationContext().startService(new Intent(getBaseContext(), Helloservice.class));
        CustomAdapter listadap=new CustomAdapter(this, classes,images);
        ListView lv=(ListView)findViewById(R.id.lv1);
        lv.setAdapter(listadap);
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(nextScree);

                    }
                });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
