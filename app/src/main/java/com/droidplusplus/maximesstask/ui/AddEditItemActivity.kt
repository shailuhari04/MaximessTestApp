package com.droidplusplus.maximesstask.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.droidplusplus.maximesstask.R
import com.droidplusplus.maximesstask.data.database.DataItemRoomDatabase
import com.droidplusplus.maximesstask.data.entity.DataItem
import com.droidplusplus.maximesstask.repository.DataItemRepositoryImpl
import com.droidplusplus.maximesstask.utils.Constants
import kotlinx.android.synthetic.main.activity_add_edit_item.*


class AddEditItemActivity : AppCompatActivity() {

    private lateinit var mViewModel: DataItemViewModel

    private var inputData: DataItem? = null

    private var isUpdateAction: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_item)

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

        getBundleData()
        setUpInput()
        setUpListener()
    }

    private fun getBundleData() {
        isUpdateAction = intent?.hasExtra(Constants.ISUPDATE_ACTION_BUNDLE)?.takeIf { it }
            ?.let { intent?.getBooleanExtra(Constants.ISUPDATE_ACTION_BUNDLE, false) } ?: false

        inputData = intent?.getParcelableExtra<DataItem>(Constants.DATAITEM_BUNDLE)
    }

    private fun setUpInput() {
        if (isUpdateAction) {
            inputData?.let {
                nameEt.setText(it.name)
                salaryEt.setText(it.salary)
                designationEt.setText(it.designation)
            }
        }
    }

    private fun setUpListener() {
        addBtn.setOnClickListener {
            validateInput()?.let {
                addEditData(isUpdateAction, it)
            }
        }
    }

    private fun validateInput(): DataItem? {
        takeIf { !TextUtils.isEmpty(nameEt.text) }?.let {
            nameTil.error = null

            takeIf { !TextUtils.isEmpty(salaryEt.text) }?.let {
                salaryEt.error = null

                takeIf { !TextUtils.isEmpty(designationEt.text) }?.let {
                    designationEt.error = null

                    return DataItem(
                        0,
                        nameEt.text.toString(),
                        salaryEt.text.toString().toInt(),
                        designationEt.text.toString()
                    )
                } ?: run {
                    designationEt.requestFocus()
                    designationTil.error = "Please Enter Designation!!"
                }

            } ?: run {
                salaryEt.requestFocus()
                salaryTil.error = "Please Enter Salary!!"
            }
        } ?: run {
            nameEt.requestFocus()
            nameTil.error = "Please Enter Name!!"
        }

        return null
    }

    private fun addEditData(isUpdate: Boolean, dataItem: DataItem) {
        if (isUpdate) {
            mViewModel.update(dataItem)
        } else {
            mViewModel.insert(dataItem)
        }

        startActivity(Intent(this, MainActivity::class.java))
    }
}
