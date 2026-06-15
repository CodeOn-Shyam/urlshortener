package com.codeon.urlshortener.util;

public class Base62Encoder {
    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static String encode(Long id){
        StringBuilder sb = new StringBuilder();
        while(id>0){
            sb.append(BASE62.charAt((int)(id%62)));
            id/=62;
        }
        return sb.reverse().toString();
    }
    public static Long decode(String shortUrl){
        long id = 0;
        for(int i=0;i<shortUrl.length();i++){
            id = id*62 + BASE62.indexOf(shortUrl.charAt(i));
        }
        return id;
    }
}
