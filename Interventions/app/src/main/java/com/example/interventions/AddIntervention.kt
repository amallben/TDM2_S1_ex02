package com.example.interventions

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.interventions.R
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_view.*
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.Writer
import java.nio.file.Paths

class AddIntervention : AppCompatActivity() {
    private val list= ArrayList<Intervention>()
    private val fichier = "fichier.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        button_save.setOnClickListener {
            writeJSONtoFile()
            startActivity(Intent(applicationContext, MainActivity::class.java))
            Toast.makeText(applicationContext, "Saved", Toast.LENGTH_LONG).show()

        }
    }
    private fun writeJSONtoFile(){
        val i : Array<Intervention>
      i=lireDonnees()
        val numVal = num!!.text.toString()
        val nomVal = nom!!.text.toString()
        val typeVal = type!!.text.toString()
        val dateVal = date!!.text.toString()
        val intervention=Intervention(numVal,nomVal,typeVal,dateVal)
        list.add(intervention)
        for(element in i)
            list.add(element)
        var gson= Gson()
        var jsonString:String = gson.toJson(list)
        sauvegarder(jsonString)



    }
    private fun sauvegarder(string: String) {

        try {

            val stream = openFileOutput(fichier, Context.MODE_PRIVATE)
            stream.write(string.toByteArray())
            stream.close()
            AppTools.showToast(this, "Données sauvegardées")
        } catch (e: FileNotFoundException) {
            AppTools.alertbox(this, "fichier non trouvé")
        } catch (e: IOException) {
            AppTools.alertbox(this, "erreur d'écriture")
        }


        }
   private fun lireDonnees():Array<Intervention> {
           var gson = Gson()
           val stream = openFileInput(fichier)
           Toast.makeText(applicationContext, "helooo", Toast.LENGTH_LONG).show()
           val text = AppTools.lire(stream)
           val t = Gson().fromJson(text, Array<Intervention>::class.java)
           stream.close()
           return t


   }
}


