package composeable

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import util.logger
import java.util.UUID

@Composable
fun MainWindow(
    modifier: Modifier = Modifier.fillMaxWidth().fillMaxHeight()
) {

    val textAreaState = remember { mutableStateMapOf<UUID, TextAreaContent>() }

    val mouseEventAdjustingModifier = modifier
        .pointerInput(Unit) {
            detectTapGestures { offset ->
                logger.debug("Mouse click coordinates: {x: ${offset.x}; y: ${offset.y}}")
                val textArea = TextAreaContent(offset = offset, text = "")
                textAreaState[textArea.id] = textArea
            }
        }

    MaterialTheme {
        Row(modifier = mouseEventAdjustingModifier) {
            LazyColumn(modifier = modifier) {
                items(count = textAreaState.size) { index ->
                    val content = textAreaState.values.toList()[index]
                    DynamicTextArea(contentState = textAreaState, id = content.id)
                }
            }
        }
    }
}
