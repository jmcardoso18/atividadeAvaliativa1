<p align="center">
  <img src="https://github.com/user-attachments/assets/857a75c5-0df0-4c02-8777-f3b0e090e5eb" alt="logo" width="350">
</p>

# 📱 Calculadora de IMC Android com Firebase

Aplicativo Android desenvolvido em Kotlin para cálculo do Índice de Massa Corporal (IMC), com autenticação de usuários, armazenamento em nuvem e histórico personalizado utilizando Firebase.

---

## 📖 Sobre o Projeto

O projeto permite que usuários realizem o cálculo do IMC de forma rápida e segura, armazenando cada resultado em um banco de dados Firebase Realtime Database.

Cada usuário possui seu próprio histórico de medições, podendo consultar e excluir registros quando desejar.

---

## ✨ Funcionalidades

### 🔐 Autenticação

- Cadastro de usuários
- Login com Firebase Authentication
- Logout seguro
- Identificação do usuário logado

### 📊 Cálculo de IMC

- Inserção de peso e altura
- Conversão automática de centímetros para metros
- Cálculo automático do IMC
- Classificação conforme tabela da OMS

### ☁️ Firebase Realtime Database

- Salvamento automático dos cálculos
- Histórico individual por usuário
- Atualização em tempo real
- Exclusão de registros

### 📋 Histórico

- Exibição dos resultados anteriores
- Data e hora do cálculo
- Classificação do IMC
- Filtro por categoria
- Exclusão de registros diretamente da lista

### 🎨 Interface

- Layout responsivo com ConstraintLayout
- Navegação entre telas
- Design moderno com cores personalizadas
- Experiência intuitiva para o usuário

---

## 🏗️ Estrutura do Projeto

```text
com.example.atividadeavaliativa1
│
├── MainActivity.kt
├── CadastroActivity.kt
├── ImcActivity.kt
├── ResultadoActivity.kt
├── HistoricoActivity.kt
├── HistoricoAdapter.kt
│
├── model
│   └── HistoricoIMC.kt
│
└── res
    ├── layout
    ├── values
    └── drawable

```
🧮 Classificações Utilizadas
IMC	Classificação
Menor que 18.5	Peso Baixo
18.5 até 24.9	Peso Ideal
25 até 29.9	Sobrepeso
30 até 34.9	Obesidade Grau I
35 até 39.9	Obesidade Grau II
Acima de 40	Obesidade Grau III
🛠️ Tecnologias Utilizadas
Kotlin
Android Studio
Firebase Authentication
Firebase Realtime Database
RecyclerView
ConstraintLayout
Material Design

📂 Banco de Dados
```
Estrutura utilizada no Firebase:

historico_imc
|
└── UID_USUARIO
     |
     └── ID_REGISTRO
          |
          ├── id
          ├── uidUsuario
          ├── nomeUsuario
          ├── peso
          ├── altura
          ├── imc
          ├── classificacao
          └── data
```
   
🚀 Como Executar

1. Clonar o projeto
``git clone https://github.com/jmcardoso18/atividadeAvaliativa1.git``
2. Abrir no Android Studio

Abra a pasta do projeto no Android Studio.

3. Configurar Firebase

Criar projeto no Firebase Console
Ativar Authentication
Ativar Realtime Database

Adicionar o arquivo:
google-services.json

na pasta:

app/

4. Sincronizar Gradle
   
Sync Project with Gradle Files

6. Executar
   
Run App

📸 Telas do Sistema

Login

<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/e2849f6b-d17c-4d67-af62-65030b0bb3ea" />

Cadastro

<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/162bbb85-7fcf-43ae-8419-c9ef88718775" />

Entrada de Dados

<img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/2cbc894c-71ea-4e94-9d73-1fadd0dbbed8" />

Resultado do IMC

<img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/808ead0a-f6e5-4de9-9f28-7d8eb3538fda" />

Histórico de Registros

<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/959f65c1-fdd3-496f-b204-51c1dfeac693" />

Histórico de Registros - Filtro

<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/e13cf7de-5049-4812-a7f1-bafaa3fa1118" />


👩‍💻 Desenvolvido por

Jamila Moraes Cardoso

Projeto acadêmico desenvolvido para a disciplina de Desenvolvimento Mobile Android utilizando Kotlin e Firebase.

📄 Licença

Projeto desenvolvido para fins educacionais.
