package com.example.irhad.anapp.tools;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.irhad.anapp.Values;
import com.example.irhad.anapp.model.MoviesShowsModel;
import com.example.irhad.anapp.movies_Fragment;
import com.example.irhad.anapp.tvShows_Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.irhad.anapp.activities.MainActivity.getTabActive;

public class ReadJson {

    private static RequestQueue requestQueue;

    public static void readJSON(String url, int tab, final List<MoviesShowsModel> listaFilmovaSerija, Context context){
        requestQueue = Volley.newRequestQueue(context);
        listaFilmovaSerija.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null,
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
                                listaFilmovaSerija.add(new MoviesShowsModel(naslov,
                                        obj2.getString("poster_path"),
                                        obj2.getString("overview"),
                                        obj2.getString("backdrop_path"),
                                        obj2.getInt("id")));
                            }
                            Values.setListafilmovaSerija(listaFilmovaSerija);

                            for (MoviesShowsModel m :
                                    listaFilmovaSerija) {
                                System.out.println("---------------------------------------------" + m.getTitle());
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);
    }//readJSON
}
