package com.example.mydell.taskit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUp extends AppCompatActivity {

    private Button signup;
    private EditText email;
    private EditText password;
    private ProgressDialog p;
    private FirebaseAuth auth;
    private EditText name;

    public void login (View view)
    {   finish();
        Intent nextScreen;
        nextScreen = new Intent(this , Login.class);
        startActivity(nextScreen);



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signup= (Button)findViewById(R.id.button3);
        email=(EditText)findViewById(R.id.editText5);
        password=(EditText)findViewById(R.id.editText6);
        name=(EditText)findViewById(R.id.editText8);

        p= new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!= null)
        {finish();
            Intent nextScree = new Intent(this ,Chat.class);
            startActivity(nextScree);
        }
    }
    public void signupuse(View view )
    { final  String email1=email.getText().toString().trim();
        final String password1=password.getText().toString().trim();
        final  String name1=name.getText().toString().trim();

        if(TextUtils.isEmpty(name1))

        {
            Toast.makeText(this,"Please Enter Name",Toast.LENGTH_SHORT).show();
            return;

        }

        if(TextUtils.isEmpty(email1))
        {
            Toast.makeText(this,"Please Enter Email",Toast.LENGTH_SHORT).show();
            return;

        }


        if(TextUtils.isEmpty(password1))
        {
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;

        }


        if(email1.indexOf('@')<1)
        {
            Toast.makeText(this,"Please Enter A Valid E-mail",Toast.LENGTH_SHORT).show();
            return;


        }
        if(!email1.contains(".com") )
        {
            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
            return;
        }
         if(email1.indexOf('@')>email1.indexOf(".com") )
        {
            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
            return;
         }
        if(email1.indexOf(".com")-email1.indexOf('@')==1 )
        {
            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(password1.length()<6)
        {
            Toast.makeText(this,"Password too Short",Toast.LENGTH_SHORT).show();
            return;

        }



        p.setMessage("Registering User");
        p.show();
        auth.createUserWithEmailAndPassword(email1,password1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {         Firebase.setAndroidContext(SignUp.this);



                            p.dismiss();
                            Toast.makeText(SignUp.this,"Registration Successful",Toast.LENGTH_SHORT).show();

                            finish();
                            Intent nextScree = new Intent(getApplicationContext() , Chat.class);
                            startActivity(nextScree);




                        }else
                        {  p.dismiss();
                            Toast.makeText(SignUp.this,"Registration UnSuccessful",Toast.LENGTH_SHORT).show();
                        }
                    }
                });




    }

}

