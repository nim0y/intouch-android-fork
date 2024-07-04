package care.intouch.app.feature.plan.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.feature.home.presentation.ui.FoldingScreen
import care.intouch.app.feature.plan.presentation.viewmodel.PlanScreenViewModel
import care.intouch.uikit.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.cards.ConformationDialog
import care.intouch.uikit.ui.cards.DropdownMenuItemsPlanCard
import care.intouch.uikit.ui.cards.PlanCard
import care.intouch.uikit.ui.screens.my_plan.my_plan.CardHolder
import care.intouch.uikit.ui.screens.my_plan.my_plan.ChipsRow
import care.intouch.uikit.ui.screens.my_plan.my_plan.PlanHeader

@Composable
fun PlanScreen(
    onTaskListItemClick: () -> Unit
) {
    val context = LocalContext.current

    val viewModel = PlanScreenViewModel()

    val assignments = viewModel.getAssignments()

    var isDialogueIsVisible by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            PlanHeader(
                modifier = Modifier.padding(bottom = 24.dp),
                onBackArrowClick = {}
            )

            ChipsRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                onFirstChipsClick = {},
                onSecondChipsClick = {},
                onThirdChipsClick = {},
                onFourthChipsClick = {}
            )

            LazyColumn(
                modifier = Modifier.padding(top = 28.dp, start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                itemsIndexed(assignments) { index, assignment ->
                    CardHolder(
                        chipText = StringVO.Plain(assignment.status.value),
                        text = assignment.title,
                        dateText = assignment.date,
                        onDuplicateMenuItemClick = {
                            Toast.makeText(
                                context,
                                "Dublicate index: $index",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        onClearMenuItemClick = {
                            Toast.makeText(
                                context,
                                "Clean index: $index",
                                Toast.LENGTH_SHORT
                            ).show()

                            isDialogueIsVisible = true
                        },
                        onClickToggle = { toggleValue ->
                            Toast.makeText(
                                context,
                                "Toggle action with $toggleValue and index: $index",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
        }

        if (isDialogueIsVisible) {
            FoldingScreen()
            ConformationDialog(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 28.dp),
                onDismissRequest = {
                    Toast.makeText(context, "On dismiss dialogue", Toast.LENGTH_SHORT).show()
                    isDialogueIsVisible = !isDialogueIsVisible
                },
                onConfirmation = {
                    Toast.makeText(context, "On confirm dialogue", Toast.LENGTH_SHORT).show()
                    isDialogueIsVisible = !isDialogueIsVisible
                },
                headerText = buildString {
                    append("Are you sure you want \n")
                    append("to delete this task?\n")
                },
                dialogText = buildString {
                    append("All your entered data will be\n")
                    append("permanently removed.")
                },
                dismissButtonText = "Cancel",
                confirmButtonText = "Yes, delete"
            )
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