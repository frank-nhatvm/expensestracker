package com.frank.expensestracker.ui.expense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frank.expensestracker.db.entities.Expense
import com.frank.expensestracker.repositories.CategoryRepository
import com.frank.expensestracker.repositories.ExpenseRepository
import com.frank.expensestracker.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class ExpensesViewModel @Inject constructor(
    val expenseRepository: ExpenseRepository,
    val categoryRepository: CategoryRepository
) : ViewModel() {

    var expenseName = MutableLiveData<String>()
    var cateId = MutableLiveData<Int>()
    var expenseAmount = MutableLiveData<String>()

    private var currentExpense: Expense? = null

    val categories = categoryRepository.categories

    private var _dataLoading = MutableLiveData<Boolean>(false)
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    private var _backToPreviousPage = MutableLiveData<Event<Boolean>>(Event(false))
    val backToPreviousPage: LiveData<Event<Boolean>>
        get() = _backToPreviousPage

    fun saveExpenseAction() {

        val expenseId = currentExpense?.id ?: -1
        val amount = expenseAmount.value?.toFloat() ?: 0f
        val cate_id = cateId.value ?: 0

        val expense = if (expenseId != -1L) {
            Expense(
                id = expenseId,
                name = expenseName.value,
                cateId = cate_id,
                amount = amount,
                createdDate = currentExpense?.createdDate,
                updatedDate = OffsetDateTime.now()
            )
        } else {

            Expense(
                name = expenseName.value,
                cateId = cate_id,
                amount = amount,
                createdDate = OffsetDateTime.now(),
                updatedDate = OffsetDateTime.now()
            )
        }

        viewModelScope.launch {
            _dataLoading.value = true
            if (expenseId != -1L) {
                updateExpenseToDb(expense)
            } else {
                saveExpenseToDB(expense)
            }
            _dataLoading.value = false
            _backToPreviousPage.value = Event(true)
        }

    }

    private suspend fun saveExpenseToDB(expense: Expense) {
        withContext(Dispatchers.IO) {
            expenseRepository.saveExpense(expense)
        }
    }

    private suspend fun updateExpenseToDb(expense: Expense) {
        withContext(Dispatchers.IO) {
            expenseRepository.updateExpense(expense)
        }
    }

    fun selectCategory(position: Int) {
        val category = categories.value?.get(position)
        cateId.value = category?.id ?: -1
    }

}
