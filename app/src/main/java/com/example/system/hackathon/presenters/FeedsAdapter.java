package com.example.system.hackathon.presenters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.system.hackathon.R;
import com.example.system.hackathon.model.Feeds;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.FeedsHolder>{

    public FeedsAdapter(ArrayList mfeeds){
        this.mFeeds = mfeeds;
    }

    private ArrayList<Feeds> mFeeds;

    @NonNull
    @Override
    public FeedsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.feed_item,parent,false);
        return new FeedsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedsHolder holder, int position) {
        
        Feeds feeds = mFeeds.get(position);
        holder.titleTV.setText(feeds.getTitle());
        holder.descriptionTV.setText(feeds.getDescription());
    }

    @Override
    public int getItemCount() {
        return mFeeds.size();
    }

    public static class FeedsHolder extends RecyclerView.ViewHolder{

        TextView titleTV ;
        TextView descriptionTV;

        public FeedsHolder(View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.feedsTitle);
            descriptionTV = itemView.findViewById(R.id.feedsDescription);
        }
    }
}
