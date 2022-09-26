package com.example.billing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Menu_Dish extends AppCompatActivity {
    EditText dish;
    EditText cost;
    Billing b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_dish);
        Bundle b1= getIntent().getExtras();
        final long[] count = {b1.getLong("count")};
        Log.d("Count:", String.valueOf(count[0]));
        b= new Billing();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Billing");
        dish = findViewById(R.id.editTextTextPersonName);
        cost = findViewById(R.id.editTextNumberDecimal);

        Button btn1= findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count[0] = count[0] +1;
                Float p=Float.parseFloat(cost.getText().toString().trim());
                b.setDish(dish.getText().toString().trim());
                b.setCost(p);
                b.setSno((int) count[0]);
                db.child(String.valueOf(count[0])).setValue(b);
                Toast.makeText(Add_Menu_Dish.this,"Data inserted!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Add_Menu_Dish.this, MenuDishes.class));
            }
        });

        Button btn2= findViewById(R.id.button3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Add_Menu_Dish.this, MenuDishes.class));
            }
        });

    }
}
