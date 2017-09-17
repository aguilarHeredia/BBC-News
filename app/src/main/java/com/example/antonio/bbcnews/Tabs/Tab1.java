package com.example.antonio.bbcnews.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.antonio.bbcnews.BBCService;
import com.example.antonio.bbcnews.R;
import com.example.antonio.bbcnews.ServiceGenerator;
import com.example.antonio.bbcnews.adapters.NewsAdapter;
import com.example.antonio.bbcnews.model.Articles;
import com.example.antonio.bbcnews.model.News;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by antonio on 15/09/2017.
 */

public class Tab1 extends Fragment implements NewsAdapter.OnArticlesSelectedListener{

    private NewsAdapter newsAdapter;
    private RecyclerView newsRecyclerView1;
    private SwipeRefreshLayout swipeRefreshLayout1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View RootView = inflater.inflate(R.layout.tab1, container, false);

        newsRecyclerView1 = (RecyclerView) RootView.findViewById(R.id.newsRecyclerView1);
        newsRecyclerView1.setHasFixedSize(true);
        newsRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(getContext(), this);
        newsRecyclerView1.setAdapter(newsAdapter);

        swipeRefreshLayout1 = (SwipeRefreshLayout) RootView.findViewById(R.id.swipeRefreshLayout1);
        swipeRefreshLayout1.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout1.setProgressBackgroundColorSchemeResource(R.color.colorAccent);
        swipeRefreshLayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cargarDatos();
            }
        });

        cargarDatos();
        return RootView;
    }


    private void cargarDatos() {
        BBCService service = ServiceGenerator.createService(BBCService.class);
        Call<News> call = service.obtenerArticulos("bbc-news", "top", "c00e8dde45124f309e7b2d77d30f4bd1");

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                swipeRefreshLayout1.setRefreshing(false);
                if (response.isSuccessful()) {
                    newsAdapter.setDataset(response.body().getArticles());
                } else {
                    Log.e("AA ", "It couldn't be possible to find data");
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                swipeRefreshLayout1.setRefreshing(false);
                Log.e("AA", "Error getting news: " + t.getMessage());
            }
        });
    }

    @Override
    public void onArticlesSelectedListener(Articles articles) {

    }
}