package com.frank.expensestracker.ui.expense

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.frank.expensestracker.R

class ListExpensesFragment : Fragment() {

    companion object {
        fun newInstance() = ListExpensesFragment()
    }

    private lateinit var viewModel: ListExpensesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ListExpensesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_list_expenses, container, false)
    }

}
