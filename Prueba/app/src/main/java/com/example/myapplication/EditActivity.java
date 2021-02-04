package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.model.ToDo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditActivity extends AppCompatActivity {

    private EditText edit_task;
    private EditText edit_priority;
    private Button btn_add;
    FirebaseDatabase database;
    DatabaseReference todoDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        edit_task=(EditText) findViewById(R.id.edit_task);
        edit_priority=(EditText) findViewById(R.id.edit_priority);
        btn_add=(Button) findViewById(R.id.btn_add);
        database = FirebaseDatabase.getInstance();
        todoDb = database.getReference("tasks");
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFirebase();
            }
        });
    }

    private void saveToFirebase() {
        String task = edit_task.getText().toString();
        String priority = edit_priority.getText().toString();
        if (!TextUtils.isEmpty(task) && !TextUtils.isEmpty(priority)){
            ToDo todo = new ToDo(task,priority);
            todoDb.push().setValue(todo).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(EditActivity.this, "Documento añadido exitosamente", Toast.LENGTH_SHORT).show();
                    edit_task.setText("");
                    edit_priority.setText("");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(EditActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(EditActivity.this, "Todos los campos deben ser llenados", Toast.LENGTH_SHORT).show();
        }
    }


}