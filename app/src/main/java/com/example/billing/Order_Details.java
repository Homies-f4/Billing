package com.example.billing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Order_Details extends AppCompatActivity implements Add_Dish_After_Order.showDialog{
    TextView ono;
    TextView tno;
    Button add_Dish;
    ArrayList<Getter_Setter_Items> list;
    RecyclerView recyclerView;
    DatabaseReference db;
    String orderno;
    MyAdapter_Order_Dishes myAdapterOrderDishes;
    long i=0;
    int k=0;
    Getter_Setter_Items o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Bundle b1= getIntent().getExtras();
        orderno = b1.getString("Ordernumber");
        final long tableno = b1.getLong("tablenumber");

        tno=findViewById(R.id.TableNumber);
        ono=findViewById(R.id.OrderNumber);
        add_Dish=findViewById(R.id.button);
        ono.setText(String.valueOf(orderno));
        tno.setText(String.valueOf(tableno));

        db = FirebaseDatabase.getInstance().getReference().child("Order").child(orderno).child("items");

        recyclerView = findViewById(R.id.recyclerView3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapterOrderDishes =new MyAdapter_Order_Dishes(this,list);
        recyclerView.setAdapter(myAdapterOrderDishes);

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                i=(snapshot.getChildrenCount());
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    Getter_Setter_Items j = dataSnapshot.getValue(Getter_Setter_Items.class);
                    list.add(j);
                }
                myAdapterOrderDishes.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        add_Dish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openPopUp();
            }
        });
    }

    private void openPopUp() {
        Add_Dish_After_Order adaf=new Add_Dish_After_Order();
        adaf.show(getSupportFragmentManager(),"hello");
    }
    @Override
    public void sentData(String dname, String qty) {
        db = FirebaseDatabase.getInstance().getReference().child("Order").child(orderno);
        DatabaseReference db1=db.child("items");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getChildrenCount() <= 3) {
                    o.setSno(1);
                    o.setDname(dname);
                    o.setQty(Integer.parseInt(qty));
                    db.child("items").child(String.valueOf(1)).setValue(o);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                k= (int) snapshot.getChildrenCount();
                o.setSno(k);
                o.setDname(dname);
                o.setQty(Integer.parseInt(qty));
                db1.child(String.valueOf(k)).setValue(o);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}