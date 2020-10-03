package com.example.rjd.image;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rjd.R;
import com.example.rjd.data.Data;
import com.example.rjd.data.ImageClickListener;
import com.example.rjd.more.ImageAdapter;

import java.util.ArrayList;

public class ImageDetailAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private ArrayList<String> listData;
    private ImageClickListener videoClickListener;

    public ImageDetailAdapter(ArrayList<String> listData, ImageClickListener videoClickListener){
        this.listData = listData;
        this.videoClickListener = videoClickListener;
    }
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.image_detail_adapter,parent,false);
        ImageAdapter.ViewHolder viewHolder = new ImageAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ViewHolder holder, int position) {
        final String url = listData.get(position);

        Glide.with(holder.icon.getContext())
                .load(url)
                .into(holder.icon);
        //holder.icon.setImageResource(data.getImgId());
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoClickListener.onClick(url);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public ViewHolder(View v) {
            super(v);
            this.icon = (ImageView) v.findViewById(R.id.image_view);
        }
    }
}
