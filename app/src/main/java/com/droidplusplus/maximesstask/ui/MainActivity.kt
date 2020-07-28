package com.droidplusplus.maximesstask.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.droidplusplus.maximesstask.R
import com.droidplusplus.maximesstask.adapter.DataListAdapter
import com.droidplusplus.maximesstask.data.database.DataItemRoomDatabase
import com.droidplusplus.maximesstask.data.entity.DataItem
import com.droidplusplus.maximesstask.repository.DataItemRepositoryImpl
import com.droidplusplus.maximesstask.utils.Constants
import com.droidplusplus.maximesstask.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: DataItemViewModel

    private val mAdapter: DataListAdapter by lazy { DataListAdapter() }

    private var mTempSelectionList: MutableList<DataItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel =
            ViewModelProvider(
                this, DataItemViewModelFactory(
                    DataItemRepositoryImpl(
                        DataItemRoomDatabase.getDBInstance(this).dataItemDao()
                    )
                )
            ).get(
                DataItemViewModel::class.java
            )

        setUpListener()

        loadData()
    }

    private fun setUpListener() {
        showBtn.setOnClickListener {
            mTempSelectionList?.takeIf { it.isNotEmpty() }?.let { data ->
                var tempSalaryCount: Int? = null
                data.forEach {
                    tempSalaryCount = tempSalaryCount?.plus(it.salary)
                }

                toast("Sum of salaries is: $tempSalaryCount")
            }
        }
    }

    private fun loadData() {
        mViewModel.getAllNotes().observe(this, Observer {
            dataListRv.adapter = mAdapter
            mAdapter.mlistener = this::handleItemClick
            mAdapter.submitList(it)
        })
    }

    private fun handleItemClick(actionType: Int, dataItem: DataItem) {
        when (actionType) {
            Constants.EDIT_ACTION -> {
                navigateToAddEditScreen(true, dataItem)
            }

            Constants.DELETE_ACTION -> {
                deleteItem(dataItem)
            }

            Constants.SELECT_ACTION -> {
                mTempSelectionList.add(dataItem)
            }

            Constants.UNSELECT_ACTION -> {
                mTempSelectionList.remove(dataItem)
            }
        }
    }

    private fun deleteItem(dataItem: DataItem) {
        mViewModel.delete(dataItem)
    }

    private fun navigateToAddEditScreen(isUpdateAction: Boolean, dataItem: DataItem) {
        val intent = Intent(this, AddEditItemActivity::class.java)
        intent.putExtra(Constants.DATAITEM_BUNDLE, dataItem)
        intent.putExtra(Constants.ISUPDATE_ACTION_BUNDLE, isUpdateAction)
        startActivity(intent)
    }
}
