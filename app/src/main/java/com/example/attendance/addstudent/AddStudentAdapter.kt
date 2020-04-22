package com.example.attendance.addstudent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.attendance.R
import com.example.attendance.database.Student

//import androidx.recyclerview.widget.ListAdapter

class AddStudentAdapter : ListAdapter<Student, AddStudentViewHolder>(AddStudentDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddStudentViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.single_add_student_row, parent, false)
        return AddStudentViewHolder(view)

    }

    override fun onBindViewHolder(holder: AddStudentViewHolder, position: Int) {
        val item = getItem(position)
        holder.studentId.text = item.studentID
        holder.studentName.text = item.studentName

    }

}

class AddStudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var studentId = itemView.findViewById(R.id.add_student_id_textview) as TextView
    var studentName = itemView.findViewById(R.id.add_student_name_textview) as TextView
}

class AddStudentDiffUtil: DiffUtil.ItemCallback<Student>(){
    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem.studentID == newItem.studentID
    }

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem == newItem
    }

}