package care.intouch.app.feature.home.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.app.feature.home.presentation.HomeViewModel
import care.intouch.app.feature.home.presentation.models.DiaryEntry
import care.intouch.app.feature.home.presentation.models.HomeUiState
import care.intouch.app.feature.home.presentation.models.Task
import care.intouch.uikit.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.cards.DropdownMenuItemsPlanCard
import care.intouch.uikit.ui.cards.PlanCard
import care.intouch.app.R as AppR

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val screenState = viewModel.homeState.collectAsState()
    var isSeeAllPlanClickable by rememberSaveable {
        mutableStateOf(false)
    }
    var isSeeAllDiaryClickable by rememberSaveable {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(InTouchTheme.colors.white),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(98.dp),
                shape = CustomHeaderShape(),
                color = InTouchTheme.colors.mainBlue
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 24.dp),
                    text = "HomeScreen",
                    style = InTouchTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
            }
            HomeScreenDataSegment(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .padding(top = 28.dp),
                isSeeAllPlanClickable,
                titleText = stringResource(id = AppR.string.my_plan_sub_title)
            ) {
                MyPlanSegment(screenState = screenState.value) { isClickable ->
                    isSeeAllPlanClickable = isClickable
                }
            }
            HomeScreenDataSegment(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
                    .padding(top = 28.dp),
                isSeeAllDiaryClickable,
                titleText = stringResource(id = AppR.string.my_diary_sub_title)
            ) {
                MyDiarySegment(screenState = screenState.value) { isClickable ->
                    isSeeAllDiaryClickable = isClickable
                }
            }
        }
    }
}

@Composable
fun HomeScreenDataSegment(
    modifier: Modifier,
    isSeeAllClickable: Boolean,
    titleText: String,
    dataSegment: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SubTitleLine(titleText = titleText, isSubTextClickable = isSeeAllClickable)
        dataSegment()
    }
}

@Composable
fun MyPlanSegment(screenState: HomeUiState, changeClickability: (Boolean) -> Unit) {
    when (screenState) {
        is HomeUiState.PlansExists -> {
            PlanPager(screenState.taskList)
            changeClickability(true)
        }

        is HomeUiState.FilledScreen -> {
            PlanPager(screenState.taskList)
            changeClickability(true)
        }

        else -> {
            PlanPlaceHolder()
            changeClickability(false)
        }
    }
}

@Composable
fun MyDiarySegment(screenState: HomeUiState, changeClickability: (Boolean) -> Unit) {
    when (screenState) {
        is HomeUiState.MyDiaryExists -> {
            DiaryLayout(screenState.diaryList)
            changeClickability(true)
        }

        is HomeUiState.FilledScreen -> {
            DiaryLayout(screenState.diaryList)
            changeClickability(true)
        }

        else -> {
            DiaryPlaceHolder()
            changeClickability(false)
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlanPager(taskList: ArrayList<Task>) {
    val pagerState = rememberPagerState(pageCount = { taskList.size + 1 })
    val menuItems: List<DropdownMenuItemsPlanCard> =
        listOf(DropdownMenuItemsPlanCard("Duplicate", R.drawable.icon_duplicate) {},
            DropdownMenuItemsPlanCard("Clear", R.drawable.icon_small_trash) {})

    HorizontalPager(state = pagerState) { page ->
        PlanCard(
            chipText = StringVO.Plain("Done"),
            text = "Socratic dialogue Learning...\n" + "Lorem ipsum dolor sit amet ",
            isSettingsClicked = true,
            onClickSetting = {},
            dropdownMenuItemsList = menuItems
        ) {
        }
    }
}

@Composable
fun DiaryLayout(diaryEntryList: ArrayList<DiaryEntry>) {
    LazyVerticalGrid(columns = GridCells.Fixed(count = 1)) {
    }
}

@Composable
fun PlanPlaceHolder() {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 28.dp, end = 28.dp, top = 16.dp),
        colors = CardDefaults.cardColors(containerColor = InTouchTheme.colors.mainBlue),
        shape = RoundedCornerShape(20.dp)
    ) {
    }


}

@Composable
fun DiaryPlaceHolder() {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 28.dp, end = 28.dp, top = 16.dp, bottom = 50.dp),
        colors = CardDefaults.cardColors(containerColor = InTouchTheme.colors.mainBlue),
        shape = RoundedCornerShape(20.dp)
    ) {
    }
}

@Composable
fun SubTitleLine(titleText: String, isSubTextClickable: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = titleText, style = InTouchTheme.typography.titleMedium)
        Text(
            text = stringResource(id = AppR.string.see_all),
            style = InTouchTheme.typography.subTitle,
            modifier = Modifier
                .alpha(0.5F)
                .clickable(enabled = isSubTextClickable) {
                }
        )
    }

}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    InTouchTheme {
        HomeScreen()
    }
}

@Composable
@Preview(showBackground = true)
fun SubTitleLinePreview() {
    InTouchTheme {
        SubTitleLine(titleText = "My plan", isSubTextClickable = true)
    }
}