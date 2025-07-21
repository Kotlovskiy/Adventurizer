package com.unewexp.adventurizer

import com.unewexp.adventurizer.data.local.ActivityDbModel

data class Activity(
    val id: String,
    val title: String,
    val category: String,
    val difficulty: String,
    val price: Float
)

fun Activity.toDbModel() = ActivityDbModel(
    serverId = this.id,
    title = this.title,
    category = this.category,
    difficulty = this.difficulty,
    price = this.price
)