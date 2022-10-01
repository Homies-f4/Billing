package com.example.billing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddOrder extends AppCompatActivity {
    EditText tno;
    Order1 o;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);


        Button btn1= findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddOrder.this, OrderDetails.class));
                Bundle b2= getIntent().getExtras();
                final long[] count = {b2.getLong("Count_Of_Orders")};
                o= new Order1();

                DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Order");
                tno = findViewById(R.id.editTextNumberSigned);

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        count[0] = count[0] +1;
                        o.setTno(Long.parseLong(String.valueOf(tno)));
                        o.setStatus("active");
                        o.setOno((int) count[0]);
                        db.child(String.valueOf(count[0])).setValue(o);
                        startActivity(new Intent(AddOrder.this, OrderDetails.class));
                    }
                });
            }
        });

        Button btn2= findViewById(R.id.button5);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddOrder.this, Order.class));
            }
        });
    }
}