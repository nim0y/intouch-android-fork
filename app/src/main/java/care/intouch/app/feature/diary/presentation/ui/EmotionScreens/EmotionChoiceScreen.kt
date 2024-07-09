package care.intouch.app.feature.diary.presentation.ui.EmotionScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.R
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionDataEvent
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionScreenState
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.viewModel.EmotionViewModel
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.PrimaryButtonGreen

@Composable
fun EmotionChoiceScreen(
    onSaveClick: () -> Unit,
    viewModel: EmotionViewModel,
) {
    val state by viewModel.uiState.collectAsState()
    EmotionChoiceScreen(
        onSaveClick = { onSaveClick },
        state = state,
        onEvent = { viewModel.onEvent(it) }
    )
}

@Composable
fun EmotionChoiceScreen(
    onSaveClick: () -> Unit,
    arrowImage: ImageVO = ImageVO.Resource(care.intouch.uikit.R.drawable.icon_arrow_left),
    state: EmotionScreenState,
    onEvent: (EmotionDataEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = arrowImage.painter(), contentDescription = "",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 28.dp, top = 38.dp)
                .clickable { }
        )
        Spacer(modifier = Modifier.height(38.dp))
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 28.dp),
            text = StringVO.Resource(R.string.emotional_type_sub_title_emotional).value(),
            style = InTouchTheme.typography.titleSmall,
            color = InTouchTheme.colors.textGreen,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 28.dp),
            text = StringVO.Resource(R.string.not_select_emotional_type).value(),
            style = InTouchTheme.typography.bodySemibold,
            color = InTouchTheme.colors.textGreen,
        )
        EmotionsPager(taskList = state.emotion) { page ->
            onEvent(EmotionDataEvent.OnEmotionSwap(state.emotion[page].imageVO))
        }
        EmotionDescriptionPager(taskList = state.emotionList, state.emotionListResult) { index ->
            onEvent(EmotionDataEvent.OnEmotionDescriptionClicked(state.emotionList[index]))
        }
        Spacer(modifier = Modifier.height(60.dp))
        PrimaryButtonGreen(
            onClick = { },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = StringVO.Resource(R.string.save_button)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun EmotionChoiceScreenPreview() {
    InTouchTheme {
        val state = EmotionScreenState(
            emotionResult = null,
            emotionList = listOf(),
            emotion = listOf(),
            emotionListResult = mutableListOf()
        )
        EmotionChoiceScreen(
            onSaveClick = {}, state = state, onEvent = {}
        )
    }
}