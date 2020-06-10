package com.frank.expensestracker.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.frank.expensestracker.db.entities.Expense
import com.frank.expensestracker.models.ExpenseAndCate

@Dao
interface ExpenseDao {


    @Transaction
    @Query("SELECT * FROM expense  ORDER BY datetime(created_date) DESC")
    fun getRecentExpenses(): LiveData<List<ExpenseAndCate>>

    @Query("SELECT * FROM expense")
    fun getAllExpenses(): LiveData<List<Expense>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(expense: Expense)

    @Update
    fun update(expense: Expense)

    @Delete
    fun delete(expense: Expense)

}