package com.example.eternal.newbanner.PascaPesan;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PascaPesan extends AppCompatActivity {
    RecyclerView RE;
    RecyclerView.LayoutManager layoutManager;
    ReDapt reDapt;
    List<Dasan> list;
    SessionManager session;
    HashMap<String,String> user;

    public static final String pasca = Config.Omni+"Bacc/Practics/List_Pasca_Pesan.php?id_pelanggan=";
    // Button But;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasca_pesan);
        RE=(RecyclerView)findViewById(R.id.RE);
        layoutManager = new LinearLayoutManager(PascaPesan.this);
        list = new ArrayList<>();
        session = new SessionManager(PascaPesan.this);
        session.checkLogin();

        user = session.getUserDetails();

        Show();
    }

    private void Show() {
        final ProgressDialog progressDialog = new ProgressDialog(PascaPesan.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, pasca+user.get(SessionManager.KEY_ID), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("result");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        Dasan data = new Dasan(
                                object.getString("id_detail_pesanan"),
                                object.getString("File_Pelanggan"),
                                object.getString("Jumlah_Pesanan"),
                                object.getString("Header"),
                                object.getString("Konten"),
                                object.getString("Subtitle"),

                                object.getString("Time"),
                                object.getString("Status")
                        );
                        list.add(data);
                    }
                    RE.setLayoutManager(layoutManager);
                    reDapt = new ReDapt(list, PascaPesan.this);
                    RE.setAdapter(reDapt);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
//                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                if(error instanceof NetworkError){
                }else if (error instanceof ServerError){
                }else if(error instanceof AuthFailureError){
                }else if(error instanceof ParseError){
                }else if(error instanceof NoConnectionError){
                }else if(error instanceof TimeoutError){
                    Toast.makeText(PascaPesan.this, "Oops, Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(PascaPesan.this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left_y,R.anim.slide_out_right_y);
    }

}
