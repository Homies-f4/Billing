package com.example.billing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter_Menu extends RecyclerView.Adapter<MyAdapter_Menu.MyViewHolder> {
    Context context;
    ArrayList<Getter_Setter_Billing> list;


    public MyAdapter_Menu(Context context, ArrayList<Getter_Setter_Billing> list) {
        this.context = context;
        this.list = list;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.card_menu_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Getter_Setter_Billing b= list.get(position);
        holder.sno.setText(String.valueOf(b.getSno()));
        holder.dish.setText(b.getDish());
        holder.cost.setText(String.valueOf(b.getCost()));
    }

    @Override
    public int getItemCount() {
        return list.size() ;
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView sno,dish,cost;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sno=(TextView) itemView.findViewById(R.id.sno);
            dish=(TextView) itemView.findViewById(R.id.dish);
            cost=(TextView) itemView.findViewById(R.id.cost);

        }
    }
}
