package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class datos extends AppCompatActivity {


    private String FIREBASE_URL = "https://pruebatopicos-2a888-default-rtdb.firebaseio.com/";
    private RecyclerView tasks;
    private FloatingActionButton addTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        tasks=(RecyclerView) findViewById(R.id.tasks);
        addTask=(FloatingActionButton) findViewById(R.id.addTask);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(datos.this,EditActivity.class);
                startActivity(i);
            }
        });
    }

    public void submitTask(View view){
        FirebaseDatabase database = FirebaseDatabase.getInstance(FIREBASE_URL);
        DatabaseReference myRef = database.getReference("tareas");
        myRef.setValue("Hello, World!");
    }
}