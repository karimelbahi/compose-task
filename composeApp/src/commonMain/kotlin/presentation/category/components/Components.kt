package presentation.category.components

import MyImageWithCoil
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.api.model.Meal
import noRippleClickable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CategoryMealComponent(
    meal: Meal,
    onClick: () -> Unit = {},
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color(0xfff8f8f8), shape = RoundedCornerShape(16.dp))
            .border(
                border = BorderStroke(2.dp, Color(0xffdfdfdf)),
                shape = RoundedCornerShape(16.dp),
            ).noRippleClickable { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        MyImageWithCoil(
            modifier =
                Modifier
                    .size(104.dp)
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(14.dp)),
            imageUrl = meal.mealUrl,
        )
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = meal.mealName,
                style =
                    TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000),
                    ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )

            Text(
                text = meal.mealName,
                style =
                    TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFF000000),
                    ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
    }
}

@Preview
@Composable
private fun CategoryMealComponentPreview() {
    CategoryMealComponent(
        Meal(
            "1",
            mealName = "Corned Beef Cabbage",
            mealUrl = "https://picsum.photos/200",
        ),
    )
}
