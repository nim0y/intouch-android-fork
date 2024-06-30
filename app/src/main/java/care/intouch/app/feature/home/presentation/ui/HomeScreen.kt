package care.intouch.app.feature.home.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.app.feature.home.presentation.HomeViewModel
import care.intouch.app.feature.home.presentation.models.DiaryEntry
import care.intouch.app.feature.home.presentation.models.EventType
import care.intouch.app.feature.home.presentation.models.HomeUiState
import care.intouch.app.feature.home.presentation.models.Mood
import care.intouch.app.feature.home.presentation.models.Status
import care.intouch.app.feature.home.presentation.models.Task
import care.intouch.uikit.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.cards.DropdownMenuItemsPlanCard
import care.intouch.uikit.ui.cards.NoteCards
import care.intouch.uikit.ui.cards.PlanCard
import care.intouch.app.R as AppR

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val screenState = viewModel.homeState.collectAsState()
    HomeScreenWithState(screenState = screenState, viewModel)
}

@Composable
fun HomeScreenWithState(
    screenState: State<HomeUiState>,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var isSeeAllPlanVisible by rememberSaveable {
        mutableStateOf(
            screenState.value.isSeeAllPlanVisible
        )
    }
    var isSeeAllDiaryClickable by rememberSaveable {
        mutableStateOf(
            screenState.value.isDiaryListEmpty
        )
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
                    text = stringResource(id = AppR.string.hi_title, "Bob"),
                    style = InTouchTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = InTouchTheme.colors.textBlue
                )
            }

            HomeScreenDataSegment(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.55f)
                    .padding(top = 28.dp),
                isSeeAllPlanVisible,
                titleText = stringResource(id = AppR.string.my_plan_sub_title),
                seeAllClicked = {}
            ) {
                MyPlanSegment(
                    screenState = screenState.value,
                    onPlanSwitcherChange = { id, index, switcherState ->
                        viewModel.executeEvent(
                            EventType.ShearTask(
                                taskId = id, index = index, isShared = switcherState
                            )
                        )
                    },
                    dropdownMenuDelete = { taskId, taskIndex ->
                        viewModel.executeEvent(
                            EventType.DeleteTask(
                                taskId = taskId,
                                index = taskIndex
                            )
                        )
                    },
                    dropdownMenuClear = { taskId, taskIndex ->
                        viewModel.executeEvent(
                            EventType.ClearTask(
                                taskId = taskId,
                                index = taskIndex
                            )
                        )
                    })
            }

            HomeScreenDataSegment(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
                    .padding(top = 28.dp),
                isSeeAllDiaryClickable,
                titleText = stringResource(id = AppR.string.my_diary_sub_title),
                seeAllClicked = {}
            ) {
                MyDiarySegment(
                    screenState = screenState.value,
                    onDeleteButtonClicked = { itemId, itemIndex ->
                        viewModel.executeEvent(
                            EventType.DeleteDiaryEntry(
                                entryId = itemId,
                                index = itemIndex
                            )
                        )
                    },
                    onDiarySwitchChanged = { id, index, switcherState ->
                        viewModel.executeEvent(
                            EventType.ShearDiaryEntry(
                                entryId = id, index = index, isShared = switcherState
                            )
                        )
                    })
            }
        }
    }
}

@Composable
fun HomeScreenDataSegment(
    modifier: Modifier,
    isSeeAllClickable: Boolean,
    titleText: String,
    seeAllClicked: () -> Unit,
    dataSegment: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SubTitleLine(
            titleText = titleText,
            isSubTextClickable = isSeeAllClickable,
            seeAllClicked = seeAllClicked
        )
        dataSegment()
    }
}

@Composable
fun MyPlanSegment(
    screenState: HomeUiState,
    onPlanSwitcherChange: (Int, Int, Boolean) -> Unit,
    dropdownMenuDelete: (itemId: Int, itemIndex: Int) -> Unit,
    dropdownMenuClear: (itemId: Int, itemIndex: Int) -> Unit
) {
    if (screenState is HomeUiState.FilledScreen && screenState.taskList.isNotEmpty()) {
        PlanPager(
            screenState.taskList,
            onSwitcherChange = onPlanSwitcherChange,
            dropdownMenuDelete = dropdownMenuDelete,
            dropdownMenuClear = dropdownMenuClear
        )
    } else {
        PlanPlaceHolder()
    }
}

@Composable
fun MyDiarySegment(
    screenState: HomeUiState,
    onDiarySwitchChanged: (Int, Int, Boolean) -> Unit,
    onDeleteButtonClicked: (itemId: Int, itemIndex: Int) -> Unit
) {
    if (screenState is HomeUiState.FilledScreen && screenState.diaryList.isNotEmpty()) {
        DiaryLayout(
            screenState.diaryList, onSwitcherChange = onDiarySwitchChanged,
            onDeleteButtonClicked = onDeleteButtonClicked
        )

    } else {
        DiaryPlaceHolder()
    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlanPager(
    taskList: ArrayList<Task>,
    onSwitcherChange: (Int, Int, Boolean) -> Unit,
    dropdownMenuDelete: (itemId: Int, itemIndex: Int) -> Unit,
    dropdownMenuClear: (itemId: Int, itemIndex: Int) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { taskList.size }, initialPage = 0)


    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(start = 28.dp, end = 28.dp),
        pageSpacing = 16.dp,
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxSize()
    ) { page ->
        val menuItems: List<DropdownMenuItemsPlanCard> =
            listOf(DropdownMenuItemsPlanCard(
                stringResource(id = AppR.string.duplicate_item_menu),
                R.drawable.icon_duplicate
            ) {
                dropdownMenuDelete(
                    taskList[page].id,
                    page
                )
            },
                DropdownMenuItemsPlanCard(
                    stringResource(id = AppR.string.clear_item_menu),
                    R.drawable.icon_small_trash
                ) {
                    dropdownMenuClear(
                        taskList[page].id,
                        page,
                    )
                })
        var toggleState by rememberSaveable {
            mutableStateOf(taskList[page].sharedWithDoc)
        }
        var dropDownMenu by rememberSaveable {
            mutableStateOf(false)
        }
        PlanCard(
            chipText = StringVO.Plain(stringResource(id = taskList[page].status.stringId)),
            text = taskList[page].description,
            isSettingsClicked = dropDownMenu,
            onClickSetting = { isClicked -> dropDownMenu = !dropDownMenu },
            dropdownMenuItemsList = menuItems,
            onClickToggle = {
                toggleState = !toggleState
                onSwitcherChange(
                    taskList[page].id,
                    page,
                    toggleState
                )
            },
            toggleIsChecked = toggleState
        )
    }
}

@Composable
fun DiaryLayout(
    diaryEntryList: ArrayList<DiaryEntry>,
    onDeleteButtonClicked: (itemId: Int, itemIndex: Int) -> Unit,
    onSwitcherChange: (Int, Int, Boolean) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 1),
        modifier = Modifier
            .padding(horizontal = 28.dp)
            .fillMaxSize()
            .fillMaxHeight(),
    ) {
        itemsIndexed(diaryEntryList) { index, item ->
            var toggleState by rememberSaveable {
                mutableStateOf(item.sharedWithDoc)
            }
            NoteCards(
                modifier = Modifier.padding(top = 16.dp),
                dateText = item.data,
                noteText = item.note,
                moodChipsList = item.moodList.map { StringVO.Plain(it.name) },
                toggleIsChecked = toggleState,
                onClickToggle = {
                    toggleState = !toggleState
                    onSwitcherChange(item.id, index, toggleState)
                },
                onClickTrash = { onDeleteButtonClicked(item.id, index) })
        }
    }
}

@Composable
fun PlanPlaceHolder() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 28.dp, end = 28.dp, top = 16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(InTouchTheme.colors.mainBlue),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = AppR.string.empty_plan),
            style = InTouchTheme.typography.bodySemibold,
            color = InTouchTheme.colors.textGreen,
            modifier = Modifier.padding(horizontal = 35.dp, vertical = 22.dp)
        )
        Image(
            modifier = Modifier
                .padding(top = 7.dp, bottom = 28.dp)
                .fillMaxWidth(0.5F),
            painter = painterResource(id = R.drawable.illustration_empty),
            contentDescription = "empty plan placeholder"
        )
    }

}

@Composable
fun DiaryPlaceHolder() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 28.dp, end = 28.dp, top = 16.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            color = InTouchTheme.colors.mainBlue, shape = RoundedCornerShape(20.dp)
        ) { }

        Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(id = AppR.string.empty_diary),
                style = InTouchTheme.typography.bodySemibold,
                color = InTouchTheme.colors.textGreen,
                modifier = Modifier.padding(horizontal = 35.dp, vertical = 22.dp)
            )

            Image(
                modifier = Modifier
                    .padding(start = 65.dp)
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.pic_arrow_new_diary),
                contentDescription = "the arrow that points on the creation icon"
            )
        }
    }

}

@Composable
fun SubTitleLine(titleText: String, isSubTextClickable: Boolean, seeAllClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = titleText,
            style = InTouchTheme.typography.titleMedium,
            color = InTouchTheme.colors.textBlue
        )
        AnimatedVisibility(visible = isSubTextClickable) {
            Text(
                text = stringResource(id = AppR.string.see_all),
                style = InTouchTheme.typography.subTitle,
                color = InTouchTheme.colors.textBlue,
                modifier = Modifier
                    .alpha(0.5F)
                    .clickable(enabled = isSubTextClickable) {
                        seeAllClicked()
                    }
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
fun HomeScreenWithPlanPreview() {
    InTouchTheme {
        val screenState = rememberSaveable {
            mutableStateOf(
                HomeUiState.FilledScreen(
                    taskList = arrayListOf
                        (
                        Task(
                            id = 1,
                            status = Status.TO_DO,
                            sharedWithDoc = false,
                            description = "aboba лфвыадловыалвоадаодылваоыдлваовыдлаоывлаодвыдалоывлаоывдалофывдлаоывдлаоывдлоаывлдаоывдлаоывдлаоывдлаоывфдлоафы"
                        ),
                        Task(
                            id = 1,
                            status = Status.TO_DO,
                            sharedWithDoc = false,
                            description = "aboba"
                        )
                    ),
                    diaryList = arrayListOf(
                    )
                )
            )
        }
        HomeScreenWithState(screenState = screenState)
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenEmptyPreview() {
    InTouchTheme {
        val screenState = remember {
            mutableStateOf(HomeUiState.Empty)
        }
        HomeScreenWithState(screenState = screenState)
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenWithDiaryPreview() {
    InTouchTheme {
        val screenState = remember {
            mutableStateOf(
                HomeUiState.FilledScreen(
                    taskList = arrayListOf(
                    ),
                    diaryList = arrayListOf(
                        DiaryEntry(
                            id = 1,
                            data = "13, jul",
                            note = "Lorem Ipsum dolor sit amet Lorem Ipsum... ",
                            moodList = listOf(
                                Mood(name = "Bad"),
                                Mood(name = "Loneliness"),
                                Mood(name = "Loneliness")
                            ),
                            sharedWithDoc = false
                        ),
                        DiaryEntry(
                            id = 1,
                            data = "13, jul",
                            note = "Lorem Ipsum dolor sit amet Lorem Ipsum... ",
                            moodList = listOf(Mood(name = "Bad")),
                            sharedWithDoc = false
                        )
                    )
                )
            )
        }
        HomeScreenWithState(screenState = screenState)
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenFullPreview() {
    val screenState = remember {
        mutableStateOf(
            HomeUiState.FilledScreen(
                taskList = arrayListOf(
                    Task(
                        id = 1,
                        status = Status.TO_DO,
                        sharedWithDoc = false,
                        description = "abobaлфвыадловыалвоадаодылваоыдлваовыдлаоывлаодвыдалоывлаоывдалофывдлаоывдлаоывдлоаывлдаоывдлаоывдлаоывдлаоывфдлоафы"
                    ),
                    Task(
                        id = 1,
                        status = Status.IN_PROGRESS,
                        sharedWithDoc = false,
                        description = "aboba"
                    )
                ),
                diaryList = arrayListOf(
                    DiaryEntry(
                        id = 1,
                        data = "13, jul",
                        note = "Lorem Ipsum dolor sit amet Lorem Ipsum... ",
                        moodList = listOf(
                            Mood(name = "Bad"),
                            Mood(name = "Loneliness"),
                            Mood(name = "Loneliness")
                        ),
                        sharedWithDoc = false
                    ),
                    DiaryEntry(
                        id = 1,
                        data = "13, jul",
                        note = "Lorem Ipsum dolor sit amet Lorem Ipsum... ",
                        moodList = listOf(Mood(name = "Bad")),
                        sharedWithDoc = false
                    )
                )
            )
        )
    }

    InTouchTheme {
        HomeScreenWithState(screenState = screenState)
    }


}
