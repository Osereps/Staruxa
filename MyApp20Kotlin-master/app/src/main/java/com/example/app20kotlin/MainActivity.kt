package com.example.app20kotlin

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private var ed1: EditText?=null
    private  var ed2:EditText?=null
    private var btn1: Button?=null
    private  var btn2:Button?=null
    var uid: String?=null
    private val auth = FirebaseAuth.getInstance()
    private var user: FirebaseUser?=null
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var userRef = db.collection("users")
        ed1 = findViewById(R.id.editText1)
        ed2 = findViewById(R.id.editText2)
        btn1 = findViewById(R.id.button1)
        btn2 = findViewById(R.id.button2)

        btn1!!.setOnClickListener{
            val VV: Boolean = Valid(ed1!!.text.toString(), ed2!!.text.toString())
            if (VV) {
                auth.signInWithEmailAndPassword(ed1!!.text.toString(), ed2!!.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            user = task.result.user!!
                            uid = user!!.uid
                            val i = Intent(this, MainActivity3::class.java)
                            i.putExtra("uid", uid)
                            startActivity(i)
                        } else {
                            Toast.makeText(
                                this,
                                "User logged in unsuccessful",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }
        btn2!!.setOnClickListener {
            val i = Intent(this, MainActivity2::class.java)
            startActivity(i)
        }
    }
    private fun Valid(email: String, pass: String): Boolean {
        return if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "The mail is incorrect", Toast.LENGTH_LONG).show()
            false
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Password is empty", Toast.LENGTH_LONG).show()
            false
        } else if (pass.length < 4) {
            Toast.makeText(this, "Password less than 4 characters ", Toast.LENGTH_LONG)
                .show()
            false
        } else {
            true
        }
    }
}