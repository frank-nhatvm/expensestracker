package com.frank.expensestracker.ui.expense.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.frank.expensestracker.databinding.AdapterDayExpenseBinding
import com.frank.expensestracker.models.DayExpense
import org.threeten.bp.OffsetDateTime

class DayExpenseAdapter(val itemEventHandler: ItemEventHandler) : ListAdapter<DayExpense, DayExpenseAdapter.ViewHolder>(DayExpenseDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dayExpense = getItem(position)
        holder.bind(dayExpense,itemEventHandler)
    }

    interface ItemEventListener{

        fun addNewExpense(date: OffsetDateTime)

        fun viewMoreExpense(date: OffsetDateTime)

        fun editExpense(expenseId: Long)
    }

    class ItemEventHandler(val itemEventListener: ItemEventListener){
        fun addNewExpense(date: OffsetDateTime) = itemEventListener.addNewExpense(date)

        fun viewMoreExpense(date: OffsetDateTime) = itemEventListener.viewMoreExpense(date)

        fun editExpense(expenseId: Long) = itemEventListener.editExpense(expenseId)
    }

    class ViewHolder private constructor(val dataBinding: AdapterDayExpenseBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {

                val inflater = LayoutInflater.from(parent.context)
                val dataBinding = AdapterDayExpenseBinding.inflate(inflater, parent, false)
                return ViewHolder(dataBinding)
            }
        }

        fun bind(dayExpense: DayExpense,itemEventHandler: ItemEventHandler) {
            dataBinding.dayExpense = dayExpense
            dataBinding.itemEventHandler = itemEventHandler
            dataBinding.executePendingBindings()
        }

    }

    class DayExpenseDiff : DiffUtil.ItemCallback<DayExpense>() {
        override fun areItemsTheSame(oldItem: DayExpense, newItem: DayExpense): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: DayExpense, newItem: DayExpense): Boolean {
            return oldItem.totalAmount == newItem.totalAmount
        }
    }

}