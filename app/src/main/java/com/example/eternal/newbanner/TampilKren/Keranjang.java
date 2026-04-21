package com.example.eternal.newbanner.TampilKren;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eternal.newbanner.Config;
import com.example.eternal.newbanner.R;
import com.example.eternal.newbanner.SessionManager;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Keranjang extends AppCompatActivity {
    Sumanto atad;

    RecyclerView Recy;
    Button Order;
    TextView Sum;
    RecyclerView.LayoutManager layoutManager;
    RecDapt recDapt;
    List<Data> list;
    List<Sumanto> sumantos;
    SessionManager session;
    HashMap<String,String> user;

    HttpResponse response;
    JSONObject json = null;
    String str = "";


    public static  final String url= Config.Omni+"Bacc/Practics/List.php?id_pelanggan=";

    public static final String uler = Config.Omni+"Bacc/Practics/Pesana.php?id_pelanggan=";

    public static final String summa = Config.Omni+"Bacc/Practics/Summa.php?id_pelanggan=";

    private JSONArray es;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);
        Order= (Button)findViewById(R.id.Order);
        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(Keranjang.this);
                dialog.setContentView(R.layout.dialog_pesen);
                dialog.setTitle("Konfirmasi");

                TextView Valid =(TextView)dialog.findViewById(R.id.Valid);
                Button Lanjut =(Button)dialog.findViewById(R.id.Lanjut);
                Button Tidak_Lanjut =(Button)dialog.findViewById(R.id.Tidak_Lanjut);

                Lanjut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Ord();
                        dialog.dismiss();
                        Recy.setAdapter(null);
                        Sum.setText(null);

                    }
                });
                Tidak_Lanjut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        Recy =(RecyclerView)findViewById(R.id.Recy);
        Recy.refreshDrawableState();

        Sum = (TextView)findViewById(R.id.Sum);
        layoutManager = new LinearLayoutManager(Keranjang.this);

        session = new SessionManager(Keranjang.this);
        session.checkLogin();

        user = session.getUserDetails();

        list = new ArrayList<>();
        Tampilkan();
        Summa();
    }

    private void Summa(){
        StringRequest srst = new StringRequest(Request.Method.GET, summa+user.get(SessionManager.KEY_ID), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("result");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        String totality = (String) object.getString("Total");

//                        Locale locale = new Locale("in", "ID");
//                        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
//                        Sum.setText(numberFormat.format(totality));
                        Sum.setText(totality);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //progressDialog.dismiss();
//                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                if(error instanceof NetworkError){
                }else if (error instanceof ServerError){
                }else if(error instanceof AuthFailureError){
                }else if(error instanceof ParseError){
                }else if(error instanceof NoConnectionError){
                }else if(error instanceof TimeoutError){
                    Toast.makeText(Keranjang.this, "Oops, Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(Keranjang.this);
        requestQueue.add(srst);

    }


    private void Ord() {
        final String Status = "Menunggu Dibayar";
        final  Config cf = new Config();

        class Upd extends AsyncTask<String,Void,String> {

            @Override
            protected String doInBackground(String... strings) {
                HashMap<String,String> uptd = new HashMap<>();
                uptd.put("Status",Status);
                //uptd.put("id_pelanggan",user.get(SessionManager.KEY_ID));
                String result = cf.sendPostRequest(uler+user.get(SessionManager.KEY_ID),uptd);
                return result;
            }
        }
        Upd upd = new Upd();
        upd.execute();
    }

    private void Tampilkan() {
        final ProgressDialog progressDialog = new ProgressDialog(Keranjang.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url+user.get(SessionManager.KEY_ID), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("result");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        Data data = new Data(
                                object.getString("id_detail_pesanan"),
                                object.getString("id_pelanggan"),
                                object.getString("File_Pelanggan"),
                                object.getString("Jumlah_Pesanan"),
                                object.getString("Header"),
                                object.getString("Konten"),
                                object.getString("Subtitle"),
                                object.getString("Width"),
                                object.getString("Height"),
                                object.getString("Finishing"),
                                //object.getString("id_template"),
                                //object.getString("id_skema_warna"),
                                object.getString("Estimasi_Harga"),
                                object.getString("Time")

                        );
                        list.add(data);
                    }
                    Recy.setLayoutManager(layoutManager);
                    recDapt = new RecDapt(list, Keranjang.this);
                    Recy.setAdapter(recDapt);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Keranjang.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(Keranjang.this);
        requestQueue.add(stringRequest);
    }




    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left_y,R.anim.slide_out_right_y);
    }


}
