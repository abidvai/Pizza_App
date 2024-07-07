package com.example.pizzaapp.AppScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pizzaapp.GifCoomposable.GifImage
import com.example.pizzaapp.Navigation.ScreenRoute
import com.example.pizzaapp.R
import com.example.pizzaapp.ui.theme.popbar_1
import com.example.pizzaapp.ui.theme.topbarContent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavHostController) {
    val chipList = listOf("Starter", "Asian", "Belgium", "spain", "Pacha & Roast & Grill")
    var chipIndex by rememberSaveable { mutableIntStateOf(0) }

    var isSearchBarEx by rememberSaveable { mutableStateOf(false) }
    var searchText by rememberSaveable { mutableStateOf("") }
    var CartCount by rememberSaveable {
        mutableIntStateOf(0)
    }
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var navigationDrawerState by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()

    val pizzaList = PizzaList

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(){
                ModalDrawerSheet {
                    NavigationDrawerItem(
                        label = { Text("Profile") },
                        selected = false,
                        onClick = {
                            navController.navigate(ScreenRoute.Profile.route)
                            scope.launch { drawerState.close() }
                            navigationDrawerState = false
                        },
                        colors = NavigationDrawerItemDefaults.colors(

                        )
                    )
                }
            }
        }, scrimColor = Color.Transparent
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        if (isSearchBarEx) {
                            TextField(
                                value = searchText, onValueChange = {
                                    searchText = it
                                },
                                placeholder = {
                                    Text(text = "Search Anything")
                                },
                                maxLines = 1,
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = popbar_1,
                                    unfocusedLabelColor = popbar_1,
                                )
                            )
                        } else {
                            Text(
                                text = "Pizza Hut",
                                color = topbarContent,
                                fontSize = 24.sp,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    },
                    actions = {
                        Box(modifier = Modifier.padding(4.dp)) {
                            IconButton(onClick = {
                                isSearchBarEx = !isSearchBarEx
                            }
                            ) {
                                Icon(
                                    Icons.Outlined.Search, contentDescription = "Search",
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                        Box(modifier = Modifier.padding(8.dp)) {
                            IconButton(onClick = { CartCount++ }) {
                                GifImage(resourceId = R.drawable.notification_bell)
                            }
                        }
                    },
                    navigationIcon = {
                        Box(modifier = Modifier.padding(6.dp)) {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }) {
                                GifImage(resourceId = R.drawable.menu)
                            }
                        }

                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        titleContentColor = topbarContent,
                        navigationIconContentColor = topbarContent,
                        actionIconContentColor = topbarContent
                    )
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    tonalElevation = 10.dp,
                    contentColor = Color.Blue
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate(ScreenRoute.Home.route)
                        },
                        modifier = Modifier.padding(start = 10.dp)
                    ) {
                        GifImage(resourceId = R.drawable.maintenance)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            navController.navigate(ScreenRoute.Profile.route)
                        }
                    ) {
                        GifImage(resourceId = R.drawable.profile)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        navController.navigate(ScreenRoute.Favourite.route)
                    }) {
                        GifImage(resourceId = R.drawable.bookmark)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            navController.navigate(ScreenRoute.Settings.route)
                        },
                        Modifier.padding(end = 10.dp)
                    ) {
                        GifImage(resourceId = R.drawable.user)
                    }
                }
            },
            floatingActionButton = {
                BadgedBox(badge = {
                    if (CartCount > 0) {
                        Badge(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        ) {
                            Text(text = "$CartCount")
                        }
                    }
                }) {
                    FloatingActionButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.size(60.dp),
                        contentColor = MaterialTheme.colorScheme.surfaceTint
                    ) {
                        Icon(
                            Icons.Outlined.ShoppingCart, contentDescription = "Shopping Cart",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                LazyRow(modifier = Modifier.padding(8.dp)) {
                    items(chipList.size) { index ->
                        CustomChips(
                            title = chipList[index],
                            selected = chipIndex == index,
                            onValueChange = {
                                chipIndex = index
                                CartCount++
                            }
                        )
                    }
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(pizzaList.size) { index ->
                        val pizza = pizzaList[index]
                        ShowPizza(pizza = pizza)
                    }
                }
            }
        }
    }


}

