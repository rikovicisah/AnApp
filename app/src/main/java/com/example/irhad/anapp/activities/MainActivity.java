package com.example.irhad.anapp.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.irhad.anapp.R;
import com.example.irhad.anapp.Values;
import com.example.irhad.anapp.adapters.ViewPagerAdapter;
import com.example.irhad.anapp.model.MoviesShowsModel;
import com.example.irhad.anapp.movies_Fragment;
import com.example.irhad.anapp.tvShows_Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private RecyclerView recyclerView;
    private List<MoviesShowsModel> listaFilmovaSerija;
    private static int tabActive;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTabActive(0);

        recyclerView = findViewById(R.id.recycler_Movies);

        tabLayout = findViewById(R.id.tabLayoutID);
        viewPager = findViewById(R.id.viewPagerID);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new movies_Fragment(), "MOVIES");
        viewPagerAdapter.addFragment(new tvShows_Fragment(), "TV SHOWS");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //model
        listaFilmovaSerija = new ArrayList<>();

        //tab layout
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setTabActive(tab.getPosition());
                readJSON();
                if(getTabActive() == 0) {
                    movies_Fragment.ispis();
                }
                else
                    tvShows_Fragment.ispis();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {readJSON();}

        });

        //volley
        requestQueue = Volley.newRequestQueue(this);
        readJSON();
        if(getTabActive() == 0) {
            new movies_Fragment();
        }
        else
            tvShows_Fragment.ispis();

        TabLayout.Tab tab = tabLayout.getTabAt(getTabActive());
        tab.select();

    }

    private void readJSON(){
        listaFilmovaSerija.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                get_URL(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String naslov = "";
                            JSONObject obj = new JSONObject(response.toString());
                            JSONArray arr = obj.getJSONArray("results");

                            for (int i = 0; i < 10; i++){
                                JSONObject obj2 = arr.getJSONObject(i);
                                naslov = (getTabActive() == 0)? obj2.getString("title") : obj2.getString("name");
                                listaFilmovaSerija.add(new MoviesShowsModel(naslov,
                                        obj2.getString("poster_path"),
                                        obj2.getString("overview"),
                                        obj2.getString("backdrop_path"),
                                        obj2.getInt("id")));
                            }
                            Values.setListafilmovaSerija(listaFilmovaSerija);

                            if(getTabActive() == 0)
                                new movies_Fragment();
                            else
                                tvShows_Fragment.ispis();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Please check your connection",Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }//readJSON

    private String get_URL(){
        return (getTabActive() == 0)?
                "https://api.themoviedb.org/3/movie/top_rated?api_key=b273d564e7a35e4008311b291409cf9f"
                : "https://api.themoviedb.org/3/tv/top_rated?api_key=b273d564e7a35e4008311b291409cf9f";
    }

    public static int getTabActive() {
        return tabActive;
    }

    public static void setTabActive(int tabActiveD) {
        tabActive = tabActiveD;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search,menu);
        MenuItem menuItem = menu.findItem(R.id.search_menu);
        SearchView searchView = (SearchView)menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.length() > 2){
                    Intent searchActivity = new Intent(getApplicationContext(), SearchResultActivity.class);
                    searchActivity.putExtra("query", query);
                    startActivity(searchActivity);

                }else{
                    readJSON();
                    if(getTabActive() == 0)
                        movies_Fragment.ispis();
                    else
                        tvShows_Fragment.ispis();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}
