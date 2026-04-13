package com.example.atividadeavaliativa1

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class HistoricoAdapter(
    private val lista: List<Pessoa>
) : RecyclerView.Adapter<HistoricoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNome = view.findViewById<TextView>(R.id.txtNomeItem)
        val txtImc = view.findViewById<TextView>(R.id.txtImcItem)
        val txtDescricao = view.findViewById<TextView>(R.id.txtDescricaoItem)
        val card = view.findViewById<CardView>(R.id.cardHistorico)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_historico, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pessoa = lista[position]
        val imc = pessoa.calcularIMC()

        holder.txtNome.text = pessoa.nome
        holder.txtImc.text = "IMC: %.2f".format(imc)

        val classificacao = pessoa.classificacao()
        holder.txtDescricao.text = classificacao

        when (classificacao) {
            "Abaixo do peso" -> holder.card.setCardBackgroundColor(Color.parseColor("#90CAF9"))
            "Peso normal" -> holder.card.setCardBackgroundColor(Color.parseColor("#A5D6A7"))
            "Sobrepeso" -> holder.card.setCardBackgroundColor(Color.parseColor("#FFF59D"))
            "Obesidade grau I" -> holder.card.setCardBackgroundColor(Color.parseColor("#FFCC80"))
            "Obesidade grau II" -> holder.card.setCardBackgroundColor(Color.parseColor("#EF9A9A"))
            "Obesidade grau III" -> holder.card.setCardBackgroundColor(Color.parseColor("#E57373"))
            else -> holder.card.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }

    override fun getItemCount(): Int = lista.size
}