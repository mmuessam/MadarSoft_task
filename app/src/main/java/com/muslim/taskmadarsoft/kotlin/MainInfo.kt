package com.muslim.taskmadarsoft.kotlin

import android.app.Activity
import android.content.Intent
import android.icu.text.IDNA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.muslim.taskmadarsoft.R
import com.muslim.taskmadarsoft.kotlin.adapters.InfoAdapter
import com.muslim.taskmadarsoft.kotlin.addinfo.Add_Info
import com.muslim.taskmadarsoft.kotlin.data.Info
import com.muslim.taskmadarsoft.kotlin.viewmodels.InfoViewModel
import kotlinx.android.synthetic.main.activity_info.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainInfo : AppCompatActivity() {


    private lateinit var viewModel: InfoViewModel
    companion object {
        const val ADD_NOTE_REQUEST = 1
        const val EDIT_NOTE_REQUEST = 2
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)



        buttonAddNote.setOnClickListener {
            startActivityForResult(
                    Intent(this, Add_Info::class.java),
                    ADD_NOTE_REQUEST
            )
        }

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        var adapter = InfoAdapter()
        recycler_view.adapter = adapter
        viewModel = ViewModelProviders.of(this).get(InfoViewModel::class.java)

        viewModel.getAllInfo().observe(this, Observer {
            adapter.submitList(it)

        })


        adapter.setOnItemClickListener(object : InfoAdapter.OnItemClickListener {
            override fun onItemClick(info: Info) {
                var intent = Intent(baseContext, Add_Info::class.java)
                intent.putExtra(Add_Info.EXTRA_ID, info.id)
                intent.putExtra(Add_Info.EXTRA_Name, info.name)
                intent.putExtra(Add_Info.EXTRA_Job, info.jobtitle)
                intent.putExtra(Add_Info.EXTRA_Age, info.age)
                intent.putExtra(Add_Info.EXTRA_GG, info.gender)

                startActivityForResult(intent, EDIT_NOTE_REQUEST)
            }
    })
}







    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            val info = Info(
                    data!!.getStringExtra(Add_Info.EXTRA_Name),
                    data.getStringExtra(Add_Info.EXTRA_Age),
                    data.getStringExtra(Add_Info.EXTRA_Job ),
                    data.getStringExtra(Add_Info.EXTRA_GG )

                    )
            viewModel.insert(info)

            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            val id = data?.getIntExtra(Add_Info.EXTRA_ID, -1)

            if (id == -1) {
                Toast.makeText(this, "Could not update! Error!", Toast.LENGTH_SHORT).show()
            }

            val updateinfoe = Info(
                    data!!.getStringExtra(Add_Info.EXTRA_Name),
                    data.getStringExtra(Add_Info.EXTRA_Age),
                    data.getStringExtra(Add_Info.EXTRA_Job),
                    data.getStringExtra(Add_Info.EXTRA_GG)

                    )
            updateinfoe.id = data.getIntExtra(Add_Info.EXTRA_ID, -1)
            viewModel.update(updateinfoe)

        } else {
            Toast.makeText(this, "Note not saved!", Toast.LENGTH_SHORT).show()
        }


    }
}