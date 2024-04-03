package care.intouch.uikit.ui

import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun ProgressBar(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    color: Color = InTouchTheme.colors.accentGreen,
    trackColor: Color = InTouchTheme.colors.accentGreen30,
    strokeCap: StrokeCap = StrokeCap.Round,
) = LinearProgressIndicator(
    progress = progress, modifier, color, trackColor, strokeCap
)

@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    color: Color = InTouchTheme.colors.accentGreen,
    trackColor: Color = InTouchTheme.colors.accentGreen30,
    strokeCap: StrokeCap = StrokeCap.Round,
) = LinearProgressIndicator(
    modifier, color, trackColor, strokeCap
)
