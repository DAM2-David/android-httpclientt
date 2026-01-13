package es.fpsumma.dam2.api.ui.screen.tareas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.fpsumma.dam2.api.model.Tarea

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleTareaContent(
    tarea: Tarea?,
    onBack: () -> Unit,
    onSave: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de Tarea") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        floatingActionButton = {
            if (tarea != null) {
            }
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (tarea == null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Cargando...")
                    }
                }
            } else {
                var titulo by remember(tarea) { mutableStateOf(tarea.titulo) }
                var descripcion by remember(tarea) { mutableStateOf(tarea.descripcion) }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = titulo,
                        onValueChange = { titulo = it },
                        label = { Text("Título") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = descripcion,
                        onValueChange = { descripcion = it },
                        label = { Text("Descripción") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }

                FloatingActionButton(
                    onClick = { onSave(titulo, descripcion) },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
                    Icon(Icons.Default.Save, contentDescription = "Guardar cambios")
                }
            }
        }
    }
}