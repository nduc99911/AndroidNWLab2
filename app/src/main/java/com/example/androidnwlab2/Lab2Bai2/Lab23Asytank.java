package com.example.androidnwlab2.Lab2Bai2;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

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

public class Lab23Asytank extends AsyncTask<Void,Void,Void> {
    private Context context;
    private String dai,rong,link;
    private String result;
    private TextView textView;

    public Lab23Asytank(Context context, String dai, String rong, String link, TextView textView) {
        this.link=link;
        this.context = context;
        this.dai = dai;
        this.rong = rong;
        this.textView = textView;
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            URL url=new URL(link);
            String param="chieurong="+ URLEncoder.encode(rong,"UTF-8")
            +"&chieudai="+URLEncoder.encode(dai,"UTF-8");
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setFixedLengthStreamingMode(param.getBytes().length);
            urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            PrintWriter p=new PrintWriter(urlConnection.getOutputStream());
            p.print(param);
            p.close();
            
            String line="";
            BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder sb=new StringBuilder();
            while ((line=br.readLine())!=null){
                sb.append(line);
            }
            result=sb.toString();
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
        textView.setText(result);
        Toast.makeText(context,"hi"+result,Toast.LENGTH_SHORT).show();
    }
}
