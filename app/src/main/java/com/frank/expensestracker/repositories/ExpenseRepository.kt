package com.frank.expensestracker.repositories

import com.frank.expensestracker.db.ExpenseTrackerDB
import javax.inject.Inject

class ExpenseRepository @Inject constructor(
    private val db: ExpenseTrackerDB
) {
}