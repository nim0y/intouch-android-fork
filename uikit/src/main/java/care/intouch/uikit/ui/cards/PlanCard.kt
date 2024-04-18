package care.intouch.uikit.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.AccentChips
import care.intouch.uikit.ui.toggle.Toggle

@Composable
fun PlanCard(
    modifier: Modifier = Modifier,
    chipText: StringVO,
    chipTextColor: Color = InTouchTheme.colors.textGreen.copy(alpha = 0.4f),
    chipColors: Color = InTouchTheme.colors.accentBeige,
    dateText: String = "",
    dateTextColor: Color = InTouchTheme.colors.textBlue.copy(alpha = 0.5f),
    dateTextStyle: TextStyle = InTouchTheme.typography.bodySemibold,
    text: String,
    textColor: Color = InTouchTheme.colors.textGreen,
    textTextStyle: TextStyle = InTouchTheme.typography.bodyBold,
    toggleIsChecked: Boolean,
    backgroundColor: Color = InTouchTheme.colors.mainBlue,
    onClickSetting: () -> Unit,
    onClickToggle: (Boolean) -> Unit
) {
    Box(
        modifier = modifier
            .height(284.dp)
            .width(334.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)

    ) {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, start = 14.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AccentChips(
                    modifier = Modifier.padding(),
                    text = chipText,
                    selected = true,
                    selectedColor = chipColors,
                    unselectedColorText = chipTextColor
                ) {}
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 14.dp),
                    text = dateText,
                    color = dateTextColor,
                    style = dateTextStyle
                )
                Image(
                    modifier = Modifier.clickable { onClickSetting() },
                    painter = painterResource(id = R.drawable.icon_elipsis_vertical),
                    contentDescription = "",
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 15.dp),
                    text = text,
                    color = textColor,
                    style = textTextStyle,
                    maxLines = 2
                )
                Toggle(isChecked = toggleIsChecked) {
                    onClickToggle(it)
                }
            }
        }

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier.padding(bottom = 55.dp, top = 20.dp),
                painter = painterResource(R.drawable.illustration_plan_card),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlanCards() {
    InTouchTheme {
        PlanCard(chipText = StringVO.Plain("Done"),
            dateText = "May, 15  2023",
            text = "Socratic dialogue Learning...\n" + "Lorem ipsum dolor sit amet ",
            toggleIsChecked = false,
            onClickToggle = {},
            onClickSetting = {})
    }
}