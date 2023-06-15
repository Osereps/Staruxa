package com.example.app20kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.app20kotlin.sampledata.ListTwo
import com.example.app20kotlin.sampledata.Zametki
import com.google.firebase.firestore.FirebaseFirestore

@Suppress("DEPRECATION")
class MainActivity4 : AppCompatActivity() {
    private var ed: EditText? = null
    private val db = FirebaseFirestore.getInstance()
    private val zametkiRef = db.collection("zametki")
    var zam: ArrayList<Zametki>? = null
    private var btn2: Button? = null
    private  var btn3:Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val arguments = intent.extras
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        ed = findViewById(R.id.edtext)
        ed!!.setText(arguments!!["txt"].toString())

        btn2!!.setOnClickListener(View.OnClickListener {
            val data = hashMapOf(
                "users_Key" to arguments["key"].toString(),
                "zametki_Text" to ed!!.text.toString()
            )
            zametkiRef
                .document(arguments["id"].toString())
                .set(data)
                .addOnSuccessListener {
                    val i = Intent(this, MainActivity3::class.java)
                    startActivity(i)
                    finish()
                }
        })
        btn3!!.setOnClickListener(View.OnClickListener {
            zametkiRef
                .document(arguments["id"].toString())
                .delete()
                .addOnSuccessListener {
                    val i = Intent(this, MainActivity3::class.java)
                    startActivity(i)
                    finish()
                }
        })

    }
}