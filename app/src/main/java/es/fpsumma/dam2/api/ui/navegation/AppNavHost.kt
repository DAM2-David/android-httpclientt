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
import es.fpsumma.dam2.api.ui.screen.tareas.ListadoTareasRoomRoute
import es.fpsumma.dam2.api.ui.screen.tareas.NuevaTareaRoomRoute
import es.fpsumma.dam2.api.viewmodel.TareasViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.TAREA_LISTADO
) {
    val vm: TareasViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Routes.TAREA_LISTADO) {
            ListadoTareasRoomRoute(
                navController = navController,
                vm = vm
            )
        }

        composable(Routes.TAREA_ADD) {
            NuevaTareaRoomRoute(
                navController = navController,
                vm = vm
            )
        }

        composable(
            route = Routes.TAREA_VIEW,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0

            DetalleTareaRoomRoute(
                id = id,
                navController = navController,
                vm = vm
            )
        }
    }
}