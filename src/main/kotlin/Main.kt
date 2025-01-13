import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.isSecondaryPressed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlin.math.roundToInt

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
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }

        Surface {
            Box(Modifier.fillMaxSize()) {
                Canvas(Modifier
                    .size(60.dp)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragOffset ->
                            offsetX += dragOffset.x
                            offsetY += dragOffset.y
                        }
                    }
                    .offset { IntOffset(x = offsetX.roundToInt(), y = offsetY.roundToInt()) }
                    .padding(8.dp)
                    .size(100.dp)
                    .border(1.dp, Color.Black)
                    .background(Color.Cyan)
                    .drawBehind {
                        drawCircle(Color.Red, size.minDimension / 3)
                    }
                ) {}
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
