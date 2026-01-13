package es.fpsumma.dam2.api.ui.screen.tareas

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import es.fpsumma.dam2.api.model.Tarea
import es.fpsumma.dam2.api.viewmodel.TareasViewModel

@Composable
fun DetalleTareaRoomRoute(
    id: Int,
    navController: NavController,
    vm: TareasViewModel,
    modifier: Modifier = Modifier
) {
    val tareaFlow = remember(id) { vm.getTarea(id) }

    val tareaEntity by tareaFlow.collectAsState(initial = null)

    val tarea = tareaEntity?.let {
        Tarea(it.id, it.titulo, it.descripcion)
    }

    DetalleTareaContent(
        tarea = tarea,
        onBack = { navController.popBackStack() },
        onSave = { titulo, descripcion ->
            vm.updateTarea(id, titulo, descripcion)
            navController.popBackStack()
        },
        modifier = modifier
    )
}