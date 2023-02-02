package br.com.alura.helloapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario(
    @PrimaryKey
    val idUsuario: String = "",
    val senha: String?,
    val nome: String?
)

data class UsuarioPOJO(
    @PrimaryKey
    val idUsuario: String = "",
    val senha: String?,
)