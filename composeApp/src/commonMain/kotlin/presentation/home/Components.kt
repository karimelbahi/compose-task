package presentation.home

import MyImageWithCoil
import MyTwoTextStartStartRow
import MyWrapShapeWithContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmp.composeapp.generated.resources.Res
import cmp.composeapp.generated.resources.beef
import cmp.composeapp.generated.resources.category_label
import cmp.composeapp.generated.resources.chocolate
import cmp.composeapp.generated.resources.dessert
import cmp.composeapp.generated.resources.irish
import cmp.composeapp.generated.resources.origin_label
import cmp.composeapp.generated.resources.pudding
import data.api.model.Category
import data.api.model.Meal
import noRippleClickable
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun CategoryComponent(category: Category, modifier: Modifier = Modifier, onClick: (Category) -> Unit = {}) {
    Column(
        modifier
            .wrapContentSize()
            .background(
                Color(0xfff8f8f8), shape = RoundedCornerShape(16.dp)
            )
            .border(
                border = BorderStroke(2.dp, Color(0xffdfdfdf)),
                shape = RoundedCornerShape(16.dp)
            )
            .noRippleClickable { onClick.invoke(category) }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyImageWithCoil(
            modifier = Modifier
                .size(width = 80.dp, height = 52.dp),
            imageUrl = category.categoryUrl
        )
        Text(
            modifier = Modifier
                .padding(top = 8.dp)
                .requiredWidth(56.dp),
            textAlign = TextAlign.Center,
            text = category.categoryName,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

    }
}

@Preview
@Composable
private fun CategoryComponentPreview() {
    CategoryComponent(
        Category(
            "1",
            categoryName = "name",
            categoryUrl = "https://picsum.photos/200",
            categoryDescription = "des"
        )
    )
}

@Composable
fun MealComponent(meal: Meal, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .background(Color(0xfff8f8f8), shape = RoundedCornerShape(16.dp))
            .border(
                border = BorderStroke(2.dp, Color(0xffdfdfdf)),
                shape = RoundedCornerShape(16.dp)
            )
            .noRippleClickable { onClick.invoke() }
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        MyImageWithCoil(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 136.dp)
                .padding(2.dp)
                .clip(shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)),
            imageUrl = meal.mealUrl
        )

        Text(
            modifier = Modifier.padding(top = 16.dp), text = meal.mealName,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000),
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        MyTwoTextStartStartRow(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            firstTxtText = stringResource(Res.string.category_label),
            firstStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF70AB99)
            ),
            secondTxtText = stringResource(Res.string.beef),
            secondStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
            )
        )
        MyTwoTextStartStartRow(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            firstTxtText = stringResource(Res.string.origin_label),
            firstStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF70AB99)
            ),
            secondTxtText = stringResource(Res.string.irish),
            secondStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
            )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MyWrapShapeWithContent(onClick = { }, txt = stringResource(Res.string.irish))
            MyWrapShapeWithContent(onClick = { }, txt = stringResource(Res.string.dessert))
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MyWrapShapeWithContent(onClick = { }, txt = stringResource(Res.string.pudding))
            MyWrapShapeWithContent(onClick = { }, txt = stringResource(Res.string.chocolate))
        }
    }
}

@Preview
@Composable
private fun MealComponentPreview() {
    MealComponent(
        Meal(
            "1",
            mealName = "Corned Beef Cabbage",
            mealUrl = "https://picsum.photos/200"
        )
    )
}


@Composable
fun HeaderSectionComponent(title: String, modifier: Modifier = Modifier) {
    Row(modifier= modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .width(2.dp)
                .height(14.dp)
                .clip(shape = RoundedCornerShape(size = 5.dp))
                .background(Color(0xff000000))
        )
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = title
        )
    }
}

@Preview
@Composable
private fun HeaderSectionComponentPreview() {
    HeaderSectionComponent(title = "title")
}