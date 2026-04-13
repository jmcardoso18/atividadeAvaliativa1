package com.example.atividadeavaliativa1

data class Pessoa(
    val nome: String,
    val peso: Double,
    val altura: Double
) {
    fun calcularIMC(): Double {
        return peso / (altura * altura)
    }

    fun classificacao(): String {
        val imc = calcularIMC()

        return when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25 -> "Peso normal"
            imc < 30 -> "Sobrepeso"
            imc < 35 -> "Obesidade grau I"
            imc < 40 -> "Obesidade grau II"
            else -> "Obesidade grau III"
        }
    }
}