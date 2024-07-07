package care.intouch.uikit.ui.questions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.buttons.SecondaryButtonDark
import care.intouch.uikit.ui.textFields.MultilineTextFieldDefaults
import care.intouch.uikit.ui.textFields.MultilineTextFieldDefaults.BLANC_STRING

@Composable
fun PopapQuestions(
    modifier: Modifier = Modifier,
    titleText: StringVO = StringVO.Plain(BLANC_STRING),
    intouchButtonText: StringVO = StringVO.Plain(BLANC_STRING),
    secondaryButtonText: StringVO = StringVO.Plain(BLANC_STRING),
    backgroundColor: Color = InTouchTheme.colors.input85,
) {
        Box(
            modifier = Modifier
                .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = backgroundColor,
                    shape = RoundedCornerShape(12.dp),
                )
        ) {
            Column (
                modifier = modifier
                    .width(MultilineTextFieldDefaults.MinWidth)
                    .align(Alignment.Center)
            ) {
                Text(
                    text = titleText.value(),
                    style = InTouchTheme.typography.titleSmall,
                    color = InTouchTheme.colors.textBlue,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(48.dp))
                IntouchButton(
                    onClick = { /*TODO*/ },
                    text = intouchButtonText,
                    contentPadding = PaddingValues(horizontal = 113.dp, vertical = 13.dp),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(4.dp))
                SecondaryButtonDark(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth(),
                    text = secondaryButtonText,
                    textStyle = InTouchTheme.typography.titleSmall
                )
            }
        }
}

@Preview
@Composable
fun PopapQuestionsPreview() {
    InTouchTheme {
        PopapQuestions(
            titleText = StringVO.Plain("Title small "),
            modifier = Modifier.padding(45.dp),
            intouchButtonText = StringVO.Plain("Back"),
            secondaryButtonText = StringVO.Plain("Complete as is")
        )
    }
}