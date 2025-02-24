package com.example.freqflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textView4 = findViewById(R.id.textView4);

        TextView textView6 = findViewById(R.id.textView6);
        textView6.setVisibility(TextView.INVISIBLE);
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setVisibility(ImageView.INVISIBLE);

        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);

        Intent intent = getIntent();
        String pid = intent.getStringExtra("passid");
        String url = "http://10.0.2.2:8080/frequentflier/Info.jsp?pid="+pid;

//        Toast.makeText(this,pid,Toast.LENGTH_LONG).show();


        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if(s.equals("error")){
                    Toast.makeText(MainActivity2.this, "Error Fetching Customer details", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{

//                    Toast.makeText(MainActivity2.this,s.trim().toString(),Toast.LENGTH_LONG).show();
                    String[] result = s.trim().split(",");
                    String pname = result[0];
                    String total_points = result[1];
//                    Toast.makeText(MainActivity2.this,"Pname = "+pname+" & points = "+total_points,Toast.LENGTH_LONG).show();
                    textView4.setText(pname);
                    textView6.setText(total_points);
//                    imageView.setImageResource(R.drawable.default_icon);
                    String url2="http://10.0.2.2:8080/frequentflier/images/"+pid+".jpg";
                    ImageRequest request2=new ImageRequest(url2, new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            imageView.setImageBitmap(bitmap);
                            imageView.setVisibility(ImageView.VISIBLE);
                        }
                    },0,0,null, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Display a default image if an error occurs
                            imageView.setImageResource(R.drawable.default_icon);
                            imageView.setVisibility(ImageView.VISIBLE);
                        }
                    });
                    queue.add(request2);
                    textView4.setVisibility(TextView.VISIBLE);
                    textView6.setVisibility(TextView.VISIBLE);
//                    imageView.setVisibility(ImageView.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(request);



        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this,MainActivity3.class);
                intent.putExtra("passid",pid);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this,MainActivity4.class);
                intent.putExtra("passid",pid);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this,MainActivity5.class);
                intent.putExtra("passid",pid);
                startActivity(intent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this,MainActivity6.class);
                intent.putExtra("passid",pid);
                startActivity(intent);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}