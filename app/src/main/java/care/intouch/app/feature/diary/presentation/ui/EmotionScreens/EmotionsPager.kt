package care.intouch.app.feature.diary.presentation.ui.EmotionScreens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.feature.diary.presentation.ui.EmotionScreens.models.EmotionTask
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.cards.EmotionCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmotionsPager(
    taskList: List<EmotionTask>,
    onClick: (page: Int) -> Unit,
) {
    val pagerState = rememberPagerState(
        pageCount = { taskList.size },
        initialPage = 0
    )
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(start = 140.dp, end = 110.dp),
        pageSpacing = 10.dp,
        modifier = Modifier
            .padding(top = 32.dp),
    ) { page ->
        val scale = if (page == pagerState.currentPage) 1.0f else 0.8f
        EmotionCard(
            modifier = Modifier,
            emotion = (if (scale == 1.0f) taskList[page].bigImageVO else taskList[page].imageVO),
            onClick = { onClick(page) }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PlanPagerPreview() {
    InTouchTheme {
        val taskList = listOf(
            EmotionTask(
                imageVO = ImageVO.Resource(care.intouch.uikit.R.drawable.icon_terrible),
                bigImageVO = ImageVO.Resource(care.intouch.uikit.R.drawable.icon_terrible_big)
            ),
            EmotionTask(
                imageVO = ImageVO.Resource(care.intouch.uikit.R.drawable.icon_bad),
                bigImageVO = ImageVO.Resource(care.intouch.uikit.R.drawable.icon_bad_big)
            ),
            EmotionTask(
                imageVO = ImageVO.Resource(care.intouch.uikit.R.drawable.icon_okay),
                bigImageVO = ImageVO.Resource(care.intouch.uikit.R.drawable.icon_okey_big)
            ),
            EmotionTask(
                imageVO = ImageVO.Resource(care.intouch.uikit.R.drawable.icon_good),
                bigImageVO = ImageVO.Resource(care.intouch.uikit.R.drawable.icon_good_big)
            ),
            EmotionTask(
                imageVO = ImageVO.Resource(care.intouch.uikit.R.drawable.icon_great),
                bigImageVO = ImageVO.Resource(care.intouch.uikit.R.drawable.icon_great_big)
            ),
        )
        EmotionsPager(
            taskList = taskList, onClick = {}
        )
    }
}