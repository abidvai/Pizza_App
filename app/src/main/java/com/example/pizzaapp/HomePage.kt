package com.example.pizzaapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pizzaapp.GifCoomposable.GifImage
import com.example.pizzaapp.Navigation.ScreenRoute
import com.example.pizzaapp.ui.theme.homepageBg
import com.example.pizzaapp.ui.theme.popbar_1
import com.example.pizzaapp.ui.theme.topbarContent

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

    val pizzaList = PizzaList


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
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = popbar_1,
                                unfocusedLabelColor = popbar_1,
                            )
                        )
                    } else {
                        Text(text = "Pizza Hut",
                            color = topbarContent,
                            fontSize = 24.sp,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold)
                    }
                },
                actions = {
                    Box(modifier = Modifier.padding(4.dp)) {
                        IconButton(onClick = {
                            isSearchBarEx = !isSearchBarEx
                        }
                        ) {
                            Icon(Icons.Default.Search, contentDescription = "SearchBar",
                                modifier = Modifier.size(40.dp))
                        }
                    }
                    Box(modifier = Modifier.padding(8.dp)) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Default.Notifications, contentDescription = "Notification",
                                modifier = Modifier.size(40.dp))
                        }
                    }
                },
                navigationIcon = {
                    Box(modifier = Modifier.padding(6.dp)) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Default.Menu, contentDescription = "MenuBar",
                                modifier = Modifier.size(40.dp))
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = popbar_1,
                    titleContentColor = topbarContent,
                    navigationIconContentColor = topbarContent,
                    actionIconContentColor = topbarContent
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = popbar_1,
                contentColor = Color.Red,
                tonalElevation = 8.dp,
                modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
            ){
                IconButton(onClick = {
                                     navController.navigate(ScreenRoute.Home.route)
                },
                    modifier = Modifier.padding(start = 10.dp)) {
                    Icon(Icons.Default.Home, contentDescription = "Homebar",
                        modifier = Modifier.size(50.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    navController.navigate(ScreenRoute.Profile.route)
                }) {
                    GifImage(resourceId = R.drawable.profile)
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    navController.navigate(ScreenRoute.Favourite.route)
                }) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite",
                        modifier = Modifier.size(50.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                                     navController.navigate(ScreenRoute.Settings.route)
                },
                    Modifier.padding(end = 10.dp)) {
                    Icon(Icons.Default.Settings, contentDescription = "Settings",
                        modifier = Modifier.size(50.dp))
                }
            }
        },
        floatingActionButton = {
            BadgedBox(badge = {
                if(CartCount > 0){
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ){
                        Text(text = "$CartCount")
                    }
                }
            }) {
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    containerColor = popbar_1,
                    contentColor = Color.Black,
                    modifier = Modifier.size(70.dp),
                    shape = MaterialTheme.shapes.large
                ){
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Shopping Cart",
                        modifier = Modifier.size(37.dp))
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .background(homepageBg)) {
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

