package com.example.system.hackathon.presenters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.system.hackathon.Views.activities.ConversationRoom;
import com.example.system.hackathon.R;
import com.example.system.hackathon.model.Users;

import java.util.ArrayList;

public class HealthAdapter extends RecyclerView.Adapter<HealthAdapter.HealthHolder>{

    public HealthAdapter(ArrayList mHealth , Context context) {
        this.mHealth = mHealth;
        this.context = context;
    }

    ArrayList mHealth;
    Context context;

    @NonNull
    @Override
    public HealthHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.health_item,parent,false);
        return new HealthHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HealthHolder holder, int position) {
        final Users users = (Users) mHealth.get(position);
        holder.healthUserName.setText(users.getUsername());
        holder.healthCardView.setOnClickListener(new View.OnClickListener() {
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
        return mHealth.size();
    }

    public static class HealthHolder extends RecyclerView.ViewHolder{

        TextView healthUserName;
        CardView healthCardView;

        public HealthHolder(View itemView) {
            super(itemView);
            healthUserName = itemView.findViewById(R.id.healthUserName);
            healthCardView = itemView.findViewById(R.id.healthCard);
        }
    }
}
