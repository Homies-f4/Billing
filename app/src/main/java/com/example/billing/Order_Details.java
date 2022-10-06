package com.example.billing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Order_Details extends AppCompatActivity {
    TextView order_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Bundle b1= getIntent().getExtras();
        final long Ono = b1.getLong("Ordernumber");
        order_number = findViewById(R.id.OrderNumber);
        order_number.setText(String.valueOf(Ono));
    }
}