package com.frank.expensestracker.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.frank.expensestracker.db.entities.Category

@Dao
interface CategoryDao {

    @Query(" SELECT * from category")
    fun getAllCategories(): LiveData<List<Category>>

    @Query("SELECT * FROM category WHERE id == :category_id")
    fun getCategoryById(category_id: Int): Category

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(category: Category)

    @Update
    fun update(category: Category)

    @Delete
    fun delete(category: Category)


}