package com.example.atividadeavaliativa1.model

data class HistoricoIMC(

    var id: String = "",
    var uidUsuario: String = "",
    var nomeUsuario: String = "",
    var peso: Double = 0.0,
    var altura: Double = 0.0,
    var imc: Double = 0.0,
    var classificacao: String = "",
    var data: String = ""
)