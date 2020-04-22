package com.example.attendance.addstudent

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.attendance.database.AttendanceDatabase
import com.example.attendance.database.DAO
import com.example.attendance.database.Student
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class AddStudentViewModel(private val dao: DAO) : ViewModel() {

    //    some temporary variables
    private var _studentsTemp = MutableLiveData<MutableList<Student>>()
    val studentsTemp: LiveData<MutableList<Student>> get() = _studentsTemp

    var _studentIdTemp = MutableLiveData<String>()
    val studentIdTemp: LiveData<String> get() = _studentIdTemp

    var _studentNameTemp = MutableLiveData<String>()
    val studentNameTemp: LiveData<String> get() = _studentNameTemp

    init {
        _studentsTemp.value = mutableListOf<Student>()
    }

    fun add() {
        var student = Student(_studentIdTemp.value!!, _studentNameTemp.value!!)
        _studentsTemp.value?.add(student)
        _studentsTemp.value = _studentsTemp.value   // just to trigger observer
    }

    //    can't run db operations on main thread
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    fun save() {
        for (student in _studentsTemp.value!!) {
            uiScope.launch {
                withContext(Dispatchers.IO) {
                    dao.addSingleStudent(student)
                    Log.i("AddStudentViewModel", "Students: (${dao.getStudents()}")
                }
            }
        }


    }
}