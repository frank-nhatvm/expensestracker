package com.frank.expensestracker.ui.category.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.frank.expensestracker.databinding.AdapterCategoryBinding
import com.frank.expensestracker.db.entities.Category

class CategoriesAdapter constructor(val itemEventHandler: ItemEventHandler) :
    ListAdapter<Category, CategoriesAdapter.CategoriesViewHolder>(CategoryDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category, itemEventHandler)
    }

    class CategoriesViewHolder private constructor(val dataBinding: AdapterCategoryBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        companion object {
            fun from(parent: ViewGroup): CategoriesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val dataBinding = AdapterCategoryBinding.inflate(layoutInflater, parent, false)
                return CategoriesViewHolder(dataBinding)
            }
        }

        fun bind(category: Category, itemEventHandler: ItemEventHandler) {
            dataBinding.category = category
            dataBinding.itemHandler = itemEventHandler
            dataBinding.executePendingBindings()
        }

    }

    class ItemEventHandler(private val itemEventListener: ItemEventListener) {

        fun deleteCategory(category: Category) = itemEventListener.deleteCategory(category)

        fun editCategory(category: Category) = itemEventListener.editCategory(category)

    }

    interface ItemEventListener {
        fun deleteCategory(category: Category)

        fun editCategory(category: Category)
    }

    class CategoryDiff : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }
    }


}