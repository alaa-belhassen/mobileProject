package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;

public class cartpayment extends AppCompatActivity {

    CardForm cardForm;
    Button buy ;
    AlertDialog.Builder alertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartpayment);

        deliverydb mydb = new deliverydb(this);
        Bundle extra = this.getIntent().getExtras();

        cardForm = findViewById(R.id.card_form);
        buy = findViewById(R.id.btnBuy);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .actionLabel("Purchase")
                .setup(cartpayment.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardForm.isValid()) {
                    alertBuilder = new AlertDialog.Builder(cartpayment.this);
                    alertBuilder.setTitle("Confirm before purchase");
                    alertBuilder.setMessage("Card number:" + cardForm.getCardNumber() + "\n" +
                            "Card expiry date:" + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                            "Card CVV:" + cardForm.getCvv() + "\n" +
                            "Postal code:" + cardForm.getPostalCode() + "\n" +
                            "phone number:" + cardForm.getMobileNumber());
                    alertBuilder.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Toast.makeText(cartpayment.this, "thank you for purchase", Toast.LENGTH_LONG).show();

                            if (!mydb.insertUser(extra.getString("user"), extra.getString("vehicleType"),extra.getDouble("prix"))){
                                Toast.makeText(getApplication(),"Delevery demand failed", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplication(),"Delevery done successfully",Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }
                    });
                    alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();

                } else {
                    Toast.makeText(cartpayment.this, "Please complete the form", Toast.LENGTH_LONG).show();
                }
            }
        });
    }}