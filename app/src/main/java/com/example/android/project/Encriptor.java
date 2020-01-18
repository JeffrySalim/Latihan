package com.example.android.project;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encriptor {

    //membuat encriptor Md5

    public static String MD5(String string){
        try {
            //buat hash untuk pesan
            MessageDigest hash = MessageDigest.getInstance("MD5");
            hash.update(string.getBytes());

            //buat variable byte[] untuk menampung array dari bit string/password panjang 16byte/128bit
            byte[]pesanByte = hash.digest();

            //variable sb untuk menampung string dari hasil hashing
            StringBuilder sb = new StringBuilder();
            //lakukan perulangan sepanjang pesan byte
            for (int i=0;i<pesanByte.length;i++)
            {
                //baca semua hasil encripsi sepanjang 32 charakter
                sb.append(Integer.toString((pesanByte[i]& 0xff)+0x100,16).substring(1));
            }

            //kembalikan nilai ecripsi dalam bentuk string
            return sb.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return  null;
    }
}
