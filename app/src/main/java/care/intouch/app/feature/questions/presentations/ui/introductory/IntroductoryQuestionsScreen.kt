package care.intouch.app.feature.questions.presentations.ui.introductory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.profile.TopPanel

@Composable
private fun IntroductoryQuestionsScreen() {
    Box (
        modifier = Modifier.background(InTouchTheme.colors.mainBlue)
    ) {
        Column (
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(55.dp))
            TopPanel(text = StringVO.Resource(resId = care.intouch.app.R.string.socratic_dialogue))
            Spacer(modifier = Modifier.height(28.dp))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun IntroductoryQuestionsScreenPreview() {
    InTouchTheme {
        IntroductoryQuestionsScreen()
    }
}