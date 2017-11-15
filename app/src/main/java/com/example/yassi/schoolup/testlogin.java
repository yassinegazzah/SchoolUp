package com.example.yassi.schoolup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class testlogin extends AppCompatActivity {
    EditText pass ;
    EditText username ;
    String usernamet,passwordt;
    AlertDialog.Builder affiche ;
    String urllog ="http://imamaben-001-site1.etempurl.com/login.php";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_testlogin);
            username=(EditText) findViewById(R.id.usern) ;
            pass=(EditText) findViewById(R.id.passw);
            affiche = new AlertDialog.Builder(testlogin.this);
            Button connect = (Button) findViewById(R.id.connect) ;
            connect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    usernamet = username.getText().toString();
                    passwordt = pass.getText().toString();
                    Intent i= new Intent(testlogin.this,activity11.class);
                    if(usernamet.equals("")||passwordt.equals(""))
                    {
                        affiche.setTitle("something went wrong");
                        affichage("Verifier username et password");
                    }
                    else
                    {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, urllog,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONArray jsonArray = new JSONArray(response);
                                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                                            String code = jsonObject.getString("code");
                                            if(code.equals("login failed"))
                                            {
                                                affiche.setTitle("login error ...");
                                                affichage(jsonObject.getString("message"));
                                            }
                                            else
                                            {
                                                Intent i= new Intent(testlogin.this,activity11.class);
                                                Bundle bundle = new Bundle();

                                                bundle.putString("username",jsonObject.getString("username"));

                                                i.putExtras(bundle);
                                                startActivity(i);

                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(testlogin.this,"ERROR",Toast.LENGTH_LONG).show();
                                error.printStackTrace();

                            }
                        })
                        {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> params = new HashMap <String, String>();
                                params.put("username",usernamet);
                                params.put("password",passwordt);
                                return params;
                            }
                        };
                        Volleyclass.getmInstance(testlogin.this).addToRequestque(stringRequest);
                    }
                }
            });
        }


        public void affichage(String message)
        {
            affiche.setMessage(message);
            affiche.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    username.setText("");
                    pass.setText("");
                }
            });
            AlertDialog alertDialog = affiche.create();
            alertDialog.show();
        }

    }

