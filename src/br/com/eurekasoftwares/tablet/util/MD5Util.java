package br.com.eurekasoftwares.tablet.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Log;

public class MD5Util {
	public static String md5(String senha)
    {  
         String sen = "";  
         MessageDigest md = null;  
         try {  
             md = MessageDigest.getInstance("MD5");  
         } catch (NoSuchAlgorithmException e) {
        	 Log.w("Problemas ao gerar MD5", e);
         }  
         BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));  
         sen = hash.toString(16);              
         return sen;  
    }
}
