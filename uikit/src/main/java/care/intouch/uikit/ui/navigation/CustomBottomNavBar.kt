package care.intouch.uikit.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import care.intouch.uikit.R
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun NavBottomBarPlusButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(70.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(InTouchTheme.colors.mainGreen)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onClick.invoke() }
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_plus_large),
            contentDescription = null,
            tint = InTouchTheme.colors.input
        )
    }
}

@Composable
@Preview(showBackground = true)
fun NavBottomBarPlusButtonPreview() {
    InTouchTheme {
        NavBottomBarPlusButton(
            onClick = {}
        )
    }
}

@Composable
fun NavBottomComplexElement(
    navController: NavHostController? = null,
    screenRoute: String,
    onClick: () -> Unit,
    text: String,
    painter: Painter,
    focusTint: Color,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .width(75.dp)
            .height(56.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    navController?.navigate(screenRoute) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }

                    onClick.invoke()
                }
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = focusTint,
            modifier = modifier.padding(bottom = 5.dp)
        )
        Text(
            text = text,
            color = focusTint,
            style = InTouchTheme.typography.tabBar,
            modifier = Modifier.padding(bottom = 4.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview(showBackground = true)
fun NavBottomComplexElementPreview() {
    InTouchTheme {
        NavBottomComplexElement(
            screenRoute = "",
            onClick = { /*TODO*/ },
            text = "Home",
            painter = painterResource(id = R.drawable.icon_home),
            focusTint = InTouchTheme.colors.mainGreen,
        )
    }
}

@Composable
fun CustomBottomNavBar(
    navController: NavHostController? = null,
    screenRoute1: String = "",
    screenRoute2: String = "",
    screenRoute3: String = "",
    screenRoute4: String = "",
    screenRoute5: String = "",
    onFocusTint: Color = InTouchTheme.colors.mainGreen,
    outFocusTint: Color = InTouchTheme.colors.mainGreen40,
    firstItemText: String = "Home",
    secondItemText: String = "My plan",
    thirdItemText: String = "My diary",
    fourthItemText: String = "Profile",
    firstItemImage: Painter = painterResource(id = R.drawable.icon_home),
    secondItemImage: Painter = painterResource(id = R.drawable.icon_plan_notebook),
    thirdItemImage: Painter = painterResource(id = R.drawable.icon_diary),
    fourthItemImage: Painter = painterResource(id = R.drawable.icon_profile),
    firstItemClick: () -> Unit = {},
    secondItemClick: () -> Unit = {},
    thirdItemClick: () -> Unit = {},
    fourthItemClick: () -> Unit = {},
    onPlusItemClick: () -> Unit = {}
) {
    ConstraintLayout(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {

        var selectedElementId: ElementId by rememberSaveable {
            mutableStateOf(ElementId.HOME_ID)
        }


        val (homeTag, progressTag, plusTag, myPlanTag, additionalTag, box) = createRefs()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.White)
                .constrainAs(box) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        NavBottomComplexElement(
            navController = navController,
            screenRoute = screenRoute1,
            onClick = {
                selectedElementId = ElementId.HOME_ID
                firstItemClick.invoke()
            },
            text = firstItemText,
            painter = firstItemImage,
            focusTint = if (selectedElementId == ElementId.HOME_ID) onFocusTint
            else outFocusTint,
            modifier = Modifier
                .constrainAs(homeTag) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )

        NavBottomComplexElement(
            navController = navController,
            screenRoute = screenRoute5,
            onClick = {
                selectedElementId = ElementId.PROFILE_ID
                fourthItemClick.invoke()
            },
            text = fourthItemText,
            painter = fourthItemImage,
            focusTint = if (selectedElementId == ElementId.PROFILE_ID) onFocusTint
            else outFocusTint,
            modifier = Modifier
                .constrainAs(additionalTag) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        )

        NavBottomBarPlusButton(
            modifier = Modifier.constrainAs(plusTag) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                top.linkTo(parent.top)
            }
        ) {
            selectedElementId = ElementId.PLUS_ID
            onPlusItemClick.invoke()
        }

        NavBottomComplexElement(
            navController = navController,
            screenRoute = screenRoute2,
            onClick = {
                selectedElementId = ElementId.PLAN_ID
                secondItemClick.invoke()
            },
            text = secondItemText,
            painter = secondItemImage,
            focusTint = if (selectedElementId == ElementId.PLAN_ID) onFocusTint
            else outFocusTint,
            modifier = Modifier
                .constrainAs(progressTag) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(homeTag.end)
                    end.linkTo(plusTag.start)
                }
        )

        NavBottomComplexElement(
            navController = navController,
            screenRoute = screenRoute4,
            onClick = {
                selectedElementId = ElementId.DIARY_ID
                thirdItemClick.invoke()
            },
            text = thirdItemText,
            painter = thirdItemImage,
            focusTint = if (selectedElementId == ElementId.DIARY_ID) onFocusTint
            else outFocusTint,
            modifier = Modifier
                .constrainAs(myPlanTag) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(plusTag.end)
                    end.linkTo(additionalTag.start)
                }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CustomBottomNavBarPreview() {
    InTouchTheme {
        CustomBottomNavBar()
    }
}

enum class ElementId {
    HOME_ID, PLAN_ID, PLUS_ID, DIARY_ID, PROFILE_ID;
}