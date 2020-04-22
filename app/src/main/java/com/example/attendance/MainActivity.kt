package com.example.attendance

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.attendance.database.AttendanceDatabase
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)
        var dao = AttendanceDatabase.getInstance(requireNotNull(this).application).dao

        uiScope.launch {
            withContext(Dispatchers.IO) {
                dao.clearAttendanceRecord()
                dao.clearStudent()

            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            item.itemId -> {
                navController.navigate(item.itemId)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}