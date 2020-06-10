package com.frank.expensestracker.models

import androidx.room.Embedded
import androidx.room.Relation
import com.frank.expensestracker.db.entities.Category
import com.frank.expensestracker.db.entities.Expense

data class ExpenseAndCate(
    @Embedded val expense: Expense,
    @Relation(
        parentColumn = "cate_id",
        entityColumn = "id"
    )
    val category: Category
)