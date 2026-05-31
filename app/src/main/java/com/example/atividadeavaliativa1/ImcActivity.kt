package com.example.atividadeavaliativa1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.atividadeavaliativa1.model.HistoricoIMC
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ImcActivity : AppCompatActivity() {

    private lateinit var editPeso: EditText
    private lateinit var editAltura: EditText
    private lateinit var btnCalcular: Button

    private lateinit var btnHistorico2: Button

    private lateinit var btnSair2: Button
    private lateinit var txtOlaNome: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados)

        // FIREBASE
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        // COMPONENTES
        txtOlaNome = findViewById(R.id.txtOlaNome)
        editPeso = findViewById(R.id.editPeso)
        editAltura = findViewById(R.id.editAltura)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnHistorico2 = findViewById(R.id.btnHistorico2)
        btnSair2 = findViewById(R.id.btnSair2)


        // USUÁRIO LOGADO
        val usuario = auth.currentUser

        // VERIFICAR LOGIN
        if (usuario == null) {

            Toast.makeText(
                this,
                "Usuário não autenticado",
                Toast.LENGTH_LONG
            ).show()

            finish()

            return
        }

        // NOME DO USUÁRIO
        val nomeUsuario =
            usuario.displayName ?: "Usuário"

        txtOlaNome.text =
            "Olá, $nomeUsuario!"

        // BOTÃO CALCULAR
        btnCalcular.setOnClickListener {

            calcularESalvarIMC()
        }

        //BOTÃO HISTÓRICO
        btnHistorico2.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    HistoricoActivity::class.java
                )
            )
        }
        //BOTÃO SAIR
        btnSair2.setOnClickListener {

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

    private fun calcularESalvarIMC() {

        val pesoStr =
            editPeso.text.toString().trim()

        val alturaStr =
            editAltura.text.toString().trim()

        // VALIDAR CAMPOS
        if (pesoStr.isEmpty()) {

            editPeso.error = "Digite o peso"

            return
        }

        if (alturaStr.isEmpty()) {

            editAltura.error = "Digite a altura"

            return
        }

        // CONVERTER PARA DOUBLE
        val peso =
            pesoStr.toDoubleOrNull()

        var altura =
            alturaStr.toDoubleOrNull()

        // VALIDAR PESO
        if (peso == null || peso <= 0) {

            editPeso.error = "Peso inválido"

            return
        }

        // VALIDAR ALTURA
        if (altura == null || altura <= 0) {

            editAltura.error = "Altura inválida"

            return
        }

        // CONVERTER CM PARA METROS
        if (altura > 3) {

            altura /= 100
        }

        // CALCULAR IMC

        val imc = peso / (altura * altura)

        // ARREDONDAR IMC
        val imcFormatado =
            Math.round(imc * 100) / 100.0

        // CLASSIFICAÇÃO

        val classificacao = when {

            imc < 18.5 ->
                "Peso Baixo"

            imc < 25 ->
                "Peso Ideal"

            imc < 30 ->
                "Sobrepeso"

            imc < 35 ->
                "Obesidade Grau I"

            imc < 40 ->
                "Obesidade Grau II"

            else ->
                "Obesidade Grau III"
        }

        // DATA ATUAL

        val dataAtual =
            SimpleDateFormat(
                "dd/MM/yyyy HH:mm",
                Locale.getDefault()
            ).format(Date())

        // USUÁRIO

        val usuario =
            auth.currentUser

        if (usuario == null) {

            Toast.makeText(
                this,
                "Usuário inválido",
                Toast.LENGTH_LONG
            ).show()

            return
        }

        val uid = usuario.uid

        // REFERÊNCIA FIREBASE

        val referencia =
            database
                .getReference("historico_imc")
                .child(uid)

        // ID DO REGISTRO

        val idRegistro = referencia.push().key

        if (idRegistro == null) {

            Toast.makeText(
                this,
                "Erro ao gerar ID",
                Toast.LENGTH_LONG
            ).show()

            return
        }

        // OBJETO HISTÓRICO

        val historico = HistoricoIMC(

            id = idRegistro,

            uidUsuario = uid,

            nomeUsuario = usuario.displayName ?: "Usuário",

            peso = peso,

            altura = altura,

            imc = imcFormatado,

            classificacao = classificacao,

            data = dataAtual
        )

        // SALVAR NO FIREBASE

        referencia
            .child(idRegistro)
            .setValue(historico)

            .addOnSuccessListener {

                Toast.makeText(
                    this,
                    "IMC salvo com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()

                abrirResultado(
                    imcFormatado,
                    classificacao
                )
            }

            .addOnFailureListener { erro ->

                Toast.makeText(
                    this,
                    "Erro: ${erro.message}",
                    Toast.LENGTH_LONG
                ).show()

                erro.printStackTrace()
            }
    }

    private fun abrirResultado(
        imc: Double,
        classificacao: String
    ) {

        val intent =
            Intent(
                this,
                ResultadoActivity::class.java
            )

        intent.putExtra(
            "IMC",
            imc
        )

        intent.putExtra(
            "CLASSIFICACAO",
            classificacao
        )

        startActivity(intent)
    }


}