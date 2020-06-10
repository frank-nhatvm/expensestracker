package com.frank.expensestracker.models


import org.threeten.bp.OffsetDateTime

data class DayExpense(
    val date: OffsetDateTime,
    val totalAmount: Float = 0f,
    val listExpenses: List<ExpenseAndCate>
)

