package com.example.billing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Add_Order extends AppCompatActivity {
    EditText tno;
    Getter_Setter_Orders_List o;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        Bundle b2= getIntent().getExtras();
        long count = b2.getLong("Count_Of_Orders");

        Date date = new Date();
        String date1 = String.valueOf(date.getDate());
        String mon=String.valueOf(date.getMonth()+1);
        String year=String.valueOf(date.getYear()+1900);
        StringBuilder newdate= new StringBuilder();
        newdate.append(date1);
        newdate.append(mon);
        newdate.append(year);


        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Order");
        o= new Getter_Setter_Orders_List();
        tno = findViewById(R.id.editTextNumberSigned);
        Button btn1= findViewById(R.id.button5);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newdate.append(count+1);

                Long p=Long.parseLong(tno.getText().toString().trim());
                o.setTno(p);
                o.setStatus("active");
                o.setOno(String.valueOf(newdate));
                db.child(String.valueOf(newdate)).setValue(o);
                Intent i=new Intent(Add_Order.this,Order_Details.class);
                i.putExtra("Ordernumber",o.getOno());
                startActivity(i);
            }
        });

        Button btn2= findViewById(R.id.button6);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Add_Order.this, Order.class));
            }
        });

    }
}
