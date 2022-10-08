package com.example.billing;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Add_Dish_After_Order extends AppCompatDialogFragment {
    AutoCompleteTextView Dname,Quantity;
    showDialog ShowDialog;
    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.add_dish_after_order,null);
        Quantity=view.findViewById(R.id.autoCompleteTextView2);
        Dname=view.findViewById(R.id.autoCompleteTextView1);
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
