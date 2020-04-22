package com.example.attendance.addstudent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.attendance.R
import com.example.attendance.database.AttendanceDatabase
import com.example.attendance.database.Student
import com.example.attendance.databinding.FragmentAddStudentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

class AddStudentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentAddStudentBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_student, container, false
        )

        var dao = AttendanceDatabase.getInstance(requireNotNull(this.activity).application).dao
        val viewModelFactory = AddStudentViewModelFactory(dao)
        val viewModel: AddStudentViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(AddStudentViewModel::class.java)

        binding.viewModel = viewModel
//        binding.lifecycleOwner = this
        var adapter = AddStudentAdapter()
        binding.listStudentsAdd.layoutManager = LinearLayoutManager(this.context)
        binding.listStudentsAdd.adapter = adapter

//        these are temporary observers to test the values
        viewModel.studentsTemp.observe(this, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })

        return binding.root
    }
}
