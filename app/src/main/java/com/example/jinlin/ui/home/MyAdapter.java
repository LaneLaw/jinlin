package com.example.jinlin.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jinlin.DetailActivity;
import com.example.jinlin.Merchant;
import com.example.jinlin.R;
import com.example.jinlin.getandpost.MyGetJob;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Merchant> allMerchant = new ArrayList<>();

    public void setAllMerchant(List<Merchant> allMerchant){
        this.allMerchant = allMerchant;
    }

    public List<Merchant> getAllMerchant() {
        return allMerchant;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_card,parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Merchant merchant = allMerchant.get(position);
        holder.merchantName.setText(String.valueOf(merchant.getName()));
        holder.merchantImage.setImageResource(R.drawable.login);
        holder.remainAmount.setText("Amount: "  + String.valueOf(merchant.getAmount()));
        holder.description.setText(String.valueOf(merchant.getDescription()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("merchant_id", merchant.getId());
                bundle.putString("merchant_name", merchant.getName());
                bundle.putInt("merchant_amount", merchant.getAmount());
                bundle.putString("merchant_description", merchant.getDescription());
                bundle.putString("merchant_image", merchant.getName());
                intent.putExtras(bundle);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allMerchant.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView merchantName, remainAmount, description;
        ImageView merchantImage;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            merchantName = itemView.findViewById(R.id.card_merchant_name);
            remainAmount = itemView.findViewById(R.id.card_remain_amount);
            description = itemView.findViewById(R.id.card_description);
            merchantImage = itemView.findViewById(R.id.card_merchant_image);
        }

    }
}
