package com.oyelabs.marvel.universe.Response.CharacterIdResponse;

import com.oyelabs.marvel.universe.Response.CharacterResponse.Data;

public class CharacterBasicResponse {

    private String copyright;

    private int code;

    private Data data;

    private String attributionHTML;

    private String attributionText;

    private String etag;

    private String status;

    public String getCopyright(){
        return copyright;
    }

    public int getCode(){
        return code;
    }

    public Data getData(){
        return data;
    }

    public String getAttributionHTML(){
        return attributionHTML;
    }

    public String getAttributionText(){
        return attributionText;
    }

    public String getEtag(){
        return etag;
    }

    public String getStatus(){
        return status;
    }
}
