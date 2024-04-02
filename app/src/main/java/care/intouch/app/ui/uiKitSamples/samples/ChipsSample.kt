package care.intouch.app.ui.uiKitSamples.samples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.AccentChips
import care.intouch.uikit.ui.EmotionalChips
import care.intouch.uikit.ui.RegularChips

@Composable
fun RegularChipsSample() {
    InTouchTheme {
        var isSelectedRegularChips by remember { mutableStateOf(false) }
        var isSelectedRegularChipsSecond by remember { mutableStateOf(false) }
        var isSelectedAccentChips by remember { mutableStateOf(false) }
        var isSelectedAccentChipsSecond by remember { mutableStateOf(false) }
        var isSelectedEmotionalChips by remember { mutableStateOf(false) }
        var isSelectedEmotionalChipsSecond by remember { mutableStateOf(false) }


        Column(modifier = Modifier.padding(20.dp)) {
            Spacer(modifier = Modifier.height(20.dp))
            RegularChips(text = StringVO.Plain("RegularChips"),
                selected = isSelectedRegularChips,
                onClick = { isSelectedRegularChips = !isSelectedRegularChips })
            Spacer(modifier = Modifier.height(8.dp))
            RegularChips(
                text = StringVO.Plain("RegularChips"),
                selected = isSelectedRegularChipsSecond,
                onClick = { isSelectedRegularChipsSecond = !isSelectedRegularChipsSecond },
                unselectedColor = InTouchTheme.colors.input
            )
            Spacer(modifier = Modifier.height(20.dp))
            AccentChips(text = StringVO.Plain("In progress"),
                selected = isSelectedAccentChips,
                onClick = { isSelectedAccentChips = !isSelectedAccentChips })
            Spacer(modifier = Modifier.padding(8.dp))
            AccentChips(
                text = StringVO.Plain("Done"),
                selected = isSelectedAccentChipsSecond,
                onClick = { isSelectedAccentChipsSecond = !isSelectedAccentChipsSecond },
                unselectedColor = InTouchTheme.colors.mainGreen
            )
            Spacer(modifier = Modifier.height(20.dp))
            EmotionalChips(text = StringVO.Plain("Loss"),
                selected = isSelectedEmotionalChips,
                onClick = { isSelectedEmotionalChips = !isSelectedEmotionalChips })
            Spacer(modifier = Modifier.padding(8.dp))
            EmotionalChips(
                text = StringVO.Plain("Guilt"),
                selected = isSelectedEmotionalChipsSecond,
                onClick = { isSelectedEmotionalChipsSecond = !isSelectedEmotionalChipsSecond },
                unselectedColor = InTouchTheme.colors.textGreen40
            )
        }
    }
}
