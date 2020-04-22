package com.example.attendance.attendance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.attendance.R
import com.example.attendance.database.AttendanceDatabase
import com.example.attendance.databinding.FragmentOldRecordBinding
import com.example.attendance.oldrecord.OldRecordAdapter
import com.example.attendance.oldrecord.OldRecordViewModel
import com.example.attendance.oldrecord.OldRecordViewModelFactory

class OldRecordFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentOldRecordBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_old_record, container, false)

        binding.lifecycleOwner = this


        var dao = AttendanceDatabase.getInstance(requireNotNull(this.activity).application).dao
        val viewModelFactory = OldRecordViewModelFactory(dao)

        val viewModel =  ViewModelProviders.of(
            this, viewModelFactory).get(OldRecordViewModel::class.java)
        binding.viewModel = viewModel

        var adapter = OldRecordAdapter()
        binding.oldRecordList.adapter = adapter
        binding.oldRecordList.layoutManager = LinearLayoutManager(this.context)

        viewModel.currentRecords.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })

        return binding.root
    }

}
