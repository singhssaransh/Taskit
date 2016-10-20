package com.example.mydell.taskit;

import android.app.ActivityManager;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.BatteryManager;
import android.os.IBinder;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class Helloservice extends Service {

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
          //  if(action.equals("android.intent.action.ACTION_POWER_CONNECTED")){
//
  //              BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();



   //             }

            }





    };

    int mStartMode;

    IBinder mBinder;

    boolean mAllowRebind;

    @Override
    public void onCreate() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        filter.addAction("android.intent.action.NEW_OUTGOING_CALL");

  //      registerReceiver(receiver, filter);
//
//

    }
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.



        //Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        //int deviceStatus;

        //BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //Intent BATTERYintent = this.registerReceiver(null, new IntentFilter(
        //      Intent.ACTION_BATTERY_CHANGED));

//        deviceStatus =BATTERYintent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);

//
        //      if(deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING){
        //        Toast.makeText(this,"Battery charging",Toast.LENGTH_LONG).show();
        //      if (!mBluetoothAdapter.isEnabled()) {
        //        mBluetoothAdapter.enable();
        //  }
        //}

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
       // Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}

