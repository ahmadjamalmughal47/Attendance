package com.example.attendance.oldrecord

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.attendance.R
import com.example.attendance.database.AttendanceRecord
import com.example.attendance.database.AttendanceRecordWithStudents
import com.example.attendance.database.Student

//import androidx.recyclerview.widget.ListAdapter

class OldRecordAdapter : ListAdapter<AttendanceRecordWithStudents, OldRecordViewHolder>(OldRecordDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OldRecordViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.single_old_record, parent, false)
        return OldRecordViewHolder(view)

    }

    override fun onBindViewHolder(holder: OldRecordViewHolder, position: Int) {
        val item = getItem(position)
        holder.studentId.text = item.student.studentID
        holder.studentAttendance.text = item.attendanceRecord.present.toString()
        holder.studentName.text = item.student.studentName
    }

}

class OldRecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var studentAttendance = itemView.findViewById(R.id.old_record_attendance) as TextView
    var studentId = itemView.findViewById(R.id.old_record_id) as TextView
    var studentName = itemView.findViewById(R.id.old_record_name) as TextView
}

class OldRecordDiffUtil: DiffUtil.ItemCallback<AttendanceRecordWithStudents>(){
    override fun areItemsTheSame(oldItem: AttendanceRecordWithStudents, newItem: AttendanceRecordWithStudents): Boolean {
        return oldItem.student.studentID == newItem.student.studentName && oldItem.attendanceRecord.date == newItem.attendanceRecord.date
    }

    override fun areContentsTheSame(oldItem: AttendanceRecordWithStudents, newItem: AttendanceRecordWithStudents): Boolean {
        return oldItem == newItem
    }

}