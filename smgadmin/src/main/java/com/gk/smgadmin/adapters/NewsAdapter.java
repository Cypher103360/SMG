package com.gk.smgadmin.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gk.smgadmin.R;
import com.gk.smgadmin.models.NewsModel;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<NewsModel> newsModelList = new ArrayList<>();
    Context context;
    NewsInterface newsInterface;

    public NewsAdapter(Context context, NewsInterface newsInterface) {
        this.context = context;
        this.newsInterface = newsInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.newsTitle.setText(newsModelList.get(position).getTitle());
        holder.newsTitle.setTextSize(17);
        Glide.with(context).load("https://gedgetsworld.in/SM_Guru/News_Images/" + newsModelList.get(position).getImage()).into(holder.newsImage);
        holder.itemView.setOnClickListener(v -> newsInterface.onItemClicked(newsModelList.get(position)));

    }

    @Override
    public int getItemCount() {
        return newsModelList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateNewsList(List<NewsModel> newsModels) {
        newsModelList.clear();
        newsModelList.addAll(newsModels);
        notifyDataSetChanged();
    }

    public interface NewsInterface {

        void onItemClicked(NewsModel newsModel);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle;
        ImageView newsImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.news_imageView);
            newsTitle = itemView.findViewById(R.id.news_title_txt);
        }
    }
}
