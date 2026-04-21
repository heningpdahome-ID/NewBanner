package com.example.eternal.newbanner.PascaPesan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eternal.newbanner.R;
import com.example.eternal.newbanner.Config;

import java.util.List;

/**
 * Created by eternal on 3/11/18.
 */

public class ReDapt extends RecyclerView.Adapter<ReDapt.MyHolder> {
    List<Dasan> list;
    Context context;

    public ReDapt(List<Dasan> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ReDapt.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.infla_pasca,parent,false);
        return new ReDapt.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(ReDapt.MyHolder holder, int position) {
        final Dasan dasan = list.get(position);
        holder.aidepes.setText("Kode_Pesanan : "+ dasan.getId_detail_pesanan());
        holder.hea_riw.setText("header : " +dasan.getHeader());
        holder.cont_riw.setText("konten : "+dasan.getKonten());
        holder.subt_riw.setText("subtitle : "+dasan.getSubtitle());
        holder.tgl_riw.setText(dasan.getTime());
        holder.qua_riw.setText("quantity : "+dasan.getJumlah_Pesanan());
        holder.Sta_riw.setText(dasan.getStatus());
        Glide.with(context).load(Config.image_kerancang+dasan.getFile_Pelanggan()).into(holder.im_riw);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView im_riw;
        TextView hea_riw,cont_riw,subt_riw,tgl_riw,qua_riw,Sta_riw,aidepes;
        public MyHolder(View itemView) {
            super(itemView);
            im_riw=(ImageView)itemView.findViewById(R.id.im_riw);
            hea_riw=(TextView)itemView.findViewById(R.id.hea_riw);
            cont_riw=(TextView)itemView.findViewById(R.id.cont_riw);
            subt_riw=(TextView)itemView.findViewById(R.id.subt_riw);
            tgl_riw=(TextView)itemView.findViewById(R.id.tgl_riw);
            qua_riw=(TextView)itemView.findViewById(R.id.qua_riw);
            Sta_riw=(TextView)itemView.findViewById(R.id.Sta_riw);
            aidepes=(TextView)itemView.findViewById(R.id.aidepes);

        }
    }
}
