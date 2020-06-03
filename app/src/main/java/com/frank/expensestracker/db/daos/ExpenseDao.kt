package com.frank.expensestracker.db.daos

import androidx.room.*
import com.frank.expensestracker.db.entities.Expense

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expense")
    fun getAllExpenses(): List<Expense>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(expense: Expense)

    @Update
    fun update(expense: Expense)

}