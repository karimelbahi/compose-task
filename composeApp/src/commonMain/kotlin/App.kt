import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import presentation.home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.home.HomeViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel = HomeViewModel()
        Navigator(HomeScreen(viewModel)) {
            SlideTransition(it)
        }
    }
}