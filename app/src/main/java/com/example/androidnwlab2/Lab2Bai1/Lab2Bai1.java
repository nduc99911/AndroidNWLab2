package com.example.androidnwlab2.Lab2Bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidnwlab2.MainActivity;
import com.example.androidnwlab2.R;

public class Lab2Bai1 extends AppCompatActivity implements View.OnClickListener {
TextView textView;
EditText edtName,edtScore;
Button button;

public static final String SEVER_NAME="http://192.168.1.8:8080/Test/std_get.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2_bai1);
        edtName=findViewById(R.id.edtName);
        edtScore=findViewById(R.id.edtSocre);
        button=findViewById(R.id.btnLoad);
        textView=findViewById(R.id.tvKetQua);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String name=edtName.getText().toString();
        String score=edtScore.getText().toString();
        Lab2AsyncTaskGet lab2AsyncTaskGet=new Lab2AsyncTaskGet(this,name,score,textView);
        lab2AsyncTaskGet.execute();
    }
}