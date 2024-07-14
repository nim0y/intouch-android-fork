package care.intouch.app.feature.diary.presentation.ui.EmotionScreens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionDescriptionEnum
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionDescriptionTask
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.cards.EmotionDescriptionCard


@Composable
fun EmotionDescriptionPager(
    taskList: List<EmotionDescriptionTask>,
    selectedList: List<EmotionDescriptionTask>,
    onClick: (index: Int) -> Unit,
) {
    val selectedItems = remember { mutableStateListOf<EmotionDescriptionTask>() }
    selectedItems.addAll(selectedList)
    LazyHorizontalStaggeredGrid(
        rows = StaggeredGridCells.Adaptive(40.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(239.dp),
        contentPadding = PaddingValues(top = 24.dp, start = 28.dp, end = 28.dp)
    ) {
        this.itemsIndexed(taskList) { index, items ->
            val isSelected = selectedItems.contains(items)
            EmotionDescriptionCard(
                modifier = Modifier.padding(end = 10.dp, bottom = 6.dp),
                text = items.text,
                selected = isSelected,
                onClick = {
                    onClick(index)
                    if (selectedItems.contains(items)) {
                        selectedItems.remove(items)
                    } else {
                        selectedItems.add(items)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmotionDescriptionPager() {
    InTouchTheme {
        EmotionDescriptionPager(
            taskList = listOf(
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.loss_clarifying_emotional),
                    EmotionDescriptionEnum.Loss
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.humility_clarifying_emotional),
                    EmotionDescriptionEnum.Humility
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.anxiety_clarifying_emotional),
                    EmotionDescriptionEnum.Anxiety
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.embarrassment_clarifying_emotional),
                    EmotionDescriptionEnum.Embarrassment
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.depression_clarifying_emotional),
                    EmotionDescriptionEnum.Depression
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.fear_clarifying_emotional),
                    EmotionDescriptionEnum.Fear
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.excitement_clarifying_emotional),
                    EmotionDescriptionEnum.Excitement
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.anger_clarifying_emotional),
                    EmotionDescriptionEnum.Anger
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.disgust_clarifying_emotional),
                    EmotionDescriptionEnum.Disgust
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.guilt_clarifying_emotional),
                    EmotionDescriptionEnum.Guilt
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.shame_clarifying_emotional),
                    EmotionDescriptionEnum.Shame
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.nervousness_clarifying_emotional),
                    EmotionDescriptionEnum.Nervousness
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.disappointment_clarifying_emotional),
                    EmotionDescriptionEnum.Dissapointment
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.sadness_clarifying_emotional),
                    EmotionDescriptionEnum.Sadness
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.impatience_clarifying_emotional),
                    EmotionDescriptionEnum.Impatience
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.exhaustion_clarifying_emotional),
                    EmotionDescriptionEnum.Exhaustion
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.frustration_clarifying_emotional),
                    EmotionDescriptionEnum.Frustration
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.confusion_clarifying_emotional),
                    EmotionDescriptionEnum.Confusion
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.loneliness_clarifying_emotional),
                    EmotionDescriptionEnum.Loneliness
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.acceptance_clarifying_emotional),
                    EmotionDescriptionEnum.Acceptance
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.laziness_clarifying_emotional),
                    EmotionDescriptionEnum.Laziness
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.interest_clarifying_emotional),
                    EmotionDescriptionEnum.Interest
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.pride_clarifying_emotional),
                    EmotionDescriptionEnum.Pride
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.hope_clarifying_emotional),
                    EmotionDescriptionEnum.Hope
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.calmness_clarifying_emotional),
                    EmotionDescriptionEnum.Calmness
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.gratitude_clarifying_emotional),
                    EmotionDescriptionEnum.Gratitude
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.happiness_clarifying_emotional),
                    EmotionDescriptionEnum.Happiness
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.respect_clarifying_emotional),
                    EmotionDescriptionEnum.Respect
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.enthusiasm_clarifying_emotional),
                    EmotionDescriptionEnum.Enthusiasm
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.satisfaction_clarifying_emotional),
                    EmotionDescriptionEnum.Satisfaction
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.self_love_clarifying_emotional),
                    EmotionDescriptionEnum.SelfLove
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.joy_clarifying_emotional),
                    EmotionDescriptionEnum.Joy
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.inspiration_clarifying_emotional),
                    EmotionDescriptionEnum.Inspiration
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.amazement_clarifying_emotional),
                    EmotionDescriptionEnum.Amazement
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.euphoria_clarifying_emotional),
                    EmotionDescriptionEnum.Euphoria
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(care.intouch.app.R.string.love_clarifying_emotional),
                    EmotionDescriptionEnum.Love
                )
            ),
            listOf(),
            onClick = {}
        )
    }
}
