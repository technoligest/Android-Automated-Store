package com.example.technoligest.lastdraft;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Technoligest on 2016-05-01.
 */
public class ProductListAdapter extends ArrayAdapter<Product> {
    Context context;
    ArrayList<Product> products;
    public ProductListAdapter(Context context,ArrayList<Product> products) {
        super(context,-1,products);
        this.context=context;
        this.products=products;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.main_list,parent,false);
        TextView textViewName= (TextView) view.findViewById(R.id.tvName);
        TextView textViewFront= (TextView) view.findViewById(R.id.tvFront);
        TextView textViewBack= (TextView) view.findViewById(R.id.tvBack);
        LinearLayout linearLayout= (LinearLayout) view.findViewById(R.id.linear);
        textViewName.setText(products.get(position).getName());
        textViewFront.setText("Front: " + products.get(position).getFront());
        textViewBack.setText("Back: " + products.get(position).getBack());
        if(Integer.parseInt(products.get(position).getFront())<4)
            linearLayout.setBackgroundColor(Color.parseColor("#ff0000"));
        else if(Integer.parseInt(products.get(position).getFront())<8)
            linearLayout.setBackgroundColor(Color.parseColor("#ffc400"));
        else
            linearLayout.setBackgroundColor(Color.parseColor("#058b00"));
        return view;
    }
}
