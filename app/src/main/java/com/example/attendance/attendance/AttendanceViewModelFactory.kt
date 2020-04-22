package com.example.attendance.attendance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.attendance.database.DAO

class AttendanceViewModelFactory(private val dao: DAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AttendanceViewModel::class.java)) {
            return AttendanceViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }

}