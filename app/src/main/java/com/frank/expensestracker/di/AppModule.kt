package com.frank.expensestracker.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.frank.expensestracker.common.Constant
import com.frank.expensestracker.db.ExpenseTrackerDB
import com.frank.expensestracker.db.daos.CategoryDao
import com.frank.expensestracker.db.daos.ExpenseDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): ExpenseTrackerDB {
        return Room.databaseBuilder(context, ExpenseTrackerDB::class.java, Constant.DB_NAME)
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideCategoryDao(db: ExpenseTrackerDB): CategoryDao {
        return db.categoryDao()
    }

    @Singleton
    @Provides
    fun provideExpenseDao(db: ExpenseTrackerDB): ExpenseDao {
        return db.expenseDao()
    }

}