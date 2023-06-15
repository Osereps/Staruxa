package com.example.app20kotlin

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app20kotlin.sampledata.ListTwo
import com.example.app20kotlin.sampledata.Users
import com.example.app20kotlin.sampledata.Zametki
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Suppress("DEPRECATION")
class MainActivity3 : AppCompatActivity(),RecyclerViewInterface {
    lateinit var listTwo: ListTwo
    private var txt: TextView?=null
    private var ed: EditText?=null
    private var list: RecyclerView?=null
    private var btn4: Button?=null
    private val db = FirebaseFirestore.getInstance()
    private val zametkiRef = db.collection("zametki")
    private val auth = FirebaseAuth.getInstance()
    var zam: ArrayList<Zametki>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        txt = findViewById(R.id.uid) as TextView
        btn4 = findViewById(R.id.btn4) as Button
        list = findViewById(R.id.list) as RecyclerView
        ed = findViewById(R.id.zamet) as EditText
        val arguments = intent.extras
        txt!!.text = arguments!!["uid"].toString()
        Search()
        var adapter = RecycletAdapter(this, this, zam) as RecycletAdapter
        list!!.layoutManager = LinearLayoutManager(this)
        list!!.adapter = adapter
        list!!.setHasFixedSize(true)

        btn4!!.setOnClickListener {
            Onksd()
        }
    }
    fun Search() {
        zam = java.util.ArrayList()
        zametkiRef.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        if (document.getString("users_Key") ==  txt!!.text.toString()) {
                            val newZam = Zametki(
                                document.id,
                                document.getString("users_Key")!!,
                                document.getString("zametki_Text")!!
                            )
                            zam!!.add(newZam)
                        }
                    }
                } else {
                    Log.d(ContentValues.TAG, "Error getting documents: ", task.exception)
                }
            }
    }
    fun Onksd(){
        val data = hashMapOf(
            "users_Key" to txt!!.text.toString(),
            "zametki_Text" to ed!!.text.toString()
        )
        zametkiRef
            .add(data)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Nice", Toast.LENGTH_SHORT).show()
                Search()
                var adapter = RecycletAdapter(this, this, zam)
                list!!.adapter = adapter
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    this,
                    e.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

    }
    override fun onItemClick(position: Int) {
        val i = Intent(this, MainActivity4::class.java)
        i.putExtra("id", zam!![position].getID())
        i.putExtra("key", zam!![position].getUsers_Key())
        i.putExtra("txt", zam!![position].getZametki_Text())
        startActivity(i)
    }
}