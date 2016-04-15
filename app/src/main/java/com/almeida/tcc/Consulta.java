package com.almeida.tcc;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
class ConsultaImg extends AsyncTask<String, String, Bitmap> {//Parametro, progresso, resultado
    private Context context;
    private ConsultaInterface ci;
    private ProgressDialog progress;

    public ConsultaImg(Context context, ConsultaInterface ci){
        this.context = context;
        this.ci = ci;
    }


    protected void onPreExecute(){
        //final ProgressDialog progress = new ProgressDialog(context);
        //progress.setMessage("Carregando Imagem...");
        //progress.show();
    }

    protected Bitmap doInBackground(String... params) {
    Bitmap img = null;

        try{
            URL url = new URL(params[0]);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            InputStream input = conexao.getInputStream();

            img = BitmapFactory.decodeStream(input);
        }

        catch (IOException e){}

        return(img);
    }

    protected void onProgressUpdate(String... params) {
    }

    protected void onPostExecute(Bitmap params) {
        //progress.setMessage("Imagem carregada!");
        ci.downloadImg(params);
        //progress.dismiss();
    }
}*/

class ConsultaTxt extends AsyncTask<String, String, String> {//Parametro, progresso, resultado
    private static final int USER_AGENT = 0; /////
    private Context context;
    private ConsultaInterface ci;
    private ProgressDialog progress;

    public ConsultaTxt(Context context, ConsultaInterface ci){
        this.context = context;
        this.ci = ci;
    }


    protected void onPreExecute(){}

    protected String doInBackground(String... params) {
        String str = null;
        StringBuffer response = new StringBuffer();

        URL url = null;
        try {
            url = new URL(params[0]);
            HttpURLConnection conexao = null;
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            //add request header
            conexao.setRequestProperty("User-Agent", String.valueOf(USER_AGENT));

            int responseCode = 0;
            responseCode = conexao.getResponseCode();

            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = null;
            in = new BufferedReader(
                    new InputStreamReader(conexao.getInputStream()));

            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }

    protected void onProgressUpdate(String... params) {    }

    protected void onPostExecute(String params) {
        //progress.setMessage("Imagem carregada!");
        ci.downloadTxt(params);
        //progress.dismiss();
    }
}