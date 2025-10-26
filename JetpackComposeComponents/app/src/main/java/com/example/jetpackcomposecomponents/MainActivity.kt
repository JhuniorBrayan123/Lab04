package com.example.jetpackcomposecomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

// La importación de la versión View de ConstraintLayout fue eliminada para evitar conflictos


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(0) }

    Column {
        // Tab para navegar entre categorías
        TabRow(selectedTabIndex = selectedTab) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("Contenedores") }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("Controles 1") }
            )
            Tab(
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 },
                text = { Text("Controles 2") }
            )
        }

        when (selectedTab) {
            0 -> ContainersSection()
            // Estas secciones se pueden implementar después
            // 1 -> ControlsSection1()
            // 2 -> ControlsSection2()
        }
    }
}

// ==================== CONTENEDORES ====================

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContainersSection() {
    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { LazyColumnDemo() }
        item { LazyRowDemo() }
        item { GridDemo() }
        item { ConstraintLayoutDemo() }
        item { ScaffoldDemo() }
        item { SurfaceDemo() }
        item { ChipDemo() }
        item { FlowRowDemo() }
        item { FlowColumnDemo() }
    }
}

@Composable
fun LazyColumnDemo() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("LazyColumn", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("Lista vertical optimizada para grandes conjuntos de datos")
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.height(150.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(20) { index ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
                    ) {
                        Text(
                            "Item $index",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LazyRowDemo() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("LazyRow", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("Lista horizontal optimizada para desplazamiento lateral")
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(10) { index ->
                    Card(
                        modifier = Modifier.size(100.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3E5F5))
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(), // Rellenar el Card
                            contentAlignment = Alignment.Center
                        ) {
                            Text("$index", fontSize = 20.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GridDemo() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("LazyVerticalGrid", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("Grilla vertical con múltiples columnas")
            Spacer(modifier = Modifier.height(8.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.height(120.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(12) { index ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E8))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("$index")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ConstraintLayoutDemo() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("ConstraintLayout", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("Layout con restricciones entre elementos")
            Spacer(modifier = Modifier.height(8.dp))

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                // Se utiliza createRefs() del scope de ConstraintLayout
                val (button1, button2, text) = createRefs()

                Button(
                    onClick = {},
                    modifier = Modifier.constrainAs(button1) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                ) {
                    Text("Inicio")
                }

                Button(
                    onClick = {},
                    modifier = Modifier.constrainAs(button2) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                ) {
                    Text("Final")
                }

                Text(
                    "Centrado",
                    modifier = Modifier.constrainAs(text) {
                        top.linkTo(button1.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        MainScreen()
    }
}
