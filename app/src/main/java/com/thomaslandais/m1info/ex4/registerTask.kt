package com.thomaslandais.m1info.ex4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class registerTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_task)
    }

    fun addTask(view: View) {
        // retrieve volue from ed_name
        val ed_name  = findViewById<EditText>(R.id.et_tache)
        val taskName = ed_name.text.toString()
        val radioGroup = findViewById<RadioGroup>(R.id.task_priority)
        val selectedId = radioGroup.checkedRadioButtonId
        if (selectedId != -1 && taskName != "") {
            Toast.makeText(this, "Tâche : $taskName, Priorité : $selectedId", Toast.LENGTH_LONG).show()
            val radioButton = findViewById<RadioButton>(selectedId)
            val priority = radioButton.text.toString()
            val intent = Intent(this, MainActivity::class.java) // replace MainActivity with the actual activity you want to send data to
            intent.putExtra("taskName", taskName)
            intent.putExtra("priority", priority)

            setResult(RESULT_OK,intent)
            finish()
        } else {
            Toast.makeText(this, "Tâche ou Priorité: Non définie mon reuf", Toast.LENGTH_LONG).show()
        }
    }
}