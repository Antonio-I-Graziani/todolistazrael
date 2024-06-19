package com.example.todolistazrael;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> todoList;
    private RecyclerView recyclerView;
    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new TodoAdapter(todoList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                showAddTodoDialog();
            }
        });
    }

    private void showAddTodoDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add a new taskk");

        final View customLayout = LayoutInflater.from(this).inflate(R.layout.dialog_add_todo, null);
        builder.setView(customLayout);

        builder.setPositiveButton("Add",(dialog, which) -> {
            EditText editText = customLayout.findViewById(R.id.editTextTask);
            String task = editText.getText().toString();
            if (!task.isEmpty()) {
                todoList.add(task);
                adapter.notifyItemInserted(todoList.size() - 1);
            } else {
                Toast.makeText(this, "Task can't be empty my fren", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }
}

