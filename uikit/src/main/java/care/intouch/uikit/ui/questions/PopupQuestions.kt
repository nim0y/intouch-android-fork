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
import care.intouch.uikit.ui.textFields.MultilineTextFieldDefaults.BLANC_STRING

@Composable
fun PopupQuestions(
    inTouchButtonClick: () -> Unit,
    secondaryButtonClick: () -> Unit,
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
                .padding(horizontal = 28.dp)
        ) {
            Column (
                modifier = modifier.align(Alignment.Center)
                    .padding(horizontal = 28.dp, vertical = 48.dp)

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
                    onClick = {
                        inTouchButtonClick.invoke()
                    },
                    text = intouchButtonText,
                    contentPadding = PaddingValues(vertical = 13.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                SecondaryButtonDark(
                    onClick = {
                        secondaryButtonClick.invoke()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = secondaryButtonText,
                    textStyle = InTouchTheme.typography.titleSmall
                )
            }
        }
    }

@Preview
@Composable
fun PopupQuestionsPreview() {
    InTouchTheme {
        PopupQuestions(
            titleText = StringVO.Plain("Title small "),
            //modifier = Modifier.padding(45.dp),
            intouchButtonText = StringVO.Plain("Back"),
            secondaryButtonText = StringVO.Plain("Complete as is"),
            inTouchButtonClick = {

            },
            secondaryButtonClick = {

            }
        )
    }
}