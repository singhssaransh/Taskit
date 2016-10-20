package com.example.mydell.taskit;

import android.app.ListActivity;
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


public class MainActivity2 extends AppCompatActivity {

    String classes2[]= {"Airplane Mode Changed","Alarm Clock","Auto Rotate On/Off","Bluetooth Configure","Brightness","Call Reject",
            "Clear Call Logs","Clear Notifications","Control Media","Daydream","Dim Screen"
            ,"Display Dialog","Display Notification","GPS Enabled/Disabled","Hotspot On/Off","Keep Device Awake","Kill Application",
            "Kill Background Apps", "Launch Application","Launch Home Screen","Make Call","Mobile Data On/Off","Notification Led On/Off",
            "Notification Sound","Open Call Log","Open File","Open Website","Reboot/Power Off","Record Microphone","Say Current Time",
            "Screen On/Off","Send Email","Send SMS","Set Wallpaper","Speak Text","Speaker Phone On/Off","Take Picture","Take Screenshot",
            "Flashlight On/Off","Vibrate","Volume Change","Voice Search","Wifi Configure"};
    int[] images2={R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4,R.drawable.a5,R.drawable.a6,R.drawable.a7,
            R.drawable.a8,R.drawable.a9,R.drawable.a10,R.drawable.a11,R.drawable.a12,R.drawable.a13,R.drawable.a14,R.drawable.a15,
            R.drawable.a16,R.drawable.a17,R.drawable.a18,R.drawable.a19,R.drawable.a20,R.drawable.a21,R.drawable.a22,R.drawable.a23,
            R.drawable.a24,R.drawable.a25,R.drawable.a26,R.drawable.a27,R.drawable.a28,R.drawable.a29,R.drawable.a30,R.drawable.a31,
            R.drawable.a32,R.drawable.a33,R.drawable.a34,R.drawable.a35,R.drawable.a36,R.drawable.a37,R.drawable.a38,R.drawable.a39,
            R.drawable.a40,R.drawable.a41,R.drawable.a42,R.drawable.a43};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_row2);

        CustomAdapter listadap=new CustomAdapter(this, classes2,images2);
        ListView lv=(ListView)findViewById(R.id.lv2);
        lv.setAdapter(listadap);
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });


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
