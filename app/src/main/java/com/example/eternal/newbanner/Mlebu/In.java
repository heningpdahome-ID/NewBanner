package com.example.eternal.newbanner.Mlebu;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
//import com.example.eternal.newbanner.Pencitraan;
import com.example.eternal.newbanner.Pencitraan.Pencitraan;
import com.example.eternal.newbanner.R;
import com.example.eternal.newbanner.Registo.Up;
import com.example.eternal.newbanner.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

public class In extends AppCompatActivity implements View.OnClickListener {
    ProgressDialog progressDialog;
    EditText In_Email,In_Password;
    Button SignIn;
    TextView Takon,Daft;
    Context context;
    SessionManager session;
    private boolean success = false;
    LinearLayout LayLin;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in);
        LayLin =(LinearLayout)findViewById(R.id.LayLin);
//       animationDrawable =(AnimationDrawable)LayLin.getBackground();
//     animationDrawable.setEnterFadeDuration(5000);
//       animationDrawable.setExitFadeDuration(2000);


        In_Email=(EditText)findViewById(R.id.In_Email);
        In_Password=(EditText)findViewById(R.id.In_Password);
        SignIn=(Button)findViewById(R.id.SignIn);
        SignIn.setOnClickListener(this);
        Takon=(TextView)findViewById(R.id.Takon);
        Daft=(TextView)findViewById(R.id.Daft);
        Daft.setOnClickListener(this);
        session = new SessionManager(getApplicationContext());


    }

    public void Mlebu(){
        final String Email = In_Email.getText().toString();
        final String Password = In_Password.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonResponse = new JSONObject(response);
                   // success = jsonResponse.optBoolean("success");
                    boolean success = jsonResponse.getBoolean("success");

                    if (success){
                        String id       = jsonResponse.getString("id_pelanggan");
                        String Nama     = jsonResponse.getString("Nama");
                        String Email1   = jsonResponse.getString("Email");
                       String Pass     = jsonResponse.getString("Password");
                        String Alamat   = jsonResponse.getString("Alamat");


                        String Phone = jsonResponse.getString("Phone");
                        String Token = jsonResponse.getString("Token");

                        session.CreateLoginSession(id,Nama,Email1,Pass,Alamat,Phone,Token);

                        Intent intent = new Intent(In.this,Pencitraan.class);
                        startActivity(intent);
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(In.this);
                        builder.setMessage("Login Gagal")
                                .setNegativeButton("Ulangi",null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        LoginRequest loginRequest = new LoginRequest(Email,Password,responseListener);
        RequestQueue queue = Volley.newRequestQueue(In.this);
        queue.add(loginRequest);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(In.this);
            builder.setTitle("Anda Yakin Keluar Aplikasi?");
            String[] pilihan = {"Ya", "Tdk"};
            builder.setItems(pilihan, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    switch(item){
                        case 0 :
                            moveTaskToBack(true);
                            break;
                        case 1 :

                            break;
                    }
                }
            });
            builder.create().show();

            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(SessionManager.PREF_NAME, Context.MODE_PRIVATE);
        success = sharedPreferences.getBoolean(SessionManager.IS_LOGIN, false);
        if(success) {
            Intent intent = new Intent(In.this, Pencitraan.class);
            startActivity(intent);
        }

       // animationDrawable.start();
    }





    @Override
    public void onClick(View view) {
        if(view==Daft){
            Intent intent = new Intent(In.this,Up.class);
            startActivity(intent);
        }
        if(view==SignIn){
            Mlebu();
        }

    }


}
