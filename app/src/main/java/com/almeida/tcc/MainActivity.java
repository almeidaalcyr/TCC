/*package com.example.alcyr.http;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements ConsultaInterface{
    private TextView textView;
    private ImageView ImageView01;
    private String s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        ImageView01 =(ImageView)findViewById(R.id.ImageView01);
        textView = (TextView) findViewById(R.id.textView);


    }

    public void testeConexao(View v) throws IOException {

        String url="http://1.1.1.103/tcc/?login=alcyr";

        ConsultaTxt consultaTxt = new ConsultaTxt(this, this);
        consultaTxt.execute(url);

        ConsultaImg consultaImg = new ConsultaImg(this, this);
        consultaImg.execute("https://upload.wikimedia.org/wikipedia/pt/5/5a/Ivete-sangalo-perfil.jpg");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void downloadImg(Bitmap bitmap) {
        ImageView imageView = (ImageView) findViewById(R.id.ImageView01);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void downloadTxt(String string){
        s1=string;
        s1="-->"+s1+"<---";
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(s1);
    }
}*/