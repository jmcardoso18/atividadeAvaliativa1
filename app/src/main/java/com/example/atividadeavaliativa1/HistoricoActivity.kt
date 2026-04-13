package com.example.atividadeavaliativa1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SearchView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoricoActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: HistoricoAdapter
    private lateinit var searchNome: SearchView
    private lateinit var spinnerGrau: Spinner

    private var listaFiltrada = mutableListOf<Pessoa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        recycler = findViewById(R.id.recyclerHistorico)
        searchNome = findViewById(R.id.searchNome)
        spinnerGrau = findViewById(R.id.spinnerGrau)

        findViewById<Button>(R.id.btnVoltarHome).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        // Inicializa a lista filtrada com todos os dados
        listaFiltrada = Historico.listaPessoas.toMutableList()

        // Configura RecyclerView
        adapter = HistoricoAdapter(listaFiltrada)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        // Configura Spinner
        val opcoesGrau = listOf(
            "Todos",
            "Abaixo do peso",
            "Peso normal",
            "Sobrepeso",
            "Obesidade grau I",
            "Obesidade grau II",
            "Obesidade grau III"
        )

        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            opcoesGrau
        )

        spinnerGrau.adapter = spinnerAdapter

        // Filtro por nome
        searchNome.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                aplicarFiltro(
                    nome = query ?: "",
                    grau = spinnerGrau.selectedItem.toString()
                )
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                aplicarFiltro(
                    nome = newText ?: "",
                    grau = spinnerGrau.selectedItem.toString()
                )
                return true
            }
        })

        // Filtro por grau
        spinnerGrau.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    aplicarFiltro(
                        nome = searchNome.query.toString(),
                        grau = spinnerGrau.selectedItem.toString()
                    )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    private fun aplicarFiltro(nome: String, grau: String) {
        listaFiltrada.clear()

        val resultado = Historico.listaPessoas.filter { pessoa ->
            val filtroNome =
                pessoa.nome.contains(nome, ignoreCase = true)

            val filtroGrau =
                grau == "Todos" ||
                        pessoa.classificacao() == grau

            filtroNome && filtroGrau
        }

        listaFiltrada.addAll(resultado)

        adapter.notifyDataSetChanged()
    }




}