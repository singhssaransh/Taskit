package com.example.mydell.taskit;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.logging.SocketHandler;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    View view;
    Context context;
    static public String a="";
    static public String b="";
    boolean check;

    public RecyclerAdapter(Context context,View view) {
        this.context = context;
        this.view = view;
    }



    private String[] titles = {"Tilt to Wake",
            "Call block",
            "Flip to Silence",
            "Flashlight Shake",
            "Launch Application",
            "Launch Application",
            "Battery Level"
           };

    public String[] details = {"Wake your device when you tilt your phone",
            "Block Incoming Calls", "Flip the device to silent any media",
            "Turn on/off Torch ", "Headset Plugged in",
            "On wave of your hand", "Notification of battery"
          };

    private int[] images = {R.drawable.t1,
            R.drawable.t2,
            R.drawable.t3,
            R.drawable.t4,
            R.drawable.t5,
            R.drawable.t6,
            R.drawable.t7
          };

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;
         int counter=0;
        Context context;
         String[] newArray = new String[1000];
         String[] par=new String[10000];
       public   Integer[] pa=new Integer[1000];
       public  int kl=0;
        Switch switch1;
        final static String tag="paras";
        public ViewHolder(View itemView,final Context context) {
            super(itemView);
            this.context = context;


            itemImage = (ImageView) itemView.findViewById(R.id.item_image);
            itemTitle = (TextView) itemView.findViewById(R.id.item_title);
            itemDetail = (TextView) itemView.findViewById(R.id.item_detail);
            this.switch1 = (Switch) itemView.findViewById(R.id.switch1);

            getItemId();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if(getLayoutPosition()==4||getLayoutPosition()==5)
                     {
                        PopupMenu popMenu = new PopupMenu(context, v, Gravity.CENTER);
                        final PackageManager pm = context.getPackageManager();
                        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

                        for (ApplicationInfo packageInfo : packages)

                        {
                            if (pm.getLaunchIntentForPackage(packageInfo.packageName) != null &&

                                    !pm.getLaunchIntentForPackage(packageInfo.packageName).equals(""))


                            {

                                newArray[counter] = packageInfo.packageName;
                                popMenu.getMenu().add(Menu.NONE, ++counter, counter, pm.getApplicationLabel(packageInfo));
                            }
                        }
                        Log.i("POP UP MENU", " OPEN");
                        popMenu.setGravity(Gravity.CENTER);
                        popMenu.show();
                        popMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                if(getLayoutPosition()==4)
                                {a = newArray[item.getItemId() - 1];
                                    SharedPreferences spf=  context.getSharedPreferences("ab",Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor= spf.edit();
                                    editor.putString("valuea",a);
                                    editor.apply();}
                                if(getLayoutPosition()==5)
                                {b=newArray[item.getItemId()-1];
                                    SharedPreferences spf=  context.getSharedPreferences("ab",Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor= spf.edit();
                                    editor.putString("valueb",b);
                                    editor.apply();}
                                Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(a);
                                if (launchIntent != null) {
                                    SharedPreferences preferences = context.getSharedPreferences("TaskIt", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("OpenActivity", newArray[item.getItemId() - 1]);
                                    editor.apply();
                                }

                /*String a;
                a=newArray[item.getItemId()-1];
                Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(a);
                if (launchIntent != null) {
                    context.startActivity(launchIntent);
                }*/

                                return true;
                            }
                        });
                    }
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardcr, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v,context);
        return viewHolder;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        SharedPreferences preferences = context.getSharedPreferences("TaskIt", Context.MODE_PRIVATE);

        boolean[] switches = {preferences.getBoolean("item0", false),
                preferences.getBoolean("item1", false),
                preferences.getBoolean("item2", false),
                preferences.getBoolean("item3", false),
                preferences.getBoolean("item4", false),
                preferences.getBoolean("item5", false),
                preferences.getBoolean("item6", false),
               };
        viewHolder.itemTitle.setText(titles[i]);
        viewHolder.itemDetail.setText(details[i]);
        viewHolder.itemImage.setImageResource(images[i]);
        viewHolder.switch1.setChecked(switches[i]);
/*

        viewHolder.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences preferences = context.getSharedPreferences("TaskIt", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                CompoundButton c = buttonView;
                if (c.isChecked()) {
                    switch (i) {
                        case 0:
                            Log.i("TAG", "" + i);
                            editor.putBoolean("item0", true);
                            editor.apply();

                            break;
                        case 1:
                            Log.i("TAG", "" + i);
                            editor.putBoolean("item1", true);
                            editor.apply();
                            break;
                        case 2:
                            Log.i("TAG", "" + i);
                            editor.putBoolean("item2", true);
                            editor.apply();
                            break;
                        case 3:
                            Log.i("TAG", "" + i);
                            editor.putBoolean("item3", true);
                            editor.apply();
                            break;
                        case 4:
                            Log.i("TAG", "" + i);
                            editor.putBoolean("item4", true);
                            editor.apply();
                            break;
                        case 5:
                            Log.i("TAG", "" + i);
                            editor.putBoolean("item5", true);
                            editor.apply();
                            break;
                        case 6:
                            Log.i("TAG", "" + i);
                            editor.putBoolean("item6", true);
                            editor.apply();
                            break;
                        case 7:
                            Log.i("TAG", "" + i);
                            editor.putBoolean("item7", true);
                            editor.apply();
                            break;

                        default:

                    }
                }else{
                    switch (i) {
                        case 0:
                            Log.i("TAG", "" + i);
                            editor.putBoolean("item0", false);
                            editor.apply();
                            break;
                        case 1:
                            Log.i("TAG", "" + i);
                            editor.putBoolean("item1", false);
                            editor.apply();
                            break;
                        case 2:
                            Log.i("TAG", "" + i);
                            editor.putBoolean("item2", false);
                            editor.apply();
                            break;
                        case 3:
                            Log.i("TAG", "" + i);
                            editor.putBoolean("item3", false);
                            editor.apply();
                            break;
                        case 4:
                            Log.i("TAG", "" + i);
                            editor.putBoolean("item4", false);
                            editor.apply();
                            break;
                        case 5:
                            Log.i("TAG", "" + i);
                            editor.putBoolean("item5", false);
                            editor.apply();
                            break;
                        case 6:
                            Log.i("TAG", "" + i);
                            editor.putBoolean("item6", false);
                            editor.apply();
                            break;
                        case 7:
                            Log.i("TAG", "" + i);
                            editor.putBoolean("item7", false);
                            editor.apply();
                            break;

                        default:
                }
            }
        }
        });*/
        viewHolder.switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("fdsfasdfadsfasdfadsff",i+"");

                SharedPreferences preferences = context.getSharedPreferences("TaskIt", 0);
                SharedPreferences.Editor editor = preferences.edit();

        /*String b = v.toString();
        b = b.replace("android.widget.Switch{","");
        b = b.replace(" VFED..C. ...P..ID 742,297-882,378 #7f0d0086 app:id/switch1}","");*/
                switch (i) {
                    case 0:
                        if(!preferences.getBoolean("item0",false)) {
                            editor.putBoolean("item0", true);
                            editor.apply();
                        }else{
                            editor.putBoolean("item0", false);
                            editor.apply();
                        }
                        Log.i("TAG"+i, "" + preferences.getBoolean("item0", false));
                        break;
                    case 1:
                        if(!preferences.getBoolean("item1",false)) {
                            editor.putBoolean("item1", true);
                            editor.apply();
                        }else{
                            editor.putBoolean("item1", false);
                            editor.apply();
                        }
                        Log.i("TAG"+i, "" + preferences.getBoolean("item1", false));
                        break;
                    case 2:
                        if(!preferences.getBoolean("item2",false)) {
                            editor.putBoolean("item2", true);
                            editor.apply();
                        }else{
                            editor.putBoolean("item2", false);
                            editor.apply();
                        }
                        Log.i("TAG"+i, "" + preferences.getBoolean("item2", false));
                        break;
                    case 3:
                        if(!preferences.getBoolean("item3",false)) {
                            editor.putBoolean("item3", true);
                            editor.apply();
                        }else{
                            editor.putBoolean("item3", false);
                            editor.apply();
                        }
                        Log.i("TAG"+i, "" + preferences.getBoolean("item3", false));
                        break;
                    case 4:
                        if(!preferences.getBoolean("item4",false)) {
                            editor.putBoolean("item4", true);
                            editor.apply();
                        }else{
                            editor.putBoolean("item4", false);
                            editor.apply();
                        }
                        Log.i("TAG"+i, "" + preferences.getBoolean("item4", false));
                        break;
                    case 5:
                        if(!preferences.getBoolean("item5",false)) {
                            editor.putBoolean("item5", true);
                            editor.apply();
                        }else{
                            editor.putBoolean("item5", false);
                            editor.apply();
                        }
                        Log.i("TAG"+i, "" + preferences.getBoolean("item5", false));
                        break;
                    case 6:
                        if(!preferences.getBoolean("item6",false)) {
                            editor.putBoolean("item6", true);
                            editor.apply();
                        }else{
                            editor.putBoolean("item6", false);
                            editor.apply();
                        }
                        Log.i("TAG"+i, "" + preferences.getBoolean("item6", false));
                        break;
                    case 7:
                        if(!preferences.getBoolean("item7",false)) {
                            editor.putBoolean("item7", true);
                            editor.apply();
                        }else{
                            editor.putBoolean("item7", false);
                            editor.apply();
                        }
                        Log.i("TAG"+i, "" + preferences.getBoolean("item7", false));
                        break;

                    default:
                        break;
                }
            }
        });
    }

    public void menu(View view){

    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    /*public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        Switch switch1;
        MyViewHolder(View view){
            super(view);
            this.textView= (TextView) view.findViewById(R.id.item_title);
            this.switch1= (Switch) view.findViewById(R.id.switch1);
            //... do same for other Button
        }
    }*/
    }

