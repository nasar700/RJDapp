package com.example.rjd.more;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rjd.R;
import com.example.rjd.data.VideoClickListener;
import com.example.rjd.data.Data;
import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private ArrayList<Data> listData;
    private VideoClickListener videoClickListener;

    public ImageAdapter(ArrayList<Data> listData, VideoClickListener videoClickListener){
        this.listData = listData;
        this.videoClickListener = videoClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.image_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        final Data data = listData.get(position);

        //holder.icon.setImageResource(data.getImgId());
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // clickListener.onClick(data);
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
