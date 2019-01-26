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

import com.example.system.hackathon.R;
import com.example.system.hackathon.Views.activities.ConversationRoom;
import com.example.system.hackathon.model.Users;

import java.util.ArrayList;

public class TranslationAdapter extends RecyclerView.Adapter<TranslationAdapter.TranslationHolder>{

    ArrayList mTranslate;
    Context context;

    public TranslationAdapter(ArrayList mTranslate, Context context) {
        this.mTranslate = mTranslate;
        this.context = context;
    }

    @NonNull
    @Override
    public TranslationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.health_item,parent,false);
        return new TranslationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TranslationHolder holder, int position) {
        final Users users = (Users) mTranslate.get(position);
        holder.translateUserName.setText(users.getUsername());
        holder.translateCardView.setOnClickListener(new View.OnClickListener() {
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
        return mTranslate.size();
    }

    public static class TranslationHolder extends RecyclerView.ViewHolder {
        TextView translateUserName;
        CardView translateCardView;

        public TranslationHolder(View itemView) {
            super(itemView);
            translateUserName = itemView.findViewById(R.id.healthUserName);
            translateCardView = itemView.findViewById(R.id.healthCard);
        }
    }
}
