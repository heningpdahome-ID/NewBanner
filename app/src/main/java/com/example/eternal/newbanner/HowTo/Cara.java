package com.example.eternal.newbanner.HowTo;

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

public class Cara extends PagerAdapter {

    private  int[] imresa = {R.drawable.caranya};
    private Context context;
    private LayoutInflater inflater;

    public Cara(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imresa.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(LinearLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itvi = inflater.inflate(R.layout.activity_cara,container,false);
        ImageView Caranya = (ImageView)itvi.findViewById(R.id.Carane);

        Caranya.setImageResource(imresa[position]);
        container.addView(itvi);


        return itvi;
    }
}
