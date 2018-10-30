package com.example.irhad.anapp;

import com.example.irhad.anapp.model.MoviesShowsModel;

import java.util.List;

public class Values {
    private static List<MoviesShowsModel> listafilmovaSerija;

    public static List<MoviesShowsModel> getListafilmovaSerija() {
        return listafilmovaSerija;
    }

    public static void setListafilmovaSerija(List<MoviesShowsModel> listafilmovaSerija) {
        Values.listafilmovaSerija = listafilmovaSerija;
    }

    public static void clear(){
        if(listafilmovaSerija != null && listafilmovaSerija.size() > 0)
            listafilmovaSerija.clear();
    }
}
