package com.example.atividadeavaliativa1
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editNome = findViewById<EditText>(R.id.editNome)
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)

        btnEntrar.setOnClickListener {
            val nome = editNome.text.toString().trim()

            if (nome.isNotEmpty()) {
                val nomeFormatado = nome
                    .trim()
                    .lowercase()
                    .replaceFirstChar { it.uppercase() }

                val intent = Intent(this, ImcActivity::class.java)
                intent.putExtra("USER_NAME", nomeFormatado)
                startActivity(intent)
            }else {
                Toast.makeText(this, "Por favor, digite seu nome", Toast.LENGTH_SHORT).show()
            }
        }
    }
}