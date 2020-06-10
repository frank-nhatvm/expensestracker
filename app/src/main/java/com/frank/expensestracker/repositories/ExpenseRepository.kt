package com.frank.expensestracker.repositories

import com.frank.expensestracker.db.daos.ExpenseDao
import com.frank.expensestracker.db.entities.Expense
import javax.inject.Inject

class ExpenseRepository @Inject constructor(
    private val expenseDao: ExpenseDao
) {


    val recentExpenses = expenseDao.getRecentExpenses()


    fun saveExpense(expense: Expense){
        expenseDao.insert(expense)
    }

    fun updateExpense(expense: Expense){
        expenseDao.update(expense)
    }



}