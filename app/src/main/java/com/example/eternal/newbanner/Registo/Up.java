package com.example.eternal.newbanner.Registo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eternal.newbanner.Config;
import com.example.eternal.newbanner.Mlebu.In;
import com.example.eternal.newbanner.R;
import com.example.eternal.newbanner.SessionManager;
import com.example.eternal.newbanner.Tokeneur.MyFirebaseInstanceIDService;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


import java.util.HashMap;

public class Up extends AppCompatActivity implements View.OnClickListener {

   FirebaseInstanceId firebaseInstanceId;
    String asu;

    EditText Nama, Email, Password, Alamat,Phone;
    Button SignUp;
    TextView Lee, Ques;
    SessionManager session;
    String TempNama,TempEmail,TempPassword,TempAlamat,TempPhone;
    LinearLayout LineLay;
    AnimationDrawable animationDrawable1;


    int success;
    public static final String URL = Config.Omni+"Bacc/Practics/Regis.php";
    private static final String TAG = Up.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    ProgressDialog pDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up);
        LineLay=(LinearLayout)findViewById(R.id.LineLay);
//       animationDrawable1=(AnimationDrawable)LineLay.getBackground();
//        animationDrawable1.setEnterFadeDuration(5000);
//        animationDrawable1.setExitFadeDuration(2000);

      asu = firebaseInstanceId.getInstance().getToken();

        Nama = (EditText) findViewById(R.id.Nama);
        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.Password);
        Alamat = (EditText) findViewById(R.id.Alamat);
        Phone = (EditText)findViewById(R.id.Phone);
        SignUp = (Button) findViewById(R.id.SignUp);
        SignUp.setOnClickListener(this);
        Ques = (TextView) findViewById(R.id.Ques);
        Lee = (TextView) findViewById(R.id.Lee);
        Lee.setOnClickListener(this);


    }




    public void Insret(){
        final String Nama1 = Nama.getText().toString().trim();
        final String Email1 = Email.getText().toString().trim();
        final String Password1 = Password.getText().toString().trim();
        final String Alamat1 = Alamat.getText().toString().trim();
        final String Phone1 = Phone.getText().toString().trim();
       final String Token1 = asu;


        class InsertMaster extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Up.this, "Adding...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Up.this, s, Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put("Nama", Nama1);
                params.put("Email", Email1);
                params.put("Password", Password1);
                params.put("Alamat", Alamat1);
                params.put("Phone", Phone1);
                params.put("Token", Token1);


                Config rh = new Config();
                String res = rh.sendPostRequest(URL, params);
                return res;
            }
        }
        if(Nama1.isEmpty()){
            Toast.makeText(this, "Isikan Nama", Toast.LENGTH_SHORT).show();
        }else if(Email1.isEmpty()){
            Toast.makeText(this, "Isikan Email", Toast.LENGTH_SHORT).show();
        }else if(Password1.isEmpty()){
            Toast.makeText(this, "Isikan Password", Toast.LENGTH_SHORT).show();
        }else if(Alamat1.isEmpty()){
            Toast.makeText(this, "Isikan Alamat", Toast.LENGTH_SHORT).show();
        }else if(Phone1.isEmpty()){
            Toast.makeText(this, "Isikan Nomor", Toast.LENGTH_SHORT).show();
        }else {

            InsertMaster ae = new InsertMaster();
            ae.execute();
        }
    }

    /*

    public void GetData(){
        TempNama= Nama.getText().toString().trim();
        TempEmail= Email.getText().toString().trim();
        TempPassword = Password.getText().toString().trim();
        TempAlamat = Alamat.getText().toString().trim();
        TempPhone= Phone.getText().toString().trim();
    }

    public void Registo( final String Nama,final String Email,final String Password,final String Alamat,final String Phone){

class SendPostAsyncTask extends AsyncTask<String,Void,String>{



    @Override
    protected String doInBackground(String... strings) {
        String Na = Nama;
        String Ema = Email;
        String Passwo = Password;
        String Alama = Alamat;
        String Phon = Phone;

        List<NameValuePair> re = new ArrayList<NameValuePair>();
        re.add(new BasicNameValuePair("Nama",Na));
        re.add(new BasicNameValuePair("Email",Ema));
        re.add(new BasicNameValuePair("Password",Passwo));
        re.add(new BasicNameValuePair("Alamat",Alama));
        re.add(new BasicNameValuePair("Phone",Phon));

        try {
            HttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(URL);

            httpPost.setEntity(new UrlEncodedFormEntity(re));

            HttpResponse httpResponse = httpClient.execute(httpPost);

            HttpEntity httpEntity = httpResponse.getEntity();


        } catch (ClientProtocolException e) {

        } catch (IOException e) {

        }

        return "Data Inserted Successfully";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(getApplicationContext(), "Data Submit Successfully", Toast.LENGTH_LONG).show();
    }
}
        SendPostAsyncTask sendPostReqAsyncTask = new SendPostAsyncTask();

        sendPostReqAsyncTask.execute(Nama, Email,Password,Alamat,Phone);

    }
    */
/*
    public void Regis() {
       final String nama = Nama.getText().toString().trim();
        final String email = Email.getText().toString().trim();
        final String password = Password.getText().toString().trim();
        final String alamat = Alamat.getText().toString().trim();

        if (nama.isEmpty()) {
            Toast.makeText(this, "Nama Masih Kosong", Toast.LENGTH_SHORT).show();
        } else if (email.isEmpty()) {
            Toast.makeText(this, "Email Jik Kosong", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Passwordmu lho bos !!", Toast.LENGTH_SHORT).show();
        } else if (alamat.isEmpty()) {
            Toast.makeText(this, "Sawangen Meneh Alamatmu wi lho", Toast.LENGTH_SHORT).show();
        } else {
            checkRegister(nama, password, alamat, email);
        }

    }

    private void checkRegister(final String nama, final String password, final String alamat, final String email) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Register ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Successfully Register!", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        Nama.setText("");
                        Email.setText("");
                        Password.setText("");
                        Alamat.setText("");

                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                HashMap<String, String> params = new HashMap<>();
                params.put("Nama", nama);
                params.put("Email", email);
                params.put("Password", password);
                params.put("Alamat",alamat);

                return params;
            }

        };
            AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
        // Adding request to request queue
        //AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }


    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

*/

    @Override
    protected void onResume() {
        super.onResume();
       //animationDrawable1.start();
    }

    @Override
    public void onClick(View view) {
        if(view==Lee){
            Intent intent = new Intent(this,In.class);
            startActivity(intent);
        }
        if(view == SignUp){
            Insret();
            //GetData();
            //Registo(TempNama,TempEmail,TempPassword,TempAlamat,TempPhone);
        }
    }


}
