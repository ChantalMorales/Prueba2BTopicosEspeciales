package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

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

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals("Actualizar")){
            showUpdateDialog(adapter.getRef(item.getOrder()).getKey(),adapter.getItem(item.getOrder()));
        } else if (item.getTitle().equals("Eliminar")) {
            deleteTask(adapter.getRef(item.getOrder()).getKey());
        }
        return super.onContextItemSelected(item);
    }

    private void deleteTask(String key) {
        todoDb.child(key).removeValue();
    }

    private void showUpdateDialog(String key, ToDo item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Actualizar");
        builder.setMessage("Porfavor actualice los campos");
        View update_layout = LayoutInflater.from(this).inflate(R.layout.custom_layout,null);
        EditText edt_update_task = update_layout.findViewById(R.id.edit_update_task);
        EditText edt_update_priority = update_layout.findViewById(R.id.edit_update_priority);
        edt_update_task.setText(item.getTask());
        edt_update_priority.setText(item.getPriority());
        builder.setView(update_layout);
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String task = edt_update_task.getText().toString();
                String priority = edt_update_priority.getText().toString();
                ToDo todo = new ToDo(task,priority);
                todoDb.child(key).setValue(todo);
                Toast.makeText(datos.this, "Documento actualizado", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.delete_all){
            todoDb.removeValue();
        }
        return super.onOptionsItemSelected(item);
    }
}