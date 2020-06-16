package com.muslim.taskmadarsoft.Java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.muslim.taskmadarsoft.Java.adapter.Infoodapter;
import com.muslim.taskmadarsoft.Java.addinfoo.add_infoo;
import com.muslim.taskmadarsoft.Java.data.Infoo;
import com.muslim.taskmadarsoft.Java.viewmodels.InfooViewModel;
import com.muslim.taskmadarsoft.R;

import java.util.List;

public
class Maininfoo extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    private InfooViewModel infooViewModel;
    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_maininfoo );


        FloatingActionButton buttonAddNote = findViewById( R.id.buttonAddNote);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Maininfoo.this, add_infoo.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });


        RecyclerView recyclerView = findViewById( R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager( this));
        recyclerView.setHasFixedSize(true);

        final Infoodapter adapter = new Infoodapter();
        recyclerView.setAdapter(adapter);



        infooViewModel = ViewModelProviders.of( this).get( InfooViewModel.class);
        infooViewModel.getAllinfoo().observe( this, new Observer <List <Infoo>>( ) {
            @Override
            public
            void onChanged(List <Infoo> infoos) {
                adapter.submitList(infoos);

            }
        } );



        adapter.setOnItemClickListener(new Infoodapter.OnItemClickListener() {
            @Override
            public void onItemClick(Infoo infoo) {
                Intent intent = new Intent(Maininfoo.this, add_infoo.class);
                intent.putExtra(add_infoo.EXTRA_ID, infoo.getId());
                intent.putExtra(add_infoo.EXTRA_Name, infoo.getName());
                intent.putExtra(add_infoo.EXTRA_Job, infoo.getJobtitle());
                intent.putExtra(add_infoo.EXTRA_Age, infoo.getAge());
                intent.putExtra(add_infoo.EXTRA_GG, infoo.getGender());

                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });

    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String name= data.getStringExtra(add_infoo.EXTRA_Name);
            String age = data.getStringExtra(add_infoo.EXTRA_Age);
            String job = data.getStringExtra(add_infoo.EXTRA_Job);
            String gg  = data.getStringExtra(add_infoo.EXTRA_GG);


            Infoo infoo = new Infoo(name,
                                 age,
                                 job,
                                 gg  );
            infooViewModel.insert(infoo);

            Toast.makeText( this, "Note saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(add_infoo.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String name= data.getStringExtra(add_infoo.EXTRA_Name);
            String age = data.getStringExtra(add_infoo.EXTRA_Age);
            String job = data.getStringExtra(add_infoo.EXTRA_Job);
            String gg  = data.getStringExtra(add_infoo.EXTRA_GG);


            Infoo infoo = new Infoo(name,
                                    age,
                                    job,
                                    gg  );
            infoo.setId(id);
            infooViewModel.update(infoo);

            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }

}