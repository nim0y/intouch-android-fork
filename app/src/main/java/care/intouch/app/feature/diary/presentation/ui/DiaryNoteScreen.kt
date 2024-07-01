package care.intouch.app.feature.diary.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.app.feature.diary.DiaryViewModel
import care.intouch.app.feature.diary.presentation.ui.models.DiaryChangeEvent
import care.intouch.app.feature.diary.presentation.ui.models.DiaryDataState
import care.intouch.app.feature.diary.presentation.ui.models.DiaryUiState
import care.intouch.app.feature.diary.presentation.ui.models.ViewsComponentsState
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.PrimaryButtonGreen
import care.intouch.uikit.ui.cards.NoteCards
import care.intouch.uikit.ui.diary.ConfirmDeleteDialog
import care.intouch.uikit.ui.diary.DiaryHeader
import care.intouch.uikit.ui.diary.EmptyDiaryPlaceHolder

@Composable
fun DiaryNoteScreen(
    onMakeNoteClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    viewModel: DiaryViewModel = hiltViewModel()
) {
    val state by viewModel.diaryState.collectAsState()
    DiaryNoteScreen(
        onMakeNoteClick = { onMakeNoteClick.invoke() },
        onBackButtonClick = { onBackButtonClick() },
        onEvent = { viewModel.onEvent(it) },
        state = state
    )
}

@Composable
private fun DiaryNoteScreen(
    onMakeNoteClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    onEvent: (DiaryChangeEvent) -> Unit,
    state: DiaryUiState
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DiaryHeader(
            modifier = Modifier.padding(bottom = 16.dp),
            onBackArrowClick = { onBackButtonClick.invoke() },
            title = StringVO.Resource(care.intouch.app.R.string.diary_title)
        )

        PrimaryButtonGreen(
            onClick = { onMakeNoteClick.invoke() },
            modifier = Modifier.padding(bottom = 28.dp),
            text = StringVO.Resource(care.intouch.app.R.string.make_note_button)
        )

        if (state.diaryDataState.noteList.isEmpty()) {
            EmptyDiaryPlaceHolder(
                title = StringVO.Resource(care.intouch.app.R.string.my_diary_sub_title),
                text = StringVO.Resource(care.intouch.app.R.string.empty_diary)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 60.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(state.diaryDataState.noteList) { diaryNote ->
                    NoteCards(
                        dateText = diaryNote.data,
                        noteText = diaryNote.note,
                        moodChipsList = diaryNote.moodList.map { StringVO.Plain(it.name) },
                        toggleIsChecked = diaryNote.sharedWithDoc,
                        onClickTrash = {
                            onEvent(DiaryChangeEvent.IntentionToDelete(diaryNote.id))
                        },
                        onClickToggle = { onEvent(DiaryChangeEvent.OnShareWithDoc(diaryNote.id)) },
                    )
                    if (state.viewsComponentsState.noteDeletePopup) {
                        ConfirmDeleteDialog(
                            deleteWarning = StringVO.Resource(care.intouch.app.R.string.warning_delete),
                            deleteQuestion = StringVO.Resource(care.intouch.app.R.string.info_delete_node_question),
                            confirmButtonText = StringVO.Resource(care.intouch.app.R.string.confirm_button),
                            cancelButtonText = StringVO.Resource(care.intouch.app.R.string.cancel_button),
                            onConfirm = {
                                onEvent(DiaryChangeEvent.ConfirmDelete(diaryNote.id))
                            },
                            onCancel = { onEvent(DiaryChangeEvent.deleteCancel) })
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DiaryNoteScreenPreview() {
    val test: DiaryDataState = DiaryDataState(emptyList())
    val state = DiaryUiState(test, ViewsComponentsState())
    InTouchTheme {
        DiaryNoteScreen(
            onMakeNoteClick = {},
            onEvent = {},
            state = state,
            onBackButtonClick = {}
        )
    }
}