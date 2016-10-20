package com.example.mydell.taskit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread timer=new Thread()
        {
            public void run()
            {
                try{
                        sleep(3000);
                }catch (InterruptedException e){e.printStackTrace();}
                finally {
                    Intent ourintent=new Intent("com.example.mydell.taskit");
                    startActivity(ourintent);

                }
            }

        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
