package com.example.androidnwlab2.Lab2Bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidnwlab2.R;

public class Lab23Activity extends AppCompatActivity implements View.OnClickListener {
EditText edcd,edcr;
Button button;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab23);
        edcd=findViewById(R.id.edtcd);
        edcr=findViewById(R.id.edtcr);
        textView=findViewById(R.id.tvKq);
        button=findViewById(R.id.btnTinh);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String link="http://192.168.1.8:8080/Test/dientich.php";
        Lab23Asytank lab23Asytank=new Lab23Asytank(this,edcd.getText().toString(),edcr.getText().toString(),link,textView);
        lab23Asytank.execute();
    }
}