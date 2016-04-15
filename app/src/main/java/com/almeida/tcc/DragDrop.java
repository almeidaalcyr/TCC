package com.almeida.tcc;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class DragDrop extends Activity {
    ImageView andar, direita, esquerda, funcao, apagar, socketVazio, f01, f02, f03, f04;
    String aluno, carro, saida;

    private Socket socket = null;

    static int LIMPAR=0;
    static int LER=1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_drop);

        findViewById(R.id.andar).setOnLongClickListener(longListener);
        findViewById(R.id.direita).setOnLongClickListener(longListener);
        findViewById(R.id.esquerda).setOnLongClickListener(longListener);
        findViewById(R.id.funcao).setOnLongClickListener(longListener);
        findViewById(R.id.apagar).setOnLongClickListener(longListener);

        findViewById(R.id.s01).setOnDragListener(dragListener);
        findViewById(R.id.s02).setOnDragListener(dragListener);
        findViewById(R.id.s03).setOnDragListener(dragListener);
        findViewById(R.id.s04).setOnDragListener(dragListener);
        findViewById(R.id.s05).setOnDragListener(dragListener);
        findViewById(R.id.s06).setOnDragListener(dragListener);
        findViewById(R.id.s07).setOnDragListener(dragListener);
        findViewById(R.id.s08).setOnDragListener(dragListener);
        findViewById(R.id.s09).setOnDragListener(dragListener);
        findViewById(R.id.s10).setOnDragListener(dragListener);
        findViewById(R.id.s11).setOnDragListener(dragListener);
        findViewById(R.id.s12).setOnDragListener(dragListener);
        findViewById(R.id.f01).setOnDragListener(dragListener);
        findViewById(R.id.f02).setOnDragListener(dragListener);
        findViewById(R.id.f03).setOnDragListener(dragListener);
        findViewById(R.id.f04).setOnDragListener(dragListener);

        andar = (ImageView) findViewById(R.id.andar);
        direita = (ImageView) findViewById(R.id.direita);
        esquerda = (ImageView) findViewById(R.id.esquerda);
        funcao = (ImageView) findViewById(R.id.funcao);
        apagar = (ImageView) findViewById(R.id.apagar);
        socketVazio = (ImageView) findViewById(R.id.socketVazio);
        f01 = (ImageView) findViewById(R.id.f01);
        f02 = (ImageView) findViewById(R.id.f02);
        f03 = (ImageView) findViewById(R.id.f03);
        f04 = (ImageView) findViewById(R.id.f04);

        Intent intent = getIntent();
        if (intent != null){
            Bundle params = intent.getExtras();

            if(params != null){
                aluno = params.getString("nome");
                carro = params.getString("carro");
            }
        }
    }

    public void enviar(View v){
        saida="s=";
        saida+=valor((ImageView) findViewById(R.id.s01), LER);
        saida+=valor((ImageView) findViewById(R.id.s02), LER);
        saida+=valor((ImageView) findViewById(R.id.s03), LER);
        saida+=valor((ImageView) findViewById(R.id.s04), LER);
        saida+=valor((ImageView) findViewById(R.id.s05), LER);
        saida+=valor((ImageView) findViewById(R.id.s06), LER);
        saida+=valor((ImageView) findViewById(R.id.s07), LER);
        saida+=valor((ImageView) findViewById(R.id.s08), LER);
        saida+=valor((ImageView) findViewById(R.id.s09), LER);
        saida+=valor((ImageView) findViewById(R.id.s10), LER);
        saida+=valor((ImageView) findViewById(R.id.s11), LER);
        saida+=valor((ImageView) findViewById(R.id.s12), LER);
        saida+="&f=";
        saida+=valor((ImageView) findViewById(R.id.f01), LER);
        saida+=valor((ImageView) findViewById(R.id.f02), LER);
        saida+=valor((ImageView) findViewById(R.id.f03), LER);
        saida+= valor((ImageView) findViewById(R.id.f04), LER);
        saida+="&";

        new Thread(new ClienteThread()).start();
        Thread.interrupted();

        //TODO enviar comandos para o servidor (HTTP)
    }

    public void limpar(View v){
        valor((ImageView)findViewById(R.id.s01), LIMPAR);
        valor((ImageView)findViewById(R.id.s02), LIMPAR);
        valor((ImageView)findViewById(R.id.s03), LIMPAR);
        valor((ImageView)findViewById(R.id.s04), LIMPAR);
        valor((ImageView)findViewById(R.id.s05), LIMPAR);
        valor((ImageView)findViewById(R.id.s06), LIMPAR);
        valor((ImageView)findViewById(R.id.s07), LIMPAR);
        valor((ImageView)findViewById(R.id.s08), LIMPAR);
        valor((ImageView)findViewById(R.id.s09), LIMPAR);
        valor((ImageView)findViewById(R.id.s10), LIMPAR);
        valor((ImageView)findViewById(R.id.s11), LIMPAR);
        valor((ImageView)findViewById(R.id.s12), LIMPAR);
        valor((ImageView)findViewById(R.id.f01), LIMPAR);
        valor((ImageView)findViewById(R.id.f02), LIMPAR);
        valor((ImageView)findViewById(R.id.f03), LIMPAR);
        valor((ImageView)findViewById(R.id.f04), LIMPAR);
    }

    public String valor(ImageView iv, int valor){
        if(valor ==LER)
            if (iv.getDrawable() == andar.getDrawable()) return "A";
            else if (iv.getDrawable() == direita.getDrawable()) return "D";
            else if (iv.getDrawable() == esquerda.getDrawable()) return "E";
            else if (iv.getDrawable() == funcao.getDrawable()) return "F";
            else return "";

        else if (valor==LIMPAR){
            iv.setImageDrawable(socketVazio.getDrawable());
            return "";
        }

        return "";
    }

    OnLongClickListener longListener = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ImageView iv = (ImageView) v;

            View.DragShadowBuilder myShadowBuilder = new MyShadowBuilder(iv);

            ClipData data = ClipData.newPlainText("", "");
            v.startDrag(data, myShadowBuilder, iv, 0);

            return true;
        }
    };

    OnDragListener dragListener = new OnDragListener(){
        @Override
        public boolean onDrag(View v, DragEvent event){
            ImageView iv = (ImageView) v;
            ImageView e = (ImageView) event.getLocalState();
            int dragEvent = event.getAction();

            if (dragEvent == DragEvent.ACTION_DROP)
                if (e != funcao || iv != f01 && iv != f02 && iv != f03 && iv != f04)
                    if (e == apagar) iv.setImageDrawable(socketVazio.getDrawable());
                    else iv.setImageDrawable(e.getDrawable());

            return true;
        }
    };

    private class MyShadowBuilder extends View.DragShadowBuilder{
        private Drawable shadow;

        public MyShadowBuilder(ImageView iv){
            super(iv);
            shadow = iv.getDrawable();
        }

        @Override
        public void onDrawShadow(Canvas canvas){
            shadow.draw(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint){
            int height = getView().getHeight();
            int width =  getView().getHeight();

            shadow.setBounds(0, 0, width, height);

            shadowSize.set(width, height);
            shadowTouchPoint.set(width/2, height/2);
        }
    }

    class ClienteThread implements Runnable {
        @Override
        public void run() {
            try {
                carro="1.1.1.4";
                InetAddress servidor = InetAddress.getByName(carro);
                socket = new Socket(servidor, 6666);
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                out.println(saida);
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}