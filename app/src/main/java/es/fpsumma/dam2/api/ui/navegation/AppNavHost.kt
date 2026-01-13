package es.fpsumma.dam2.api.ui.navegation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import es.fpsumma.dam2.api.ui.screen.tareas.DetalleTareaRoomRoute
import es.fpsumma.dam2.api.ui.screen.tareas.ListadoTareasRemoteRoute
import es.fpsumma.dam2.api.ui.screen.tareas.ListadoTareasRoomRoute
import es.fpsumma.dam2.api.ui.screen.tareas.NuevaTareaRoomRoute
import es.fpsumma.dam2.api.viewmodel.TareasRemoteViewModel
import es.fpsumma.dam2.api.viewmodel.TareasViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.TAREA_LISTADO_API
) {
    val vmRoom: TareasViewModel = viewModel()

    val vmRemote: TareasRemoteViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Routes.TAREA_LISTADO) {
            ListadoTareasRoomRoute(navController = navController, vm = vmRoom)
        }

        composable(Routes.TAREA_ADD) {
            NuevaTareaRoomRoute(navController = navController, vm = vmRoom)
        }

        composable(
            route = Routes.TAREA_VIEW,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            DetalleTareaRoomRoute(id = id, navController = navController, vm = vmRoom)
        }

        composable(Routes.TAREA_LISTADO_API) {
            ListadoTareasRemoteRoute(
                navController = navController,
                vm = vmRemote
            )
        }
    }
}