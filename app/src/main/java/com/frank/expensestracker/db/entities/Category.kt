package com.frank.expensestracker.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime

@Entity(tableName = "category")
class Category (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "icon") val icon: String? = null,
    @ColumnInfo(name = "color") val color: String? = null,
    @ColumnInfo(name = "created_date") val createdDate: OffsetDateTime? = null,
    @ColumnInfo(name = "updated_date") val updatedDate: OffsetDateTime? = null
)