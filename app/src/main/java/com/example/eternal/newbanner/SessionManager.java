package com.example.eternal.newbanner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.eternal.newbanner.Mlebu.In;
import com.example.eternal.newbanner.Splash.Splash;
import com.example.eternal.newbanner.Registo.Up;
import com.example.eternal.newbanner.Tokeneur.MyFirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;

/**
 * Created by eternal on 2/27/18.
 */

public class SessionManager {
   // RemoteMessage remoteMessage;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    public static final String PREF_NAME = "ss";
    public static final String IS_LOGIN = "success";
    public static final String KEY_USERNAME = "Email";
    public static final String KEY_NAMA = "Nama";
    public static final String KEY_PASSWORD="Password";
    public static final String KEY_ALAMAT = "Alamat";
    public static final String KEY_ID = "id_pelanggan";
    public static final String KEY_PHONE = "Phone";
    public static final String KEY_TOKEN = "Token";

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public void CreateLoginSession(String id_pelanggan, String Nama,String Email,String Password, String Alamat,String Phone,String Token){
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(KEY_ID,id_pelanggan);
        editor.putString(KEY_NAMA, Nama);
        editor.putString(KEY_USERNAME, Email);
       editor.putString(KEY_PASSWORD,Password);
        editor.putString(KEY_ALAMAT,Alamat);
        editor.putString(KEY_PHONE,Phone);
        editor.putString(KEY_TOKEN,Token);
        editor.commit();
    }

    public void checkLogin(){
        if (!this.isLoggedIn()){
            Intent i = new Intent(_context, Splash.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public HashMap<String,String> getUserDetails(){
        HashMap<String,String> user = new HashMap<String, String>();

        user.put(KEY_ID,pref.getString(KEY_ID, null));
        user.put(KEY_NAMA,pref.getString(KEY_NAMA,null));
        user.put(KEY_USERNAME,pref.getString(KEY_USERNAME,null));
        user.put(KEY_PASSWORD,pref.getString(KEY_PASSWORD,null));
        user.put(KEY_ALAMAT,pref.getString(KEY_ALAMAT, null));
        user.put(KEY_PHONE,pref.getString(KEY_PHONE,null));
        user.put(KEY_TOKEN,pref.getString(KEY_TOKEN,null));

        return user;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, In.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN,false);
    }
}
