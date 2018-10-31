package com.example.irhad.anapp.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.irhad.anapp.R;
import com.example.irhad.anapp.adapters.MyAdapter;
import com.example.irhad.anapp.model.MoviesShowsModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import static com.example.irhad.anapp.activities.MainActivity.getTabActive;

public class SearchResultActivity extends AppCompatActivity {

    private static RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private static List<MoviesShowsModel> searchLista;
    private static Context context;
    private static RequestQueue requestQueue2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchLista = new ArrayList<>();
        requestQueue2 = Volley.newRequestQueue(this);
        context = getApplicationContext();
        recyclerView = findViewById(R.id.recycler_Search);

        readJSON(getIntent().getExtras().getString("query"));
        ispis();

    }

    public static void ispis(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //lista filmova
        adapter = new MyAdapter(searchLista, context);
        recyclerView.setAdapter(adapter);
    }

    public static void readJSON(String query){
        searchLista.clear();
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET,
                getSearchURL(query), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String naslov = "";
                            JSONObject obj = new JSONObject(response.toString());
                            JSONArray arr = obj.getJSONArray("results");

                            for (int i = 0; i < arr.length(); i++){
                                JSONObject obj2 = arr.getJSONObject(i);
                                naslov = (getTabActive() == 0)? obj2.getString("title") : obj2.getString("name");
                                searchLista.add(new MoviesShowsModel(naslov,
                                        obj2.getString("poster_path"),
                                        obj2.getString("overview"),
                                        obj2.getString("backdrop_path"),
                                        obj2.getInt("id")));
                            }

                            ispis();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        requestQueue2.add(jsonObjectRequest2);
    }

    private static String getSearchURL(String query){
        return (getTabActive() == 0)?
                "https://api.themoviedb.org/3/search/movie?query="+query+"&api_key=b273d564e7a35e4008311b291409cf9f"
                :"https://api.themoviedb.org/3/search/tv?query="+query+"&api_key=b273d564e7a35e4008311b291409cf9f";
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
