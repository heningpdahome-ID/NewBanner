package com.example.eternal.newbanner.Pencitraan;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.eternal.newbanner.Aboutela.Aboutela;
import com.example.eternal.newbanner.EdProf.EdProf;
import com.example.eternal.newbanner.HowTo.HowDy;
import com.example.eternal.newbanner.Multi.Multi;
import com.example.eternal.newbanner.PascaPesan.PascaPesan;
import com.example.eternal.newbanner.Profileo.Profileo;
import com.example.eternal.newbanner.R;
import com.example.eternal.newbanner.SessionManager;
import com.example.eternal.newbanner.SwaPesan.TemplatePesan;
import com.example.eternal.newbanner.TampilKren.Keranjang;
import com.example.eternal.newbanner.TemPes.SelfPesan;

import java.util.HashMap;

public class Pencitraan extends AppCompatActivity implements View.OnClickListener {
    SessionManager session;
    HashMap<String,String> user;
    ImageView kustom,template,profile,about,howto;
//    Button Out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencitraan);
        session = new SessionManager(this);
        session.checkLogin();

        user = session.getUserDetails();
//        Out = (Button)findViewById(R.id.Out);
//        Out.setOnClickListener(this);
        kustom=(ImageView)findViewById(R.id.kustom);
        kustom.setOnClickListener(this);
        template=(ImageView)findViewById(R.id.template);
        template.setOnClickListener(this);
        profile=(ImageView)findViewById(R.id.profile);
        profile.setOnClickListener(this);
        about=(ImageView)findViewById(R.id.about);
        about.setOnClickListener(this);
        howto=(ImageView)findViewById(R.id.howto);
        howto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == kustom){
           startActivity(new Intent(this, TemplatePesan.class));
           overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
        if(view == template){
            startActivity(new Intent(this, SelfPesan.class));
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
        if(view == profile){

            startActivity(new Intent(this, Profileo.class));
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
        if(view == about){
           // startActivity(new Intent(this, Aboutela.class));
            Intent intennt = new Intent(this, Aboutela.class);
            intennt.putExtra("name","1");
            startActivity(intennt);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
        if(view == howto){
            Intent intennt = new Intent(this, HowDy.class);
            intennt.putExtra("namek","1");
            startActivity(intennt);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opmen, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.Chat){

            Uri uri = Uri.parse("smsto:" + "+6285335741179");
            Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
//            sendIntent.putExtra(Intent.EXTRA_TEXT, "Test");
//            sendIntent.setType("text/plain");
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);

            return true;
        }

        if(id == R.id.Riwayat){
            startActivity(new Intent(Pencitraan.this, PascaPesan.class));
            overridePendingTransition(R.anim.slide_in_right_y,R.anim.slide_out_left_y);
            return true;
        }
        if(id == R.id.Carte){
            startActivity(new Intent(Pencitraan.this, Keranjang.class));
            overridePendingTransition(R.anim.slide_in_right_y,R.anim.slide_out_left_y);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
