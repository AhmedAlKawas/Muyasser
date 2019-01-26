package com.example.system.hackathon.presenters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.system.hackathon.R;
import com.example.system.hackathon.Views.activities.ConversationRoom;
import com.example.system.hackathon.model.Users;

import java.util.ArrayList;

public class FatwaAdapter extends RecyclerView.Adapter<FatwaAdapter.FatwaHolder>{

    public FatwaAdapter(ArrayList fatwaArray , Context context) {
        this.fatwaArray = fatwaArray;
        this.context = context;
    }

    ArrayList fatwaArray;
    Context context;

    @NonNull
    @Override
    public FatwaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fatwa_item_view,parent,false);
        return new FatwaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FatwaHolder holder, int position) {
        final Users users = (Users) fatwaArray.get(position);
        holder.fatwaUserName.setText(users.getUsername());
        holder.fatwaCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ConversationRoom.class);
                i.putExtra("id",users.getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fatwaArray.size();
    }

    public class FatwaHolder extends RecyclerView.ViewHolder {
        TextView fatwaUserName;
        CardView fatwaCardView;
        public FatwaHolder(View itemView) {
            super(itemView);
            fatwaUserName = itemView.findViewById(R.id.fatwaUserName);
            fatwaCardView = itemView.findViewById(R.id.fatwaCard);
        }
    }
}
