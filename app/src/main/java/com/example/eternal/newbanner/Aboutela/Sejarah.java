package com.example.eternal.newbanner.Aboutela;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.eternal.newbanner.R;

public class Sejarah extends PagerAdapter {

    private  int[] imres = {R.drawable.sejarahe,R.drawable.mivis};
    private Context context;
    private LayoutInflater inflater;

    public Sejarah(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imres.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(LinearLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itvi = inflater.inflate(R.layout.activity_sejarah,container,false);
        ImageView Sejarahe = (ImageView)itvi.findViewById(R.id.Sejarahe);

        Sejarahe.setImageResource(imres[position]);
        container.addView(itvi);


        return itvi;
    }
}
