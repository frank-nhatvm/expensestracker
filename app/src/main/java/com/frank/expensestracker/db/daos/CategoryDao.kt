package com.frank.expensestracker.db.daos

import androidx.room.*
import com.frank.expensestracker.db.entities.Category

@Dao
interface CategoryDao {

    @Query(" SELECT * from category")
    fun getAllCategories(): List<Category>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(category: Category)

    @Update
    fun update(category: Category)




}