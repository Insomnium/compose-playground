package control

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.UUID

@Composable
fun DynamicTextArea(content: TextAreaContent) {

    val text = remember { mutableStateOf(content.text)  }
    TextField(
        value = text.value,
        onValueChange = { text.value = it }, modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .offset(content.offset.x.dp, content.offset.y.dp)
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
    )
}

data class TextAreaContent(val id: UUID = UUID.randomUUID(), val offset: Offset, var text: String)
