package com.frank.expensestracker.repositories

import com.frank.expensestracker.db.daos.CategoryDao
import com.frank.expensestracker.db.entities.Category
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryDao: CategoryDao) {

    val categories = categoryDao.getAllCategories()

    fun getCategoryById(category_id: Int): Category{
        return categoryDao.getCategoryById(category_id)
    }

    fun addNewCategory(category: Category) {
        categoryDao.insert(category)
    }

    fun deleteCategory(category: Category) {
        categoryDao.delete(category)
    }

    fun updateCategory(category: Category) {
        categoryDao.update(category)
    }

}