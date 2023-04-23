package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    TextView email=(TextView) findViewById(R.id.newmail);
    TextView password=(TextView) findViewById(R.id.password);
    TextView register= (TextView) findViewById(R.id.register);
    Button login = (Button) findViewById(R.id.login);
    db mydb = new db (this);
    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if ( email.equals("") || password.equals("")){
                //correct
                Toast.makeText(MainActivity.this,"fill in all the fields",Toast.LENGTH_SHORT).show();

            }else{
                //error
                if ( mydb.checkLogin(email.getText().toString(),password.getText().toString()) ){
                    Toast.makeText(MainActivity.this,"login successful",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,homepage.class);
                    //preparing data to be sent in a bundle
                    Bundle data = new Bundle();
                    data.putString("user",email.getText().toString());
                    intent.putExtras(data);
                    //starting appmication
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"login Failed",Toast.LENGTH_SHORT).show();
                }

            }
        }
    });
    register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
            startActivity(intent);

        }
    });

    }
}