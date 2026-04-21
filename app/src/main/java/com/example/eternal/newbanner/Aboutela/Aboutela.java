package com.example.eternal.newbanner.Aboutela;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eternal.newbanner.R;

public class Aboutela extends Activity {
    TextView idev;
    ViewPager vipa;
    Sejarah adp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutela);
        idev = (TextView)findViewById(R.id.idev);
        vipa=(ViewPager)findViewById(R.id.vipa);

        Intent intentur = getIntent();
        String isine = intentur.getStringExtra("name");
        idev.setText(isine);

        if(idev.getText().equals("1")){
            adp1 = new Sejarah(this);
            vipa.setAdapter(adp1);

        }
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
