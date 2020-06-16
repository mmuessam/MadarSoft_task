package com.muslim.taskmadarsoft.kotlin.addinfo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.muslim.taskmadarsoft.R
import kotlinx.android.synthetic.main.activity_add__info.*

class Add_Info : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "com.mogalabs.tagnotes.EXTRA_ID"
        const val EXTRA_Name = "com.mogalabs.tagnotes.EXTRA_Name"
        const val EXTRA_Job = "com.mogalabs.tagnotes.EXTRA_Job"
        const val EXTRA_Age = "com.mogalabs.tagnotes.EXTRA_Age"
        const val EXTRA_GG = "com.mogalabs.tagnotes.EXTRA_GG"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__info)





        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit User Information"
            name.setText(intent.getStringExtra(EXTRA_Name))
            age.setText(intent.getStringExtra(EXTRA_Age))
            job.setText ( intent.getStringExtra(EXTRA_Job))
            gg.setText ( intent.getStringExtra(EXTRA_GG))

        } else {
            title = "Add User"
        }




        save.setOnClickListener{
            saveNote()

        }




    }


    private fun saveNote() {
        if (name.text.toString().trim().isBlank() ||
                age.text.toString().trim().isBlank()||
                job.text.toString().trim().isBlank()||
                gg.text.toString().trim().isBlank()

                ) {
            Toast.makeText(this, "Can not insert empty Information!", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_Name, name.text.toString())
            putExtra(EXTRA_Age, age.text.toString())
            putExtra(EXTRA_Job, job.text.toString())
            putExtra(EXTRA_GG, gg.text.toString())

            if (intent.getIntExtra(EXTRA_ID, -1) != -1) {
                putExtra(EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1))
            }
        }

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}