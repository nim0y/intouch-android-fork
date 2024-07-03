package care.intouch.app.feature.questions.presentations.ui.introductory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.questions.TopPanel

@Composable
private fun IntroductoryQuestionsScreen() {
    Box (
        modifier = Modifier.background(InTouchTheme.colors.mainBlue)
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(55.dp))
            TopPanel(text = StringVO.Resource(resId = R.string.name_task_mock))
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = StringVO.Resource(resId = R.string.description_task_mock).value(),
                modifier = Modifier.padding(horizontal = 28.dp),
                color = InTouchTheme.colors.textBlue,
                style = InTouchTheme.typography.bodySemibold
            )
            Spacer(modifier = Modifier.height(26.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
                .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(id = care.intouch.uikit.R.drawable.task_image_mock),
                    contentDescription = "task image on introductory questions screen",
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            IntouchButton(
                onClick = { /*TODO*/ },
                modifier = Modifier,
                text = StringVO.Resource(R.string.next_button)
            )
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