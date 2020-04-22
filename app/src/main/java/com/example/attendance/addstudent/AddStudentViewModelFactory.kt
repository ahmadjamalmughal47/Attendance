package com.example.attendance.addstudent

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.attendance.database.DAO

class AddStudentViewModelFactory(private val dao: DAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddStudentViewModel::class.java)) {
                return AddStudentViewModel(dao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")

    }

}