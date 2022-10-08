package com.example.billing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Order_Details extends AppCompatActivity {
    TextView ono;
    TextView tno;
    ArrayList<Getter_Setter_Items> list;
    RecyclerView recyclerView;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Bundle b1= getIntent().getExtras();
        final long orderno = b1.getLong("Ordernumber");
        db = FirebaseDatabase.getInstance().getReference().child(String.valueOf(orderno));
    }
}