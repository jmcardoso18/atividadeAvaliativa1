package com.example.atividadeavaliativa1
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.atividadeavaliativa1.R

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)
        val txtNomeRes = findViewById<TextView>(R.id.txtNomeResultado)
        val txtCalculo = findViewById<TextView>(R.id.txtResultadoCalculo)
        val txtDescricao = findViewById<TextView>(R.id.txtResultadoDescricao)
        val btnInicio = findViewById<Button>(R.id.btnInicio)
        val btnHistorico = findViewById<Button>(R.id.btnHistorico)

        val nome = intent.getStringExtra("USER_NAME")
        val peso = intent.getFloatExtra("PESO", 0f)
        val altura = intent.getFloatExtra("ALTURA", 0f)

        // Cálculo do IMC: peso / (altura * altura)
        val imc = peso / (altura * altura)

        txtNomeRes.text = "$nome, seu imc é:"
        txtCalculo.text = String.format("%.2f", imc)

        // Lógica da descrição
        val classificacao = when {
            imc < 18.5 -> "Abaixo do peso"
            imc in 18.5..24.9 -> "Peso Normal"
            imc in 25.0..29.9 -> "Sobrepeso"
            imc in 30.0..34.9 -> "Obesidade Grau I"
            else -> "Obesidade Grau II/III"
        }

        txtDescricao.text = classificacao

        // Volta para tela inicial
        btnInicio.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_NEW_TASK

            startActivity(intent)
            finish()
        }

        // Vai para histórico
        btnHistorico.setOnClickListener {
            val intent = Intent(this, HistoricoActivity::class.java)
            startActivity(intent)
        }
    }
}