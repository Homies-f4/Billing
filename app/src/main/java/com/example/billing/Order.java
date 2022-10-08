package com.example.billing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Order extends AppCompatActivity {

    ArrayList<Getter_Setter_Orders_List> list;
    RecyclerView recyclerView;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Order");
    MyAdapter_Orders_List myAdapterOrderslist;
    long i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapterOrderslist =new MyAdapter_Orders_List(this, list, new MyAdapter_Orders_List.OnOrderListener() {
            @Override
            public void onOrderClick(Getter_Setter_Orders_List orderGettersetter) {
                Intent i1=new Intent(Order.this,Order_Details.class);
                Bundle extras = new Bundle();
                extras.putString("Ordernumber",orderGettersetter.getOno());
                extras.putLong("tablenumber",orderGettersetter.getTno());
                i1.putExtras(extras);
                startActivity(i1);
            }
        });
        recyclerView.setAdapter(myAdapterOrderslist);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                i=(snapshot.getChildrenCount());
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Getter_Setter_Orders_List o= dataSnapshot.getValue(Getter_Setter_Orders_List.class);
                    if(String.valueOf(o.getStatus()).equals("active")) {
                        list.add(o);
                    }
                }
                myAdapterOrderslist.notifyDataSetChanged();
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