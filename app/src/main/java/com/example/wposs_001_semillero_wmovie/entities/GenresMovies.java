package com.example.wposs_001_semillero_wmovie.entities;

public class GenresMovies {
    private String id;
    private final String name;

    public GenresMovies(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

}
