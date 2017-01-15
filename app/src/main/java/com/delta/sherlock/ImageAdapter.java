package com.delta.sherlock;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Harshit Bansal on 1/8/2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder>  {

    private Context mContext;
    private List<Image> imageList;
    private LinearLayout linearLayout;
    private ClickListener clickListener=null;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView image;
        public MyViewHolder(View view) {
            super(view);
            name=(TextView)view.findViewById(R.id.name);
            image=(ImageView)view.findViewById(R.id.image);
            linearLayout=(LinearLayout)view.findViewById(R.id.linear);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clickListener!=null){
                        clickListener.itemClicked(view,getAdapterPosition());
                    }
                }
            });
        }
    }
    public ImageAdapter(Context mContext,List<Image> imageList){
        this.mContext=mContext;
        this.imageList=imageList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
           Image image=imageList.get(position);
            holder.name.setText(image.getName());
        Glide.with(mContext).load(image.getImage()).into(holder.image);
    }
    @Override
    public int getItemCount() {
        return imageList.size();
    }

}
