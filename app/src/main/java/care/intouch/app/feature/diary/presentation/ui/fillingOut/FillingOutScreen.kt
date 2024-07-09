package care.intouch.app.feature.diary.presentation.ui.fillingOut

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.PrimaryButtonGreen
import care.intouch.uikit.ui.cards.AddEmotionCard
import care.intouch.uikit.ui.navigation.TopBarArcButton
import care.intouch.uikit.ui.textFields.MultilineTextField
import care.intouch.uikit.ui.toggle.Toggle

@Composable
fun FillingOutScreen(
    onNextClick: () -> Unit,
) {
    Column(
        Modifier.background(InTouchTheme.colors.mainBlue)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopBarArcButton(
                onClick = { },
                enabled = true,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 28.dp)
            )
            Spacer(modifier = Modifier.height(28.dp))
            MultilineTextField(
                titleText = StringVO.Resource(R.string.event_sub_title_emotional),
                subtitleText = StringVO.Resource(R.string.event_desc_emotional),
                value = "",
                onValueChange = {},
                isError = false,
                enabled = true,
                hint = StringVO.Resource(R.string.edit_text_hint),
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(40.dp))
            MultilineTextField(
                titleText = StringVO.Resource(R.string.thoughts_sub_title_emotional),
                subtitleText = StringVO.Resource(R.string.thoughts_desc_emotional),
                value = "",
                onValueChange = {},
                isError = false,
                enabled = true,
                hint = StringVO.Resource(R.string.edit_text_hint),
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(40.dp))
            MultilineTextField(
                titleText = StringVO.Resource(R.string.emotional_type_sub_title_emotional),
                subtitleText = StringVO.Resource(R.string.emotional_type_desc_emotional),
                value = "",
                onValueChange = {},
                isError = false,
                enabled = true,
                hint = StringVO.Resource(R.string.edit_text_hint),
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(8.dp))
            AddEmotionCard(onClick = {}, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(40.dp))
            MultilineTextField(
                titleText = StringVO.Resource(R.string.sensations_sub_title_emotional),
                subtitleText = StringVO.Resource(R.string.sensations_desc_emotional),
                value = "",
                onValueChange = {},
                isError = false,
                enabled = true,
                hint = StringVO.Resource(R.string.edit_text_hint),
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = StringVO.Resource(R.string.share_with_therapist).value(),
                    style = InTouchTheme.typography.subTitle,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                Toggle(
                    isChecked = false,
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {

                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            PrimaryButtonGreen(
                onClick = { },
                modifier = Modifier,
                text = StringVO.Resource(R.string.save_button)
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FillingOutScreenPreview() {
    InTouchTheme {
        FillingOutScreen(onNextClick = {})
    }
}