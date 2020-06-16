package com.muslim.taskmadarsoft.Java.addinfoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.muslim.taskmadarsoft.R;

public
class add_infoo extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_Name = "EXTRA_Name";
    public static final String EXTRA_Job = "cEXTRA_Job";
    public static final String EXTRA_Age = "EXTRA_Age";
    public static final String EXTRA_GG = "EXTRA_GG";

    private EditText name, age, job, gg;
    private
    Button Save;

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_infoo );


        name = findViewById( R.id.name );
        age = findViewById( R.id.age );
        job = findViewById( R.id.job );
        gg = findViewById( R.id.gg );
        Save = findViewById( R.id.save );

        Save.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public
            void onClick(View v) {
                saveinfoo();
            }
        } );


//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit User Information");
            name.setText(intent.getStringExtra(EXTRA_Name));
            age.setText(intent.getStringExtra(EXTRA_Age));
            job.setText ( intent.getStringExtra(EXTRA_Job));
            gg.setText ( intent.getStringExtra(EXTRA_GG));
        } else {
            setTitle("Add User");
        }
    }




    private void saveinfoo() {
        if (name.getText().toString().trim().isEmpty() ||
                age.getText().toString().trim().isEmpty()||
                job.getText().toString().trim().isEmpty()||
                gg.getText().toString().trim().isEmpty()

        ) {
            Toast.makeText( this, "Can not insert empty Information!", Toast.LENGTH_SHORT).show();
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_Name, name.getText().toString());
        data.putExtra(EXTRA_Age, age.getText().toString());
        data. putExtra(EXTRA_Job, job.getText().toString());
        data.putExtra(EXTRA_GG, gg.getText().toString());




        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }



        setResult(RESULT_OK, data);
        finish();
    }


}