package com.example.eternal.newbanner.Multi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eternal.newbanner.Config;
import com.example.eternal.newbanner.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class Multi extends AppCompatActivity implements View.OnClickListener {
    ImageView Img,Imge;
    EditText Jeneng,Jeneng01;
    Button Send;
    Bitmap bitmap,bitmap1;
    int ImageRequest = 1;
    int ImageRequest01 = 2;
    private static final String url = Config.Omni+ "Bacc/Practics/Multi.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        Jeneng=(EditText)findViewById(R.id.Jeneng);
        Jeneng01=(EditText)findViewById(R.id.Jeneng01);
        Img=(ImageView)findViewById(R.id.Img);
        Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                intent.setType("image/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), ImageRequest);
            }
        });
        Imge=(ImageView)findViewById(R.id.Imge);
        Imge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();

                intent1.setType("image/*");

                intent1.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent1, "Select Image From Gallery"), ImageRequest01);
            }
        });
        Send=(Button)findViewById(R.id.Send);
        Send.setOnClickListener(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== 1 && resultCode == RESULT_OK && data!=null) {
            Uri path = data.getData();


            try {

                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), path);
                Img.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        if(requestCode== 2 && resultCode == RESULT_OK && data!=null) {
            Uri path1 = data.getData();


            try {

                bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), path1);
                Imge.setImageBitmap(bitmap1);


            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    private void Uploades(){
        final String Jenen = Jeneng.getText().toString();
        final  String Jenen01 = Jeneng01.getText().toString();

        class UpIm extends AsyncTask<Bitmap,Void,String> {
            ProgressDialog loading;
            Config rh = new Config();
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Multi.this, "Uploading...", null, true, true);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Multi.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... bitmaps) {
                Bitmap bitma = bitmaps[0];
                Bitmap bitm = bitmaps[1];

                String uploadImage = getStringImage(bitma);
                String uploadImage1 = getStringImage(bitm);



                HashMap<String,String> data = new HashMap<>();

                data.put("Jeneng",Jenen);
                data.put("File", uploadImage);
                data.put("Jeneng01",Jenen01);
                data.put("File01",uploadImage1);

                if (Jenen.isEmpty()){
                    Toast.makeText(Multi.this, "Isikan Jeneng", Toast.LENGTH_SHORT).show();
                }else if (Jenen01.isEmpty()){
                    Toast.makeText(Multi.this, "Isikan Jeneng01", Toast.LENGTH_SHORT).show();
                }

                String result = rh.sendPostRequest(url,data);
                return  result;
            }

        }

        UpIm ui = new UpIm();
        ui.execute(bitmap,bitmap1);
    }


    @Override
    public void onClick(View view) {

        if(view == Send){
            Uploades();
        }
    }
}
