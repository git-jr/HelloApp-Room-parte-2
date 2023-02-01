package br.com.alura.helloapp.sampleData

import br.com.alura.helloapp.data.Contato
import br.com.alura.helloapp.data.Usuario
import java.util.*

val contatosExemplo: List<Contato> = listOf(
    Contato(
        nome = "Ana",
        sobrenome = "Clara",
        telefone = "123",
        fotoPerfil = "https://images.pexels.com/photos/3922294/pexels-photo-3922294.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",

        ),
    Contato(
        nome = "Bill",
        sobrenome = "Lima",
        telefone = "321",
        fotoPerfil = "https://images.pexels.com/photos/1415882/pexels-photo-1415882.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        aniversario = Calendar.getInstance().time
    ),
    Contato(
        nome = "Odes",
        sobrenome = "Conhecido",
        telefone = "321",
        fotoPerfil = "urlTesteParaDarErro"
    )
)

val usuariosExemplo: List<Usuario> = listOf(
    Usuario("manu02", "azul123", "Manuel"),
    Usuario("ana_1", "456*_$3", "Ana"),
    Usuario("yud1", "yudi", "Yudi"),
    Usuario("cintia", "senha", "Cintia"),
)