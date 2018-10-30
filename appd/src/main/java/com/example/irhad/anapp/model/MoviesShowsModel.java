package com.example.irhad.anapp.model;

public class MoviesShowsModel {

    private int id;
    private String title;
    private String name;
    private String poster_path;
    private String overview;
    private String backdrop_path;

    public MoviesShowsModel() {
    }

    public MoviesShowsModel(String title, String poster_path, String overview, String backdrop_path, int id) {
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.backdrop_path = backdrop_path;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return "https://image.tmdb.org/t/p/w500" + poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdrop_path() {
        return "https://image.tmdb.org/t/p/w500" + backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
