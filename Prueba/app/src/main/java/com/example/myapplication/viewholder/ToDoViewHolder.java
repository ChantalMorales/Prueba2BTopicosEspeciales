package com.example.myapplication.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class ToDoViewHolder extends RecyclerView.ViewHolder {

    public TextView text_task, text_priority;


    public ToDoViewHolder(@NonNull View itemView) {
        super(itemView);
        text_task = itemView.findViewById(R.id.text_task);
        text_priority = itemView.findViewById(R.id.text_ptiority);
    }
}
