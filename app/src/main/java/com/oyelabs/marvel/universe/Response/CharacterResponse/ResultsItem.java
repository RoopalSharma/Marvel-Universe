package com.oyelabs.marvel.universe.Response.CharacterResponse;

import java.util.List;

public class ResultsItem {

    private Thumbnail thumbnail;

    private List<UrlsItem> urls;

    private Stories stories;

    private Series series;

    private Comics comics;

    private String name;

    private String description;

    private String modified;

    private int id;

    private String resourceURI;

    private Events events;

    public Thumbnail getThumbnail(){
        return thumbnail;
    }

    public List<UrlsItem> getUrls(){
        return urls;
    }

    public Stories getStories(){
        return stories;
    }

    public Series getSeries(){
        return series;
    }

    public Comics getComics(){
        return comics;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getModified(){
        return modified;
    }

    public int getId(){
        return id;
    }

    public String getResourceURI(){
        return resourceURI;
    }

    public Events getEvents(){
        return events;
    }
}
