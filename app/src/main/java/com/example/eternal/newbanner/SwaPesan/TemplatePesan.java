package com.example.eternal.newbanner.SwaPesan;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eternal.newbanner.Config;
import com.example.eternal.newbanner.R;
import com.example.eternal.newbanner.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class TemplatePesan extends AppCompatActivity implements View.OnClickListener,Spinner.OnItemSelectedListener {
    private String[] pitakon = {"Banner","X_Banner"};
    Spinner Ukurane;
    TextView Price_Ukuran,  text_Gam, text_Logo;
    LinearLayout B,XB , Line_Jagrak,  Img_Logo;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText Quan,Wid,Hei,Cont,Heade,Subti,Keterangan_Tambahan;
    Button Pes;
    Spinner Cara,Ingre;
    TextView Har,Rego,Opsi,choose;
    //    RadioButton Fin,Non;
    RadioButton rabuto;
    ImageView Gam,Logo;
    RadioGroup Ragru;
    SessionManager session;

    HashMap<String,String> user;

    Bitmap bitmap,bitmap1;


    boolean check = true;
    ProgressDialog progressDialog ;
    String ImageName = "File_Pelanggan" ;
    public static  final String url = Config.Omni+"Bacc/Practics/Self.php";

    public static final String url1 = Config.Omni+"Bacc/Practics/Bahan.php";

    public static final String uku=Config.Omni+"Bacc/Practics/Ukuran.php";
    public static final String pesenx=Config.Omni+"Bacc/Practics/XPesan.php";

    private static final String multi = Config.Omni+ "Bacc/Practics/Multi.php";

    private  static  final  String multixe = Config.Omni+ "Bacc/Practics/Multi_X.php";

    ArrayList<String> array;
    // ArrayList<String> are;

    private JSONArray res;
    //private JSONArray ser;

    int Radi;
    String Rag;

    ArrayList<String> ukur;
    private JSONArray ran;

    TextView CM,CM1;

    String uploadImage;


    RadioGroup Rad_Jagrak;
    RadioButton radjag;
    int RadJagra;
    String jagrakan;

    Switch Witch;
    boolean b;

    //public static SQLiteHelper sqLiteHelper;

    private int ImageRequest= 1;
    private int ImageRequest01= 2;

    private static final int PERMISSION_CODE = 123;


    public  Double Hitung,Hitung1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_pesan);
        CM = (TextView) findViewById(R.id.CM);
        CM1 = (TextView) findViewById(R.id.CM1);
        ProgressDialog loading = new ProgressDialog(this);

        session = new SessionManager(this);
        session.checkLogin();
        user = session.getUserDetails();

        Quan = (EditText) findViewById(R.id.Quan);
        Quan.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (Cara.getSelectedItem().toString().equals("Banner")) {
                    Itung();
                } else if (Cara.getSelectedItem().toString().equals("X_Banner")) {
                    Hitsung();
                }
                return false;
            }
        });
        Wid = (EditText) findViewById(R.id.Wid);
        Hei = (EditText) findViewById(R.id.Hei);
        Cont = (EditText) findViewById(R.id.Cont);
        Heade = (EditText) findViewById(R.id.Heade);
        Subti = (EditText) findViewById(R.id.Subti);
        Keterangan_Tambahan=(EditText)findViewById(R.id.Keterangan_Tambahan);

        choose = (TextView) findViewById(R.id.choose);
        Ragru = (RadioGroup) findViewById(R.id.Ragru);
        Ragru.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Radi = Ragru.getCheckedRadioButtonId();
                rabuto = findViewById(Radi);
                choose.setText(rabuto.getText());
            }
        });


        Pes = (Button) findViewById(R.id.Pes);
        Pes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dialog = new Dialog(TemplatePesan.this);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("Konfirmasi");

                TextView Warn = (TextView) dialog.findViewById(R.id.Warn);
                Button Sido = (Button) dialog.findViewById(R.id.Sido);
                Button Gak = (Button) dialog.findViewById(R.id.Gak);

                Sido.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Cara.getSelectedItem().toString().equals("Banner")) {
                            if(Img_Logo.getVisibility() == View.VISIBLE){
                                Upload_Plus();
                                dialog.dismiss();
                            }else if(Img_Logo.getVisibility() == View.GONE) {
                                Upload();
                                dialog.dismiss();
                            }
                        }else if (Cara.getSelectedItem().toString().equals("X_Banner")) {
                            if(Img_Logo.getVisibility() == View.VISIBLE){
                                Upload_Plus_X();
                                dialog.dismiss();
                            }else if(Img_Logo.getVisibility() == View.GONE) {
                                Upload_X();
                                dialog.dismiss();
                            }
                        }
                    }
                });
                Gak.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
        Har = (TextView) findViewById(R.id.Har);
        Rego = (TextView) findViewById(R.id.Rego);

        text_Gam=(TextView)findViewById(R.id.text_Gam);
        Gam = (ImageView) findViewById(R.id.Gam);
        Opsi = (TextView) findViewById(R.id.Opsi);
        Opsi.setVisibility(View.GONE);
        Gam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                intent.setType("image/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), ImageRequest);

            }
        });
        getBahan();
        Ingre = (Spinner) findViewById(R.id.Ingre);
        Ingre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (Ingre.getSelectedItem().equals("- Pilih Bahan -")) {
                    String idunit = "0";
                    String alias2 = "0";
                    Har.setText(alias2);

                } else {

                    String Rego = getHarga(i - 1).toString();
                    Har.setText(Rego);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        array = new ArrayList<String>();

        Ukurane = (Spinner) findViewById(R.id.Ukurane);
        getUkuran();
        Ukurane.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (Ukurane.getSelectedItem().equals("- Pilih Bahan -")) {
                    String idunit = "0";
                    String alias2 = "0";
                    Price_Ukuran.setText(alias2);

                } else {

                    String Rego = getPriceUkuran(i - 1).toString();
                    Price_Ukuran.setText(Rego);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ukur = new ArrayList<>();
        Price_Ukuran = (TextView) findViewById(R.id.Price_Ukuran);
        B = (LinearLayout) findViewById(R.id.B);
        XB = (LinearLayout) findViewById(R.id.XB);
        Line_Jagrak=(LinearLayout)findViewById(R.id.Line_Jagrak);

        Cara = (Spinner) findViewById(R.id.Cara);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, pitakon);
        Cara.setAdapter(adapter);
        Cara.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (Cara.getSelectedItem().toString().equals("Banner")) {
                    B.setVisibility(View.VISIBLE);
                    XB.setVisibility(View.GONE);
                    Line_Jagrak.setVisibility(View.GONE);
                } else if (Cara.getSelectedItem().toString().equals("X_Banner")) {
                    B.setVisibility(View.GONE);
                    XB.setVisibility(View.VISIBLE);
                    Line_Jagrak.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Rad_Jagrak=(RadioGroup)findViewById(R.id.Rad_Jagrak);
        Rad_Jagrak.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadJagra= Rad_Jagrak.getCheckedRadioButtonId();
                radjag = findViewById(RadJagra);
                jagrakan = radjag.getText().toString();
            }
        });

        Img_Logo=(LinearLayout)findViewById(R.id.Img_Logo);
        Img_Logo.setVisibility(View.GONE);
        text_Logo=(TextView)findViewById(R.id.text_Logo);
        Logo=(ImageView)findViewById(R.id.Logo);
        Logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                intent.setType("image/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), ImageRequest01);
            }
        });

        Witch=(Switch)findViewById(R.id.Witch);
        Witch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Img_Logo.setVisibility(View.VISIBLE);
                }else{
                    Img_Logo.setVisibility(View.GONE);
                }
            }
        });


    }


    //---------------------------------------------------------------------------------------------
    private void getBahan(){
        StringRequest stringRequest = new StringRequest(url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            res = j.getJSONArray("result");
                            getNamaBahan(res);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(TemplatePesan.this);
        requestQueue.add(stringRequest);
    }

    private void getNamaBahan(JSONArray j){
        array.clear();
        array.add("- Pilih Bahan -");
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                array.add(json.getString("Nama_Bahan"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Ingre.setAdapter(new ArrayAdapter<String>(TemplatePesan.this, android.R.layout.simple_spinner_dropdown_item, array));
    }
    private String getHarga(int position){
        String Rego="";
        try {
            JSONObject json = res.getJSONObject(position);
            Rego = json.getString("Rego_Bahan");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Rego;
    }

//---------------------------------Ukurane X_Banner----------------------------------------


    private void getUkuran(){
        StringRequest stringRequest = new StringRequest(uku,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            ran = j.getJSONArray("result");
                            getUkurane(ran);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(TemplatePesan.this);
        requestQueue.add(stringRequest);
    }

    private void getUkurane(JSONArray j){
        ukur.clear();
        ukur.add("- Pilih Ukuran -");
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                ukur.add(json.getString("Ukuran"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Ukurane.setAdapter(new ArrayAdapter<String>(TemplatePesan.this, android.R.layout.simple_spinner_dropdown_item, ukur));
    }
    private String getPriceUkuran(int position){
        String Rego="";
        try {
            JSONObject json = ran.getJSONObject(position);
            Rego = json.getString("Rego");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Rego;
    }


//--------------------------------Pesan_Banner------------------------------------------

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(Img_Logo.getVisibility() == View.VISIBLE){
            if(requestCode== 1 && resultCode == RESULT_OK && data!=null){
                Uri path = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), path);
                    Gam.setImageBitmap(bitmap);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(requestCode== 2 && resultCode == RESULT_OK && data!=null){
                Uri path1 = data.getData();
                try {
                    bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), path1);
                    Logo.setImageBitmap(bitmap1);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if(Img_Logo.getVisibility() == View.GONE){
            if(requestCode== 1 && resultCode == RESULT_OK && data!=null){
                Uri path = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), path);
                    Gam.setImageBitmap(bitmap);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void Upload(){
        final String idpel = user.get(SessionManager.KEY_ID).toString();
        final String jamal = Quan.getText().toString().trim();
        final String Headre = Heade.getText().toString().trim();
        final String Konten = Cont.getText().toString().trim();
        final String Subtile = Subti.getText().toString().trim();
        final String Wide= Wid.getText().toString().trim();
        final String He = Hei.getText().toString().trim();

//       final String Rag = String.valueOf(Ragru.getCheckedRadioButtonId());


        final String Rag = choose.getText().toString();

        final String Estima = Rego.getText().toString().trim();
        final String email = user.get(SessionManager.KEY_USERNAME);
        final String phone = user.get(SessionManager.KEY_PHONE);
        final String Tamket1 = Keterangan_Tambahan.getText().toString().trim();




        class UploadImage extends AsyncTask<Bitmap,Void,String> {
            ProgressDialog loading;
            Config rh = new Config();
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TemplatePesan.this, "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TemplatePesan.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... bitmaps) {
                Bitmap bitmap = bitmaps[0];

                String uploadImage = getStringImage(bitmap);



                HashMap<String,String> data = new HashMap<>();

                data.put("File_Pelanggan", uploadImage);
                data.put("id_pelanggan",idpel);
                data.put("Jumlah_Pesanan",jamal);
                data.put("Bahan",Ingre.getSelectedItem().toString());
                data.put("Header",Headre);
                data.put("Konten",Konten);
                data.put("Subtitle",Subtile);
                data.put("Width",Wide);
                data.put("Height",He);
               /* if(Fin.isSelected()) {
                    data.put("Finishing", Fin.getText().toString());
                }else if(Non.isSelected()){
                    data.put("Finishing",Non.getText().toString());
                }*/
                data.put("Finishing",Rag);
                data.put("Estimasi_Harga",Estima);
                // data.put("Time", String.valueOf(new Timestamp(new Date().getTime())));
                data.put("Email",email);
                data.put("Phone",phone);
                data.put("Tambahan_Keterangan",Tamket1);
                String result = rh.sendPostRequest(url,data);

                return result;
            }
        }
        if (Headre.isEmpty()){
            Toast.makeText(TemplatePesan.this, "Isikan Header", Toast.LENGTH_SHORT).show();
        }else if (Konten.isEmpty()){
            Toast.makeText(TemplatePesan.this, "Isikan Konten", Toast.LENGTH_SHORT).show();
        }else if (Subtile.isEmpty()){
            Toast.makeText(TemplatePesan.this, "Isikan Subtitle", Toast.LENGTH_SHORT).show();
        }else if (jamal.isEmpty()){
            Toast.makeText(TemplatePesan.this, "Isikan Jumlah", Toast.LENGTH_SHORT).show();
        }
        else{
            UploadImage ui = new UploadImage();
            ui.execute(bitmap);
        }
    }



//-----------------------------------Pesan_X_Banner--------------------------------------



    private void Upload_X (){
        final String idpel = user.get(SessionManager.KEY_ID).toString();
        final String jamal = Quan.getText().toString().trim();
        final String Headre = Heade.getText().toString().trim();
        final String Konten = Cont.getText().toString().trim();
        final String Subtile = Subti.getText().toString().trim();
        final String Ukuran = Ukurane.getSelectedItem().toString();
        final String Bahan= Ingre.getSelectedItem().toString();

//         final String Rage = String.valueOf(GroupRad.getCheckedRadioButtonId());
        final String Jagrakans = jagrakan;
        final String Rag = choose.getText().toString();

        final String Estima = Rego.getText().toString().trim();
        final String email = user.get(SessionManager.KEY_USERNAME);
        final String phone = user.get(SessionManager.KEY_PHONE);
        final String Tamket1 = Keterangan_Tambahan.getText().toString().trim();




        class UploadImage extends AsyncTask<Bitmap,Void,String> {
            ProgressDialog loading;
            Config rh = new Config();
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TemplatePesan.this, "Sabar...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TemplatePesan.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... bitmaps) {
                Bitmap bitmap = bitmaps[0];

                String uploadImage = getStringImage(bitmap);


                HashMap<String,String> data = new HashMap<>();

                data.put("File_Pelanggan", uploadImage);
                data.put("id_pelanggan",idpel);
                data.put("Jumlah_Pesanan",jamal);
                data.put("Bahan",Bahan);
                data.put("Header",Headre);
                data.put("Konten",Konten);
                data.put("Subtitle",Subtile);
                data.put("Ukuran",Ukuran);
                data.put("Penyangga",Jagrakans);
                data.put("Finishing",Rag);
                data.put("Estimasi_Harga",Estima);
                // data.put("Time", String.valueOf(new Timestamp(new Date().getTime())));
                data.put("Email",email);
                data.put("Phone",phone);
                data.put("Tambahan_Keterangan",Tamket1);
                String result = rh.sendPostRequest(pesenx,data);

                return result;
            }
        }
        if (Headre.isEmpty()){
            Toast.makeText(TemplatePesan.this, "Isikan Header", Toast.LENGTH_SHORT).show();
        }else if (Konten.isEmpty()){
            Toast.makeText(TemplatePesan.this, "Isikan Konten", Toast.LENGTH_SHORT).show();
        }else if (Subtile.isEmpty()){
            Toast.makeText(TemplatePesan.this, "Isikan Subtitle", Toast.LENGTH_SHORT).show();
        }else if (jamal.isEmpty()){
            Toast.makeText(TemplatePesan.this, "Isikan Jumlah", Toast.LENGTH_SHORT).show();
        }else {
            UploadImage ui = new UploadImage();
            ui.execute(bitmap);
        }
    }


//-----------------------------------Pesan_Banner_With_Tambahan----------------------------



    private void Upload_Plus(){
        final String idpel = user.get(SessionManager.KEY_ID).toString();
        final String jamal = Quan.getText().toString().trim();
        final String Headre = Heade.getText().toString().trim();
        final String Konten = Cont.getText().toString().trim();
        final String Subtile = Subti.getText().toString().trim();
        final String Wide= Wid.getText().toString().trim();
        final String He = Hei.getText().toString().trim();

//       final String Rag = String.valueOf(Ragru.getCheckedRadioButtonId());


        final String Rag = choose.getText().toString();

        final String Estima = Rego.getText().toString().trim();
        final String email = user.get(SessionManager.KEY_USERNAME);
        final String phone = user.get(SessionManager.KEY_PHONE);
        final String Tamket1 = Keterangan_Tambahan.getText().toString().trim();




        class UploadImage extends AsyncTask<Bitmap,Void,String> {
            ProgressDialog loading;
            Config rh = new Config();
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TemplatePesan.this, "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TemplatePesan.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... bitmaps) {
                Bitmap bitmap = bitmaps[0];
                Bitmap bitmap1 = bitmaps[1];

                String uploadImage = getStringImage(bitmap);
                String uploadImage1 = getStringImage(bitmap1);


                HashMap<String,String> data = new HashMap<>();

                data.put("File_Pelanggan", uploadImage);
                data.put("Tambahan_File",uploadImage1);
                data.put("id_pelanggan",idpel);
                data.put("Jumlah_Pesanan",jamal);
                data.put("Bahan",Ingre.getSelectedItem().toString());
                data.put("Header",Headre);
                data.put("Konten",Konten);
                data.put("Subtitle",Subtile);
                data.put("Width",Wide);
                data.put("Height",He);
               /* if(Fin.isSelected()) {
                    data.put("Finishing", Fin.getText().toString());
                }else if(Non.isSelected()){
                    data.put("Finishing",Non.getText().toString());
                }*/
                data.put("Finishing",Rag);
                data.put("Estimasi_Harga",Estima);
                // data.put("Time", String.valueOf(new Timestamp(new Date().getTime())));
                data.put("Email",email);
                data.put("Phone",phone);
                data.put("Tambahan_Keterangan",Tamket1);
                String result = rh.sendPostRequest(multi,data);

                return result;
            }
        }
        if (Headre.isEmpty()){
            Toast.makeText(TemplatePesan.this, "Isikan Header", Toast.LENGTH_SHORT).show();
        }else if (Konten.isEmpty()){
            Toast.makeText(TemplatePesan.this, "Isikan Konten", Toast.LENGTH_SHORT).show();
        }else if (Subtile.isEmpty()){
            Toast.makeText(TemplatePesan.this, "Isikan Subtitle", Toast.LENGTH_SHORT).show();
        }else if (jamal.isEmpty()){
            Toast.makeText(TemplatePesan.this, "Isikan Jumlah", Toast.LENGTH_SHORT).show();
        }
        else{
            UploadImage ui = new UploadImage();
            ui.execute(bitmap,bitmap1);
        }
    }


//-----------------------------------Pesan_Banner_X_With_Tambahan---------------------------



    private void Upload_Plus_X(){
        final String idpel = user.get(SessionManager.KEY_ID).toString();
        final String jamal = Quan.getText().toString().trim();
        final String Headre = Heade.getText().toString().trim();
        final String Konten = Cont.getText().toString().trim();
        final String Subtile = Subti.getText().toString().trim();
        final String Wide= Wid.getText().toString().trim();
        final String He = Hei.getText().toString().trim();

//       final String Rag = String.valueOf(Ragru.getCheckedRadioButtonId());

        //final String Penyang = jagrakan;
        final String Penyonggo = jagrakan;
        final String Rag = choose.getText().toString();

        final String Estima = Rego.getText().toString().trim();
        final String email = user.get(SessionManager.KEY_USERNAME);
        final String phone = user.get(SessionManager.KEY_PHONE);
        final String Tamket1 = Keterangan_Tambahan.getText().toString().trim();




        class UploadImage extends AsyncTask<Bitmap,Void,String> {
            ProgressDialog loading;
            Config rh = new Config();
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TemplatePesan.this, "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TemplatePesan.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... bitmaps) {
                Bitmap bitmap = bitmaps[0];
                Bitmap bitmap1 = bitmaps[1];

                String uploadImage = getStringImage(bitmap);
                String uploadImage1 = getStringImage(bitmap1);


                HashMap<String,String> data = new HashMap<>();

                data.put("File_Pelanggan", uploadImage);
                data.put("Tambahan_File",uploadImage1);
                data.put("id_pelanggan",idpel);
                data.put("Jumlah_Pesanan",jamal);
                data.put("Bahan",Ingre.getSelectedItem().toString());
                data.put("Header",Headre);
                data.put("Konten",Konten);
                data.put("Subtitle",Subtile);
                data.put("Width",Wide);
                data.put("Height",He);
               /* if(Fin.isSelected()) {
                    data.put("Finishing", Fin.getText().toString());
                }else if(Non.isSelected()){
                    data.put("Finishing",Non.getText().toString());
                }*/
               data.put("Penyangga", Penyonggo);
                data.put("Finishing",Rag);
                data.put("Estimasi_Harga",Estima);
                // data.put("Time", String.valueOf(new Timestamp(new Date().getTime())));
                data.put("Email",email);
                data.put("Phone",phone);
                data.put("Tambahan_Keterangan",Tamket1);
                String result = rh.sendPostRequest(multixe,data);

                return result;
            }
        }
        if (Headre.isEmpty()){
            Toast.makeText(TemplatePesan.this, "Isikan Header", Toast.LENGTH_SHORT).show();
        }else if (Konten.isEmpty()){
            Toast.makeText(TemplatePesan.this, "Isikan Konten", Toast.LENGTH_SHORT).show();
        }else if (Subtile.isEmpty()){
            Toast.makeText(TemplatePesan.this, "Isikan Subtitle", Toast.LENGTH_SHORT).show();
        }else if (jamal.isEmpty()){
            Toast.makeText(TemplatePesan.this, "Isikan Jumlah", Toast.LENGTH_SHORT).show();
        }
        else{
            UploadImage ui = new UploadImage();
            ui.execute(bitmap,bitmap1);
        }
    }





//----------Perhitungans---------------------------------------------------------

    //----------------Banner----------------------------------------------------------
    private void Itung() {
//        Locale locale = new Locale("in", "ID");
//        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

        Double Harr = Double.parseDouble(Har.getText().toString().trim());
        Double Widd= Double.parseDouble(Wid.getText().toString().trim());
        Double Heii= Double.parseDouble(Hei.getText().toString().trim());
        Double Quann= Double.parseDouble(Quan.getText().toString().trim());
        Double Senti = Double.parseDouble("100");

        Double Hitung= (((Widd*Harr)+(Heii*Harr))*Quann)/Senti;

        //Rego.setText(numberFormat.format(Hitung));
        Rego.setText("Rp"+Hitung.toString());


    }


    //--------------------------------X_Banner---------------------------------------
    private void Hitsung() {

//        Locale locale = new Locale("in", "ID");
//        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

        Double Pri = Double.parseDouble(Har.getText().toString().trim());
        Double Pri_Uk= Double.parseDouble(Price_Ukuran.getText().toString().trim());
        Double Juma= Double.parseDouble(Quan.getText().toString().trim());
        Double Senti = Double.parseDouble("10000");

        Double Hitung= ((Pri*Pri_Uk)*Juma)/Senti;

        if(jagrakan.equals("Include_Jagrakan")){
         Double Hitung1 = Hitung+Double.parseDouble("55000");
         //Rego.setText(numberFormat.format(Hitung1));
        Rego.setText("Rp"+Hitung1.toString());
        }else {
            //Rego.setText(numberFormat.format(Hitung));
            Rego.setText("Rp"+Hitung.toString());
        }

    }

//--------------------------------------------------------------------------------




    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
