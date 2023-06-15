package com.example.app19;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {
    Button bt1,bt2;
    EditText em1,pass1;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = findViewById(R.id.button);
        bt2 = findViewById(R.id.button3);
        em1=findViewById(R.id.editTextTextEmailAddress2);
        pass1=findViewById(R.id.editTextTextPassword2);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            Intent i = new Intent(MainActivity.this,MainActivity3.class);
            startActivity(i);
        }
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail,pass;
                mail=em1.getText().toString();
                pass=pass1.getText().toString();
                boolean VV = Valid(mail,pass);
                if(VV) {
                    firebaseAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "User logged in successful", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(MainActivity.this, MainActivity3.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(MainActivity.this, "User logged in unsuccessful", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);
                finish();
            }
        });
    }
    private  boolean Valid(String email,String pass){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(MainActivity.this, "The mail is incorrect", Toast.LENGTH_LONG).show();
            return false;
        }else if (TextUtils.isEmpty(pass)){
            Toast.makeText(MainActivity.this, "Password is empty", Toast.LENGTH_LONG).show();
            return false;
        }else if(pass.length()<4) {
            Toast.makeText(MainActivity.this, "Password less than 4 characters ", Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }
}