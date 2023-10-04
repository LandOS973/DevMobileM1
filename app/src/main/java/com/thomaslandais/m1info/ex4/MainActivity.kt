package com.thomaslandais.m1info.ex4

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tasks = mutableListOf<String>()

    private val registerTaskResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val taskName = data?.getStringExtra("taskName")
            val priority = data?.getStringExtra("priority")
            
            if (taskName != null && priority != null) {
                val task = "<$priority> $taskName"
                tasks.add(task)
                display_tasks(tasks)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tasks.addAll(FakeData.get_tasks()) // Remplacer par vos tâches existantes
        display_tasks(tasks)
    }

    private fun display_tasks(tasks: List<String>) {
        val tasks_list = findViewById<TextView>(R.id.textView)
        tasks_list.text = tasks.joinToString("\n")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action1 -> {
                val intent = Intent(this, registerTask::class.java)
                registerTaskResult.launch(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
