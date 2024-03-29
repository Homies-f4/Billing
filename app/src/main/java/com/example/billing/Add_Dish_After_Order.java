package com.example.billing;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Add_Dish_After_Order extends DialogFragment {
    AutoCompleteTextView Dname,Quantity;
    int i=0;
    showDialog ShowDialog;
    String[] menu1;
    String[] listItems={"1","2","3","4","5","6","7","8","9","10"};
    DatabaseReference db;
    MyAdapter_Menu myAdapterMenu;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.add_dish_after_order,null);

        Quantity=view.findViewById(R.id.qty);
        Dname=view.findViewById(R.id.dishname);

        db = FirebaseDatabase.getInstance().getReference().child("Billing");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                menu1=new String[(int)snapshot.getChildrenCount()];
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Getter_Setter_Billing b= dataSnapshot.getValue(Getter_Setter_Billing.class);
                    menu1[i]=b.getDish();
                    i++;
                }
                ArrayAdapter<String> adapt1=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,menu1);
                Dname.setAdapter(adapt1);
            }
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayAdapter<String> adapt=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,listItems);
        Quantity.setAdapter(adapt);

        builder.setView(view).
                setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String dname=Dname.getText().toString();

                        String quantity= Quantity.getText().toString();
                        ShowDialog.sentData(dname,quantity);
                    }
                });

        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            ShowDialog = (showDialog) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface showDialog{
        void sentData(String dname,String qty);
    }
}
