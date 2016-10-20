package com.example.mydell.taskit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;


public class Login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private FirebaseAuth auth;
    private Button signin;

    private ProgressDialog p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=(EditText)findViewById(R.id.editText2);
        password=(EditText)findViewById(R.id.editText);
        auth =FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!= null)
        {
            finish();

            Intent nextScree = new Intent(this , Chat.class);
            startActivity(nextScree);
        }
        p= new ProgressDialog(this);





    }
    public void signin(View view)
    {        final  String email1=email.getText().toString().trim();
             final String password1=password.getText().toString().trim();


        if (TextUtils.isEmpty(email1)) {
        Toast.makeText(Login.this, "email id required", Toast.LENGTH_SHORT).show(); return;}

        else if (TextUtils.isEmpty(password1)) {
            Toast.makeText(Login.this, "password required", Toast.LENGTH_SHORT).show(); return;}
        else if(!email1.contains("@") )
        {
            Toast.makeText(Login.this, "Invalid email", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!email1.contains(".com") )
        {
            Toast.makeText(Login.this, "Invalid email", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(email1.indexOf('@')>email1.indexOf(".com") )
        {
            Toast.makeText(Login.this, "Invalid email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(email1.indexOf(".com")-email1.indexOf('@')==1 )
        {
            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
            return;
        }

        else if (password1.length()<6) {
            Toast.makeText(Login.this, "password too short", Toast.LENGTH_SHORT).show();
            return;
        }


        p.setMessage("Signing in");
        p.show();
        auth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    p.dismiss();
                    finish();
                    Intent nextScree = new Intent(getApplicationContext(),Chat.class);
                    startActivity(nextScree);

                }

                if(!task.isSuccessful()) {
                    Toast.makeText(Login.this, "Cannot login", Toast.LENGTH_SHORT).show();

                p.dismiss();



                    return;
                }

            }


        });



    }



    public void signup(View view)
    {
        finish();
        Intent next=new Intent(this,SignUp.class);
        startActivity(next);


    }




}


