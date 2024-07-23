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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import cmp.composeapp.generated.resources.Res
import cmp.composeapp.generated.resources.app_name
import cmp.composeapp.generated.resources.ic_baseline_arrow_back_24
import cmp.composeapp.generated.resources.placeholder
import com.seiko.imageloader.rememberImagePainter
import common.convertNullToEmpty
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


const val LOADING_DIALOG_TAG = "LoadingIndicator"

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
            modifier = Modifier.testTag(LOADING_DIALOG_TAG),
            color = Color(0xFFF9CB24)
        )
    }
}

@Preview
@Composable
private fun LoadingDialogPreview() {
    LoadingDialog(true)
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun MyImageWithCoil(
    modifier: Modifier,
    imageUrl: String?,
    contentScale: ContentScale = ContentScale.Crop
) {
    if (imageUrl.convertNullToEmpty().isNotEmpty()) {
        Image(
            painter = rememberImagePainter(url = imageUrl.toString()),
            contentDescription = "Image content description",
            modifier = modifier,
            contentScale = contentScale,
        )
    } else {
        Image(
            painterResource(Res.drawable.placeholder),
            contentDescription = stringResource( Res.string.app_name),
            modifier = modifier,
            contentScale = ContentScale.Crop,
        )

    }
}


@Preview
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

@Preview
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
            .background(color = if (isActive) Color(0xff70AB99) else Color(0xFFF6EBBB))
            .clickable { onClick() }
            .padding(vertical = 2.dp, horizontal = 8.dp)
    ) {
        Text(
            text = txt,
            color = if (isActive) Color.White else Color(0xFF97999B),
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

@Preview
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

@Preview
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
    drawableResource: DrawableResource? = null,
    modifier: Modifier = Modifier,
    titleContentAlignment: Alignment = Alignment.Center,
    titleColor: Color = Color(0xFF191919),
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
            drawableResource?.let {
                Image(
                    painter = painterResource(drawableResource),
                    contentDescription = "Thumb Up",
                    modifier = Modifier
                        .noRippleClickable { onclickBack() }
                )
            }
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

@Preview
@Composable
private fun MyToolbarArrowWithTitlePreview() {
    MyToolbarArrowWithTitle(
        "title",
        drawableResource = Res.drawable.ic_baseline_arrow_back_24,
        onclickBack = {})
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
