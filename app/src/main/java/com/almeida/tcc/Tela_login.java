package com.almeida.tcc;
//TODO elaborar o design da activity

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;

public class Tela_login extends Activity implements ConsultaInterface {
    private String nomeConsulta=null;
    private String carroConsulta=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
    }

    public void login(View v) throws InterruptedException {

        EditText carro=(EditText) findViewById(R.id.carro_et);

        Bundle params = new Bundle();
        params.putString("nome",nomeConsulta);
        params.putString("carro", carro.getText().toString());
        Thread.sleep(1000); //Faz a Thread principal esperar a segunda Thread consultar login e senha

        //TODO Verificar existencia do aluno no DB.
        //TODO Verificar existencia do carro no DB e se ele está disponível.
        //if (nomeConsulta==null){
        //    Log.e(" >>>> : Não encontrado " + nomeConsulta, String.valueOf(Toast.LENGTH_LONG));
        //}
        //else {
            Intent intent = new Intent(this, DragDrop.class);
            intent.putExtras(params);

            startActivity(intent);
        //}
    }

    public void testaNome(View v){
        EditText nome= (EditText) findViewById(R.id.usuario_et);
        String url="http://192.168.0.15/tcc/?login=";
        url+=nome.getText().toString();
        ConsultaTxt consultaTxt = new ConsultaTxt(this, this);
        consultaTxt.execute(url);
    }

    public void downloadTxt(String string){
        nomeConsulta=string;
        Log.e(" <<<<nome>>>> : " + nomeConsulta, String.valueOf(Toast.LENGTH_LONG));
    }

    public void testaSocket(View v) throws IOException {

    }

}