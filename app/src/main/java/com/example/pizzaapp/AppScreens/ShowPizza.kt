package com.example.pizzaapp.AppScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizzaapp.ui.theme.topbar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ShowPizza(
    pizza: Pizza
) {

    val sheetState = rememberModalBottomSheetState()
    var sheetVisible by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .width(175.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onTertiary)
            ) {
                Image(
                    painter = painterResource(id = pizza.image), contentDescription = "Pizza",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = pizza.price,
                    style = TextStyle(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = topbar
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = pizza.name,
                    style = TextStyle(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = pizza.description,
                    style = TextStyle(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        sheetVisible = true
                    },
                    modifier = Modifier.padding(bottom = 20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                ) {
                    Text(text = "View Details")
                }
                if (sheetVisible) {
                    ModalBottomSheet(
                        onDismissRequest = { sheetVisible = false },
                        sheetState = sheetState,
                        tonalElevation = 100.dp,
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.fillMaxSize(),
                        windowInsets = BottomSheetDefaults.windowInsets.exclude(WindowInsets.navigationBars),

                        ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = pizza.image),
                                contentDescription = "Pizza",
                                modifier = Modifier
                                    .height(250.dp)
                                    .width(800.dp)
                                    .clip(RoundedCornerShape(40.dp))
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Row(modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly){
                                Column {
                                    Text(text = pizza.name,
                                        style = TextStyle(
                                            fontSize = 26.sp,
                                            fontWeight = FontWeight.Bold,
                                            fontSynthesis = FontSynthesis.All,
                                            color = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(text = pizza.price,
                                        color = Color.Red,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp)
                                }
                                Column {
                                    Text(modifier = Modifier.basicMarquee(),
                                        text = "Ingredients: ",
                                        style = TextStyle(fontSize = 20.sp,
                                            color = Color.Magenta,
                                            fontWeight = FontWeight.Bold))
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(text = "1. Mozzarella \n\n"+
                                            "2. Tomato \n\n"+
                                            "3. Basil")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

