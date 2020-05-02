package com.example.interventions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.interventions.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_update.*
import java.io.FileNotFoundException
import java.io.IOException

class UpdateInterventionActivity: AppCompatActivity() {
    private val liste= ArrayList<Intervention>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        val i = intent.getSerializableExtra("Intervention") as Intervention
        loadIntervention(i)
        button_update.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View) {
                Toast.makeText(getApplicationContext(), "Mis à jour", Toast.LENGTH_LONG).show()
                updateIntervention(i)
                startActivity(Intent(this@UpdateInterventionActivity, MainActivity::class.java))
            }
        })
        button_delete.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                deleteIntervention(i)
                startActivity(Intent(this@UpdateInterventionActivity, MainActivity::class.java))
            }
        })
    }
    private fun loadIntervention(i:Intervention) {
        editNom.setText(i.nom)
        editNum.setText(i.numero)
        editType.setText(i.type)
        editdate.setText(i.date)
    }

    private fun updateIntervention(i: Intervention){
        lireDonnees(i)
        val n=lireDonnee(i)
        val numVal = editNum!!.text.toString()
        val nomVal = editNom!!.text.toString()
        val typeVal = editType!!.text.toString()
        val dateVal = editdate!!.text.toString()
         n.nom=nomVal
         n.numero=numVal
         n.type=typeVal
         n.date=dateVal
        liste.add(n)
        var gson= Gson()
        var jsonString:String = gson.toJson(liste)
        sauvegarder(jsonString)
    }
    private fun deleteIntervention(i: Intervention) {
        lireDonnees(i)
        var gson= Gson()
        var jsonString:String = gson.toJson(liste)
        sauvegarder(jsonString)
    }

    private fun sauvegarder(string: String) {

        try {

            val stream = openFileOutput("fichier.json", Context.MODE_PRIVATE)
            stream.write(string.toByteArray())
            stream.close()
            AppTools.showToast(this, "Données sauvegardées")
        } catch (e: FileNotFoundException) {
            AppTools.alertbox(this, "fichier non trouvé")
        } catch (e: IOException) {
            AppTools.alertbox(this, "erreur d'écriture")
        }


    }
    private fun lireDonnee(i:Intervention):Intervention{
        var j=Intervention("","","","")
        var gson = Gson()
        val stream = openFileInput("fichier.json")
        val text = AppTools.lire(stream)
        val t = Gson().fromJson(text, Array<Intervention>::class.java)
        stream.close()
        for(element in t)
        {if (element.numero==i.numero)
            j=element}
        return j
    }
    private fun lireDonnees(i:Intervention){
        var gson = Gson()
        val stream = openFileInput("fichier.json")
        val text = AppTools.lire(stream)
        val t = Gson().fromJson(text, Array<Intervention>::class.java)
        stream.close()
        for(element in t)
        {if (element.numero!=i.numero) liste.add(element) }

    }
    }

