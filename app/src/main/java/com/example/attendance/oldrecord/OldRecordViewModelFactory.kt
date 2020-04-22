package com.example.attendance.oldrecord

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.attendance.database.DAO

class OldRecordViewModelFactory(private val dao: DAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OldRecordViewModel::class.java)) {
            return OldRecordViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }

}