package com.example.irhad.anapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.irhad.anapp.R;
import com.example.irhad.anapp.Values;
import com.example.irhad.anapp.movies_Fragment;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    TextView details_txtNaslov;
    TextView details_txtOpis;
    ImageView details_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        details_txtNaslov = findViewById(R.id.details_txtNaslov);
        details_txtOpis = findViewById(R.id.details_txtOpis);
        details_image = findViewById(R.id.details_image);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String naslov = this.getIntent().getExtras().getString("naslov");
        String opis = this.getIntent().getExtras().getString("opis");
        String slika = this.getIntent().getExtras().getString("slika");
        MainActivity.setTabActive(this.getIntent().getExtras().getInt("tab"));

        details_txtNaslov.setText(naslov);
        details_txtOpis.setText(opis);
        Picasso.with(this).load(slika).into(details_image);

    }
}
