package com.example.androidnwlab2.Lab2Bai3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidnwlab2.Lab2Bai2.Lab23Asytank;
import com.example.androidnwlab2.R;

public class Lab2Bai3Activity extends AppCompatActivity implements View.OnClickListener {
public static final String SERVER_NAME="http://192.168.1.8:8080/Test/hinhlapphuong.php";
EditText edtcanhEditText;
Button btnTinh;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2_bai3);
        edtcanhEditText=findViewById(R.id.edtCanh);
        btnTinh=findViewById(R.id.btnTinh3);
        textView=findViewById(R.id.tvkq3);
        btnTinh.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Lab2Bia3AsyTask task=new Lab2Bia3AsyTask(textView,this,edtcanhEditText.getText().toString());
        task.execute();
    }
}