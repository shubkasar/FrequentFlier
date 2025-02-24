package com.example.freqflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        Intent intent=getIntent();
        String spid = intent.getStringExtra("passid");
//        String dpid;
//        Toast.makeText(this, "spid: "+spid, Toast.LENGTH_SHORT).show();

        EditText editText4 = findViewById(R.id.editText4);
        editText4.setHint("Enter Points");

        Spinner spinner3 = findViewById(R.id.spinner3);

        Button button7 = findViewById(R.id.button7);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url1 = "http://10.0.2.2:8080/frequentflier/GetPassengerids.jsp?pid="+spid;

        StringRequest request1 = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String result = s.trim();
                String[] ids = result.split("#");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity6.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,ids);
                spinner3.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(request1);

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String dpid = adapterView.getItemAtPosition(i).toString();

                button7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String npoints = editText4.getText().toString();
                        String url2 = "http://10.0.2.2:8080/frequentflier/TransferPoints.jsp?spid="+spid+"&dpid="+dpid+"&npoints="+npoints;

                        StringRequest request2 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {

                                String result2 = s.trim();
                                Toast.makeText(MainActivity6.this, result2, Toast.LENGTH_SHORT).show();


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        });
                        queue.add(request2);
                    }
                });



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}