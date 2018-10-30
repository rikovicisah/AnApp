package com.example.irhad.anapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.irhad.anapp.adapters.MyAdapter;

public class tvShows_Fragment extends Fragment {
    View v;

    private static RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private static Context context;

    public tvShows_Fragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_tv_shows_,container,false);
        context = container.getContext();
        recyclerView = v.findViewById(R.id.recycler_Shows);
        ispis();

        return v;
    }

    public static void ispis(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        adapter = new MyAdapter(Values.getListafilmovaSerija(), context);

        recyclerView.setAdapter(adapter);
    }
}
