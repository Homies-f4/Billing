package com.example.billing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Order extends AppCompatActivity {

    ArrayList<Order1> list;
    RecyclerView recyclerView;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Order");
    MyAdapter1 myAdapter1;
    long i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter1 =new MyAdapter1(this, list, new MyAdapter1.OnOrderListener() {
            @Override
            public void onOrderClick(Order1 order1) {
                Intent i=new Intent(Order.this,Order_Details.class);
                i.putExtra("Ordernumber",order1.getOno());
                startActivity(i);
            }
        });
        recyclerView.setAdapter(myAdapter1);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                i=(snapshot.getChildrenCount());
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Order1 o= dataSnapshot.getValue(Order1.class);
                    list.add(o);
                }
                myAdapter1.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        Button btn = findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 =new Intent(Order.this, Add_Order.class);
                i2.putExtra("Count_Of_Orders",i);
                startActivity(i2);
            }
        });
    }
}