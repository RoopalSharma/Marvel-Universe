package com.oyelabs.marvel.universe.Response.CharacterIdResponse;

import java.util.List;

public class Data {

    private int total;

    private int offset;

    private int limit;

    private int count;

    private List<ResultsItem> results;

    public int getTotal(){
        return total;
    }

    public int getOffset(){
        return offset;
    }

    public int getLimit(){
        return limit;
    }

    public int getCount(){
        return count;
    }

    public List<ResultsItem> getResults(){
        return results;
    }
}
