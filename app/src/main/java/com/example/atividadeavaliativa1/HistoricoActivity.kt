package com.example.atividadeavaliativa1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SearchView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.atividadeavaliativa1.model.HistoricoIMC
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HistoricoActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: HistoricoAdapter
    private lateinit var spinnerGrau: Spinner


    private var listaCompleta = mutableListOf<HistoricoIMC>()
    private var listaFiltrada = mutableListOf<HistoricoIMC>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        recycler = findViewById(R.id.recyclerHistorico)
        spinnerGrau = findViewById(R.id.spinnerGrau)

        findViewById<Button>(R.id.btnVoltarHome).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        // Configura o Adapter passando a ação de deletar do Firebase
        adapter = HistoricoAdapter(listaFiltrada) { pessoaSelecionada ->
            confirmarExclusao(pessoaSelecionada)
        }
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        // Busca dados sincronizados do Firebase Realtime Database
        buscarDadosDoFirebase()

        // Configura Spinner
        val opcoesGrau = listOf(
            "Todos",
            "Peso Baixo",
            "Peso Ideal",
            "Sobrepeso",
            "Obesidade Grau I",
            "Obesidade Grau II",
            "Obesidade Grau III"
        )
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opcoesGrau)
        spinnerGrau.adapter = spinnerAdapter


        // Filtro por seleção do Spinner
        spinnerGrau.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                aplicarFiltro(
                    spinnerGrau.selectedItem.toString()
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun buscarDadosDoFirebase() {

        val uid = FirebaseAuth.getInstance()
            .currentUser
            ?.uid ?: return

        FirebaseDatabase.getInstance()
            .getReference("historico_imc")
            .child(uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    listaCompleta.clear()

                    for (itemSnapshot in snapshot.children) {

                        val historico =
                            itemSnapshot.getValue(HistoricoIMC::class.java)

                        historico?.let {
                            listaCompleta.add(it)
                        }
                    }

                    aplicarFiltro(spinnerGrau.selectedItem.toString())
                }



                override fun onCancelled(error: DatabaseError) {

                    Toast.makeText(
                        this@HistoricoActivity,
                        "Erro ao carregar dados: ${error.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }

    private fun aplicarFiltro(grau: String) {

        listaFiltrada.clear()

        val resultado = listaCompleta.filter {

            grau == "Todos" ||
                    it.classificacao.equals(grau, ignoreCase = true)
        }

        listaFiltrada.addAll(resultado)

        adapter.notifyDataSetChanged()
    }

    private fun confirmarExclusao(pessoa: HistoricoIMC) {

        val uid = FirebaseAuth.getInstance()
            .currentUser
            ?.uid ?: return

        if (pessoa.id.isNullOrEmpty()) return

        AlertDialog.Builder(this)
            .setTitle("Excluir Registro")
            .setMessage("Tem certeza de que deseja apagar permanentemente este registro de IMC?")
            .setPositiveButton("Sim") { _, _ ->

                FirebaseDatabase.getInstance()
                    .getReference("historico_imc")
                    .child(uid)
                    .child(pessoa.id!!)
                    .removeValue()
                    .addOnSuccessListener {

                        Toast.makeText(
                            this,
                            "Registro excluído com sucesso!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .addOnFailureListener {

                        Toast.makeText(
                            this,
                            "Erro ao excluir: ${it.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }
            .setNegativeButton("Não", null)
            .show()
    }
}