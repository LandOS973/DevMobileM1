package com.thomaslandais.m1info.ex4

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tasks_list = findViewById<ListView>(R.id.listView_tasks)
        val adapter = AdapterTask(this)
        tasks_list.adapter = adapter
        tasks.addAll(FakeData.get_tasks())

        // add tasks to adapter
        for (task in tasks) {
            val priority = task.substring(1, 2)
            val taskName = task.substring(4)
            adapter.addTask(taskName, priority)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

}
