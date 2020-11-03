package com.example.androidnwlab2.Lab2Bai3;

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

public class Lab2Bia3AsyTask extends AsyncTask<String,Void,Void> {
    ProgressDialog progressDialog;
    TextView textView;
    String duongdan=Lab2Bai3Activity.SERVER_NAME;
    Context context;
    String strkq;
    String canh;

    public Lab2Bia3AsyTask( TextView textView, Context context,String canh) {
        this.textView = textView;
        this.context = context;
this.canh=canh;
    }

    @Override
    protected void onPreExecute() {
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Xin vui lòng chờ...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            URL url=new URL(duongdan);
            String param="canh="+ URLEncoder.encode(canh,"UTF-8");
            HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
            //Nó nhận một giá trị làm tham số và đặt giá trị này của doOutputtrường cho URLConnection này thành giá trị được chỉ định.
            //
            //Kết nối URL có thể được sử dụng cho đầu vào và / hoặc đầu ra. Đặt cờ DoOutput thành true nếu bạn định sử dụng kết nối URL cho đầu ra, false nếu không. Mặc định này sai.
            urlConnection.setDoOutput(true);

            urlConnection.setRequestMethod("POST");
            //Phương pháp này được sử dụng để cho phép phát trực tuyến nội dung yêu cầu HTTP mà không có bộ đệm nội bộ, khi độ dài nội dung được biết trước.
            urlConnection.setFixedLengthStreamingMode(param.getBytes().length);
            urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            PrintWriter writer=new PrintWriter(urlConnection.getOutputStream());
            writer.print(param);
            writer.close();
            String line="";
            //được sử dụng để đọc văn bản từ một input stream
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder=new StringBuilder();
            while ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line);
            }
            strkq=stringBuilder.toString();
            urlConnection.disconnect();
        } catch (MalformedURLException | UnsupportedEncodingException e) {
            e.printStackTrace();
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
        textView.setText(strkq);
    }
}
