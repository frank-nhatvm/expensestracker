package com.frank.expensestracker.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frank.expensestracker.db.entities.Category
import com.frank.expensestracker.repositories.CategoryRepository
import com.frank.expensestracker.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    val categoryRepository: CategoryRepository
) : ViewModel() {

    var categoryName = MutableLiveData<String>()

    private var currentCategory: Category? = null

    private var _backToCategoriesPage = MutableLiveData<Event<Boolean>>()
    val backToCategoriesPage: LiveData<Event<Boolean>>
        get() = _backToCategoriesPage

    private var _dataLoading = MutableLiveData<Boolean>(false)
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    fun saveCategoryAction() {
        val id = currentCategory?.id ?: -1

        val category = if (id != -1) {
            Category(
                id = id,
                name = categoryName.value,
                createdDate = currentCategory?.createdDate,
                updatedDate = OffsetDateTime.now()
            )
        } else {
            Category(
                name = categoryName.value,
                createdDate = OffsetDateTime.now(),
                updatedDate = OffsetDateTime.now()
            )
        }

        viewModelScope.launch {
            _dataLoading.value = true
            if (id == -1) {
                saveCategoryToDb(category)
            } else {
                updateCategoryToDb(category)
            }
            _dataLoading.value = false
            _backToCategoriesPage.value = Event(true)
        }

    }

    private suspend fun saveCategoryToDb(category: Category) {
        withContext(Dispatchers.IO) {
            categoryRepository.addNewCategory(category)
        }
    }

    private suspend fun updateCategoryToDb(category: Category) {
        withContext(Dispatchers.IO) {
            categoryRepository.updateCategory(category)
        }
    }

    fun getCategoryById(category_id: Int) {
        viewModelScope.launch {
            _dataLoading.value = true
            getCategoryFromDb(category_id)
            categoryName.value = currentCategory?.name
            _dataLoading.value = false
        }
    }

    private suspend fun getCategoryFromDb(category_id: Int) {
        withContext(Dispatchers.IO) {
            currentCategory = categoryRepository.getCategoryById(category_id)
        }
    }


}
