package com.example.project;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class application extends AppCompatActivity {
    double addedTax=00;
    boolean famamenou=false;
    double prix=00;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.application);
        // getting data from activity
        deliverydb mydb = new deliverydb(this);

        EditText pos1= (EditText) findViewById(R.id.pos1);
        EditText pos2= (EditText) findViewById(R.id.pos2);
        TextView distance = (TextView) findViewById(R.id.distance);
        TextView receipt = (TextView) findViewById(R.id.receipt);
        Button btn = (Button) findViewById(R.id.button);
        Button card = (Button) findViewById(R.id.card);
        Button delivery = (Button) findViewById(R.id.delivery);
        Bundle extra = this.getIntent().getExtras();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (extra.getString("vehicleType").equals("truck")){
                    addedTax=50;
                }else if (extra.getString("vehicleType").equals("motorcycle")){
                    addedTax=20;
                }else if ( extra.getString("vehicleType").equals("van")){
                    addedTax=30;
                }
                if (pos1.getText().toString().equals("") || pos2.getText().toString().equals("")){
                    Toast.makeText(getApplication(),"enter positions", Toast.LENGTH_SHORT).show();
                }else{
                    double formula= (pos1.getText().toString().length()+pos2.getText().toString().length());
                    prix=formula * 2.250 + 40+addedTax;
                    distance.setText(String.valueOf(formula)+"km");
                    receipt.setText(String.valueOf(prix)+"dt");
                    famamenou=true;
                }

            }
        });
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (famamenou){
                    if ( mydb.insertUser(extra.getString("user"),extra.getString("vehicleType"),prix) == false){
                        Toast.makeText(getApplication(),"Delevery demand failed", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplication(),"Delevery done successfully",Toast.LENGTH_SHORT).show();
                        finish();
                    }


                }else{
                    Toast.makeText(getApplication(),"insert deliverry information first ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (famamenou) {
                    Intent card = new Intent(getApplication(), cartpayment.class);
                    card.putExtra("vehicleType", extra.getString("vehicleType"));
                    card.putExtra("user", extra.getString("user"));
                    card.putExtra("prix", prix);
                    startActivity(card);
                }else{
                    Toast.makeText(getApplication(),"insert deliverry information first ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
