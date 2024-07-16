package com.example.task.common.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.task.R
import com.example.task.common.utils.convertNullToEmpty
import com.example.task.common.utils.noRippleClickable
import com.example.task.ui.theme.colorBlack19
import com.example.task.ui.theme.colorNotActiveButtonBg
import com.example.task.ui.theme.colorNotActiveButtonTxt
import com.example.task.ui.theme.colorYellow

@Composable
fun LoadingDialog(
    isDialogOpen: Boolean
) {
    if (isDialogOpen) {
        Dialog(
            onDismissRequest = { /* Dismiss the dialog when back button is pressed or tapped outside */ }
        ) {
            DefaultLoading()
        }
    }
}

@Composable
fun DefaultLoading() {
    Box(
        modifier = Modifier.size(50.dp),
        contentAlignment = Alignment.Center
    ) {

        CircularProgressIndicator(
            color = colorYellow
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingDialogPreview() {
    LoadingDialog(true)
}

@Composable
fun MyImageWithCoil(modifier: Modifier, imageUrl: String?,contentScale : ContentScale = ContentScale.Crop) {
    if (imageUrl.convertNullToEmpty().isNotEmpty()) {
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = imageUrl.convertNullToEmpty())
                .apply(block = fun ImageRequest.Builder.() {
                    placeholder(R.drawable.placeholder)
                    error(R.drawable.placeholder)
                }).build()
        )
        Image(
            painter = painter,
            contentDescription = "Image content description",
            modifier = modifier,
            contentScale = contentScale,
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.placeholder),
            contentDescription = "Image content description",
            modifier = modifier,
            contentScale = ContentScale.Crop,
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun MyImageWithCoilPreview() {
    MyImageWithCoil(
        modifier = Modifier
            .size(width = 80.dp, height = 52.dp),
        imageUrl = "url"
    )
}

@Composable
fun MyTwoTextStartStartRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(2.dp),
    firstTxtText: String,
    firstStyle: TextStyle = TextStyle(
        fontSize = 35.sp,
        fontWeight = FontWeight(800),
        color = Color(0xFF323136),
    ),
    secondTxtText: String,
    secondStyle: TextStyle = TextStyle(
        fontSize = 25.sp,
        fontWeight = FontWeight(800),
        color = Color(0xFF323136)
    )
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement
    ) {
        Text(
            text = firstTxtText,
            style = TextStyle(
                fontSize = firstStyle.fontSize,
                fontWeight = firstStyle.fontWeight,
                color = firstStyle.color,
            )
        )
        Text(
            text = secondTxtText,
            style = TextStyle(
                fontSize = secondStyle.fontSize,
                fontWeight = secondStyle.fontWeight,
                color = secondStyle.color
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyTwoTextStartStartRowPreview() {
    MyTwoTextStartStartRow(firstTxtText = "firstTxtText", secondTxtText = "SecondTxtText")
}

@Composable
fun MyWrapShapeWithContent(
    modifier: Modifier = Modifier,
    txt: String,
    isActive: Boolean = true,
    onClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .clip(shape = RoundedCornerShape(24.dp))
            .background(color = if (isActive) Color(0xff70AB99) else colorNotActiveButtonBg)
            .clickable { onClick() }
            .padding(vertical = 2.dp, horizontal = 8.dp)
    ) {
        Text(
            text = txt,
            color = if (isActive) Color.White else colorNotActiveButtonTxt,
            textAlign = TextAlign.Center,
            lineHeight = 1.5.em,
            style = TextStyle(
                fontSize = 14.sp, fontWeight = FontWeight(400)
            ),
            modifier = Modifier
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyWrapShapeWithContentPreview() {
    MyWrapShapeWithContent(onClick = { }, txt = "text")
}

@Composable
fun VerticalGrid(
    composableList: List<@Composable () -> Unit>,
    itemsPerRow: Int,
    modifier: Modifier = Modifier,
) {
    Column(modifier.fillMaxWidth()) {
        composableList
            .chunked(itemsPerRow)
            .forEach { rowComponents ->
                RowWithItems(rowComponents)
            }
    }
}

@Composable
fun RowWithItems(items: List<@Composable () -> Unit>) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        items.forEach { item ->
            Box(Modifier.weight(1f)) {
                item()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VerticalGridPreview() {
    val composableList: List<@Composable () -> Unit> = listOf(
        { Text(text = "Text 1") },
        { Text(text = "Text 2") },
        { Text(text = "Text 3") },
        { Text(text = "Text 4") },
        { Text(text = "Text 5") },
        { Text(text = "Text 6") }
    )
    VerticalGrid(composableList, 2)
}

@Composable
fun MyToolbarArrowWithTitle(
    title: String,
    @DrawableRes icon: Int? = null,
    modifier: Modifier = Modifier,
    titleContentAlignment: Alignment = Alignment.Center,
    titleColor: Color = colorBlack19,
    titleStyle: TextStyle = TextStyle(
        fontWeight = FontWeight(600),
        fontSize = 17.sp
    ),
    onclickBack: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.width(48.dp)) {
            Image(
                painter = painterResource(id = icon ?: R.drawable.ic_baseline_arrow_back_24),
                contentDescription = "Thumb Up",
                modifier = Modifier
                    .noRippleClickable { onclickBack() }
            )
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = titleContentAlignment
        ) {
            Text(
                text = title,
                color = titleColor,
                style = titleStyle,
            )
        }
        Spacer(modifier = Modifier.width(56.dp)) // Space to balance the row layout
    }
}

@Preview(showBackground = true)
@Composable
private fun MyToolbarArrowWithTitlePreview() {
    MyToolbarArrowWithTitle("title", icon = R.drawable.ic_baseline_arrow_back_24, onclickBack = {})
}

@Composable
fun MySpace4() {
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
fun MySpace8() {
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun MySpace16() {
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun MySpace12() {
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun MySpace20() {
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun MySpace24() {
    Spacer(modifier = Modifier.height(24.dp))
}
