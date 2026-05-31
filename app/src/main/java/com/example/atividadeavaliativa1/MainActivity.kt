package com.example.atividadeavaliativa1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // FIREBASE AUTH
        auth = FirebaseAuth.getInstance()

        // VERIFICA SE JÁ ESTÁ LOGADO
        if (auth.currentUser != null) {

            startActivity(
                Intent(
                    this,
                    ImcActivity::class.java
                )
            )

            finish()
        }

        // COMPONENTES
        val editEmail =
            findViewById<EditText>(R.id.editEmail)

        val editSenha =
            findViewById<EditText>(R.id.editSenha)

        val btnEntrar =
            findViewById<Button>(R.id.btnEntrar)

        val txtCadastro =
            findViewById<TextView>(R.id.txtCadastro)

        // LOGIN
        btnEntrar.setOnClickListener {

            val email =
                editEmail.text.toString().trim()

            val senha =
                editSenha.text.toString().trim()

            // VALIDAÇÃO
            if (
                email.isEmpty() ||
                senha.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // LOGIN FIREBASE
            auth.signInWithEmailAndPassword(
                email,
                senha
            ).addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    Toast.makeText(
                        this,
                        "Login realizado com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(
                        Intent(
                            this,
                            ImcActivity::class.java
                        )
                    )

                    finish()

                } else {

                    Toast.makeText(
                        this,
                        "Email ou senha inválidos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        // ABRIR CADASTRO


        txtCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}