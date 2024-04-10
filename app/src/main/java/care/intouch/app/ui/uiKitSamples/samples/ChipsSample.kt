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
import care.intouch.uikit.ui.EmotionalChipsSmall
import care.intouch.uikit.ui.RegularChips

@Composable
fun RegularChipsSample() {
    InTouchTheme {
        var isSelectedRegularChips by remember { mutableStateOf(false) }
        var isSelectedRegularChipsSecond by remember { mutableStateOf(false) }
        var isSelectedAccentChips by remember { mutableStateOf(false) }
        var isSelectedAccentChipsSecond by remember { mutableStateOf(false) }
        var isSelectedEmotionalChips by remember { mutableStateOf(false) }
        var isSelectedEmotionalChipsSecond by remember { mutableStateOf(true) }
        var isSelectedEmotionalChipsSmall by remember { mutableStateOf(true) }
        var isSelectedEmotionalChipsSmallSecond by remember { mutableStateOf(true) }


        Column(modifier = Modifier.padding(20.dp)) {
            Spacer(modifier = Modifier.height(20.dp))
            RegularChips(
                text = StringVO.Plain("RegularChips"),
                selected = isSelectedRegularChips
            ) {
                isSelectedRegularChips = !isSelectedRegularChips
            }
            Spacer(modifier = Modifier.height(8.dp))
            RegularChips(
                text = StringVO.Plain("RegularChips"),
                selected = isSelectedRegularChipsSecond,
                unselectedColor = InTouchTheme.colors.input
            ) {
                isSelectedRegularChipsSecond = !isSelectedRegularChipsSecond
            }
            Spacer(modifier = Modifier.height(20.dp))

            AccentChips(
                text = StringVO.Plain("In progress"),
                selected = isSelectedAccentChips
            ) {
                isSelectedAccentChips = !isSelectedAccentChips
            }
            Spacer(modifier = Modifier.padding(8.dp))
            AccentChips(
                text = StringVO.Plain("Done"),
                selected = isSelectedAccentChipsSecond,
                unselectedColor = InTouchTheme.colors.mainGreen
            ) {
                isSelectedAccentChipsSecond = !isSelectedAccentChipsSecond
            }
            Spacer(modifier = Modifier.height(20.dp))

            EmotionalChips(
                text = StringVO.Plain("Loss"),
                selected = isSelectedEmotionalChips
            ) {
                isSelectedEmotionalChips = !isSelectedEmotionalChips
            }
            Spacer(modifier = Modifier.padding(8.dp))
            EmotionalChips(
                text = StringVO.Plain("Guilt"),
                selected = isSelectedEmotionalChipsSecond
            ) {
                isSelectedEmotionalChipsSecond = !isSelectedEmotionalChipsSecond
            }
            Spacer(modifier = Modifier.height(20.dp))

            EmotionalChipsSmall(
                text = StringVO.Plain("Bad"),
                selected = isSelectedEmotionalChipsSmall
            ) {
                isSelectedEmotionalChipsSmall = !isSelectedEmotionalChipsSmall
            }
            Spacer(modifier = Modifier.padding(8.dp))
            EmotionalChipsSmall(
                text = StringVO.Plain("Loneliness"),
                selected = isSelectedEmotionalChipsSmallSecond
            ) {
                isSelectedEmotionalChipsSmallSecond = !isSelectedEmotionalChipsSmallSecond
            }
        }
    }
}
