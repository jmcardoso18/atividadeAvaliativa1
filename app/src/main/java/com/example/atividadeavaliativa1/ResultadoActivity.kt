package com.example.atividadeavaliativa1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.atividadeavaliativa1.model.HistoricoIMC
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val txtNomeRes = findViewById<TextView>(R.id.txtNomeResultado)
        val txtCalculo = findViewById<TextView>(R.id.txtResultadoCalculo)
        val txtDescricao = findViewById<TextView>(R.id.txtResultadoDescricao)
        val btnHistorico = findViewById<Button>(R.id.btnHistorico)
        val btnSair = findViewById<Button>(R.id.btnSair)

        val uid = FirebaseAuth.getInstance().currentUser?.uid

        if (uid != null) {

            FirebaseDatabase.getInstance()
                .getReference("historico_imc")
                .child(uid)
                .limitToLast(1)
                .get()
                .addOnSuccessListener { snapshot ->

                    for (item in snapshot.children) {

                        val historico =
                            item.getValue(HistoricoIMC::class.java)

                        historico?.let {

                            txtNomeRes.text =
                                "${it.nomeUsuario}, seu IMC é:"

                            txtCalculo.text =
                                String.format("%.2f", it.imc)

                            txtDescricao.text =
                                it.classificacao
                        }
                    }
                }
                .addOnFailureListener {

                    Toast.makeText(
                        this,
                        "Erro ao carregar resultado",
                        Toast.LENGTH_LONG
                    ).show()
                }
        }

        btnHistorico.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    HistoricoActivity::class.java
                )
            )
        }

        btnSair.setOnClickListener {

            FirebaseAuth.getInstance().signOut()

            val intent = Intent(
                this,
                MainActivity::class.java
            )

            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            finish()
        }

    }
    }