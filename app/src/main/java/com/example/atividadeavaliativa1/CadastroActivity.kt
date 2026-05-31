package com.example.atividadeavaliativa1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest // Importe correto adicionado
import com.google.firebase.database.FirebaseDatabase

class CadastroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // FIREBASE AUTH
        auth = FirebaseAuth.getInstance()

        // COMPONENTES
        val editNome = findViewById<EditText>(R.id.editNomeCadastro)
        val editEmail = findViewById<EditText>(R.id.editLoginCadastro)
        val editSenha = findViewById<EditText>(R.id.editSenhaCadastro)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)

        // BOTÃO CADASTRAR
        btnCadastrar.setOnClickListener {

            val nome = editNome.text.toString().trim()
            val email = editEmail.text.toString().trim()
            val senha = editSenha.text.toString().trim()

            // VALIDAÇÃO
            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // SENHA MÍNIMA
            if (senha.length < 6) {
                Toast.makeText(this, "A senha deve ter pelo menos 6 caracteres", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // CADASTRO FIREBASE AUTHENTICATION
            auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    // UID DO USUÁRIO
                    val uid = auth.currentUser!!.uid

                    // DADOS PARA O BANCO
                    val usuario = hashMapOf(
                        "uid" to uid,
                        "nome" to nome,
                        "email" to email
                    )

                    // ATUALIZAR NOME NO FIREBASE AUTH (Formato universal atualizado)
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(nome)
                        .build()

                    auth.currentUser?.updateProfile(profileUpdates)

                    // SALVAR NO REALTIME DATABASE (E fechar a tela apenas após o sucesso)
                    FirebaseDatabase.getInstance().getReference("usuarios")
                        .child(uid)
                        .setValue(usuario)
                        .addOnCompleteListener { dbTask ->
                            if (dbTask.isSuccessful) {
                                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show()
                                finish() // Fecha a tela com segurança após gravar no banco
                            } else {
                                Toast.makeText(this, "Erro ao salvar dados no banco: ${dbTask.exception?.message}", Toast.LENGTH_LONG).show()
                            }
                        }

                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}