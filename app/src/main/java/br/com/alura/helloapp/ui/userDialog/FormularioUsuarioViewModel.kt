package br.com.alura.helloapp.ui.userDialog

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.PrimaryKey
import br.com.alura.helloapp.data.Usuario
import br.com.alura.helloapp.data.UsuarioPOJO
import br.com.alura.helloapp.database.UsuarioDao
import br.com.alura.helloapp.util.ID_USUARIO_ATUAL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormularioUsuarioViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dataStore: DataStore<Preferences>,
    private val usuarioDao: UsuarioDao
) : ViewModel() {

    private val nomeUsuario = savedStateHandle.get<String>(ID_USUARIO_ATUAL)

    private val _uiState = MutableStateFlow(FormularioUsuarioUiState())
    val uiState: StateFlow<FormularioUsuarioUiState>
        get() = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(onNomeMudou = {
                _uiState.value = _uiState.value.copy(
                    nome = it
                )
            })
        }

        carregaUsuario()
    }

    private fun carregaUsuario() {
        viewModelScope.launch {
            nomeUsuario?.let {
                usuarioDao
                    .buscaPorNomeDeUsuario(nomeUsuario = nomeUsuario)
                    .collect {
                        it?.let {
                            _uiState.value = _uiState.value.copy(
                                nomeUsuario = it.idUsuario,
                                nome = it.nome.toString(),
                                senha = it.senha.toString()
                            )
                        }
                    }
            }
        }
    }

    suspend fun atualiza() {
        with(_uiState.value) {
            usuarioDao.atualiza(
                UsuarioPOJO(
                    idUsuario = nomeUsuario,
                    senha = senha
                )
            )
        }
    }

    suspend fun apaga() {
        with(_uiState.value) {
            usuarioDao.apaga(
                Usuario(
                    idUsuario = nomeUsuario,
                    nome = nome,
                    senha = senha
                )
            )
        }
    }

    fun onClickMostraMensagemExclusao() {
        _uiState.value = _uiState.value.copy(
            mostraMensagemExclusao = true
        )
    }
}