package com.frank.expensestracker.ui.expense.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.frank.expensestracker.databinding.AdapterSelectCateExpenseBinding
import com.frank.expensestracker.db.entities.Category

class SelectCategoryExpenseAdapter(): BaseAdapter() {

    var listCategories: List<Category> = emptyList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val inflater = LayoutInflater.from(parent?.context)
        val dataBinding = AdapterSelectCateExpenseBinding.inflate(inflater,parent,false)
        val category = getItem(position)
        dataBinding.category = category as Category
        return dataBinding.root
    }

    override fun getItem(position: Int): Any {
        return listCategories[position]
    }

    override fun getItemId(position: Int): Long {
        return listCategories[position].id.toLong()
    }

    override fun getCount(): Int {
        return listCategories.size
    }
}