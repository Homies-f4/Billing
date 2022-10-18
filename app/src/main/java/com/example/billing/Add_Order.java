package com.example.billing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Add_Order extends AppCompatActivity {
    EditText tno;
    Getter_Setter_Orders_List o;
    LocalDate dateObj;
    DateTimeFormatter formatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        Bundle b2= getIntent().getExtras();
        long count = b2.getLong("Count_Of_Orders");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dateObj = LocalDate.now();
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        }
        String date = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = dateObj.format(formatter);
        }
        System.out.println(date);
        StringBuilder newdate= new StringBuilder();
        newdate.append(date);

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
                Bundle extras = new Bundle();
                Intent i=new Intent(Add_Order.this,Order_Details.class);
                extras.putString("Ordernumber",o.getOno());
                extras.putLong("tablenumber",o.getTno());
                i.putExtras(extras);
                startActivity(i);
                finish();
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
