package care.intouch.app.feature.plan.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.app.feature.home.presentation.ui.FoldingScreen
import care.intouch.app.feature.plan.domain.models.AssignmentStatus
import care.intouch.app.feature.plan.domain.useCase.mockAssignments
import care.intouch.app.feature.plan.presentation.models.PlanScreenEvent
import care.intouch.app.feature.plan.presentation.models.PlanScreenState
import care.intouch.app.feature.plan.presentation.viewmodel.PlanScreenViewModel
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.cards.ConformationDialog
import care.intouch.uikit.ui.screens.my_plan.my_plan.CardHolder
import care.intouch.uikit.ui.screens.my_plan.my_plan.ChipsRow
import care.intouch.uikit.ui.screens.my_plan.my_plan.PlanHeader

@Composable
fun PlanScreen(
    onTaskListItemClick: () -> Unit,
    onBackArrowClick: () -> Unit
) {
    val viewModel: PlanScreenViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    PlanScreen(
        state = state,
        onEvent = { planScreenEvent ->
            viewModel.onEvent(planScreenEvent)
        },
        onTaskListItemClick = onTaskListItemClick,
        onBackArrowClick = onBackArrowClick
    )
}

@Composable
fun PlanScreen(
    state: PlanScreenState,
    onEvent: (PlanScreenEvent) -> Unit,
    onTaskListItemClick: () -> Unit,
    onBackArrowClick: () -> Unit
) {
    val context = LocalContext.current

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
                onBackArrowClick = {
                    onBackArrowClick.invoke()
                }
            )

            ChipsRow(
                modifier = Modifier.padding(horizontal = 28.dp),
                onFirstChipsClick = {
                    onEvent(PlanScreenEvent.FilterAssignmentsEvent(
                        assignmentStatus = AssignmentStatus.SHOW_ALL
                    ))
                },
                onSecondChipsClick = {
                    onEvent(PlanScreenEvent.FilterAssignmentsEvent(
                        assignmentStatus = AssignmentStatus.TO_DO
                    ))
                },
                onThirdChipsClick = {
                    onEvent(PlanScreenEvent.FilterAssignmentsEvent(
                        assignmentStatus = AssignmentStatus.IN_PROGRESS
                    ))
                },
                onFourthChipsClick = {
                    onEvent(PlanScreenEvent.FilterAssignmentsEvent(
                        assignmentStatus = AssignmentStatus.DONE
                    ))
                }
            )

            LazyColumn(
                modifier = Modifier.padding(top = 28.dp, start = 28.dp, end = 28.dp),
                verticalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                itemsIndexed(state.filteredAssignments) { index, assignment ->
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

                            onEvent(PlanScreenEvent.SetDialogueIsVisibleEvent(isVisible = true))
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

        if (state.isDialogueVisible) {
            FoldingScreen()
            ConformationDialog(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 28.dp),
                onDismissRequest = {
                    Toast.makeText(context, "On dismiss dialogue", Toast.LENGTH_SHORT).show()
                    onEvent(PlanScreenEvent.SetDialogueIsVisibleEvent(isVisible = false))
                },
                onConfirmation = {
                    Toast.makeText(context, "On confirm dialogue", Toast.LENGTH_SHORT).show()
                    onEvent(PlanScreenEvent.SetDialogueIsVisibleEvent(isVisible = false))
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
            state = PlanScreenState(
                filteredAssignments = mockAssignments
            ),
            onEvent = {},
            onTaskListItemClick = {},
            onBackArrowClick = {}
        )
    }
}