package com.example.app19;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity3 extends AppCompatActivity {
    TextView txt;
    Button btn;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btn = findViewById(R.id.button6);

        txt = findViewById(R.id.textView);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            String pass = user.getUid();
            txt.append(email+"\nToken:\n");
            txt.append(pass+"\n");
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.getInstance().signOut();
                Intent i = new Intent(MainActivity3.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}