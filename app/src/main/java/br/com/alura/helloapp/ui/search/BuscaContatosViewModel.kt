package br.com.alura.helloapp.ui.search

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.helloapp.database.ContatoDao
import br.com.alura.helloapp.preferences.PreferencesKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuscaContatosViewModel @Inject constructor(
    private val contatoDao: ContatoDao,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        BuscaContatosUiState()
    )
    val uiState: StateFlow<BuscaContatosUiState>
        get() = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(onValorBuscaMudou = {
                _uiState.value = _uiState.value.copy(
                    valorBusca = it
                )
                buscaContatosPorValor()
            })
        }

        viewModelScope.launch {
            val idUsuario = dataStore.data.first()[PreferencesKey.USUARIO_ATUAL]
            _uiState.value = _uiState.value.copy(
                usuarioAtual = idUsuario
            )

            idUsuario?.let {
                contatoDao.buscaTodosPeloUsuario(it).collect { contatosBuscados ->
                    _uiState.value = _uiState.value.copy(
                        contatos = contatosBuscados
                    )
                }
            }
        }
    }

    fun buscaContatosPorValor() {
        viewModelScope.launch {
            with(_uiState) {
                contatoDao
                    .buscaPorValorEPeloUsuario(value.usuarioAtual, value.valorBusca)
                    .collect {
                        value = value.copy(
                            contatos = it
                        )
                    }
            }
        }
    }
}