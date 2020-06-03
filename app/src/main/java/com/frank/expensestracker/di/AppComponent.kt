package com.frank.expensestracker.di

import android.app.Application
import android.content.Context
import com.frank.expensestracker.MainActivity
import com.frank.expensestracker.ui.category.CategoriesFragment
import com.frank.expensestracker.ui.category.CategoryFragment
import com.frank.expensestracker.ui.expense.ExpensesFragment
import com.frank.expensestracker.ui.expense.ListExpensesFragment
import com.frank.expensestracker.ui.report.ReportFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,ViewModelModule::class])
interface AppComponent{

    @Component.Factory
    interface  Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(categoryFragment: CategoryFragment)
    fun inject(categoriesFragment: CategoriesFragment)
    fun inject(expensesFragment: ExpensesFragment)
    fun inject(listExpensesFragment: ListExpensesFragment)
    fun inject(reportFragment: ReportFragment)

}