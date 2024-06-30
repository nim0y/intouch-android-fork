package care.intouch.uikit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.buttons.SecondaryButtonDark

@Composable
fun ConformationDialog(
    modifier: Modifier,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    headerText: String,
    headerTextStyle: TextStyle = InTouchTheme.typography.bodySemibold,
    dialogText: String,
    dialogTextStyle: TextStyle = InTouchTheme.typography.bodySemibold,
    dismissButtonText: String,
    dismissButtonTextStyle: TextStyle = InTouchTheme.typography.titleMedium,
    confirmButtonText: String,
    confirmButtonTextStyle: TextStyle = InTouchTheme.typography.titleMedium,
    backgroundColor: Color = InTouchTheme.colors.mainBlue
) {
    Column(
        modifier = modifier
            .background(backgroundColor)
            .wrapContentSize()
            .clip(RoundedCornerShape(20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 48.dp),
            text = headerText,
            textAlign = TextAlign.Center,
            style = headerTextStyle,
            color = InTouchTheme.colors.textBlue
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = dialogText,
            textAlign = TextAlign.Center,
            style = dialogTextStyle,
            color = InTouchTheme.colors.textBlue
        )
        IntouchButton(
            onClick = onConfirmation,
            modifier = Modifier
                .padding(top = 48.dp)
                .padding(horizontal = 50.dp),
            text = confirmButtonText,
            textStyle = confirmButtonTextStyle
        )
        SecondaryButtonDark(
            onClick = onDismissRequest,
            modifier = Modifier.padding(top = 4.dp, bottom = 34.dp),
            text = dismissButtonText,
            textStyle = dismissButtonTextStyle,
            isEnabled = true,
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ConformationDialogPreview() {
    InTouchTheme {
        ConformationDialog(
            modifier = Modifier,
            onDismissRequest = { },
            onConfirmation = {},
            headerText = "Are you sure you want \n" +
                    "to delete this task?\n",
            dialogText = "All your entered data will be\n" + "permanently removed.",
            dismissButtonText = "Cancel",
            confirmButtonText = "Yes, delete"
        )
    }
}