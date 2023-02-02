package br.com.alura.helloapp.database

import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import br.com.alura.helloapp.data.Contato
import br.com.alura.helloapp.data.Usuario
import br.com.alura.helloapp.database.converters.*

@Database(
    entities = [Contato::class, Usuario::class],
    version = 6,
    autoMigrations = [
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4, Migration3To4::class),
        AutoMigration(from = 4, to = 5)
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
