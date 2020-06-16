package com.muslim.taskmadarsoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.muslim.taskmadarsoft.Java.Maininfoo;
import com.muslim.taskmadarsoft.kotlin.MainInfo;

public
class MainActivity extends AppCompatActivity {

    ImageView kot,java;
    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        kot = findViewById( R.id.kotlinsss );
        java = findViewById( R.id.java );
        java.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public
            void onClick(View v) {
                startActivity( new Intent( MainActivity.this, Maininfoo.class ) );

            }
        } );
        kot.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public
            void onClick(View v) {
                startActivity( new Intent( MainActivity.this, MainInfo.class ) );
            }
        } );

    }
}