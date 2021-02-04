package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myapplication.model.ToDo;
import com.example.myapplication.viewholder.ToDoViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class datos extends AppCompatActivity {


    private RecyclerView tasks;
    private FloatingActionButton addTask;
    FirebaseDatabase database;
    DatabaseReference todoDb;
    FirebaseRecyclerOptions<ToDo> options;
    FirebaseRecyclerAdapter<ToDo, ToDoViewHolder> adapter;
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
        database = FirebaseDatabase.getInstance();
        todoDb = database.getReference("tasks");
        tasks.setHasFixedSize(true);
        tasks.setLayoutManager(new LinearLayoutManager(this));
        showTask();

    }

    private void showTask() {
        options = new FirebaseRecyclerOptions.Builder<ToDo>()
                .setQuery(todoDb,ToDo.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<ToDo, ToDoViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ToDoViewHolder holder, int position, @NonNull ToDo model) {
                holder.text_task.setText(model.getTask());
                holder.text_priority.setText(model.getPriority());
            }

            @NonNull
            @Override
            public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View itemView = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.todo_row,viewGroup, false);
                return new ToDoViewHolder(itemView);
            }
        };
        tasks.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}