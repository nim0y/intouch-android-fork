package care.intouch.app.ui.uiKitSamples.samples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.cards.CardLine
import care.intouch.uikit.ui.cards.NoteCards
import care.intouch.uikit.ui.cards.PlanCard

@Composable
fun CardsSample() {
    InTouchTheme {
        var isSelectedNoteCard by remember { mutableStateOf(false) }
        var isSelectedPlanCard by remember { mutableStateOf(false) }

        Column(modifier = Modifier.fillMaxSize()) {
            NoteCards(
                dateText = "13 jul",
                noteText = "Lorem Ipsum dolor sit amet Lorem Ipsum... ",
                moodChipsList = listOf(StringVO.Plain("Bad"), StringVO.Plain("Loneliness")),
                toggleIsChecked = isSelectedNoteCard,
                onClickTrash = { }) { isSelectedNoteCard = !isSelectedNoteCard }
            Spacer(modifier = Modifier.height(8.dp))
            NoteCards(
                dateText = "13 jul",
                noteText = "Lorem Ipsum dolor ",
                moodChipsList = listOf(StringVO.Plain("Bad"), StringVO.Plain("Loneliness")),
                toggleIsChecked = isSelectedNoteCard,
                onClickTrash = { }) {}
            Spacer(modifier = Modifier.height(8.dp))
            PlanCard(
                chipText = StringVO.Plain("In progress"),
                text = "Socratic dialogue Learning...Lorem ipsum dolor sit amet ",
                toggleIsChecked = isSelectedPlanCard,
                onClickSetting = {}) {
                isSelectedPlanCard = !isSelectedPlanCard
            }
            Spacer(modifier = Modifier.height(8.dp))
            CardLine(
                dateText = "May, 15  2023",
                chipText = StringVO.Plain("In progress"),
                chipTextColor = InTouchTheme.colors.input.copy(alpha = .85f),
                chipColors = InTouchTheme.colors.textBlue
            ) {}
        }
    }
}