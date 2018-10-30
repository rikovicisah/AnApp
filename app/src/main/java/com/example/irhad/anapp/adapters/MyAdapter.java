package com.example.irhad.anapp.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.irhad.anapp.R;
import com.example.irhad.anapp.activities.DetailsActivity;
import com.example.irhad.anapp.activities.MainActivity;
import com.example.irhad.anapp.model.MoviesShowsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private List<MoviesShowsModel> listafilmova;
    private Context context;

    public MyAdapter(List<MoviesShowsModel> listafilmova, Context context) {
        this.listafilmova = listafilmova;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final MoviesShowsModel moviesShowsModel = listafilmova.get(i);

        viewHolder.listItem_txtNaslov.setText(moviesShowsModel.getTitle());
        //loada sliku sa interneta u image
        Picasso.with(context).load(moviesShowsModel.getPoster_path()).into(viewHolder.listItem_Slika);

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details = new Intent(context, DetailsActivity.class);
                details.putExtra("naslov", moviesShowsModel.getTitle());
                details.putExtra("opis", moviesShowsModel.getOverview());
                details.putExtra("slika", moviesShowsModel.getBackdrop_path());
                details.putExtra("tab", MainActivity.getTabActive());
                details.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(details);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (listafilmova != null && listafilmova.size() > 0)? listafilmova.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView listItem_Slika;
        TextView listItem_txtNaslov;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            listItem_Slika = itemView.findViewById(R.id.listItem_Slika);
            listItem_txtNaslov = itemView.findViewById(R.id.listItem_txtNaslov);

            linearLayout = itemView.findViewById(R.id.listItem_LinearLayout);
        }
    }
}
