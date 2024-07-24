package presentation.category

import LoadingDialog
import MyImageWithCoil
import MyToolbarArrowWithTitle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cmp.composeapp.generated.resources.Res
import cmp.composeapp.generated.resources.beef_meals_tile
import cmp.composeapp.generated.resources.category
import cmp.composeapp.generated.resources.ic_baseline_arrow_back_24
import cmp.composeapp.generated.resources.meal
import common.MainMargin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.category.components.CategoryMealComponent

data class CategoriesScreen(
    val categoryName: String,
    val categoryUrl: String,
    val state: StateFlow<CategoriesState>,
) : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val stateValue = state.collectAsState().value
        val scrollState = rememberScrollState()

        LoadingDialog(stateValue.loading)

        if ( stateValue.isSuccess || LocalInspectionMode.current) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(MainMargin),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
            MyToolbarArrowWithTitle(
                stringResource(Res.string.beef_meals_tile),
                drawableResource = Res.drawable.ic_baseline_arrow_back_24,
                titleContentAlignment = Alignment.TopStart,
                titleStyle = TextStyle(
                    fontWeight = FontWeight(400),
                    fontSize = 17.sp
                ),
                onclickBack = {
                    navigator.pop()
                })

                Row(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .background(Color(0xfff8f8f8)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = stringResource(Res.string.category),
                            style = TextStyle(fontWeight = FontWeight.Light, fontSize = 16.sp)
                        )
                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = categoryName + " " + stringResource(Res.string.meal),
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    MyImageWithCoil(
                        modifier = Modifier.size(104.dp),
                        imageUrl = categoryUrl,
                        contentScale = ContentScale.FillBounds
                    )
                }

                for (meal in stateValue.meals) {
                    CategoryMealComponent(meal = meal)
                }
            }
        }
    }
}

// TODO:(karim) need to fix the preview to be from android not jetbrains
@Preview
@Composable
private fun CategoriesScreenPreview() {
    CategoriesScreen(
        categoryName = "",
        categoryUrl = "https://duckduckgo.com/?q=ac",
        state = MutableStateFlow(CategoriesState())
   )
}