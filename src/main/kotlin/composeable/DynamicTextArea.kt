package composeable

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.UUID

@Composable
fun DynamicTextArea(
    id: UUID,
    contentState: SnapshotStateMap<UUID, TextAreaContent>,
    width: Dp = 100.dp,
    height: Dp = 100.dp,
) {

    var offsetX by rememberSaveable { mutableStateOf(0f) }
    var offsetY by rememberSaveable { mutableStateOf(0f) }

    var content by remember { mutableStateOf(contentState.getValue(id).text) }

    TextField(
        value = content,
        onValueChange = {
            content = it
            contentState[id] = contentState.getValue(id).apply { this.text = content }
        }, modifier = Modifier
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
            .width(width)
            .height(height)
            .offset(contentState.getValue(id).offset.x.dp, contentState.getValue(id).offset.y.dp)
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
    )
}

data class TextAreaContent(val id: UUID = UUID.randomUUID(), val offset: Offset, var text: String)
