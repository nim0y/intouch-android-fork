package care.intouch.app.ui.uiKitSamples.samples

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.checkmark.Checkmark
import care.intouch.uikit.ui.checkmark.CheckmarkWithText

@Composable
fun CheckmarkSample() {

    var firstCheckmarkState by remember {
        mutableStateOf(true)
    }

    var secondCheckmarkState by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Simple checkmarks with callback",
                style = InTouchTheme.typography.titleMedium
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkmark(
                    isChecked = firstCheckmarkState,
                    onChangeState = {
                        firstCheckmarkState = it
                    }
                )

                Text(
                    modifier = Modifier.width(92.dp),
                    text = "Turned to $firstCheckmarkState"
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkmark(
                    isChecked = secondCheckmarkState,
                    onChangeState = {
                        secondCheckmarkState = it
                    }
                )

                Text(
                    modifier = Modifier.width(92.dp),
                    text = "Turned to $secondCheckmarkState"
                )
            }

            Spacer(modifier = Modifier.padding(top = 24.dp))

            Text(
                text = "Checkmarks with callback and label",
                style = InTouchTheme.typography.titleMedium
            )

            CheckmarkWithText(
                text = "Pursuing further education or certifications",
                onChangeState = {}
            )

            CheckmarkWithText(
                text = "Attending workshops and conferences",
                onChangeState = {}
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CheckmarkSamplePreview() {
    InTouchTheme {
        CheckmarkSample()
    }
}