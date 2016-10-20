package com.example.mydell.taskit;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MyService extends Service implements SensorEventListener {
    int scale;
    Context context;
    Camera mCamera;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast;
    private int counter=0,counter2=0;
    long firstmovetime=0;
    long now=0;
    boolean islight=true;
    private CameraManager mCameraManager;
    String mCameraId="";
    BluetoothAdapter bluetoothAdapter;
    int counter1=0;
    long firstmovetime1=0;
    long now1=0;
    private String a,b;
    public int flag=0;






    private SensorManager sensorManager;

    private BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {

        @TargetApi(Build.VERSION_CODES.KITKAT)
        public void onReceive(Context context, Intent intent) {
            //context.unregisterReceiver(this);

            SharedPreferences pref = getSharedPreferences("TaskIt",MODE_PRIVATE);

           // Toast.makeText(getApplicationContext(), "Receiver Called", Toast.LENGTH_LONG).show();

            String action = intent.getAction();

            if(action.equals("android.intent.action.HEADSET_PLUG") && pref.getBoolean("item4",false)) {
                int state = intent.getIntExtra("state", -1);
                if (state == 0) {

                } else if(state == 1){
                    /*Toast.makeText(MyService.this,"dasdasdasdasd",Toast.LENGTH_LONG).show();
                    */Intent launchIntent;

                    SharedPreferences spf=getSharedPreferences("ab",Context.MODE_PRIVATE);
                    a=spf.getString("valuea","");
                    if(a.isEmpty())
                        launchIntent = context.getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");

                    else
                        launchIntent = context.getPackageManager().getLaunchIntentForPackage(a);



                    if (launchIntent != null) {
                        context.startActivity(launchIntent);
                    }
/*
                    if(RecyclerAdapter.a.isEmpty())
                     launchIntent = context.getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");

                    else
                     launchIntent = context.getPackageManager().getLaunchIntentForPackage(RecyclerAdapter.a);



                    if (launchIntent != null) {
                        context.startActivity(launchIntent);
                    }*/

/*

                    final PackageManager pm = getPackageManager();
                    List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);


                    for (ApplicationInfo packageInfo : packages) {

                        if (packageInfo.packageName.equals("com.sonyericsson.music")) {
                            Intent i = new Intent(Intent.ACTION_MAIN);
                            PackageManager managerclock = getPackageManager();
                            i = managerclock.getLaunchIntentForPackage("com.sonyericsson.music");
                            i.addCategory(Intent.CATEGORY_LAUNCHER);
                            startActivity(i);
                            break;

                        } else if (packageInfo.packageName.equals("com.google.android.music")) {
                            Intent i = new Intent(Intent.ACTION_MAIN);
                            PackageManager managerclock = getPackageManager();
                            i = managerclock.getLaunchIntentForPackage("com.google.android.music");
                            i.addCategory(Intent.CATEGORY_LAUNCHER);
                            startActivity(i);
                            break;
                        } else if (packageInfo.packageName.equals("com.musixmatch.android.lyrify")) {
                            Intent i = new Intent(Intent.ACTION_MAIN);
                            PackageManager managerclock = getPackageManager();
                            i = managerclock.getLaunchIntentForPackage("com.musixmatch.android.lyrify");
                            i.addCategory(Intent.CATEGORY_LAUNCHER);
                            startActivity(i);
                            break;
                        } else if (packageInfo.packageName.equals("com.mxtech.videoplayer.pro")) {
                            Intent i = new Intent(Intent.ACTION_MAIN);
                            PackageManager managerclock = getPackageManager();
                            i = managerclock.getLaunchIntentForPackage("com.mxtech.videoplayer.pro");
                            i.addCategory(Intent.CATEGORY_LAUNCHER);
                            startActivity(i);
                            break;
                        } else if (packageInfo.packageName.equals("com.google.android.youtube")) {
                            Intent i = new Intent(Intent.ACTION_MAIN);
                            PackageManager managerclock = getPackageManager();
                            i = managerclock.getLaunchIntentForPackage("com.google.android.youtube");
                            i.addCategory(Intent.CATEGORY_LAUNCHER);
                            startActivity(i);
                            break;
                        }


                    }

*/

                }
            }/*
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = context.registerReceiver(null, ifilter);*/
            Log.i("BAttery TAG ", "reached");
            if(pref.getBoolean("item6",false)){
                Log.i("BAttery TAG ", "INSIDE");
                int rawlevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale1 = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int level = -1;
                if (rawlevel >= 0 && scale1 > 0) {
                    level = (rawlevel * 100) / scale1;
                }

                scale = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

                if (scale == BatteryManager.BATTERY_STATUS_CHARGING) {
                    showNotification("Battery Charging", "Charger is Plugged in ("+level+")", 1);
                } else if (scale == BatteryManager.BATTERY_STATUS_FULL) {
                    showNotification("Battery Full", "Unplug Charger", 2);
                } else if (level < 15) {
                    showNotification("Battery is Very LOW :( ", "Plug Charger ("+level+")", 3);
                } /*else if (scale == BatteryManager.BATTERY_STATUS_DISCHARGING) {
                    showNotification("Battery Discharging", "", 4);
                }*/
            }

        }/*
        class MyPhoneStateListener extends PhoneStateListener {

            public void onCallStateChanged(int state, String incomingNumber) {

                Log.d("MyPhoneListener",state+"   incoming no:"+incomingNumber);

                if (state == 1) {

                    String msg = "New Phone Call Event. Incomming Number : "+incomingNumber;
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(getApplicationContext(), msg, duration);
                    toast.show();

                }
            }
        }*/
    };


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void showNotification(String title, String text, int number) {
        Intent intent = null;
        Uri NotificationSound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notifysnd);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setSound(NotificationSound)
                        .setDefaults(~Notification.DEFAULT_SOUND);


// Creates an explicit intent for an Activity in your app
        if (number == 3) {
          //  Toast.makeText(getApplicationContext(), "Number 3", Toast.LENGTH_LONG).show();
            String packageName = "com.android.settings";

            intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");

            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentActivities(intent, 0);

            for (ResolveInfo info : resolveInfoList)
                if (info.activityInfo.packageName.equalsIgnoreCase(packageName)) {
                    final ComponentName cn = new ComponentName(info.activityInfo.packageName, info.activityInfo.name);
                    intent.setComponent(cn);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                }

        } else {
            intent = new Intent(getApplicationContext(), MainActivity.class);
        }

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(0, mBuilder.build());
    }

    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();

    public MyService() {

    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    private void launchCall() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        String package_name = "com.android.calculator2";
        String class_name = "com.android.calculator2.Calculator";
     //   Toast.makeText(this.getApplicationContext(), "PRO2 ", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Intent.ACTION_MAIN);
        PackageManager managerclock = getPackageManager();
       /* if(RecyclerAdapter.b.isEmpty())
        i = managerclock.getLaunchIntentForPackage("com.google.android.youtube");
        else
            i = managerclock.getLaunchIntentForPackage(RecyclerAdapter.b);

        i.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivity(i);*/
        SharedPreferences spf=getSharedPreferences("ab",Context.MODE_PRIVATE);
        b=spf.getString("valueb","");
        if(b.isEmpty())
            i = managerclock.getLaunchIntentForPackage("com.google.android.youtube");
        else
            i = managerclock.getLaunchIntentForPackage(b);

        i.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivity(i);



    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
        SharedPreferences pref = getSharedPreferences("TaskIt",MODE_PRIVATE);

        if(mySensor.getType()== Sensor.TYPE_PROXIMITY && pref.getBoolean("item5",false)) {
           // Toast.makeText(this.getApplicationContext(), "PROXIMITY"+counter1, Toast.LENGTH_SHORT).show();

            if (event.values[0] < 100.0) {

                if (counter1 == 0) {
                    counter1++;
                    firstmovetime1 = System.currentTimeMillis();
                } else {
                    now1 = System.currentTimeMillis();


                    if ((now1 - firstmovetime1) < 800) {
                        counter1++;
                        firstmovetime1 = now1;
                    } else {
                        counter1 = 0;
                        now1 = 0;
                        firstmovetime1 = System.currentTimeMillis();
                        counter1++;
                    }
                    if (counter1 == 2) {
                        launchCall();
                    }

                }
            }
        }

        if(mySensor.getType()== Sensor.TYPE_PROXIMITY && pref.getBoolean("item0",false)) {
            // Toast.makeText(this.getApplicationContext(), "PROXIMITY"+counter1, Toast.LENGTH_SHORT).show();
            if ( event.values[0]==3.0 ||event.values[0]==0.0) {
                Log.d("paras", String.valueOf(event.values[0]));
                counter2++;
            }
            if(counter2>0){
                if(event.values[0]==1.0||event.values[0]==100.0) {
                    Log.d("paras", String.valueOf(event.values[0]));
                    PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);

                    boolean isScreenOn = pm.isScreenOn();



                    if (isScreenOn == false ) {


                        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "MyLock");

                        wl.acquire(10000);
                        PowerManager.WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyCpuLock");

                        wl_cpu.acquire(10000);

                    }
                    counter2=0;
                }
            }


        }
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            // Log.e("Accelerometer", "X"+x+" Y"+y+" Z"+z);


            {   mAccelLast=mAccelCurrent;
                mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
                float delta = mAccelCurrent - mAccelLast;
                mAccel = mAccel * 0.9f + delta;

                if (mAccel > 5) {
                    if (counter == 0) {
                        counter++;
                        firstmovetime = System.currentTimeMillis();
                    } else {
                        now = System.currentTimeMillis();
                        if ((now - firstmovetime) < 400) {
                            counter++;
                            firstmovetime = now;
                        } else {
                            counter = 0;
                            now = 0;
                            firstmovetime = System.currentTimeMillis();
                            counter++;
                        }
                        if (counter == 2 && pref.getBoolean("item3",false)) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                                try {
                                    mCameraId = mCameraManager.getCameraIdList()[0];
                                } catch (CameraAccessException e) {
                                    e.printStackTrace();
                                }

                                if (islight == true) {
                                    try {
                                        mCameraManager.setTorchMode(mCameraId, true);
                                    } catch (CameraAccessException e) {
                                        e.printStackTrace();
                                    }
                                    islight = false;
                                } else {
                                    try {
                                        mCameraManager.setTorchMode(mCameraId, false);
                                    } catch (CameraAccessException e) {
                                        e.printStackTrace();
                                    }
                                    islight = true;
                                }


                            } else
                            {
                                if (islight == true) {

                                    if (getApplicationContext().getPackageManager()
                                            .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {


                                        mCamera = Camera.open();
                                        Parameters parameters = mCamera.getParameters();
                                        parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
                                        mCamera.setParameters(parameters);
                                        mCamera.startPreview();
                                        islight = false;

                                    } else {
                                        AlertDialog alert = new AlertDialog.Builder(this).create();
                                        alert.setTitle("Error");
                                        alert.setMessage("Sorry, your device doesn't support flash light!");
                                        alert.show();
                                    }
                                } else {
                                    mCamera.stopPreview();
                                    mCamera.release();
                                    islight = true;
                                }
                            }
                        }
                    }


                }
            }

            if(z>0)
            {  flag=0;}

            if(z<0 && flag==0)//  && pref.getBoolean("item2",false))

            {
                AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                if(audioManager.isMusicActive() && !audioManager.isWiredHeadsetOn() )
                {                     audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                  //  Toast.makeText(this.getApplicationContext(), "Flipped ", Toast.LENGTH_SHORT).show();


                }


                flag=1;
            }

          /*  if((y>7||x>7||x<-7) && pref.getBoolean("item0",false))

            {
                // Toast.makeText(this.getApplicationContext(), "now ", Toast.LENGTH_SHORT).show();


                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);

                boolean isScreenOn = pm.isScreenOn();

                Log.i("screen on.", "" + isScreenOn);

                if (isScreenOn == false) {

                    PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "MyLock");

                    wl.acquire(10000);
                    PowerManager.WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyCpuLock");

                    wl_cpu.acquire(10000);
                }

            }*/

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction("android.intent.action.HEADSET_PLUG");
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
        registerReceiver(batteryLevelReceiver, filter);



        /*final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            Log.d("Packages", "" + packageInfo.packageName);
        }*/
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // For time consuming an long tasks you can launch a new thread here...
        /*IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevelReceiver, batteryLevelFilter);*/

     //   Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);

      /*Timer timer  =  new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run()
            {   //Toast.makeText(getApplicationContext(), "Browser is running", Toast.LENGTH_LONG).show();




                ActivityManager activityManager = (ActivityManager) getSystemService( ACTIVITY_SERVICE );
                List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
                for(int i = 0; i < procInfos.size(); i++)
                {
                    if(procInfos.get(i).processName.equals("com.google.android.youtube")||procInfos.get(i).processName.equals("com.android.Chrome"))
                    {
                        //Toast.makeText(getApplicationContext(), "Browser is running", Toast.LENGTH_LONG).show();

                        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                            wifiManager.setWifiEnabled(true);





                    }
                }
                ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                List< ActivityManager.RunningTaskInfo > runningTaskInfo = manager.getRunningTasks(1);

                ComponentName componentInfo = runningTaskInfo.get(0).topActivity;
                if(componentInfo.getPackageName().equals("com.android.chrome")) {
                    WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                    wifiManager.setWifiEnabled(true);


                }


            }
        }, 20000, 3000);


*/

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        sensorManager.unregisterListener(this);
        unregisterReceiver(batteryLevelReceiver);

       // Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

}

