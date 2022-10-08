package com.example.billing;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter_Order_Dishes extends RecyclerView.Adapter<MyAdapter_Order_Dishes.MyViewHolder> {

    Context context;
    ArrayList<Getter_Setter_Items> list;

    public MyAdapter_Order_Dishes(Context context, ArrayList<Getter_Setter_Items> list) {
        this.context = context;
        this.list = list;

    }
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.card_order_dishes,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter_Order_Dishes.MyViewHolder holder, int position)
    {
        Getter_Setter_Items i=list.get(position);
        holder.sno.setText(String.valueOf(i.getSno()));
        holder.dname.setText(i.getDname());
        holder.qty.setText(String.valueOf(i.getQty()));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sno,dname,qty;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sno=(TextView) itemView.findViewById(R.id.sno);
            dname=(TextView) itemView.findViewById(R.id.dname);
            qty=(TextView) itemView.findViewById(R.id.qty);
        }
    }
}
