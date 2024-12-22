package com.example.a18_3_lazyhorizontalgrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Start()
        }
    }
}

@Composable
fun Start() {

    val nutsTypes = listOf(
        R.drawable.chestnut,
        R.drawable.nut,
        R.drawable.nuts,
        R.drawable.pine_nut,
        R.drawable.pistachio,
        R.drawable.walnut,
    )

    val rowsCount = 5000
    val nuts: MutableList<Pair<Int, Color>> = MutableList(rowsCount * 3) { Pair(0, Color.White) }
    val winColor = Color.LightGray

    for (i in nuts.indices) {
        nuts[i] = Pair(nutsTypes[Random.nextInt(nutsTypes.size)], Color.White)

        if (i % 3 == 2) {
            if (nuts[i].first == nuts[i - 1].first && nuts[i].first == nuts[i - 2].first) {
                nuts[i] = Pair(nuts[i].first, winColor)
                nuts[i - 1] = Pair(nuts[i].first, winColor)
                nuts[i - 2] = Pair(nuts[i].first, winColor)
            }
        }
    }

    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                ) {
                    items(nuts.size) { id ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 20.dp)
                                .background(nuts[id].second)
                        ) {
                            if (nuts[id].second == winColor) {
                                Text(
                                    text = "WIN!",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color.Red
                                )
                            }
                            Image(
                                painter = painterResource(id = nuts[id].first),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    Start()
}