package com.example.mydell.taskit;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Main3Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Switch service;
    TextView t3;
    int counter1=0;
   TextView loginemailview;
FirebaseUser user;
    FirebaseAuth auth;
    @Override
    protected void onResume() {
        super.onResume();
        ListView mainList ;
        ArrayAdapter<String> listAdapter ;
        SharedPreferences preference = getSharedPreferences("TaskIt", Context.MODE_PRIVATE);

        boolean[] switches = {preference.getBoolean("item0", false),
                preference.getBoolean("item1", false),
                preference.getBoolean("item2", false),
                preference.getBoolean("item3", false),
                preference.getBoolean("item4", false),
                preference.getBoolean("item5", false),
                preference.getBoolean("item6", false),
                preference.getBoolean("item7", false)};
        String[] details = {"Wake screen using proximity",
                "Block Incoming Calls", "Flip the device to silent any media",
                "Turn on/off Torch ", "Headset Plugged in",
                "On wave of your hand", "Notification of battery",
                "Item eight details"};
        ArrayList<String> planetList = new ArrayList<String>();

        listAdapter = new ArrayAdapter<String>(this,R.layout.simple, planetList);
        listAdapter.clear();
         counter1=0;
        mainList=(ListView)findViewById(R.id.listView1);
        mainList.setAdapter( listAdapter );

        if(switches[0])
        {
            listAdapter.add( details[0] );
            counter1++;
        }
        if(switches[1])
        {
            listAdapter.add( details[1] );
            counter1++;
        }
        if(switches[2])
        {
            listAdapter.add( details[2] );
            counter1++;
        }
        if(switches[3])
        {
            listAdapter.add( details[3] );
            counter1++;
        }
        if(switches[4])
        {counter1++;
            listAdapter.add( details[4] );

        }
        if(switches[5])
        {counter1++;
            listAdapter.add( details[5] );

        }
        if(switches[6])
        {counter1++;
            listAdapter.add( details[6] );

        }
        t3.setText(""+counter1);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        t3=(TextView)findViewById(R.id.t3);
        Context context = null;
        final ActivityManager activityManager  =  (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();






        ListView mainList ;
         ArrayAdapter<String> listAdapter ;
        SharedPreferences preference = getSharedPreferences("TaskIt", Context.MODE_PRIVATE);

        boolean[] switches = {preference.getBoolean("item0", false),
                preference.getBoolean("item1", false),
                preference.getBoolean("item2", false),
                preference.getBoolean("item3", false),
                preference.getBoolean("item4", false),
                preference.getBoolean("item5", false),
                preference.getBoolean("item6", false),
                preference.getBoolean("item7", false)};
         String[] details = {"Wake screen using proximity",
                "Block Incoming Calls", "Flip the device to silent any media",
                "Turn on/off Torch ", "Headset Plugged in",
                "On wave of your hand", "Notification of battery",
                "Item eight details"};
        ArrayList<String> planetList = new ArrayList<String>();

        listAdapter = new ArrayAdapter<String>(this,R.layout.simple, planetList);
        listAdapter.clear();
        if(switches[0])
        {
            listAdapter.add( details[0] );
           counter1++;
        }
        if(switches[1])
        {
            listAdapter.add( details[1] );
            counter1++;
        }
        if(switches[2])
        {
            listAdapter.add( details[2] );
            counter1++;
        }
        if(switches[3])
        {
            listAdapter.add( details[3] );
            counter1++;
        }
        if(switches[4])
        {counter1++;
            listAdapter.add( details[4] );

        }
        if(switches[5])
        {counter1++;
            listAdapter.add( details[5] );

        }
        if(switches[6])
        {counter1++;
            listAdapter.add( details[6] );

        }
      t3.setText(""+counter1);
        /*
listAdapter.add( details[0] );
        listAdapter.add( "Pluto" );
        listAdapter.add( "Haumea" );
        listAdapter.add( "Makemake" );
        listAdapter.add( "Eris" );
listAdapter.remove(details[0]);
        listAdapter.add(details[1]);
*/
        mainList=(ListView)findViewById(R.id.listView1);
        mainList.setAdapter( listAdapter );
        service = (Switch) findViewById(R.id.serviceButton);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        SharedPreferences preferences = getSharedPreferences("TaskIt", Context.MODE_PRIVATE);
        if (preferences.getBoolean("ServiceOn", false) && isMyServiceRunning(MyService.class)) {
            service.setChecked(true);
        } else if(preferences.getBoolean("ServiceOn", false)){
            startService(intent);
            service.setChecked(true);
        }else{
            service.setChecked(false);
        }

        service.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Intent intent = new Intent(getApplicationContext(), MyService.class);

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Toast.makeText(getApplicationContext(),"service on ",Toast.LENGTH_LONG).show();
                    SharedPreferences preferences = getSharedPreferences("TaskIt", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("ServiceOn", true);
                    editor.apply();
                    //Toast.makeText(getApplicationContext(),"service on "+preferences.getBoolean("TaskIt",false),Toast.LENGTH_LONG).show();
                    startService(intent);
                } else {
                    //Toast.makeText(getApplicationContext(),"service off ",Toast.LENGTH_LONG).show();

                    SharedPreferences preferences = getSharedPreferences("TaskIt", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("ServiceOn", false);
                    editor.apply();
                    //Toast.makeText(getApplicationContext(),"service off "+preferences.getBoolean("TaskIt",false),Toast.LENGTH_LONG).show();
                    stopService(intent);
                }
            }
        });
        auth= FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!= null)
        {
            NavigationView navigationView1=(NavigationView)findViewById(R.id.nav_view) ;
            View header=navigationView.getHeaderView(0);
            loginemailview = (TextView) header.findViewById(R.id.t1);
            user=auth.getCurrentUser();
            loginemailview.setText(user.getEmail());
        }

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main3, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent ourintent=new Intent("com.example.mydell.taskit12");
            startActivity(ourintent);
        } else if (id == R.id.nav_gallery) {
            Intent ourintent=new Intent("com.example.mydell.template");
            startActivity(ourintent);

        } else if (id == R.id.nav_slideshow) {

            Intent nextScree = new Intent(this , Login.class);
            startActivity(nextScree);
        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
