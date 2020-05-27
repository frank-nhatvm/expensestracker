package com.frank.expensestracker.ui.expense

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.frank.expensestracker.R

class ExpensesFragment : Fragment() {

    companion object {
        fun newInstance() = ExpensesFragment()
    }

    private lateinit var viewModel: ExpensesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ExpensesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_expenses, container, false)
    }

}
