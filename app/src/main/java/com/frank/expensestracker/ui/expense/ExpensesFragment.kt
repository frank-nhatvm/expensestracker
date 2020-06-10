package com.frank.expensestracker.ui.expense

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.frank.expensestracker.R
import com.frank.expensestracker.databinding.FragmentExpensesBinding
import com.frank.expensestracker.di.ViewModelFactory
import com.frank.expensestracker.extensions.expenseTrackerApplication
import com.frank.expensestracker.ui.expense.adapters.SelectCategoryExpenseAdapter
import com.frank.expensestracker.utils.EventObserver
import javax.inject.Inject

class ExpensesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ExpensesViewModel

    private lateinit var dataBinding: FragmentExpensesBinding

    private lateinit var adapterSelectCategory: SelectCategoryExpenseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = FragmentExpensesBinding.inflate(inflater)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        dataBinding.viewModel = viewModel


        viewModel.categories.observe(viewLifecycleOwner, Observer {
            adapterSelectCategory = SelectCategoryExpenseAdapter()
            adapterSelectCategory.listCategories = it
            dataBinding.spinnerCateExpense.adapter = adapterSelectCategory
        })

        dataBinding.spinnerCateExpense.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.selectCategory(position)
            }
        }


        viewModel.backToPreviousPage.observe(viewLifecycleOwner,EventObserver{
            if(it){
                backToPreviousPage()
            }
        })

        return dataBinding.root
    }

    private fun backToPreviousPage(){
        this.findNavController().popBackStack()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        expenseTrackerApplication.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ExpensesViewModel::class.java)
    }

}
