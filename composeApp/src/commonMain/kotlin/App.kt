import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        HomeScreen()
    }
}