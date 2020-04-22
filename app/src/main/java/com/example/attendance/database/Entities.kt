package com.example.attendance.database

import androidx.room.*

@Entity
data class Student(
    @PrimaryKey
    @ColumnInfo(name = "student_id")
    val studentID: String,

    @ColumnInfo(name = "first_name")
    val studentName: String
)

@Entity(primaryKeys = ["attendance_student_id", "date"])
data class AttendanceRecord(

    @ColumnInfo(name = "attendance_student_id")
    val attendanceStudentID: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "present")
    val present : Boolean
)


data class AttendanceRecordWithStudents(
    @Embedded val attendanceRecord: AttendanceRecord,
    @Relation(
        parentColumn = "attendance_student_id",
        entityColumn = "student_id"
    )
    val student: Student
)