import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.isSecondaryPressed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    val count = remember { mutableStateOf(0) }

    val modifier = Modifier.pointerInput(Unit) {
        detectTapGestures { offset ->
            println("Mouse click coordinates: {x: ${offset.x}; y: ${offset.y}}")
        }
    }

    MaterialTheme {
        Row(modifier = modifier) {
            Column {
                Button(onClick = {
                    count.value++
                    if (count.value % 2 == 0) {

                    }
                }) {
                    Text(if (count.value == 0) "Press me" else "Hello, ${count.value}")
                }
                Button(onClick = { count.value = 0 }) {
                    Text("Reset counter")
                }
            }
            LazyColumn {
                items(count = count.value) {
                    Text("Item: $it")
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
