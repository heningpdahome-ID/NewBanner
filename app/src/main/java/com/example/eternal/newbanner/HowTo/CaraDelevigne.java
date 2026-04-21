package com.example.eternal.newbanner.HowTo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eternal.newbanner.R;

public class CaraDelevigne extends Activity {
    TextView carade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cara_delevigne);
        carade=(TextView)findViewById(R.id.carade);
    }
}
