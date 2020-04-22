package com.example.attendance.attendance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.attendance.R
import com.example.attendance.addstudent.AddStudentViewModelFactory
import com.example.attendance.database.AttendanceDatabase
import com.example.attendance.databinding.FragmentAttendanceBinding

class AttendanceFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentAttendanceBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_attendance, container, false)

        binding.lifecycleOwner = this

        var dao = AttendanceDatabase.getInstance(requireNotNull(this.activity).application).dao
        val viewModelFactory = AttendanceViewModelFactory(dao)


        val viewModel =  ViewModelProviders.of(
            this, viewModelFactory).get(AttendanceViewModel::class.java)

        binding.viewModel = viewModel



        return binding.root
    }

}
