package com.example.labsoftware.pokemonapi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    Button btn2;
    ImageView ima1;
    ImageView ima2;
    TextView hp1;
    TextView hp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
        //se llaman los metodos para mostrar los pokemones
        imaPoke1();
        imaPoke2();
        hp();
    }

    public void init(   ){
	/*llamada de los id de la activity*/
        btn2 = (Button)findViewById(R.id.btnataca);
        btn2.setOnClickListener(this);
        ima1 = (ImageView)findViewById(R.id.imageView2);
        ima1.setOnClickListener(this);
        ima2 = (ImageView)findViewById(R.id.imageView3);
        ima2.setOnClickListener(this);
        hp1 = (TextView)findViewById(R.id.textView5);
        hp2 = (TextView)findViewById(R.id.textView7);

    }

    public void hp(){
        hp1.setText("100");
        hp2.setText("100");
    }

    //Boton inicio del menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
		/*Se añade un boton guardar en el menú*/
        menu.add("Inicio").setOnMenuItemClickListener(this.InicioClickListener).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    //metodo del boton inicio del menù para regresar al inicio de la App
    MenuItem.OnMenuItemClickListener InicioClickListener = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            Intent pag1 = new Intent(Main2Activity.this, MainActivity.class);
            startActivity(pag1);

            return false;
        }
    };

    //método para mostrar el pokémon del la maquina
    public void imaPoke1(){

        int nume1 = (int) (Math.random() * 500);
        int num1 = nume1;
        String url = "http://pokeapi.co/media/sprites/pokemon/"+num1+".png";

        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .into(ima1);

    }

    //método para mostrar nuestro pokémon
    public void imaPoke2(){

        int nume2 = (int) (Math.random() * 500);
        int num2 = nume2;
        String url = "http://pokeapi.co/media/sprites/pokemon/"+num2+".png";

        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .into(ima2);

    }

    @Override
    public void onClick(View v) {

    }
}
