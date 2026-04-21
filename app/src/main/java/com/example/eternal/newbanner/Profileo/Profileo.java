package com.example.eternal.newbanner.Profileo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eternal.newbanner.EdProf.EdProf;
import com.example.eternal.newbanner.R;
import com.example.eternal.newbanner.SessionManager;

import java.util.HashMap;

public class Profileo extends AppCompatActivity implements View.OnClickListener {
    TextView Name,Alamat,Aidi,Esmail,Passworde,Phones,TOken;
    SessionManager session;
    HashMap<String,String> user;
    Button Out,Edito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileo);
        ImageView Pic = (ImageView)findViewById(R.id.Pic);
        Name= (TextView)findViewById(R.id.Name);
        Aidi =(TextView)findViewById(R.id.Aidi);
        Esmail=(TextView)findViewById(R.id.Esmail);
        Phones=(TextView)findViewById(R.id.Phones);
        Passworde=(TextView)findViewById(R.id.Passworde);
        Alamat=(TextView)findViewById(R.id.Alamat);
        TOken=(TextView)findViewById(R.id.TOken);
        Edito=(Button)findViewById(R.id.Edito);
        Edito.setOnClickListener(this);
        Out=(Button)findViewById(R.id.Out);
        Out.setOnClickListener(this);
//        Pic.setOnClickListener(this);


        session = new SessionManager(this);
        session.checkLogin();

        user = session.getUserDetails();

        Aidi.setText(user.get(SessionManager.KEY_ID));
        Name.setText(user.get(SessionManager.KEY_NAMA));
        Esmail.setText(user.get(SessionManager.KEY_USERNAME));
        Passworde.setText(user.get(SessionManager.KEY_PASSWORD));
        Phones.setText(user.get(SessionManager.KEY_PHONE));
        Alamat.setText(user.get(SessionManager.KEY_ALAMAT));
        TOken.setText(user.get(SessionManager.KEY_TOKEN));

    }

    @Override
    public void onClick(View view) {
        if(view == Out){
            session.logoutUser();

        }
        if(view == Edito){
            Intent intenta= new Intent(this, EdProf.class);
            intenta.putExtra("id",Aidi.getText().toString());
            intenta.putExtra("Namae",Name.getText().toString());
            intenta.putExtra("Emaile",Esmail.getText().toString());
            intenta.putExtra("Passworde",Passworde.getText().toString());
            intenta.putExtra("Alamate",Alamat.getText().toString());
            intenta.putExtra("Phone",Phones.getText().toString());
            intenta.putExtra("Token",TOken.getText().toString());


            startActivity(intenta);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
