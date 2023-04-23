package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db mydb = new db(this);
        TextView mail =(TextView) findViewById(R.id.newmail);
        TextView pass =(TextView) findViewById(R.id.password);
        TextView confirmPass =(TextView) findViewById(R.id.confirmpassword);
        Button register = (Button) findViewById(R.id.login);
        Button goBack = (Button) findViewById(R.id.goback);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( mail.equals("") || pass.equals("") || confirmPass.equals("")){
                    Toast.makeText(MainActivity2.this,"you need to fill all the inputs .",Toast.LENGTH_SHORT).show();
                }else {
                    if (mydb.checkUser(mail.getText().toString())){
                        Toast.makeText(MainActivity2.this,"user already exists",Toast.LENGTH_SHORT).show();
                    }else{
                        mydb.insertUser(mail.getText().toString(),pass.getText().toString());
                        Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity2.this,"registration succefull",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}