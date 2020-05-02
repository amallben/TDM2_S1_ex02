package com.example.interventions


import com.example.interventions.R
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class InterventionAdapter(private val mCtx: Context, private val list: Array<Intervention>) :
    RecyclerView.Adapter<InterventionAdapter.InterventionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):InterventionViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.item_view, parent, false)
        return InterventionViewHolder(view)
    }

    override fun onBindViewHolder(holder: InterventionViewHolder, position: Int) {
        val p = list[position]
        holder.item_numero.setText(p.numero)
        holder.item_nom.setText(p.nom)
        holder.item_type.setText(p.type)
        holder.item_date.setText(p.date)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class InterventionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var item_numero: TextView
        var item_nom: TextView
        var item_type: TextView
        var item_date: TextView

        init {

            item_numero= itemView.findViewById(R.id.item_numero)
            item_nom = itemView.findViewById(R.id.item_nom)
            item_type = itemView.findViewById(R.id.item_type)
            item_date = itemView.findViewById(R.id.item_date)

            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val i = list[adapterPosition]

            val intent = Intent(mCtx, UpdateInterventionActivity::class.java)
            intent.putExtra("Intervention", i)

            mCtx.startActivity(intent)
        }
    }
}



