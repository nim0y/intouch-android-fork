package care.intouch.app.feature.plan.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.feature.plan.presentation.viewmodel.PlanScreenViewModel
import care.intouch.uikit.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.cards.DropdownMenuItemsPlanCard
import care.intouch.uikit.ui.cards.PlanCard
import care.intouch.uikit.ui.screens.my_plan.my_plan.ChipsRow
import care.intouch.uikit.ui.screens.my_plan.my_plan.Header

@Composable
fun PlanScreen(
    onTaskListItemClick: () -> Unit
) {

    val viewModel = PlanScreenViewModel()

    val assignments = viewModel.getAssignments()

    var onClickSetting by remember { mutableStateOf(false) }

    var toggleIsChecked by remember {
        mutableStateOf(false)
    }
    val menuItems: List<DropdownMenuItemsPlanCard> =
        listOf(DropdownMenuItemsPlanCard("Duplicate", R.drawable.icon_duplicate) {},
            DropdownMenuItemsPlanCard("Clear", R.drawable.icon_small_trash) {})

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            modifier = Modifier.padding(bottom = 24.dp),
            onBackArrowClick = {}
        )

        ChipsRow(
            onFirstChipsClick = {},
            onSecondChipsClick = {},
            onThirdChipsClick = {},
            onFourthChipsClick = {}
        )

        LazyColumn(
            modifier = Modifier.padding(top = 28.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            items(assignments) { assignment ->
                PlanCard(
                    chipText = StringVO.Plain(text = assignment.status.value),
                    dateText = assignment.date,
                    text = assignment.title,
                    toggleIsChecked = toggleIsChecked,
                    onClickToggle = { toggleIsChecked = !toggleIsChecked },
                    onClickSetting = { onClickSetting = it },
                    dropdownMenuItemsList = menuItems,
                    isSettingsClicked = onClickSetting,
                    onCardClick = {
                        onTaskListItemClick.invoke()
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PlanScreenPreview() {
    InTouchTheme {
        PlanScreen(
            onTaskListItemClick = {}
        )
    }
}