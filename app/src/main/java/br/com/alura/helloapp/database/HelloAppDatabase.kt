package br.com.alura.helloapp.database

import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import br.com.alura.helloapp.data.Contato
import br.com.alura.helloapp.data.Usuario
import br.com.alura.helloapp.database.converters.*

@Database(
    entities = [Contato::class, Usuario::class],
    version = 4,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 2, to = 3),
        AutoMigration(3, 4, Migration3To4::class)
    ]
)
@TypeConverters(Converters::class)
abstract class HelloAppDatabase : RoomDatabase() {
    abstract fun contatoDao(): ContatoDao
    abstract fun usuarioDao(): UsuarioDao
}

@RenameColumn(
    tableName = "Usuario",
    fromColumnName = "nomeDeUsuario",
    toColumnName = "idUsuario"
)
class Migration3To4 : AutoMigrationSpec