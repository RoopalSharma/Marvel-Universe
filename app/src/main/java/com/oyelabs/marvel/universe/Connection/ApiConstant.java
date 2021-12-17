package com.oyelabs.marvel.universe.Connection;

public interface ApiConstant {

    String BASE_URL="https://gateway.marvel.com:443/v1/";
    String CHARACTER_URL="https://gateway.marvel.com:443/v1/";
    String PUBLIC_KEY="0db4aca2e3516889ebf6ca4ce1c884f2";
    String PRIVATE_KEY="eeeb4a7930f814e6920e5caafc5a68a136e45531";
    String time=String.valueOf(System.currentTimeMillis());
    String hashkey=MD5.md5(time+ApiConstant.PRIVATE_KEY+ApiConstant.PUBLIC_KEY);
}
