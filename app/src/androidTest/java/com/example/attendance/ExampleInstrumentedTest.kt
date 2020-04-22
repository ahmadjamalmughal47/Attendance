package com.example.attendance

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.attendance.database.*
//import com.example.attendance.database.AttendanceDatabase.Companion.getInstance
import org.hamcrest.CoreMatchers
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var attendanceDatabase: AttendanceDatabase
    private lateinit var dao: DAO

    @Before
    @Throws(IOException::class)
    fun connectDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        attendanceDatabase = Room.inMemoryDatabaseBuilder(
            context, AttendanceDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = attendanceDatabase.dao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        attendanceDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun testAddStudent() {
        dao.addSingleStudent(
            Student("14007065222", "Ahmad Jamal Mughal")
        )
        dao.addSingleStudent(
            Student("14007065202", "Jalal Jan Khan")
        )

        dao.addSingleStudent(
            Student("14007065211", "Muhammad Usman")
        )

        dao.markAttendance(
            AttendanceRecord("14007065222", "04/2/2013", true)
        )

        dao.markAttendance(
            AttendanceRecord("14007065202", "04/2/2013", true)
        )

        dao.markAttendance(
            AttendanceRecord("14007065211", "04/3/2013", true)
        )

        var recordsOfADate = dao.getOldRecords(dao.getDates().get(1))
        Log.i(
            "testAddStudent", (recordsOfADate.get(0).student.studentName)
        )
    }


}