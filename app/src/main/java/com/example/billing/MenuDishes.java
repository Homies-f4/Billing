package com.example.billing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuDishes extends AppCompatActivity {
    ArrayList<Getter_Setter_Billing> list;
    ArrayList<String> list1;
    RecyclerView recyclerView;
    DatabaseReference db;
    MyAdapter_Menu myAdapterMenu;
    long i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dishes);
        recyclerView = findViewById(R.id.recyclerView);
        db = FirebaseDatabase.getInstance().getReference().child("Billing");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myAdapterMenu =new MyAdapter_Menu(this,list);
        recyclerView.setAdapter(myAdapterMenu);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 i=(snapshot.getChildrenCount());
                 for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Getter_Setter_Billing b= dataSnapshot.getValue(Getter_Setter_Billing.class);
                    list.add(b);
                    list1.add(b.getDish());

                 }
                myAdapterMenu.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Log.d("Hello",list1.get(0));

        Button btn= findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 =new Intent(MenuDishes.this, Add_Menu_Dish.class);
                i1.putExtra("count",i);
                startActivity(i1);
            }
        });
    }
    public String[] menuList(){
        String[] a= new String[list1.size()];
        for (int k=0;i<list1.size();k++)
        {
            a[k]=list1.get(k);
        }
        return a;
    }

}