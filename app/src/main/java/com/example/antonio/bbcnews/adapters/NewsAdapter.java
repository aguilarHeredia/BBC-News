package com.example.antonio.bbcnews.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.antonio.bbcnews.R;
import com.example.antonio.bbcnews.model.Articles;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonio on 14/09/2017.
 */

public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter.DeviceViewHolder>{

    private static Context context;
    private List<Articles> dataset;
    private OnArticlesSelectedListener onArticlesSelectedListener;


    public interface OnArticlesSelectedListener {
        void onArticlesSelectedListener(Articles articles);
    }

    public NewsAdapter(Context context, OnArticlesSelectedListener listener) {
        this.dataset = new ArrayList<>();
        this.context = context;
        this.onArticlesSelectedListener = listener;
    }

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        final Articles n = dataset.get(position);
        holder.tv_article_title.setText(n.getTitle());
        holder.tv_article_description.setText(n.getDescription());

        if( n.getPublishedAt() != null ){
            holder.tv_article_date.setText(n.getDate());
        }


        holder.tv_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goPage = new Intent(Intent.ACTION_VIEW, Uri.parse(n.getUrl()));
                context.startActivity(goPage);
            }
        });

        String url = n.getUrlToImage();
        Glide.with(context).load(url).into(holder.iv_article_image);
        holder.setDeviceSelectedListener(n, onArticlesSelectedListener);

        holder.btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, n.getUrl());
                context.startActivity(Intent.createChooser(share, "Share using"));

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class DeviceViewHolder extends RecyclerView.ViewHolder {

        View cardView;

        TextView tv_article_title;
        TextView tv_article_description;
        TextView tv_article_date;
        TextView tv_url;
        LinearLayout btn_share;

        ImageView iv_article_image;

        public DeviceViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);

            tv_article_title = itemView.findViewById(R.id.tv_article_title);
            tv_article_description = itemView.findViewById(R.id.tv_article_description);
            tv_article_date =  itemView.findViewById(R.id.tv_article_date);
            tv_url = itemView.findViewById(R.id.tv_url);
            iv_article_image = itemView.findViewById(R.id.iv_article_image);
            btn_share = itemView.findViewById(R.id.btn_share);

        }

        public void setDeviceSelectedListener(final Articles articles, final OnArticlesSelectedListener onArticlesSelectedListener) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onArticlesSelectedListener.onArticlesSelectedListener(articles);
                }
            });
        }
    }

    public void add(Articles articles) {
        dataset.add(articles);
        notifyDataSetChanged();
    }

    public void setDataset(List<Articles> articles) {
        if (articles == null) {
            dataset = new ArrayList<>();
        } else {
            dataset = articles;
        }
        notifyDataSetChanged();
    }

    public void clear() {
        dataset.clear();
        notifyDataSetChanged();
    }

}
