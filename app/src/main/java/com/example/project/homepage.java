package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        TextView hi = (TextView) findViewById(R.id.textView);
        Bundle extra = this.getIntent().getExtras();
        hi.setText(getResources().getString(R.string.hi)+extra.getString("user"));
        ImageView truckImg = (ImageView) findViewById(R.id.truck);
        ImageView vanImg = (ImageView) findViewById(R.id.van);
        ImageView motorcycleImg = (ImageView) findViewById(R.id.motorcycle);
        ImageView cart = (ImageView) findViewById(R.id.cartimg);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),DisplayAllDelivery.class);
                intent.putExtra("user",extra.getString("user"));
                startActivity(intent);
            }
        });
        truckImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),application.class);
                intent.putExtra("vehicleType", "truck");
                intent.putExtra("user",extra.getString("user") );
                startActivity(intent);
            }
        });

        vanImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), application.class);
                intent.putExtra("vehicleType", "van");
                intent.putExtra("user",extra.getString("user") );
                startActivity(intent);
            }
        });

        motorcycleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), application.class);
                intent.putExtra("vehicleType", "motorcycle");
                intent.putExtra("user",extra.getString("user") );
                startActivity(intent);
            }
        });
    }
}