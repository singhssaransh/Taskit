package com.example.mydell.taskit;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Chat extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseUser user1;
    private Message u;
    private  int count=0;
    EditText msg;
    String g;
    private int count2;
    ProgressDialog p;
   /* ListView mainList ;
    ArrayAdapter<String> listAdapter ;
   */ private int a=1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.logout, menu);  return true; }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_settings)
        {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent nextScree = new Intent(getApplicationContext(), Main3Activity.class);
            startActivity(nextScree);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        auth =FirebaseAuth.getInstance();
       msg=(EditText)findViewById(R.id.editText3);
        /*ArrayList<String> planet = new ArrayList<String>();

        listAdapter = new ArrayAdapter<String>(this,R.layout.simple1, planet);
        listAdapter.clear();
        mainList=(ListView)findViewById(R.id.listView2);
        mainList.setAdapter( listAdapter );

        */
        final ChatArrayAdapter chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.item_send);
        final ListView listView = (ListView) findViewById(R.id.listView2);
        listView.setAdapter(chatArrayAdapter);
        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(chatArrayAdapter);
        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });



        p=new ProgressDialog(this) ;
        p.setMessage("Loading messages");
        Firebase.setAndroidContext(getApplicationContext());
        user1 = auth.getCurrentUser();
        if(user1!=null) {




            Firebase ref = new Firebase("https://task-it-b48d0.firebaseio.com/Message");
            count2=0;
           // p.show();

            ref.child(user1.getUid()).addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                    count2++;
                    Message post = snapshot.getValue(Message.class);
                  if(post.getsender()!=null) {
                      if (post.getsender().equals(user1.getUid()))
                          chatArrayAdapter.add(new ChatMessage(true, post.getmessage()));
                      else
                          chatArrayAdapter.add(new ChatMessage(false, post.getmessage()));
                  }
                                chatArrayAdapter.notifyDataSetChanged();
                        // listAdapter.add(post.getmessage() );

                   // listAdapter.notifyDataSetChanged();
             /*       if (snapshot.getChildrenCount()==0) {
                       p.dismiss();
                    }
                    if(count2 >= snapshot.getChildrenCount()){
                        p.dismiss();
                    }
*/
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {


                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());
                p.dismiss();
                }
            });



        }





    }


   public void abc(View v)
    {  g=msg.getText().toString();
         int flag3=0;
         for(int o=0;o<g.length();o++)
         {
             if(g.charAt(o)!=' ')
             {flag3=1;}

         }
         if(g.isEmpty() || flag3==0) {}
        else {
        count = 1;
        Firebase.setAndroidContext(getApplicationContext());
        user1 = auth.getCurrentUser();
        if (user1 != null) {
            Firebase ref = new Firebase("https://task-it-b48d0.firebaseio.com/Message");

            ref.child(user1.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        Message post = postSnapshot.getValue(Message.class);
                        count++;

                    }
                    Firebase ref1 = new Firebase("https://task-it-b48d0.firebaseio.com/Message");


                    u = new Message();
                    u.setMessage("" + msg.getText().toString().trim());
                    u.setSender("" + user1.getUid());
                    ref1.child(user1.getUid()).child("" + count).setValue(u);
                   // Toast.makeText(getApplicationContext(), "sent" + count, Toast.LENGTH_LONG).show();
                    msg.setText(" ");
                    //listAdapter.notifyDataSetChanged();
                    count = 1;
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            });
        }


    }



    }


}
