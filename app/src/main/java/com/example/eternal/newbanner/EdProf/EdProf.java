package com.example.eternal.newbanner.EdProf;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eternal.newbanner.R;
import com.example.eternal.newbanner.SessionManager;
import com.example.eternal.newbanner.Config;
import com.example.eternal.newbanner.Config;
import com.example.eternal.newbanner.R;
import com.example.eternal.newbanner.SessionManager;

import java.util.HashMap;

public class EdProf extends AppCompatActivity implements View.OnClickListener {
    TextView LamanEdit;
    EditText Edit_Nama,Edit_Email,Edit_Password,Edit_Phone,Edit_Alamat;
    Button Update;
    SessionManager session;
    HashMap<String,String> user;
    String id;
    String Token;


    public static final String upda= Config.Omni+"Bacc/Practics/UpdateProfile.php?id_pelanggan=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ed_prof);
        session = new SessionManager(this);
        session.checkLogin();
        user = session.getUserDetails();

        id = getIntent().getStringExtra("id");

        LamanEdit=(TextView)findViewById(R.id.LamanEdit);
        Edit_Nama=(EditText)findViewById(R.id.Edit_Nama);
        Edit_Nama.setText(getIntent().getStringExtra("Namae"));
        Edit_Email=(EditText)findViewById(R.id.Edit_Email);
        Edit_Email.setText(getIntent().getStringExtra("Emaile"));
        Edit_Password=(EditText)findViewById(R.id.Edit_Password);
        Edit_Password.setText(getIntent().getStringExtra("Passworde"));
        Edit_Phone=(EditText)findViewById(R.id.Edit_Phone);
        Edit_Phone.setText(getIntent().getStringExtra("Phone"));
        Edit_Alamat=(EditText)findViewById(R.id.Edit_Alamat);
        Edit_Alamat.setText(getIntent().getStringExtra("Alamate"));
        Token = getIntent().getStringExtra("Token");
        Update=(Button)findViewById(R.id.Update);
        Update.setOnClickListener(this);
    }
    public void Updateur(){

        final String Nama1 = Edit_Nama.getText().toString().trim();
        final String Email1 = Edit_Email.getText().toString().trim();
        final String Password1 = Edit_Password.getText().toString().trim();
        final String Alamat1 = Edit_Alamat.getText().toString().trim();
        final String Phone1 = Edit_Phone.getText().toString().trim();
        final String Token1 = Token;


        class InsertMaster extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EdProf.this, "Adding...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(EdProf.this, s, Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put("Nama", Nama1);
                params.put("Email", Email1);
                params.put("Password", Password1);
                params.put("Alamat", Alamat1);
                params.put("Phone", Phone1);
                params.put("Token",Token1);

                session.CreateLoginSession(id,Nama1,Email1,Password1,Alamat1,Phone1,Token1);
                Config rh = new Config();
                String res = rh.sendPostRequest(upda+id, params);
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



    @Override
    public void onClick(View view) {
        if(view == Update){
            Updateur();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
