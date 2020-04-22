package com.example.attendance.database

import androidx.lifecycle.LiveData
import androidx.room.*

// todo change all methods that return list to livedata
@Dao
interface DAO {

    //    For AttendanceFragment
    @Query("SELECT * FROM Student")
    suspend fun getStudents(): List<Student>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun markAttendance(attendanceRecord: AttendanceRecord)

    //    For AddStudentFragment - after save is pressed
    @Insert(onConflict = OnConflictStrategy.REPLACE)    //todo objection: not working
    suspend fun addStudents(student: List<Student>)

    //    For OldRecordsFragment
    @Query("SELECT DISTINCT date FROM AttendanceRecord")
    suspend fun getDates(): List<String>

    @Transaction
    @Query("SELECT * FROM AttendanceRecord WHERE date = :date")
    suspend fun getOldRecords(date: String): List<AttendanceRecordWithStudents>

    //    testing
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSingleStudent(student: Student)

    @Query("SELECT * FROM Student WHERE :id = student_id")
    suspend fun getSingleStudent(id: String) : Student

    @Query("SELECT * FROM AttendanceRecord")
    suspend fun getAllRecords(): List<AttendanceRecord>

    @Query("DELETE FROM Student")
    suspend fun clearStudent()

    @Query("DELETE FROM AttendanceRecord")
    suspend fun clearAttendanceRecord()



}



