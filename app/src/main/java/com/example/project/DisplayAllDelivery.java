package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayAllDelivery extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> name, email, age;
    deliverydb DB;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_delivery);
        Bundle extra = this.getIntent().getExtras();
        DB = new deliverydb(this);
        name = new ArrayList<>();
        email = new ArrayList<>();
        age = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new Adapter(this, name, email, age);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata()
    {

        Cursor cursor = DB.getdata();
        if(cursor.getCount()==0)
        {
            Toast.makeText(DisplayAllDelivery.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                name.add(cursor.getString(1));
                email.add(cursor.getString(2));
                age.add(cursor.getString(3));
            }
        }
    }
}