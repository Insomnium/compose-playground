import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import control.DynamicTextArea
import control.TextAreaContent
import java.util.UUID

@Composable
@Preview
fun App() {

    val textAreaState = mutableStateMapOf<UUID, TextAreaContent>()

    val adjustingModifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()

    val mouseEventAdjustingModifier = adjustingModifier
        .pointerInput(Unit) {
            detectTapGestures { offset ->
                println("Mouse click coordinates: {x: ${offset.x}; y: ${offset.y}}")
//                areaClickOffset.value = offset
                TextAreaContent(offset = offset, text = "").apply {
                    textAreaState[this.id] = this
                }
            }
        }

    MaterialTheme {
        Row(modifier = mouseEventAdjustingModifier) {
            LazyColumn(adjustingModifier) {
                items(count = textAreaState.size) { index ->
                    val content = textAreaState.values.toList()[index]
                    DynamicTextArea(content)
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
