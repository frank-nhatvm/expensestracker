package com.frank.expensestracker.ui.expense

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frank.expensestracker.R
import com.frank.expensestracker.databinding.FragmentListExpensesBinding
import com.frank.expensestracker.di.ViewModelFactory
import com.frank.expensestracker.extensions.expenseTrackerApplication
import com.frank.expensestracker.models.DayExpense
import com.frank.expensestracker.models.ExpenseAndCate
import com.frank.expensestracker.ui.expense.adapters.DayExpenseAdapter
import com.frank.expensestracker.utils.EventObserver
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class ListExpensesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ListExpensesViewModel

    private lateinit var dataBinding: FragmentListExpensesBinding

    private lateinit var adapter: DayExpenseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentListExpensesBinding.inflate(inflater)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.viewModel = viewModel

        viewModel.addNewExpense.observe(viewLifecycleOwner, EventObserver {
            if (it) {
                navigateToExpensePage()
            }
        })

        initRecycleview()

        viewModel.recentExpenses.observe(viewLifecycleOwner, Observer { cateAndExpenses ->


            val dayExpenses = cateAndExpenses.groupBy { it.expense.createdDate?.dayOfYear }

            val listDayExpense = mutableListOf<DayExpense>()
            var hadExpenseForToday = false
            for (dayExpense in dayExpenses) {
                val listCateAndExpense = dayExpense.value
                val date = listCateAndExpense.first().expense.createdDate ?: OffsetDateTime.now()
                if (date.dayOfMonth == OffsetDateTime.now().dayOfMonth) {
                    hadExpenseForToday = true
                }
                val totalAmount =
                    listCateAndExpense.sumByDouble { it.expense.amount.toDouble() }.toFloat()
                val newDayExpense = DayExpense(date, totalAmount, listCateAndExpense)
                listDayExpense.add(newDayExpense)
            }

            if (!hadExpenseForToday) {
                val todayExpense = DayExpense(OffsetDateTime.now(), 0f, listOf<ExpenseAndCate>())
                listDayExpense.add(todayExpense)
            }

            adapter.submitList(listDayExpense)

        })

        return dataBinding.root
    }

    private fun initRecycleview() {

        val manager = LinearLayoutManager(requireActivity())
        dataBinding.rcvExpenses.layoutManager = manager

        val itemDividerItemDecoration =
            DividerItemDecoration(requireActivity(), RecyclerView.VERTICAL)
        itemDividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.line_divider))
        dataBinding.rcvExpenses.addItemDecoration(itemDividerItemDecoration)

        val itemEventListener = object : DayExpenseAdapter.ItemEventListener {
            override fun addNewExpense(date: OffsetDateTime) {
                navigateToExpensePage()
            }

            override fun viewMoreExpense(date: OffsetDateTime) {

            }

            override fun editExpense(expenseId: Long) {

            }
        }

        adapter = DayExpenseAdapter(DayExpenseAdapter.ItemEventHandler(itemEventListener))
        dataBinding.rcvExpenses.adapter = adapter
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        expenseTrackerApplication.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ListExpensesViewModel::class.java)
    }

    private fun navigateToExpensePage() {
        this.findNavController()
            .navigate(ListExpensesFragmentDirections.actionListExpensesFragmentToExpensesFragment())
    }
}
