package com.frank.expensestracker.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime


@Entity(tableName = "expense")

class Expense(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "amount") val amount: Float = 0f,
    @ColumnInfo(name = "cate_id") val cateId: Int,
    @ColumnInfo(name = "created_date") val createdDate: OffsetDateTime? = null,
    @ColumnInfo(name = "updated_date") val updatedDate: OffsetDateTime? = null
)