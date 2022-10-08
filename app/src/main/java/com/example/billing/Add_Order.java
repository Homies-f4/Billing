package com.example.billing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Order extends AppCompatActivity {
    EditText tno;
    Getter_Setter_Orders_List o;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        Bundle b2= getIntent().getExtras();
        final long[] count = {b2.getLong("Count_Of_Orders")};

        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Order");
        o= new Getter_Setter_Orders_List();
        tno = findViewById(R.id.editTextNumberSigned);
        Button btn1= findViewById(R.id.button5);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count[0] = count[0] +1;
                Long p=Long.parseLong(tno.getText().toString().trim());
                o.setTno(p);
                o.setStatus("active");
                o.setOno((int)count[0]);
                db.child(String.valueOf(count[0])).setValue(o);
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
