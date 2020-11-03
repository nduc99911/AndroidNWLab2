package com.example.androidnwlab2.Lab2Bai1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Lab2AsyncTaskGet extends AsyncTask<Void,Void,Void> {
private Context context;
private String name,score;
private TextView textView;
ProgressDialog progressDialog;
String str;
String duongdan=Lab2Bai1.SEVER_NAME;
    public Lab2AsyncTaskGet(Context context, String name, String score, TextView textView) {
        this.context = context;
        this.name = name;
        this.score = score;
        this.textView = textView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Xin vui lòng chờ...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        duongdan =duongdan + "?name=" +this.name+ "&score=" + this.score;
        try {
            URL url=new URL(duongdan);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line="";

            StringBuffer stringBuffer=new StringBuffer();
            //read 1 dòng văn bản
            while ((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
            str=stringBuffer.toString();
            httpURLConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        textView.setText(str);
        Toast.makeText(context,"HI"+str,Toast.LENGTH_SHORT).show();
    }
}
