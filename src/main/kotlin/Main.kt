import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import composeable.MainWindow

@Composable
@Preview
fun App() {
    MainWindow()
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
