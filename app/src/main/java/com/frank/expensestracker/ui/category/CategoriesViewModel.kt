package com.frank.expensestracker.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frank.expensestracker.db.daos.CategoryDao
import com.frank.expensestracker.db.entities.Category
import com.frank.expensestracker.repositories.CategoryRepository
import com.frank.expensestracker.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    val categoryRepository: CategoryRepository
) : ViewModel() {

    val categories = categoryRepository.categories

    private var _addNewCategory = MutableLiveData<Event<Boolean>>()
    val addNewCategory: LiveData<Event<Boolean>>
        get() = _addNewCategory

    private var _dataLoading = MutableLiveData<Boolean>(false)
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading


    fun addNewCategoryAction() {
        _addNewCategory.value = Event(true)
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            _dataLoading.value = true
            deleteCategoryFromDB(category)
            _dataLoading.value = false
        }
    }

    private suspend fun deleteCategoryFromDB(category: Category) {
        withContext(Dispatchers.IO) {
            categoryRepository.deleteCategory(category)
        }
    }


}
