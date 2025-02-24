package com.example.freqflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
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
import com.google.android.material.tabs.TabLayout;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TableLayout tableLayout = (TableLayout)findViewById(R.id.tableLayout);

        Intent intent=getIntent();
        String pid = intent.getStringExtra("passid");

        String url = "http://10.0.2.2:8080/frequentflier/Flights.jsp?pid="+pid;

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest reqFlights = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if(s.equals("error")){
                    Toast.makeText(MainActivity3.this, "Error Fetching Customer details", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
//                    String result = Html.fromHtml(s).toString();
//                    result = result.substring(12);
//                    result.trim();
                    String result = s.trim();

                    String[] resultRows = result.split("#");

//                    Toast.makeText(MainActivity3.this, result, Toast.LENGTH_SHORT).show();

//                    tableLayout.setBackgroundColor(Color.WHITE);

                    TableRow rowIndex = new TableRow(MainActivity3.this);
//                    rowIndex.setBackgroundColor(Color.WHITE);
//                    rowIndex.setGravity(View.FOCUS_LEFT);

                    TextView tv0 = new TextView(MainActivity3.this);
                    tv0.setText(" Flight Id ");
                    tv0.setTextColor(Color.BLACK);
                    tv0.setGravity(Gravity.LEFT);
                    tv0.setTextSize(24);
                    tv0.setTypeface(null, Typeface.BOLD);
                    tv0.setPadding(30, 30, 30, 30);

                    rowIndex.setBackgroundColor(Color.LTGRAY);
//                    rowIndex.setPadding(0,10,0,10);
                    rowIndex.addView(tv0);
                    TextView tv1 = new TextView(MainActivity3.this);
                    tv1.setText(" Miles ");
                    tv1.setTextColor(Color.BLACK);
                    tv1.setGravity(Gravity.LEFT);
                    tv1.setTextSize(24);
                    tv1.setTypeface(null, Typeface.BOLD);
                    tv1.setPadding(30, 30, 30, 30);

                    rowIndex.addView(tv1);
                    TextView tv2 = new TextView(MainActivity3.this);
                    tv2.setText(" Destination ");
                    tv2.setTextColor(Color.BLACK);
                    tv2.setGravity(Gravity.LEFT);
                    tv2.setTextSize(24);
                    tv2.setTypeface(null, Typeface.BOLD);
                    tv2.setPadding(30, 30, 30, 30);
                    rowIndex.addView(tv2);

                    tableLayout.addView(rowIndex);

                    for(int i=0; i<resultRows.length; i++){

                        String[] columns = resultRows[i].trim().split(",");

                        TableRow rows = new TableRow(MainActivity3.this);
                        TextView tbView1 = new TextView(MainActivity3.this);
                        tbView1.setText(" " + columns[0] + "  ");
                        tbView1.setTextColor(Color.BLACK);
                        tbView1.setGravity(Gravity.LEFT);
                        tbView1.setTextSize(20);
                        rows.addView(tbView1);

                        TextView tbView2 = new TextView(MainActivity3.this);
                        tbView2.setText( " " + columns[1].trim().split(" ")[0] + "  ");
                        tbView2.setTextColor(Color.BLACK);
                        tbView2.setGravity(Gravity.LEFT);
                        tbView2.setTextSize(20);
                        rows.addView(tbView2);

                        TextView tbView3 = new TextView(MainActivity3.this);
                        tbView3.setText(" " + columns[2] + "  ");
                        tbView3.setTextColor(Color.BLACK);
                        tbView3.setGravity(Gravity.LEFT);
                        tbView3.setTextSize(20);
                        rows.addView(tbView3);

                        tableLayout.addView(rows);
                    }


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(reqFlights);

    }
}