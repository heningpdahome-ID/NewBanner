package com.example.eternal.newbanner.HowTo;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eternal.newbanner.Aboutela.Sejarah;
import com.example.eternal.newbanner.R;

public class HowDy extends Activity {
    TextView ideva;
    ViewPager vipas;
    Cara adp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_dy);

        ideva = (TextView)findViewById(R.id.ideva);
        vipas=(ViewPager)findViewById(R.id.vipas);

        Intent intentur = getIntent();
        String isine = intentur.getStringExtra("namek");
        ideva.setText(isine);

        if(ideva.getText().equals("1")){
            adp2 = new Cara(this);
            vipas.setAdapter(adp2);

        }
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
