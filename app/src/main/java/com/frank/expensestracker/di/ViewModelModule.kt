package com.frank.expensestracker.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.frank.expensestracker.ui.category.CategoriesViewModel
import com.frank.expensestracker.ui.category.CategoryViewModel
import com.frank.expensestracker.ui.expense.ExpensesViewModel
import com.frank.expensestracker.ui.expense.ListExpensesViewModel
import com.frank.expensestracker.ui.report.ReportViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    abstract fun bindCategoriesViewModel(categoriesViewModel: CategoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    abstract fun bindCategoryViewModel(categoryViewModel: CategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExpensesViewModel::class)
    abstract fun bindExpensesViewModel(expensesViewModel: ExpensesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListExpensesViewModel::class)
    abstract fun bindListExpensesViewModel(listExpensesViewModel: ListExpensesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReportViewModel::class)
    abstract  fun bindReportViewModel(reportViewModel: ReportViewModel): ViewModel
}