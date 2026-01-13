package es.fpsumma.dam2.api.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.fpsumma.dam2.api.data.remote.RetrofitClient
import es.fpsumma.dam2.api.model.Tarea
import es.fpsumma.dam2.api.ui.screen.tareas.TareasUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TareasRemoteViewModel : ViewModel() {

    private val api = RetrofitClient.tareaAPI

    private val _state = MutableStateFlow(TareasUIState())

    val state: StateFlow<TareasUIState> = _state

    fun loadTareas() = viewModelScope.launch {
        _state.update { current ->
            current.copy(loading = true, error = null)
        }

        runCatching {
            val res = api.listar()

            if (!res.isSuccessful) error("HTTP ${res.code()}")

            res.body() ?: emptyList()
        }.onSuccess { listaDto ->
            val tareas = listaDto.map { dto ->
                Tarea(
                    id = dto.id,
                    titulo = dto.titulo,
                    descripcion = dto.descripcion
                )
            }

            _state.update { current ->
                current.copy(tareas = tareas, loading = false)
            }
        }.onFailure { e ->
            _state.update { current ->
                current.copy(
                    error = e.message ?: "Error cargando tareas",
                    loading = false
                )
            }
        }
    }
}