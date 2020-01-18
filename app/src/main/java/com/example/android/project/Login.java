package com.example.android.project;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    Button btnLogin;
    EditText edtUser;
    EditText edtPass;

    String PASSWORD = "12345";
    String USER = "NAMA";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);

        btnLogin = findViewById(R.id.btnLogin);
        edtPass = findViewById(R.id.edtPass);
        edtUser = findViewById(R.id.edtUser);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edtUser.getText())){
                    edtUser.setError("Wajib Di Isi");
                }
                if (TextUtils.isEmpty(edtPass.getText())){
                    edtPass.setError("Wajib Di Isi");
                } else {
                    String user = edtUser.getText().toString();
                    String pass = edtPass.getText().toString();
                    //if(user.equals(USER) && pass.equals(PASSWORD)){
                    //    Toast.makeText(Login.this,"Anda Terdaftar",Toast.LENGTH_LONG).show();
                    //    Home();
                    //}else {
                    //    if(pass.equals(PASSWORD)){
                    //        Toast.makeText(Login.this,"User Anda Salah",Toast.LENGTH_LONG).show();
                    //   } else if(user.equals(USER)){
                    //        Toast.makeText(Login.this,"Password Anda Salah",Toast.LENGTH_LONG).show();
                    //    } else {
                    //       Toast.makeText(Login.this,"Anda Belum Terdaftar",Toast.LENGTH_LONG).show();
                    //    }
                    String PassMd5 = MD5(pass);
                    Log.d("Pass","Password Md5 Login "+MD5(pass));
                    CekUser(user,PassMd5);
                    //}
                }
            }
        });

    }

    public void CekUser(final String LoginUser, final String LoginPass){
        RequestQueue queue = Volley.newRequestQueue(this);
        String Url = BuildConfig.BASE_URL+"User.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray arr = object.getJSONArray("data");
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject object1 = arr.getJSONObject(i);
                        User Cekuser = new User(object1);

                        if (LoginUser.equals(Cekuser.user) && LoginPass.equals((Cekuser.pass))) {

                            Intent intent = new Intent(Login.this, Home.class);
                            startActivity(intent);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

    public void Home(){
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }
}
