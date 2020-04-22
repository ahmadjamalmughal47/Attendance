package com.example.attendance.oldrecord

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.attendance.database.AttendanceRecord
import com.example.attendance.database.AttendanceRecordWithStudents
import com.example.attendance.database.DAO
import kotlinx.coroutines.*

class OldRecordViewModel(val dao: DAO) : ViewModel() {

//    Problem: not showing date
    //    can't run db operations on main thread
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var dates = mutableListOf<String>()

    private var _currentRecords = MutableLiveData<List<AttendanceRecordWithStudents>>()
    val currentRecords: LiveData<List<AttendanceRecordWithStudents>> get() = _currentRecords

    var currentIndex = 0

    init {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                dates = dao.getDates() as MutableList<String>
                if (dates.isNotEmpty())
                    _currentRecords.postValue(dao.getOldRecords(dates[currentIndex]))
            }
        }
    }

    fun back() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                if (currentIndex != 0) {
                    currentIndex--
                    _currentRecords.postValue(dao.getOldRecords(dates[currentIndex]))
                }
            }
        }
    }

    fun next() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                if (currentIndex != (dates.size-1)) {
                    currentIndex++
                    _currentRecords.postValue(dao.getOldRecords(dates[currentIndex]))
                }
            }
        }
    }
}