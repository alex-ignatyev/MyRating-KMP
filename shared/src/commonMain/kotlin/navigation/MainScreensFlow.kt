package navigation

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import screens.main.MainComponent
import screens.main.MainComponent.MainScreen.AddCategory
import screens.main.MainComponent.MainScreen.Feed
import screens.main.MainComponent.MainScreen.Profile
import screens.main.category.category_add.AddCategoryScreen
import screens.main.feed.FeedScreen
import ui.components.MRNavigationBar
import ui.components.TabNavigationItem

@Composable
internal fun MainScreensFlow(component: MainComponent, modifier: Modifier = Modifier) {

    var currentTab by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier,
        content = { paddingValues ->
            Children(
                stack = component.stack,
                modifier = modifier,
                animation = stackAnimation(
                    fade() + scale(
                        animationSpec = tween(durationMillis = 400),
                        frontFactor = 0.95F,
                        backFactor = 1.15F
                    )
                )
            ) {
                when (val instance = it.instance) {
                    is AddCategory -> {
                        AddCategoryScreen(instance.component, modifier.padding(paddingValues))
                    }

                    is Feed -> {
                        currentTab = 0
                        FeedScreen(instance.component, modifier.padding(paddingValues))
                    }

                    is Profile -> {
                        currentTab = 1
                        ProfileFlow(instance.component, modifier.padding(paddingValues))
                    }
                }
            }
        },
        bottomBar = {
            MRNavigationBar(onFloatingAction = {
                component.navigateToAddCategory()
            }) {
                TabNavigationItem(tab = FeedTab(), currentTab) {
                    component.navigateToFeedFlow()
                }
                TabNavigationItem(ProfileTab(), currentTab) {
                    component.navigateToProfileFlow()
                }
            }
        }
    )
}
