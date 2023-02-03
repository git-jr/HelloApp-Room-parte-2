package br.com.alura.helloapp.database

import androidx.room.*
import br.com.alura.helloapp.data.Usuario
import br.com.alura.helloapp.data.UsuarioPOJO
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {
    @Insert
    suspend fun insere(usuario: Usuario)

    @Query("SELECT * FROM Usuario")
    fun buscaTodos(): Flow<List<Usuario>>

    @Query("SELECT * FROM Usuario WHERE idUsuario =:nomeUsuario")
    fun buscaPorNomeDeUsuario(nomeUsuario: String): Flow<Usuario?>

    @Update(entity = Usuario::class)
    suspend fun atualiza(usuario: UsuarioPOJO)

    @Delete
    suspend fun deleta(usuario: Usuario)

}