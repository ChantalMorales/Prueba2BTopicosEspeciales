package com.example.myapplication.viewholder;

import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class ToDoViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

    public TextView text_task, text_priority;


    public ToDoViewHolder(@NonNull View itemView) {
        super(itemView);
        text_task = itemView.findViewById(R.id.text_task);
        text_priority = itemView.findViewById(R.id.text_ptiority);
        itemView.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Seleccione:");
        menu.add(0,0,getAdapterPosition(),"Actualizar");
        menu.add(0,1,getAdapterPosition(),"Eliminar");
    }
}
