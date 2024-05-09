package care.intouch.uikit.ui.cards

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
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
    isSettingsClicked: Boolean,
    onClickSetting: (Boolean) -> Unit,
    onClickToggle: (Boolean) -> Unit,
    dropdownMenuItemsList: List<DropdownMenuItemsPlanCard>,
    dropdownMenuItemIconColor: Color = InTouchTheme.colors.textBlue.copy(alpha = 0.5f),
    dropdownMenuItemTextColor: Color = InTouchTheme.colors.textBlue.copy(alpha = 0.5f),
    dropdownMenuItemTextStyle: TextStyle = InTouchTheme.typography.caption2Semibold,
) {

    Box(
        modifier = modifier
            .wrapContentHeight()
            .width(334.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
    ) {

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier.padding(top = 49.dp),
                painter = painterResource(R.drawable.illustration_plan_card),
                contentDescription = "illustration plan card",
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier.wrapContentSize(), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 14.dp, end = 20.dp),
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
                        .padding(start = 23.dp),
                    text = dateText,
                    color = dateTextColor,
                    style = dateTextStyle
                )
                DropdownMenu(modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(InTouchTheme.colors.input),
//                    offset = DpOffset(x = 5.dp, y = 30.dp),
                    expanded = true,
                    onDismissRequest = { onClickSetting.invoke(!isSettingsClicked) }) {
                    dropdownMenuItemsList.forEach {
                        DropdownMenuItem(
                            modifier = Modifier.padding(start = 0.dp),
                            leadingIcon = {
                                Icon(
                                    tint = dropdownMenuItemIconColor,
                                    painter = painterResource(id = it.icon),
                                    contentDescription = ""
                                )
                            },
                            text = {
                                Text(
                                    text = it.text,
                                    color = dropdownMenuItemTextColor,
                                    style = dropdownMenuItemTextStyle
                                )
                            },
                            onClick = { it.onClick },
                            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 1.dp)
                        )
                    }
                }
                Box {
                    Image(
                        modifier = Modifier.clickable {
                            onClickSetting.invoke(!isSettingsClicked)
                            Log.d("TAG", isSettingsClicked.toString())
                        },
                        painter = painterResource(id = R.drawable.icon_elipsis_vertical),
                        contentDescription = "",
                    )

                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp)
                    .padding(top = 183.dp)
                    .background(backgroundColor),//Для обрезки нижней части изображения. Без жестких размеров
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = 15.dp, top = 5.dp, bottom = 8.dp),
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
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPlanCards() {
    val menuItems: List<DropdownMenuItemsPlanCard> =
        listOf(DropdownMenuItemsPlanCard("Duplicate", R.drawable.icon_duplicate) {},
            DropdownMenuItemsPlanCard("Clear", R.drawable.icon_small_trash) {})
    InTouchTheme {
        var onClickSetting by remember { mutableStateOf(false) }
        PlanCard(
            chipText = StringVO.Plain("Done"),
            dateText = "May, 15  2023",
            text = "Socratic dialogue Learning...\n" + "Lorem ipsum dolor sit amet ",
            toggleIsChecked = false,
            onClickToggle = {},
            onClickSetting = { onClickSetting = it },
            dropdownMenuItemsList = menuItems,
            isSettingsClicked = onClickSetting
        )
    }
}