package com.example.billing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter_Orders_List extends RecyclerView.Adapter<MyAdapter_Orders_List.MyViewHolder> {
    Context context;
    ArrayList<Getter_Setter_Orders_List> list;
    OnOrderListener mOnOrderListener;

    public MyAdapter_Orders_List(Context context, ArrayList<Getter_Setter_Orders_List> list, OnOrderListener mOnOrderListener) {
        this.context = context;
        this.list = list;
        this.mOnOrderListener=mOnOrderListener;
    }
    public void onItemClick(int position){

    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.card_orders_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Getter_Setter_Orders_List o= list.get(position);
            holder.ono.setText(String.valueOf(o.getOno()));
            holder.tno.setText(String.valueOf(o.getTno()));
            holder.itemView.setOnClickListener(view ->{
                mOnOrderListener.onOrderClick(list.get(position));
                });
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
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                                            }
            }
            );
        }
    }
    public interface OnOrderListener{
        void onOrderClick(Getter_Setter_Orders_List orderGettersetter);
    }
}