package com.oyelabs.marvel.universe.Connection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    public static String md5(final String s)
    {
        final String MD5= "MD5";

        try
        {
            //create hash

            MessageDigest digest=java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[]= digest.digest();

            StringBuilder hexstring=new StringBuilder();
            for (byte aMessageDigest: messageDigest)
            {
                String h=Integer.toHexString(0xFF&aMessageDigest);

                while (h.length()<2)
                    h="0"+h;
                hexstring.append(h);
            }
            return hexstring.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
