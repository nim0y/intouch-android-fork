package care.intouch.app.feature.home.presentation.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.feature.home.presentation.models.DiaryEntry
import care.intouch.app.feature.home.presentation.models.Mood
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.cards.NoteCards

@Composable
fun DiaryLayout(
    diaryEntryList: List<DiaryEntry>,
    onDeleteButtonClicked: (itemId: Int, itemIndex: Int) -> Unit,
    onSwitcherChange: (Int, Int, Boolean) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 28.dp)
            .fillMaxSize()
            .fillMaxHeight(),
    ) {
        itemsIndexed(diaryEntryList) { index, item ->
            var toggleState by rememberSaveable {
                mutableStateOf(item.isSharedWithDoctor)
            }
            NoteCards(
                modifier = Modifier.padding(top = 16.dp),
                dateText = item.data,
                noteText = item.note,
                moodChipsList = item.moodList.map { StringVO.Plain(it.name) },
                toggleIsChecked = toggleState,
                onClickToggle = {
                    toggleState = !toggleState
                    onSwitcherChange(item.id, index, toggleState)
                },
                onClickTrash = { onDeleteButtonClicked(item.id, index) })
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    heightDp = 360
)
fun DiaryLayoutPreview() {
    val diaryEntryList = listOf(
        DiaryEntry(
            id = 1,
            data = "13, jul",
            note = "Lorem Ipsum dolor sit amet Lorem Ipsum... ",
            moodList = listOf(
                Mood(name = "Bad"),
                Mood(name = "Loneliness"),
                Mood(name = "Loneliness")
            ),
            isSharedWithDoctor = false
        ),
        DiaryEntry(
            id = 1,
            data = "13, jul",
            note = "Lorem Ipsum dolor sit amet Lorem Ipsum... ",
            moodList = listOf(Mood(name = "Bad")),
            isSharedWithDoctor = false
        )
    )
    var onSwitcherChange by rememberSaveable {
        mutableStateOf(false)
    }
    InTouchTheme {
        DiaryLayout(
            diaryEntryList = diaryEntryList,
            onDeleteButtonClicked = { _, _ -> },
            onSwitcherChange = { _, _, _ -> onSwitcherChange = !onSwitcherChange }
        )
    }

}