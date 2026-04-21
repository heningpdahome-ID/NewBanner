package com.example.eternal.newbanner.TampilKren;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eternal.newbanner.Pencitraan.Pencitraan;
import com.example.eternal.newbanner.R;
import com.example.eternal.newbanner.SessionManager;
import com.example.eternal.newbanner.Config;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by eternal on 3/3/18.
 */

public class RecDapt extends RecyclerView.Adapter<RecDapt.MyHolder> {
    List<Data> list;
    Context context;
    private String pelanggan;
    public static final String Pri_Pes = Config.Omni+"Bacc/Practics/Pri_Pesana.php?id_detail_pesanan=";

    public static final String Pri_Del = Config.Omni+"Bacc/Practics/Pri_Delete.php?id_detail_pesanan=";
    SessionManager session;

    public RecDapt(List<Data> listdata, Context context) {
        this.list = listdata;
        this.context = context;

    }

    @Override
    public RecDapt.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.infla,parent,false);
        //HashMap<String,String> user = session.getUserDetails();
        //pelanggan= user.get(SessionManager.KEY_ID);

        return new RecDapt.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecDapt.MyHolder holder, final int position) {
        final Data data = list.get(position);

        Glide.with(context).load(Config.image_kerancang+data.getFile_Pelanggan()).into(holder.get_Image);
        holder.get_Header.setText("header : "+data.getHeader());
        holder.get_Konten.setText("konten : "+data.getKonten());
        holder.get_Subtitle.setText("subtitle : "+data.getSubtitle());

            holder.get_Height.setText("Height : " + data.getHeight());


            holder.get_Width.setText("Width :" + data.getWidth());

        holder.get_Jumlah_Pesanan.setText(data.getJumlah_Pesanan());
        holder.aidi_detail_pesanan.setText("Kode_Pesanan : "+data.getId_detail_pesanan());
        holder.aidi_pelanggan.setText(data.getId_pelanggan());
        holder.get_Finishing.setText(data.getFinishing());

//        Locale locale = new Locale("in", "ID");
//       NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

       //holder.get_Estimasi_Harga.setText(numberFormat.format(data.getEstimasi_Harga()));
        holder.get_Estimasi_Harga.setText(data.getEstimasi_Harga());


       // holder.aidi_template.setText(data.getId_template());
        //holder.aidi_skema_warna.setText(data.getId_skema_warna());
        holder.get_Time.setText(data.getTime());
        holder.Pri_Pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Status = "Menunggu Dibayar";
                final  Config cf = new Config();

                class Upd extends AsyncTask<String,Void,String> {

                    @Override
                    protected String doInBackground(String... strings) {
                        HashMap<String,String> uptd = new HashMap<>();
                        uptd.put("Status",Status);
                        //uptd.put("id_pelanggan",user.get(SessionManager.KEY_ID));
                        String result = cf.sendPostRequest(Pri_Pes+data.getId_detail_pesanan().toString(),uptd);
                        return result;
                    }
                }
                Upd upd = new Upd();
                upd.execute();
            }
        });

        holder.Pri_Batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    //final String Status = "Setujui";
                    final  Config cf = new Config();

                    class Dup extends AsyncTask<String,Void,String> {

                        @Override
                        protected String doInBackground(String... strings) {
                            HashMap<String,String> up = new HashMap<>();
                           // uptd.put("Status",Status);
                            //uptd.put("id_pelanggan",user.get(SessionManager.KEY_ID));
                            String result = cf.sendPostRequest(Pri_Del+data.getId_detail_pesanan().toString(),up);
                            return result;
                        }
                    }
                    Dup dup = new Dup();
                    dup.execute();

              Intent intent = new Intent(context, Pencitraan.class);
              context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class MyHolder extends  RecyclerView.ViewHolder  {
        TextView get_Header,get_Subtitle,
                get_Konten,get_Jumlah_Pesanan,get_Width,get_Height,get_Finishing,get_Estimasi_Harga,get_Time;
        ImageView get_Image;
        Button Pri_Pesan,Pri_Batal;
        TextView aidi_pelanggan,aidi_detail_pesanan,aidi_template,aidi_skema_warna;


        public MyHolder(View itemView){
            super(itemView);


            get_Image=(ImageView)itemView.findViewById(R.id.get_Image);
            aidi_pelanggan=(TextView)itemView.findViewById(R.id.aidi_pelanggan);
            aidi_detail_pesanan=(TextView)itemView.findViewById(R.id.aidi_detail_pesanan);
            aidi_template=(TextView)itemView.findViewById(R.id.aidi_template);
            aidi_skema_warna=(TextView)itemView.findViewById(R.id.aidi_skema_warna);
            get_Finishing=(TextView)itemView.findViewById(R.id.get_Finishing);
            get_Header=(TextView)itemView.findViewById(R.id.get_Header);
            get_Konten=(TextView)itemView.findViewById(R.id.get_Konten);
            get_Subtitle=(TextView)itemView.findViewById(R.id.get_Subtitle);
            get_Jumlah_Pesanan=(TextView)itemView.findViewById(R.id.get_Jumlah_Pesanan);
            get_Width=(TextView)itemView.findViewById(R.id.get_Width);
            get_Height=(TextView)itemView.findViewById(R.id.get_Height);
            get_Estimasi_Harga=(TextView)itemView.findViewById(R.id.get_Estimasi_Harga);
            get_Time=(TextView)itemView.findViewById(R.id.get_Time);
            Pri_Pesan=(Button)itemView.findViewById(R.id.Pri_Pesan);
            Pri_Batal=(Button)itemView.findViewById(R.id.Pri_Batal);


        }


    }









//    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView aidi_pelanggan,aidi_detail_pesanan,aidi_template,aidi_skema_warna,
//        get_Konten,get_Jumlah_Pesanan,get_Width,get_Height,get_Finishing,get_Estimasi_Harga;
//        ImageView get_Image;
//
//        public MyHolder(View itemView) {
//            super(itemView);
//            get_Image=(ImageView)itemView.findViewById(R.id.get_Image);
//            aidi_pelanggan=(TextView)itemView.findViewById(R.id.aidi_pelanggan);
//            aidi_detail_pesanan=(TextView)itemView.findViewById(R.id.aidi_detail_pesanan);
//            aidi_template=(TextView)itemView.findViewById(R.id.aidi_template);
//            aidi_skema_warna=(TextView)itemView.findViewById(R.id.aidi_skema_warna);
//            get_Finishing=(TextView)itemView.findViewById(R.id.get_Finishing);
//            get_Konten=(TextView)itemView.findViewById(R.id.get_Konten);
//            get_Jumlah_Pesanan=(TextView)itemView.findViewById(R.id.get_Jumlah_Pesanan);
//            get_Width=(TextView)itemView.findViewById(R.id.get_Width);
//            get_Height=(TextView)itemView.findViewById(R.id.get_Height);
//            get_Estimasi_Harga=(TextView)itemView.findViewById(R.id.get_Estimasi_Harga);
//        }
//
//        @Override
//        public void onClick(View view) {
//
//        }
//    }
}
