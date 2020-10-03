package com.example.rjd.more;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rjd.data.ClickListener;
import com.example.rjd.data.Data;
import com.example.rjd.R;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private ArrayList<Data> listData;
    private ClickListener clickListener;


    public VideoAdapter(ArrayList<Data> listData, ClickListener clickListener){
        this.listData = listData;
        this.clickListener = clickListener;
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
        final Data data = listData.get(position);

        holder.textTitle.setText(data.getTitle());
       // holder.textDescription.setText(data.getDesciption());
        //holder.icon.setImageResource(data.getImgId());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clickListener.onClick(data);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle;
       // public TextView textDescription;
        public ImageView icon;
        public RelativeLayout relativeLayout;


        public ViewHolder(View v) {
            super(v);
            this.textTitle = (TextView)v.findViewById(R.id.title);
           // this.textDescription = (TextView)v.findViewById(R.id.description);
            this.icon = (ImageView) v.findViewById(R.id.icon);
            relativeLayout = (RelativeLayout)v.findViewById(R.id.relative_layout);
        }
    }
}
