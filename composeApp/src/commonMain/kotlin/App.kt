import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.home.HomeScreen

@Composable
@Preview
fun App() {

    MaterialTheme {
        Navigator(screen = HomeScreen()) {
            SlideTransition(it)
        }
    }
}