package com.example.atividadeavaliativa1

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.atividadeavaliativa1.model.HistoricoIMC

class HistoricoAdapter(
    private val lista: List<HistoricoIMC>,
    private val onDeletarClick: (HistoricoIMC) -> Unit // Callback para gerenciar o clique de exclusão
) : RecyclerView.Adapter<HistoricoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtImc = view.findViewById<TextView>(R.id.txtImcItem)
        val txtDescricao = view.findViewById<TextView>(R.id.txtDescricaoItem)
        val txtPeso = view.findViewById<TextView>(R.id.txtPesoItem)
        val txtAltura = view.findViewById<TextView>(R.id.txtAlturaItem)
        val txtData = view.findViewById<TextView>(R.id.txtDataItem)
        val btnDeletar = view.findViewById<ImageButton>(R.id.btnDeletarItem)
        val card = view.findViewById<CardView>(R.id.cardHistorico)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_historico, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pessoa = lista[position]

        holder.txtImc.text = "IMC: %.2f".format(pessoa.imc)
        holder.txtPeso.text = "Peso: %.1f kg".format(pessoa.peso)
        holder.txtAltura.text = "Altura: %.2f m".format(pessoa.altura)
        holder.txtData.text = "Data: ${pessoa.data}"

        val classificacao = pessoa.classificacao ?: "Não informada"
        holder.txtDescricao.text = classificacao

        // Dinâmica de cores baseada no texto da classificação

        when (classificacao) {
            "Peso Baixo" -> holder.card.setCardBackgroundColor(Color.parseColor("#90CAF9"))
            "Peso Ideal" -> holder.card.setCardBackgroundColor(Color.parseColor("#A5D6A7"))
            "Sobrepeso" -> holder.card.setCardBackgroundColor(Color.parseColor("#FFF59D"))
            "Obesidade Grau I" -> holder.card.setCardBackgroundColor(Color.parseColor("#FFCC80"))
            "Obesidade Grau II" -> holder.card.setCardBackgroundColor(Color.parseColor("#EF9A9A"))
            "Obesidade Grau III" -> holder.card.setCardBackgroundColor(Color.parseColor("#E57373"))
            else -> holder.card.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
        }

        // Ação do botão de exclusão
        holder.btnDeletar.setOnClickListener {
            onDeletarClick(pessoa)
        }
    }

    override fun getItemCount(): Int = lista.size
}