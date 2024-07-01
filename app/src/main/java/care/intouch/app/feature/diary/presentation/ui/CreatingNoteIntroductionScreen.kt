package care.intouch.app.feature.diary.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.PrimaryButtonGreen
import care.intouch.uikit.ui.navigation.CustomTopBar

@Composable
fun CreatingNoteIntroductionScreen(
    onNextClick: () -> Unit,
    title: StringVO = StringVO.Resource(R.string.emotional_title),
    text: StringVO = StringVO.Resource(care.intouch.app.R.string.diary_introduction_text),
    nextButtonText: StringVO = StringVO.Resource(care.intouch.app.R.string.next_button)
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = InTouchTheme.colors.mainBlue) {}
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTopBar(
                title = title.value(),
                titleStyle = InTouchTheme.typography.titleMedium,
                onBackArrowClick = {},
                onCloseButtonClick = { },
                enabledArcButton = false,
                addBackArrowButton = true,
                addCloseButton = false
            )
            Text(
                modifier = Modifier.padding(horizontal = 28.dp, vertical = 12.dp),
                style = InTouchTheme.typography.bodySemibold,
                color = InTouchTheme.colors.textGreen,
                text = text.value()
            )

            Image(
                painter = ImageVO.Resource(care.intouch.uikit.R.drawable.illustartion_make_note_introduction)
                    .painter(), contentDescription = "Lotus man"
            )
            PrimaryButtonGreen(
                modifier = Modifier.padding(top = 30.dp),
                onClick = { onNextClick.invoke() },
                isEnabled = true,
                text = nextButtonText
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CreatingNoteIntroductionScreenPreview() {
    InTouchTheme {
        CreatingNoteIntroductionScreen(
            onNextClick = {},
        )
    }
}