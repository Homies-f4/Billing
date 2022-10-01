package com.example.billing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {
    Context context;
    ArrayList<Order1> list;


    public MyAdapter1(Context context, ArrayList<Order1> list) {
        this.context = context;
        this.list = list;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.activity_order_card,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Order1 o= list.get(position);
        holder.ono.setText(String.valueOf(o.getOno()));
        holder.tno.setText(String.valueOf(o.getTno()));
    }

    @Override
    public int getItemCount() {
        return list.size() ;
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView ono,tno;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ono=(TextView) itemView.findViewById(R.id.ono);
            tno=(TextView) itemView.findViewById(R.id.tno);


        }
    }
}