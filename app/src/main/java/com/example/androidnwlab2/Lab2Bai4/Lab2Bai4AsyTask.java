package com.example.androidnwlab2.Lab2Bai4;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Lab2Bai4AsyTask extends AsyncTask<Void, Void, Void> {
    ProgressDialog progressDialog;
    TextView textView;
    Context context;
    String duongdan=Lab2Bai4Activity.SERVER_NAME;
    String a,b,c;
    String kq;

    public Lab2Bai4AsyTask( TextView textView, Context context, String a, String b, String c) {
        this.textView = textView;
        this.context = context;
        this.a = a;
        this.b = b;
        this.c = c;
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
    protected Void doInBackground(Void... voids) {
        try {
            URL url=new URL(duongdan);
            String ts="a="+ URLEncoder.encode(a,"UTF-8")
                    +"&b="+URLEncoder.encode(b,"UTF-8")
                    +"&c="+URLEncoder.encode(c,"UTF-8");
            HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setFixedLengthStreamingMode(ts.getBytes().length);
            urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            Writer out;
            PrintWriter printWriter=new PrintWriter(urlConnection.getOutputStream());
            printWriter.print(ts);
            printWriter.close();
            String line="";
InputStream in;
BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder=new StringBuilder();
            while ((line=bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }
            kq=stringBuilder.toString();
            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        textView.setText(kq);
    }
}
