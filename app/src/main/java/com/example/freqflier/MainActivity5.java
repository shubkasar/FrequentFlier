package com.example.freqflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Intent intent=getIntent();
        String pid = intent.getStringExtra("passid");
//        Toast.makeText(MainActivity5.this, "pid: "+pid, Toast.LENGTH_SHORT).show();

        Spinner spinner2 = findViewById(R.id.spinner2);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url1 = "http://10.0.2.2:8080/frequentflier/AwardIds.jsp?pid="+pid;

        StringRequest request1 = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String result = s.trim();
//                Toast.makeText(MainActivity5.this, "s: "+s, Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity5.this, "pid: "+pid, Toast.LENGTH_SHORT).show();
                String[] ids = result.split("#");
//                String[] ids = new String[rows.length];
//                Toast.makeText(MainActivity5.this, ids[0], Toast.LENGTH_SHORT).show();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity5.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,ids);
                spinner2.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText((Context) MainActivity5.this, volleyError.toString(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request1);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(MainActivity5.this,"selected item: "+selectedItem, Toast.LENGTH_LONG).show();

                String url2 = "http://10.0.2.2:8080/frequentflier/RedemptionDetails.jsp?awardid="+selectedItem+"&pid="+pid;
                StringRequest request2 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
//                        Toast.makeText(MainActivity5.this, "s: "+s, Toast.LENGTH_SHORT).show();
                        String result2 = s.trim();
                        int endIndex = result2.indexOf("#");
                        String string1 = result2.substring(0, endIndex);
                        TextView textView12 = findViewById(R.id.textView12);
                        textView12.setVisibility(TextView.INVISIBLE);

                        String string2 = result2.substring(endIndex+1);
//                        Toast.makeText(MainActivity5.this, "table: "+string2, Toast.LENGTH_SHORT).show();

                        string1 = string1.replace(",","");
                        textView12.setText(string1);
                        textView12.setVisibility(TextView.VISIBLE);

                        TableLayout tableLayout2 = (TableLayout)findViewById(R.id.tableLayout2);
                        tableLayout2.removeAllViews();
                        String[] resultRows = string2.split("#");
                        TableRow rowIndex = new TableRow(MainActivity5.this);
                        TextView tv0 = new TextView(MainActivity5.this);
                        tv0.setText("Redemption Date");
                        tv0.setTextColor(Color.BLACK);
                        tv0.setGravity(Gravity.LEFT);
                        tv0.setTextSize(18);
                        tv0.setTypeface(null, Typeface.BOLD);
                        tv0.setPadding(30, 30, 30, 30);
                        rowIndex.setBackgroundColor(Color.LTGRAY);

                        rowIndex.addView(tv0);
                        TextView tv1 = new TextView(MainActivity5.this);
                        tv1.setText("Exchange Center");
                        tv1.setTextColor(Color.BLACK);
                        tv1.setGravity(Gravity.LEFT);
                        tv1.setTextSize(18);
                        tv1.setTypeface(null, Typeface.BOLD);
                        tv1.setPadding(30, 30, 30, 30);
                        rowIndex.addView(tv1);

                        tableLayout2.addView(rowIndex);

                        for(int i=0; i<resultRows.length; i++){

                            String[] columns = resultRows[i].trim().split(",");

                            TableRow rows = new TableRow(MainActivity5.this);
                            TextView tbView1 = new TextView(MainActivity5.this);
                            tbView1.setText(" " + columns[0] + "  ");
                            tbView1.setTextColor(Color.BLACK);
                            tbView1.setGravity(Gravity.LEFT);
                            tbView1.setTextSize(16);
                            rows.addView(tbView1);

                            TextView tbView2 = new TextView(MainActivity5.this);
                            tbView2.setText( " " + columns[1].trim() + "  ");
                            tbView2.setTextColor(Color.BLACK);
                            tbView2.setGravity(Gravity.LEFT);
                            tbView2.setTextSize(16);
                            rows.addView(tbView2);

                            tableLayout2.addView(rows);
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText((Context) MainActivity5.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(request2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}