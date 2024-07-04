package care.intouch.app.feature.questions.presentations.ui.questions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.navigation.TopBarArcButton
import care.intouch.uikit.ui.textFields.MultilineTextField

@Composable
private fun QuestionsScreen() {
    Box(
        modifier = Modifier.background(InTouchTheme.colors.mainBlue)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 27.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(55.dp))
            TopBarArcButton(
                onClick = { /*TODO*/ },
                enabled = true,
                modifier = Modifier.align(Alignment.End)
            )
            Spacer(modifier = Modifier.height(48.dp))
            MultilineTextField(
                subtitleText = StringVO.Resource(R.string.motivates_question),
                captionText = StringVO.Resource(R.string.inscribe_motivates_question),
                value = "",
                modifier = Modifier.fillMaxWidth(),
                hint = StringVO.Resource(R.string.write_your_answer_here),
                onValueChange = { },
                isError = false,
                enabled = true)
        }
        Spacer(modifier = Modifier.height(40.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun QuestionsScreenPreview() {
    InTouchTheme {
        QuestionsScreen()
    }
}