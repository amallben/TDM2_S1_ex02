package com.example.interventions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.interventions.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_view.*
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val l = ArrayList<Intervention>()
    private var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        val date = findViewById<EditText>(R.id.editsearch)
        floatingButtonAdd.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(this@MainActivity, AddIntervention::class.java)
                startActivity(intent)
            }
        })
        search.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                Toast.makeText(applicationContext, date.text.toString(), Toast.LENGTH_LONG).show()
                getInterventionBydate(date.text.toString())
            }
        })
        lireDonnees()
    }

    private fun lireDonnees() {
        val i = ArrayList<Intervention>()

        try {
            var gson = Gson()
            val stream = openFileInput("fichier.json")
            val text = AppTools.lire(stream)
            val t = Gson().fromJson(text, Array<Intervention>::class.java)
            stream.close()

            val adapter = InterventionAdapter(this@MainActivity, t)
            recyclerView!!.adapter = adapter


        } catch (ex: FileNotFoundException) {
            AppTools.alertbox(this, "fichier non trouvé")
        } catch (ex: IOException) {
            AppTools.alertbox(this, "erreur de lecture")

        }

    }

    private fun getInterventionBydate(date: String) {
        var i = Array<Intervention>(2){Intervention("","","","")}
        var j = 0
        var gson = Gson()
        val stream = openFileInput("fichier.json")
        val text = AppTools.lire(stream)
        val t = Gson().fromJson(text, Array<Intervention>::class.java)
        stream.close()
        for (element in t) {
            if (element.date == date) {
                i[j] = element
                j++
            }
        }
            val adapter = InterventionAdapter(this@MainActivity, i)
            recyclerView!!.adapter = adapter




    }
}
