package com.frank.expensestracker.ui.expense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.frank.expensestracker.repositories.ExpenseRepository
import com.frank.expensestracker.utils.Event
import javax.inject.Inject

class ListExpensesViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    val recentExpenses = expenseRepository.recentExpenses



    val isEmpty : LiveData<Boolean> = Transformations.map(recentExpenses){
        it.isEmpty()
    }

    private var _dataLoading = MutableLiveData<Boolean>(false)
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading


    private var _addNewExpense = MutableLiveData<Event<Boolean>>(Event(false))
    val addNewExpense: LiveData<Event<Boolean>>
        get() = _addNewExpense

    fun addNewExpenseAction() {
        _addNewExpense.value = Event(true)
    }

    fun getDayExpenses(){

    }


}
