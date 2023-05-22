package com.alekhin.fovmeapex.onboarding

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alekhin.fovmeapex.R
import com.alekhin.fovmeapex.navigation.graph.Graph
import com.alekhin.fovmeapex.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// region SCREEN

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavHostController) {
    val onBoardingDataList = OnBoardingData.getOnBoardingDataList()
    val state = rememberPagerState()
    val scope = rememberCoroutineScope()

    Scaffold(bottomBar = { OnBoardingBottomBar(state = state, onBoardingDataList = onBoardingDataList, navController = navController, times = onBoardingDataList.size, currentPage = state.currentPage, scope = scope) }) {
        OnBoardingContent(onBoardingDataList = onBoardingDataList, state = state, contentPadding = it)
    }
}

// endregion

// region COMPONENTS

// region CONTENT

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OnBoardingContent(onBoardingDataList: List<OnBoardingData>, state: PagerState, contentPadding: PaddingValues) {
    HorizontalPager(pageCount = onBoardingDataList.size, state = state, contentPadding = contentPadding) { index ->
        OnBoardingItem(onBoardingData = onBoardingDataList[index])
    }
}

@Composable
private fun OnBoardingItem(onBoardingData: OnBoardingData) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = onBoardingData.id), contentDescription = null, modifier = Modifier.padding(bottom = 32.dp))
        Text(text = stringResource(id = onBoardingData.titleText), modifier = Modifier.padding(bottom = 8.dp), fontSize = Typography.headlineMedium.fontSize)
        Text(text = stringResource(id = onBoardingData.descriptionText), modifier = Modifier.padding(horizontal = 16.dp), fontSize = Typography.bodyLarge.fontSize, textAlign = TextAlign.Center)
    }
}

// endregion

// region BOTTOM BAR

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OnBoardingBottomBar(state: PagerState, onBoardingDataList: List<OnBoardingData>, onBoardingViewModel: OnBoardingViewModel = hiltViewModel(), navController: NavHostController, times: Int, currentPage: Int, scope: CoroutineScope) {
    BottomAppBar(containerColor = Color.Transparent) {
        Box(modifier = Modifier.fillMaxWidth()) {
            SkipButton(visible = state.currentPage + 1 < onBoardingDataList.size) {
                completeOnBoardingProcess(onBoardingViewModel, navController)
            }

            HorizontalPagerIndicator(times = times, currentPage = currentPage)
            NextButton(id = if (state.currentPage + 1 < onBoardingDataList.size) R.string.next else R.string.get_started) {
                scope.launch {
                    if (state.currentPage + 1 < onBoardingDataList.size) state.animateScrollToPage(page = state.currentPage + 1) else completeOnBoardingProcess(onBoardingViewModel, navController)
                }
            }
        }
    }
}

// endregion

// region SKIP BUTTON

@Composable
private fun BoxScope.SkipButton(visible: Boolean, onClick: () -> Unit) {
    AnimatedVisibility(visible = visible) {
        Button(onClick = onClick, modifier = Modifier
            .align(Alignment.CenterStart)
            .padding(start = 16.dp)) {
            Text(text = stringResource(id = R.string.skip))
        }
    }
}

// endregion

// region HORIZONTAL PAGER INDICATOR

@Composable
private fun BoxScope.HorizontalPagerIndicator(times: Int, currentPage: Int) {
    Row(modifier = Modifier.align(Alignment.Center)) {
        repeat(times = times) {
            PageIndicator(selected = it == currentPage)
        }
    }
}

@Composable
private fun PageIndicator(selected: Boolean) {
    Box(modifier = Modifier
        .padding(horizontal = 8.dp)
        .clip(shape = CircleShape)
        .background(color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant)
        .height(height = 8.dp)
        .width(
            width = animateDpAsState(
                targetValue = if (selected) 8.dp else 8.dp,
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
            ).value
        ))
}

// endregion

// region NEXT BUTTON

@Composable
private fun BoxScope.NextButton(@StringRes id: Int, onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier
        .align(Alignment.CenterEnd)
        .padding(end = 16.dp)) {
        Text(text = stringResource(id = id))
    }
}

// endregion

// endregion

private fun completeOnBoardingProcess(onBoardingViewModel: OnBoardingViewModel, navController: NavHostController) {
    onBoardingViewModel.saveOnBoardingState()
    navController.navigate(route = Graph.MAIN) {
        popUpTo(route = Graph.MAIN) {
            inclusive = true
        }
    }
}

// endregion

// region PREVIEW

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    val navController = rememberNavController()
    val onBoardingDataList = OnBoardingData.getOnBoardingDataList()
    val state = rememberPagerState()
    val scope = rememberCoroutineScope()

    Scaffold(bottomBar = { OnBoardingBottomBar(state = state, onBoardingDataList = onBoardingDataList, navController = navController, times = onBoardingDataList.size, currentPage = state.currentPage, scope = scope) }) {
        OnBoardingContent(onBoardingDataList = onBoardingDataList, state = state, contentPadding = it)
    }
}

@Preview(showBackground = true)
@Composable
private fun OnBoardingItem1Preview() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = OnBoardingData.getOnBoardingDataList()[0].id), contentDescription = null, modifier = Modifier.padding(bottom = 32.dp))
        Text(text = stringResource(id = OnBoardingData.getOnBoardingDataList()[0].titleText), modifier = Modifier.padding(bottom = 8.dp), fontSize = Typography.headlineMedium.fontSize)
        Text(text = stringResource(id = OnBoardingData.getOnBoardingDataList()[0].descriptionText), modifier = Modifier.padding(horizontal = 16.dp), fontSize = Typography.bodyLarge.fontSize, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
private fun OnBoardingItem2Preview() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = OnBoardingData.getOnBoardingDataList()[1].id), contentDescription = null, modifier = Modifier.padding(bottom = 32.dp))
        Text(text = stringResource(id = OnBoardingData.getOnBoardingDataList()[1].titleText), modifier = Modifier.padding(bottom = 8.dp), fontSize = Typography.headlineMedium.fontSize)
        Text(text = stringResource(id = OnBoardingData.getOnBoardingDataList()[1].descriptionText), modifier = Modifier.padding(horizontal = 16.dp), fontSize = Typography.bodyLarge.fontSize, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
private fun OnBoardingItem3Preview() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = OnBoardingData.getOnBoardingDataList()[2].id), contentDescription = null, modifier = Modifier.padding(bottom = 32.dp))
        Text(text = stringResource(id = OnBoardingData.getOnBoardingDataList()[2].titleText), modifier = Modifier.padding(bottom = 8.dp), fontSize = Typography.headlineMedium.fontSize)
        Text(text = stringResource(id = OnBoardingData.getOnBoardingDataList()[2].descriptionText), modifier = Modifier.padding(horizontal = 16.dp), fontSize = Typography.bodyLarge.fontSize, textAlign = TextAlign.Center)
    }
}

// endregion