package com.unewexp.adventurizer.ui.components

import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.unewexp.adventurizer.Activity
import com.unewexp.adventurizer.ui.theme.AdventurizerTheme
import kotlin.math.roundToInt

@Composable
fun ActivityCard(
    activity: Activity,
    onLike: () -> Unit,
    onDisLike: () -> Unit,
    onSwipeUp: () -> Unit,
    onSwipeDown: () -> Unit
){
    var offsetY by remember { mutableFloatStateOf(0f) }
    val swipeThreshold = 100.dp.value

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .offset { IntOffset(0, offsetY.roundToInt()) }
                .pointerInput(Unit) {
                    detectVerticalDragGestures(
                        onDragEnd = {
                            when {
                                offsetY < -swipeThreshold -> {
                                    onSwipeUp()
                                    offsetY = 0f
                                }
                                offsetY > swipeThreshold -> {
                                    onSwipeDown()
                                    offsetY = 0f
                                }
                                else -> offsetY = 0f
                            }
                        },
                        onVerticalDrag = { _, dragAmount ->
                            offsetY += dragAmount
                        }
                    )
                },
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = when (activity.category) {
                                "cooking" -> Icons.Default.MailOutline
                                "travel" -> Icons.Default.Place
                                else -> Icons.Default.Person
                            },
                            contentDescription = null,
                            modifier = Modifier.size(24.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = activity.title,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    DetailRow("Категория:", activity.category)
                    Spacer(modifier = Modifier.height(10.dp))
                    DetailRow("Сложность:", activity.difficulty)
                    Spacer(modifier = Modifier.height(10.dp))
                    DetailRow("Цена:", if (activity.price == 0f) "Бесплатно" else "%.2f ₽".format(activity.price))
                }
                Row(
                    modifier = Modifier.width(300.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    IconButton(onClick = onLike){
                        Icon(
                            Icons.Default.Favorite,
                            "В избранное",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(onClick = onDisLike){
                        Icon(
                            Icons.Default.Close,
                            "Не нравится",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityCardPreview(){
    AdventurizerTheme {
        ActivityCard(
            activity = Activity(
                id = "1",
                title = "Приготовить пасту",
                category = "cooking",
                difficulty = "Легко",
                price = 0f
            ),
            onLike = {},
            onDisLike = {},
            onSwipeUp = {},
            onSwipeDown = {}
        )
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = value.substring(0, 1).uppercase() + value.substring(1).lowercase(),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}