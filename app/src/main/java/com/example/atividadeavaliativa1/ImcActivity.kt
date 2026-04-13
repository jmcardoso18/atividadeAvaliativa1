package com.example.atividadeavaliativa1
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ImcActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados)

        val txtOlaNome = findViewById<TextView>(R.id.txtOlaNome)

        val nomeRecebido = intent.getStringExtra("USER_NAME")
        txtOlaNome.text = "Olá, $nomeRecebido!"
        val editPeso = findViewById<EditText>(R.id.editPeso)
        val editAltura = findViewById<EditText>(R.id.editAltura)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)

        btnCalcular.setOnClickListener {
            val pesoStr = editPeso.text.toString().trim()
            val alturaStr = editAltura.text.toString().trim()

            if (pesoStr.isEmpty()) {
                Toast.makeText(this, "Preencha o campo peso", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (alturaStr.isEmpty()) {
                Toast.makeText(this, "Preencha o campo altura", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val peso = pesoStr.toDoubleOrNull()
            var altura = alturaStr.toDoubleOrNull()

            if (peso == null) {
                Toast.makeText(this, "Peso inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (altura == null) {
                Toast.makeText(this, "Altura inválida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (altura <= 0) {
                Toast.makeText(this, "Altura deve ser maior que zero", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Conversão automática
            if (altura > 100) {
                altura /= 100
                Toast.makeText(this, "Altura convertida para metros: $altura", Toast.LENGTH_SHORT).show()
            }

            val pessoa = Pessoa(
                nome = nomeRecebido ?: "Usuário",
                peso = peso,
                altura = altura
            )

            Historico.listaPessoas.add(pessoa)

            val intent = Intent(this, ResultadoActivity::class.java)
            intent.putExtra("USER_NAME", nomeRecebido)
            intent.putExtra("PESO", peso.toFloat())
            intent.putExtra("ALTURA", altura.toFloat())
            startActivity(intent)
        }
    }
}