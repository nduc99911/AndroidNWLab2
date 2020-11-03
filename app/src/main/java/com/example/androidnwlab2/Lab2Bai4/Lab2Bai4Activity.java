package com.example.androidnwlab2.Lab2Bai4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidnwlab2.R;

public class Lab2Bai4Activity extends AppCompatActivity implements View.OnClickListener {
EditText edtsoa,edtsob,edtsoc;
Button button;
TextView textView;
public static final String SERVER_NAME="http://192.168.1.174:8080/Test/phuongtrinhbachai.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2_bai4);
        edtsoa=findViewById(R.id.edtSoa);
        edtsob=findViewById(R.id.edtSob);
        edtsoc=findViewById(R.id.edtSoc);
        button=findViewById(R.id.btnTinhBai4);
        textView=findViewById(R.id.tvKqbai4);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    Lab2Bai4AsyTask lab2Bai4AsyTask=new Lab2Bai4AsyTask(textView,this,edtsoa.getText().toString(),edtsob.getText().toString(),edtsoc.getText().toString());
    lab2Bai4AsyTask.execute();
    }
}