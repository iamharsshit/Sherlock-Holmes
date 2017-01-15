package com.delta.sherlock;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Harshit Bansal on 12/22/2016.
 */

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.MyViewHolder> {
    private Context mContext;
    private List<Episode> episodeList;
    private ClickListener clickListener=null;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count,date;
        public ImageView thumbnail;
        private RelativeLayout main;
        public MyViewHolder(View view) {

            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            date=(TextView)view.findViewById(R.id.date) ;
            main=(RelativeLayout) view.findViewById(R.id.main);
            main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.itemClicked(view, getAdapterPosition());
                    }
                }
            });

        }
    }


    public EpisodeAdapter(Context mContext, List<Episode> albumList) {
        this.mContext = mContext;
        this.episodeList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.episode_card, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Episode episode = episodeList.get(position);
        holder.title.setText(episode.getName());
        holder.count.setText(episode.getRatings());
        holder.date.setText(episode.getDate());
        // loading album cover using Glide library
        Glide.with(mContext).load(episode.getThumbnail()).into(holder.thumbnail);


    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }
}