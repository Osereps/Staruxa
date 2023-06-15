package com.example.app19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {
    Button bt1,bt2;
    EditText em2,pass2;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bt2 = findViewById(R.id.button2);
        bt1 = findViewById(R.id.button4);
        em2=findViewById(R.id.editTextTextEmailAddress3);
        pass2=findViewById(R.id.editTextTextPassword3);

        firebaseAuth = FirebaseAuth.getInstance();

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail,pass;
                mail=em2.getText().toString();
                pass=pass2.getText().toString();
                boolean VV = Valid(mail,pass);
                if(VV){
                firebaseAuth.createUserWithEmailAndPassword(mail,pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                String currectUserEmail = firebaseUser.getEmail();
                                Toast.makeText(MainActivity2.this,"User: "+currectUserEmail+" is created",Toast.LENGTH_LONG).show();
                                Intent i = new Intent(MainActivity2.this,MainActivity3.class);
                                startActivity(i);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity2.this,e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                }
            }

        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    private  boolean Valid(String email,String pass){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(MainActivity2.this, "The mail is incorrect", Toast.LENGTH_LONG).show();
            return false;
        }else if (TextUtils.isEmpty(pass)){
            Toast.makeText(MainActivity2.this, "Password is empty", Toast.LENGTH_LONG).show();
            return false;
        }else if(pass.length()<4) {
            Toast.makeText(MainActivity2.this, "Password less than 4 characters ", Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }
}