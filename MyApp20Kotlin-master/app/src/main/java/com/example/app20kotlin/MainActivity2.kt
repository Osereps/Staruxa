package com.example.app20kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app20kotlin.sampledata.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity2 : AppCompatActivity() {
    private var ed1: EditText?=null
    private  var ed2:EditText?=null
    private var btn3: Button?=null
    private  var btn4:Button?=null
    private val db = FirebaseFirestore.getInstance()
    private val userRef = db.collection("users")
    private val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        ed1 = findViewById(R.id.editText3)
        ed2 = findViewById(R.id.editText4)
        btn3 = findViewById(R.id.button3)
        btn4 = findViewById(R.id.button4)

        btn3!!.setOnClickListener {
            IntoUsers(ed1!!.text.toString(), ed2!!.text.toString())
        }
        btn4!!.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
    fun IntoUsers(email: String?, password: String?) {
        auth.createUserWithEmailAndPassword(email!!, password!!)
            .addOnSuccessListener {
                val user = auth.currentUser
                val newuser = hashMapOf(
                    "email" to email,
                    "key" to user!!.uid!!,
                    "Password" to password!!
                )
                userRef.add(newuser)
                    .addOnSuccessListener(this) {
                        val i = Intent(this, MainActivity::class.java)
                        startActivity(i)
                        finish()
                    }
                    .addOnFailureListener(this) { e ->
                        Toast.makeText(
                            this,
                            e.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }.addOnFailureListener(this) { e ->
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
    }
}