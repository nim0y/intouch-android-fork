package care.intouch.uikit.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import care.intouch.uikit.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.EmotionalChipsSmall
import care.intouch.uikit.ui.toggle.Toggle

@Composable
fun NoteCards(
    modifier: Modifier = Modifier,
    dateText: String,
    dateTextDivider: String = " ",
    dateColor: Color = InTouchTheme.colors.textBlue,
    dateTextStyle: TextStyle = InTouchTheme.typography.titleMedium,
    noteText: String,
    noteColor: Color = InTouchTheme.colors.textBlue,
    noteTextStyle: TextStyle = InTouchTheme.typography.bodyRegular,
    moodChipsList: List<StringVO>,
    moodChipsTextColor: Color = InTouchTheme.colors.textGreen,
    moodChipsBackgroundColor: Color = InTouchTheme.colors.accentBeige,
    toggleIsChecked: Boolean,
    backgroundColor: Color = InTouchTheme.colors.mainBlue,
    onClickTrash: () -> Unit,
    onClickToggle: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .height(109.dp)
            .width(334.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        Text(
            modifier = modifier.padding(start = 14.dp),
            text = dateText.replace(dateTextDivider, "\n"),
            color = dateColor,
            style = dateTextStyle,
            lineHeight = 30.sp
        )
        Column(
            modifier = modifier
                .padding(start = 18.dp)
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = modifier.padding(top = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier.padding(end = 18.dp, top = 10.5.dp),
                    text = stringResource(R.string.note),
                    style = InTouchTheme.typography.bodyBold,
                    color = InTouchTheme.colors.textGreen
                )
                Text(
                    maxLines = 2,
                    text = noteText,
                    overflow = TextOverflow.Ellipsis,
                    style = noteTextStyle,
                    color = noteColor
                )
            }

            Row(
                modifier = modifier.padding(bottom = 17.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    modifier = modifier.padding(end = 9.dp),
                    text = stringResource(R.string.mood),
                    color = InTouchTheme.colors.textGreen,
                    style = InTouchTheme.typography.bodyBold,
                )
                for (mood in moodChipsList.take(2)) {
                    EmotionalChipsSmall(
                        modifier = modifier.padding(start = 3.dp),
                        text = mood,
                        selected = true,
                        selectedColorText = moodChipsTextColor,
                        selectedColor = moodChipsBackgroundColor
                    ) {

                    }
                }
                if (moodChipsList.size > 2) {
                    Image(
                        modifier = modifier.padding(start = 1.dp),
                        painter = painterResource(id = R.drawable.icon_elipsis_horizontal),
                        contentDescription = "Ellipsis"
                    )
                }
            }
        }
        Column(
            modifier = modifier
                .fillMaxHeight()
                .padding(end = 14.dp, bottom = 13.dp, top = 14.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            Image(
                modifier = modifier
                    .padding(end = 6.dp)
                    .clickable { onClickTrash() },
                painter = painterResource(id = R.drawable.icon_trash),
                contentDescription = "delete"
            )
            Toggle(isChecked = toggleIsChecked) {
                onClickToggle(it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNoteCards() {
    InTouchTheme {
        NoteCards(dateText = "13 jul", noteText = "Lorem Ipsum dolor", moodChipsList = listOf(
            StringVO.Plain("Bad"), StringVO.Plain("Bad"), StringVO.Plain("Bad")
        ), toggleIsChecked = false, onClickToggle = {}, onClickTrash = {})
    }
}