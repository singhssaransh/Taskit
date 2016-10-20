package com.example.mydell.taskit;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class Forum extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user1;
    private Message u;
     private  int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        auth =FirebaseAuth.getInstance();

    }
   public void abcd(View v)
   {

       if(v.getId()==R.id.button2) {
           FirebaseAuth.getInstance().signOut();
           finish();
           Intent nextScree = new Intent(getApplicationContext(), Main3Activity.class);
           startActivity(nextScree);
       }
       if(v.getId()==R.id.button5)
       {

           finish();
           Intent nextScree = new Intent(getApplicationContext(), Chat.class);
           startActivity(nextScree);


       }

   }


}
