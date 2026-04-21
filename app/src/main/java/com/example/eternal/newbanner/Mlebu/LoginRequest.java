package com.example.eternal.newbanner.Mlebu;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.eternal.newbanner.Config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eternal on 2/27/18.
 */

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = Config.Omni+"Bacc/Practics/Login.php";
    private Map<String,String> params;

    public LoginRequest(String Email, String Password, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("Email",Email);
        params.put("Password",Password);
    }

    public Map<String, String> getParams(){
        return params;
    }

}
