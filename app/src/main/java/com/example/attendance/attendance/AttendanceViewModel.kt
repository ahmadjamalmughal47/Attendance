package com.example.attendance.attendance

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.attendance.database.AttendanceRecord
import com.example.attendance.database.AttendanceRecordWithStudents
import com.example.attendance.database.DAO
import com.example.attendance.database.Student
import kotlinx.coroutines.*
import java.time.LocalDate
import java.time.LocalDateTime

class AttendanceViewModel(val dao: DAO) : ViewModel() {


    var studentsList = mutableListOf<Student>()
    var currentIndex = 0
    var currentStudent = MutableLiveData<Student>()

    //    can't run db operations on main thread
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                studentsList = dao.getStudents() as MutableList<Student>
                if (studentsList.isNotEmpty()) currentStudent.postValue(studentsList[currentIndex])
                Log.i(
                    "markPresent: ",
                    dao.getAllRecords().toString()
                )
            }
        }
    }

    fun present() {
        markPresent()
    }

    private fun markPresent() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                dao.markAttendance(
                    AttendanceRecord(
                        currentStudent.value!!.studentID,
                        java.sql.Date(System.currentTimeMillis()).toString(),
                        true
                    )
                )

                Log.i(
                    "markPresent: ",
                    dao.getAllRecords().toString()
                )

                goNext()

            }
        }
    }

    fun absent() {
        markAbsent()
    }

    fun back() {
        goBack()
    }

    fun next() {
        goNext()
    }

    private fun markAbsent() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                dao.markAttendance(
                    AttendanceRecord(
                        currentStudent.value!!.studentID,
                        java.sql.Date(System.currentTimeMillis()).toString(),
                        false
                    )
                )

                Log.i(
                    "markAbsent: ",
                    dao.getOldRecords(java.sql.Date(System.currentTimeMillis()).toString()).toString()
                )

                goNext()


            }
        }
    }

    private fun goBack() {
        if (currentIndex != 0) {
            currentIndex--
            currentStudent.postValue(studentsList[currentIndex])
        }
    }

    private fun goNext() {
        if (currentIndex != (studentsList.size - 1)) {
            currentIndex++
            currentStudent.postValue(studentsList[currentIndex])
        }

    }
}