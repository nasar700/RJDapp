package com.example.rjd.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.rjd.R;
import com.example.rjd.data.VideoClickListener;
import com.example.rjd.data.Item;
import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private ArrayList<Item> listData;
    private VideoClickListener videoClickListener;


    public HomeAdapter(ArrayList<Item> listData, VideoClickListener videoClickListener){
        this.listData = listData;
        this.videoClickListener = videoClickListener;
    }

    public void updateData(ArrayList<Item> listData){
        this.listData = listData;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.row_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        final Item data = listData.get(position);
        holder.textTitle.setText(data.getSnippet().getTitle());

        Glide.with(holder.icon.getContext())
                .load(data.getSnippet().getThumbnails().getDefaults().getUrl())
                .into(holder.icon);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoClickListener.onClick(data);
            }
        });
        if(position%2==0){
            holder.relativeLayout.setBackgroundColor(holder.icon.getContext().getResources().getColor(R.color.colorGray));
        }else {
            holder.relativeLayout.setBackgroundColor(holder.icon.getContext().getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle;
        public ImageView icon;
        public RelativeLayout relativeLayout;

        public ViewHolder(View v) {
            super(v);
            this.textTitle = (TextView)v.findViewById(R.id.title);
            this.icon = (ImageView) v.findViewById(R.id.icon);
            relativeLayout = (RelativeLayout)v.findViewById(R.id.relative_layout);
        }
    }
}
