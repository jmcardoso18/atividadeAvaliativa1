# 📱 Calculadora de IMC

Aplicativo Android desenvolvido em **Kotlin** com o objetivo de calcular o **Índice de Massa Corporal (IMC)** de forma rápida, intuitiva e organizada.

Este projeto foi desenvolvido como atividade acadêmica da disciplina de **Desenvolvimento Mobile**, aplicando conceitos de interface gráfica, validação de dados, navegação entre telas e uso de `RecyclerView`.

---

## 🎯 Proposta do Projeto

O aplicativo tem como finalidade permitir que o usuário informe seus dados físicos, como **nome, peso e altura**, para que o sistema realize automaticamente o cálculo do IMC e apresente a classificação correspondente.

Além do cálculo, o sistema também exibe um **histórico dos resultados**, permitindo melhor visualização das consultas realizadas.

---

## ✨ Funcionalidades

- ✅ Tela inicial
- ✅ Cadastro de dados do usuário
- ✅ Validação de campos obrigatórios
- ✅ Cálculo automático do IMC
- ✅ Exibição do resultado
- ✅ Classificação por faixa
- ✅ Histórico com `RecyclerView`
- ✅ Interface intuitiva
- ✅ Geração de APK para instalação

---

## 🧮 Fórmula Utilizada

:contentReference[oaicite:0]{index=0}

---

## 🏷️ Classificação do IMC

- **Abaixo do peso** → IMC menor que 18,5
- **Peso normal** → IMC entre 18,5 e 24,9
- **Sobrepeso** → IMC entre 25,0 e 29,9
- **Obesidade** → IMC acima de 30,0

---

## 🛠️ Tecnologias Utilizadas

- **Kotlin**
- **Android Studio**
- **XML**
- **RecyclerView**
- **Intents**
- **ConstraintLayout**

---

## 📂 Estrutura do Projeto

```bash
app/
 ├── java/com/example/atividadeavaliativa1/
 │   ├── MainActivity.kt
 │   ├── ResultadoActivity.kt
 │   ├── HistoricoActivity.kt
 │   ├── HistoricoAdapter.kt
 │   └── Pessoa.kt
 │
 └── res/layout/
     ├── activity_main.xml
     ├── activity_resultado.xml
     ├── activity_historico.xml
     └── item_historico.xml
```

---

## 🚀 Como Executar

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/nome-do-repositorio.git
```

2. Abra o projeto no **Android Studio**

3. Execute no emulador ou dispositivo físico

---

## 📦 APK

A APK do projeto foi gerada para entrega final e testes em dispositivo Android.

---

## 👩‍💻 Desenvolvido por

**Jamila M. Cardoso**  
Projeto acadêmico — Desenvolvimento Mobile
