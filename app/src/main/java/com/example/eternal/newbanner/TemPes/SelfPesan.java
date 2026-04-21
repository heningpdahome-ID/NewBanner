package com.example.eternal.newbanner.TemPes;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.eternal.newbanner.Config;
import com.example.eternal.newbanner.R;
import com.example.eternal.newbanner.SessionManager;
import com.example.eternal.newbanner.SwaPesan.TemplatePesan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import yuku.ambilwarna.AmbilWarnaDialog;

public class SelfPesan extends AppCompatActivity {
    private String[] pitakon = {"Banner","X_Banner"};
    Spinner Ukurane;
    TextView Price_Ukuran;
    LinearLayout B,XB , LineGrak;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Spinner Cara,Category,Bahan,Spin_Image,Spin_Warna;
    Button Pesan;
    TextView Harga,Total,aidtem,aidske;
    EditText Qty,Content,Width,Height,Header,Subtitle,Keterangan_Tambahan1;
    ImageView Pic,Warna,Coloures;
    SurfaceView Surface,Surface1,Surface2,Surface3;

    RadioButton Finishing,Tanpa_Finishing;
    RadioButton RB;
    String Rbu;
    RadioGroup Radiog;


    SessionManager session;

    HashMap<String,String> user;




    public static final String UboRampe= Config.Omni+"Bacc/Practics/Bahan.php";

    public static final String Temple= Config.Omni+"Bacc/Practics/BahTem.php?id_kategori=";

    public static final String KAT=Config.Omni+"Bacc/Practics/Kateg.php";

    public static final String Skema=Config.Omni+"Bacc/Practics/Skewar.php";

    public static final String OrByTem= Config.Omni+"Bacc/Practics/TemplatePesan.php";

    public static final String XOrByTem= Config.Omni+"Bacc/Practics/X_TemplatePesan.php";
    ArrayList<String> are;
    private JSONArray ser;
    //ArrayList<String> tem;

    ArrayList<String> plat;
    private JSONArray met;

    ArrayList<String> alp;
    private JSONArray pla;

    ArrayList<String> Ske;
    private JSONArray War;

    ArrayList<String> ukur;
    private JSONArray ran;

    int DefaultColour,DefaultColour1,DefaultColour2,DefaultColour3;
    String colou,colou1,colou2,colou3;




    RadioGroup RadioGrak;
    RadioButton YesGrak, NoGrak;
    String RGrak;
    RadioButton RBuGrak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_pesan);
        session = new SessionManager(SelfPesan.this);
        session.checkLogin();
        user = session.getUserDetails();
        aidtem=(TextView)findViewById(R.id.aidtem);
//        aidske=(TextView)view.findViewById(R.id.aidske);

        Category= (Spinner)findViewById(R.id.Category);
        getKategori();
        Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(Category.getSelectedItem().equals("- Pilih Template -")){

                    String idunit      = "0";
                    String Ide      = "0";
                    // Harga.setText(a);
                    getTemple(Ide);


                }else{
                    String Ide    = getIdKat(i-1).toString();
                    // Harga.setText(Ide);
                    getTemple(Ide);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        alp= new ArrayList<String>();

        //getTemple();
        Spin_Image=(Spinner)findViewById(R.id.Spin_Image);
        Spin_Image.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(Spin_Image.getSelectedItem().equals("- Pilih Template -")){

                    String idunit      = "0";
                    String a      = "0";
                    Glide.with(SelfPesan.this).load(a).into(Pic);

                }else{
                    //String IdTem= getIdTem(i-1).toString();
                    String IdTem= getIdTem(i-1).toString();
                    String Ngre    = getIKat(i-1).toString();
                    String Na= getTem(i-1).toString();
                    Glide.with(SelfPesan.this).load(Config.template+Na).into(Pic);
                    aidtem.setText(IdTem);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        plat= new ArrayList<String>();

        Bahan=(Spinner)findViewById(R.id.Bahan);
        getBah();

        Bahan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(Bahan.getSelectedItem().equals("- Pilih Bahan -")){
                    String idunit      = "0";
                    String ali      = "0";
                    Harga.setText(ali);

                }else{

                    String Oger    = getRego(i-1).toString();
                    Harga.setText(Oger);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        are = new ArrayList<String>();

        Keterangan_Tambahan1=(EditText)findViewById(R.id.Keterangan_Tambahan1);

        Header=(EditText)findViewById(R.id.Header);
        Content=(EditText)findViewById(R.id.Content);
        Subtitle=(EditText)findViewById(R.id.Subtitle);
        Qty=(EditText)findViewById(R.id.Qty);
        Qty.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(Cara.getSelectedItem().toString().equals("Banner")) {
                    Calculeta();
                }else if(Cara.getSelectedItem().toString().equals("X_Banner")) {
                    Hitsung();
                }
                return false;
            }
        });
        Width=(EditText)findViewById(R.id.Width);
        Height=(EditText)findViewById(R.id.Height);
        Harga=(TextView)findViewById(R.id.Harga);
        Total=(TextView)findViewById(R.id.Total);
        Pic=(ImageView)findViewById(R.id.Pic);

        DefaultColour = ContextCompat.getColor(SelfPesan.this,R.color.colorPrimary);
        DefaultColour1 =ContextCompat.getColor(SelfPesan.this,R.color.cream);
        DefaultColour2 =ContextCompat.getColor(SelfPesan.this,R.color.hakai);
        DefaultColour3 =ContextCompat.getColor(SelfPesan.this,R.color.kaio);

        Coloures =(ImageView)findViewById(R.id.Coloures);
        Surface=(SurfaceView)findViewById(R.id.Surface);
        Surface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jupuk();
            }
        });
        Surface1=(SurfaceView)findViewById(R.id.Surface1);
        Surface1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jupuk1();
            }
        });
        Surface2=(SurfaceView)findViewById(R.id.Surface2);
        Surface2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jupuk2();
            }
        });
        Surface3=(SurfaceView)findViewById(R.id.Surface3);
        Surface3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jupuk3();
            }
        });
        Pesan=(Button)findViewById(R.id.Pesan);
        Pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(SelfPesan.this);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("Konfirmasi");

                TextView Warn =(TextView)dialog.findViewById(R.id.Warn);
                Button Sido =(Button)dialog.findViewById(R.id.Sido);
                Button Gak =(Button)dialog.findViewById(R.id.Gak);

                Sido.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(Cara.getSelectedItem().toString().equals("Banner")) {
                            PesanByTemplate();
                            dialog.dismiss();
                        }else if(Cara.getSelectedItem().toString().equals("X_Banner")){
                            XPesanByTemplate();
                            dialog.dismiss();
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
//        Finishing=(RadioButton)view.findViewById(R.id.Finishing);
//        Tanpa_Finishing=(RadioButton)view.findViewById(R.id.Tanpa_Finishing);
        Radiog=(RadioGroup)findViewById(R.id.Radiog);
        Radiog.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int RG = Radiog.getCheckedRadioButtonId();
                RB= findViewById(RG);
                Rbu= RB.getText().toString();
            }
        });





        Total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Ukurane=(Spinner)findViewById(R.id.Ukurane);
        getUkuran();
        Ukurane.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(Ukurane.getSelectedItem().equals("- Pilih Bahan -")){
                    String idunit      = "0";
                    String alias2      = "0";
                    Price_Ukuran.setText(alias2);

                }else{

                    String Rego     = getPriceUkuran(i-1).toString();
                    Price_Ukuran.setText(Rego);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ukur= new ArrayList<>();
        Price_Ukuran=(TextView)findViewById(R.id.Price_Ukuran);
        B=(LinearLayout)findViewById(R.id.B);
        XB=(LinearLayout)findViewById(R.id.XB);

        Cara=(Spinner)findViewById(R.id.Cara);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelfPesan.this,
                android.R.layout.simple_list_item_1,pitakon);
        Cara.setAdapter(adapter);
        Cara.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(Cara.getSelectedItem().toString().equals("Banner")){
                    B.setVisibility(View.VISIBLE);
                    XB.setVisibility(View.GONE);
                    LineGrak.setVisibility(View.GONE);
                }else if(Cara.getSelectedItem().toString().equals("X_Banner")){
                    B.setVisibility(View.GONE);
                    XB.setVisibility(View.VISIBLE);
                    LineGrak.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        LineGrak=(LinearLayout)findViewById(R.id.LineGrak);
        RadioGrak=(RadioGroup)findViewById(R.id.RadioGrak);
        RadioGrak.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int RJ = RadioGrak.getCheckedRadioButtonId();
                RBuGrak= findViewById(RJ);
                RGrak = RBuGrak.getText().toString();

            }
        });

    }

    private void getKategori(){
        StringRequest stringRequest1 = new StringRequest(KAT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        JSONObject u = null;
                        try {
                            j = new JSONObject(response);
                            pla = j.getJSONArray("result");
                            // pla = u.getJSONArray("result");
                            getKate(pla);
                            // getIdTem(pla);
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

        RequestQueue requestQ = Volley.newRequestQueue(SelfPesan.this);
        requestQ.add(stringRequest1);
    }

    private void getKate(JSONArray m) {
        alp.clear();
        alp.add("- Pilih Kategori -");
        for(int i=0;i<m.length();i++){
            try {
                JSONObject json = m.getJSONObject(i);
                alp.add(json.getString("Nama_Kategori"));
                // alp.add(json.getString("id_kategori"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Category.setAdapter(new ArrayAdapter<String>(SelfPesan.this, android.R.layout.simple_spinner_dropdown_item, alp));
    }

    private String getIdKat(int o){
        String Ide="";
        try {
            JSONObject json = pla.getJSONObject(o);
            Ide = json.getString("id_kategori");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Ide;
    }
    private void getTemple(String Ide){
        StringRequest stringRequest1 = new StringRequest(Temple+Ide,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        JSONObject u = null;
                        try {
                            j = new JSONObject(response);
                            met = j.getJSONArray("result");
                            // pla = u.getJSONArray("result");
                            getKet(met);
                            // getIdTem(pla);
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

        RequestQueue requestQ = Volley.newRequestQueue(SelfPesan.this);
        requestQ.add(stringRequest1);
    }


    private void getKet(JSONArray l){
        plat.clear();
        plat.add("- Pilih Kategori -");
        for(int i=0;i<l.length();i++){
            try {
                JSONObject json = l.getJSONObject(i);
                plat.add(json.getString("Keterangan"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Spin_Image.setAdapter(new ArrayAdapter<String>(SelfPesan.this, android.R.layout.simple_spinner_dropdown_item, plat));
    }
    private String getTem(int posis){
        String Ngre="";
        try {
            JSONObject json = met.getJSONObject(posis);
            Ngre = json.getString("Nama_File");
            //Ngre = json.getString("Nama_Template");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Ngre;
    }
    private String getIdTem(int posis){
        String Adi="";
        try {
            JSONObject json = met.getJSONObject(posis);
            Adi = json.getString("id_template");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Adi;
    }
    private String getIKat(int op){
        String Ada="";
        try {
            JSONObject json = met.getJSONObject(op);
            Ada = json.getString("id_kategori");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Ada;
    }


    //----------------------------------Skema Warna -----------------------------------------


    public void Jupuk(){
        final AmbilWarnaDialog coloure = new AmbilWarnaDialog(SelfPesan.this, DefaultColour, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color ) {
                DefaultColour = color;
                Surface.setBackgroundColor(DefaultColour);
//                aidske.setText("#"+Integer.toHexString(color));
                colou = "#"+Integer.toHexString(color);

            }



        });
        coloure.show();
    }

    public void Jupuk1(){
        final AmbilWarnaDialog coloure1 = new AmbilWarnaDialog(SelfPesan.this, DefaultColour1, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color ) {
                DefaultColour1 = color;
                Surface1.setBackgroundColor(DefaultColour1);
//                aidske.setText("#"+Integer.toHexString(color));
                colou1 = "#"+Integer.toHexString(color);

            }



        });
        coloure1.show();
    }

    public void Jupuk2(){
        final AmbilWarnaDialog coloure2 = new AmbilWarnaDialog(SelfPesan.this, DefaultColour2, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color ) {
                DefaultColour2 = color;
                Surface2.setBackgroundColor(DefaultColour2);
//                aidske.setText("#"+Integer.toHexString(color));
                colou2 = "#"+Integer.toHexString(color);

            }



        });
        coloure2.show();
    }

    public void Jupuk3(){
        final AmbilWarnaDialog coloure3 = new AmbilWarnaDialog(SelfPesan.this, DefaultColour3, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color ) {
                DefaultColour3 = color;
                Surface3.setBackgroundColor(DefaultColour3);
//                aidske.setText("#"+Integer.toHexString(color));
                colou3 = "#"+Integer.toHexString(color);

            }



        });
        coloure3.show();
    }


    //-------------------------Bahan---------------------------------------------------------

    private void getBah(){
        StringRequest stringRequest1 = new StringRequest(TemplatePesan.url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            ser = j.getJSONArray("result");
                            getNaBah(ser);
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

        RequestQueue requestQ = Volley.newRequestQueue(SelfPesan.this);
        requestQ.add(stringRequest1);
    }

    private void getNaBah(JSONArray k){
        are.clear();
        are.add("- Pilih Bahan -");
        for(int i=0;i<k.length();i++){
            try {
                JSONObject json = k.getJSONObject(i);
                are.add(json.getString("Nama_Bahan"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Bahan.setAdapter(new ArrayAdapter<String>(SelfPesan.this, android.R.layout.simple_spinner_dropdown_item, are));
    }
    private String getRego(int posi){
        String Oger="";
        try {
            JSONObject json = ser.getJSONObject(posi);
            Oger = json.getString("Rego_Bahan");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Oger;
    }

    //-------------------------------------Ukuran_X_Banner------------------------------------

    private void getUkuran(){
        StringRequest stringRequest = new StringRequest(TemplatePesan.uku,
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

        RequestQueue requestQueue = Volley.newRequestQueue(SelfPesan.this);
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
        Ukurane.setAdapter(new ArrayAdapter<String>(SelfPesan.this, android.R.layout.simple_spinner_dropdown_item, ukur));
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

    //-------------------------Calculetas------------------------------------------------------


    public void Calculeta(){
//        Locale locale = new Locale("in", "ID");
//        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);


        Double Harge= Double.parseDouble(Harga.getText().toString().trim());
        Double Qtys= Double.parseDouble(Qty.getText().toString().trim());
        Double Widtho = Double.parseDouble(Width.getText().toString().trim());
        Double Heighto= Double.parseDouble(Height.getText().toString().trim());

        Double Smash = (((Widtho*Harge)+(Heighto*Harge))*Qtys)/100;
        //Total.setText(numberFormat.format(Smash));
       Total.setText("Rp"+Smash.toString());
    }


    private void Hitsung() {

//        Locale locale = new Locale("in", "ID");
//        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

        Double Pri = Double.parseDouble(Harga.getText().toString().trim());
        Double Pri_Uk= Double.parseDouble(Price_Ukuran.getText().toString().trim());
        Double Juma= Double.parseDouble(Qty.getText().toString().trim());
        Double Senti = Double.parseDouble("10000");

        Double Hitung= ((Pri*Pri_Uk)*Juma)/Senti;

        if(RGrak.equals("Include_Jagrakan")){
            Double Hitung1 = Hitung+Double.parseDouble("55000");
            //Total.setText(numberFormat.format(Hitung1));
            Total.setText("Rp"+Hitung1.toString());

        }else {
            //Total.setText(numberFormat.format(Hitung));
            Total.setText("Rp"+Hitung.toString());
        }

    }

    //--------------------------------Pesen By Your Side--------------------------------------

    public void PesanByTemplate(){
        final String idpel = user.get(SessionManager.KEY_ID).toString();
        final String jamal = Qty.getText().toString().trim();
        final String Headre = Header.getText().toString().trim();
        final String Konten = Content.getText().toString().trim();
        final String Subtile = Subtitle.getText().toString().trim();
        final String Wide= Width.getText().toString().trim();
        final String He = Height.getText().toString().trim();
        //final String ops = Opsi.getText().toString().trim();
//        final String Rag = String.valueOf(Radiog.getCheckedRadioButtonId());
        final String Rag = Rbu;
        final String Estima = Total.getText().toString().trim();
        final String idTem= aidtem.getText().toString();
//        final String idSkem= aidske.getText().toString();
        final String idSkem = colou+ "," + colou1 +","+ colou2 +","+colou3;

        final String Tamket = Keterangan_Tambahan1.getText().toString().trim();
//        final String idSkem1 =colou1;
//        final String idSkem2= colou2;
//        final String idSkem3 = colou3;

        class ByTemplate extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                loading = ProgressDialog.show(SelfPesan.this, "Adding...", "Wait...", false, false);
                super.onPreExecute();
            }


            @Override
            protected String doInBackground(Void... strings) {
                HashMap<String, String> params = new HashMap<>();
                params.put("id_pelanggan",idpel);
                params.put("Jumlah_Pesanan",jamal);
                params.put("Bahan",Bahan.getSelectedItem().toString());
                params.put("Header",Headre);
                params.put("Konten",Konten);
                params.put("Subtitle",Subtile);
                params.put("Width",Wide);
                params.put("Height",He);
                params.put("Finishing",Rag);
                params.put("id_template",idTem);
                params.put("kode_skema",idSkem);
                params.put("Estimasi_Harga",Estima);
//                params.put("Time", String.valueOf(new Timestamp(new Date().getTime())));
                params.put("Email",user.get(SessionManager.KEY_USERNAME));
                params.put("Phone",user.get(SessionManager.KEY_PHONE));
                params.put("Tambahan_Keterangan",Tamket);



                Config rh = new Config();
                String se = rh.sendPostRequest(OrByTem, params);
                return se;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(SelfPesan.this, s, Toast.LENGTH_LONG).show();
            }
        }
        if (Headre.isEmpty()){
            Toast.makeText(SelfPesan.this, "Isikan Header", Toast.LENGTH_SHORT).show();
        }else if (Konten.isEmpty()){
            Toast.makeText(SelfPesan.this, "Isikan Konten", Toast.LENGTH_SHORT).show();
        }else if (Subtile.isEmpty()){
            Toast.makeText(SelfPesan.this, "Isikan Subtitle", Toast.LENGTH_SHORT).show();
        }else if (jamal.isEmpty()){
            Toast.makeText(SelfPesan.this, "Isikan Jumlah", Toast.LENGTH_SHORT).show();
        }else {
            ByTemplate ae = new ByTemplate();
            ae.execute();
        }

    }


    public void XPesanByTemplate(){
        final String idpel = user.get(SessionManager.KEY_ID).toString();
        final String jamal = Qty.getText().toString().trim();
        final String Headre = Header.getText().toString().trim();
        final String Konten = Content.getText().toString().trim();
        final String Subtile = Subtitle.getText().toString().trim();
//        final String Wide= Width.getText().toString().trim();
//        final String He = Height.getText().toString().trim();
        //final String ops = Opsi.getText().toString().trim();
//        final String Rag = String.valueOf(Radiog.getCheckedRadioButtonId());
        final String Ukuran = Ukurane.getSelectedItem().toString().trim();
        final String JGrak = RGrak;
        final String Rag = Rbu;
        final String Estima = Total.getText().toString().trim();
        final String idTem= aidtem.getText().toString();
//        final String idSkem= aidske.getText().toString();
        final String idSkem = colou+ "," + colou1 +","+ colou2 +","+colou3;

        final String Tamket = Keterangan_Tambahan1.getText().toString().trim();
//        final String idSkem1 =colou1;
//        final String idSkem2= colou2;
//        final String idSkem3 = colou3;

        class XByTemplate extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                loading = ProgressDialog.show(SelfPesan.this, "Adding...", "Wait...", false, false);
                super.onPreExecute();
            }


            @Override
            protected String doInBackground(Void... strings) {
                HashMap<String, String> params = new HashMap<>();
                params.put("id_pelanggan",idpel);
                params.put("Jumlah_Pesanan",jamal);
                params.put("Bahan",Bahan.getSelectedItem().toString());
                params.put("Header",Headre);
                params.put("Konten",Konten);
                params.put("Subtitle",Subtile);
                params.put("Ukuran",Ukuran);
                params.put("Penyangga",JGrak);
                params.put("Finishing",Rag);
                params.put("id_template",idTem);
                params.put("kode_skema",idSkem);
                params.put("Estimasi_Harga",Estima);
//                params.put("Time", String.valueOf(new Timestamp(new Date().getTime())));
                params.put("Email",user.get(SessionManager.KEY_USERNAME));
                params.put("Phone",user.get(SessionManager.KEY_PHONE));
                params.put("Tambahan_Keterangan",Tamket);



                Config rh = new Config();
                String se = rh.sendPostRequest(XOrByTem, params);
                return se;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(SelfPesan.this, s, Toast.LENGTH_LONG).show();
            }
        }
        if (Headre.isEmpty()){
            Toast.makeText(SelfPesan.this, "Isikan Header", Toast.LENGTH_SHORT).show();
        }else if (Konten.isEmpty()){
            Toast.makeText(SelfPesan.this, "Isikan Konten", Toast.LENGTH_SHORT).show();
        }else if (Subtile.isEmpty()){
            Toast.makeText(SelfPesan.this, "Isikan Subtitle", Toast.LENGTH_SHORT).show();
        }else if (jamal.isEmpty()){
            Toast.makeText(SelfPesan.this, "Isikan Jumlah", Toast.LENGTH_SHORT).show();
        }else {
            XByTemplate ae = new XByTemplate();
            ae.execute();
        }

    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
