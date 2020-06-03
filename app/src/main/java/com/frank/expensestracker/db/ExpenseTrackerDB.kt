package com.frank.expensestracker.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.frank.expensestracker.db.daos.CategoryDao
import com.frank.expensestracker.db.daos.ExpenseDao
import com.frank.expensestracker.db.entities.Category
import com.frank.expensestracker.db.entities.Expense

@Database(entities = [Category::class,Expense::class],version = 1,exportSchema = false)
@TypeConverters(DBConverters::class)
abstract  class ExpenseTrackerDB : RoomDatabase(){

    abstract fun categoryDao(): CategoryDao

    abstract fun expenseDao(): ExpenseDao

}