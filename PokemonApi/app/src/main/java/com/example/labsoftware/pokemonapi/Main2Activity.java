package com.example.labsoftware.pokemonapi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    TextView name1;
    TextView name2;
    int hppc = 100;
    int hpjp = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
        //se llaman los metodos para mostrar los pokemones
        /*imaPoke1();
        imaPoke2();*/
        int nume1 = (int) (Math.random() * 721);
        int num1 = nume1;
        int nume2 = (int) (Math.random() * 721);
        int num2 = nume2;
        cargarimg(num1,ima1);
        cargarimg(num2,ima2);
        cargarname(num1,name1);
        cargarname(num2, name2);

        hp1.setText(String.valueOf(hppc));
        hp2.setText(String.valueOf(hpjp));
        //hp();
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
        name1 = (TextView)findViewById(R.id.textView13);
        name2 = (TextView)findViewById(R.id.textView6);

    }

    /*public void hp(){
        hp1.setText("100");
        hp2.setText("100");
    }*/

    public void downloadimg(ImageView imageView,String url){
        ImageLoader mImageLoader;
        mImageLoader = MySingleton.getInstance(this).getImageLoader();
        mImageLoader.get(url, ImageLoader.getImageListener(imageView,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher));

    }
    //metodo para cargar las imagenes de los pokemon's
    public void cargarimg(int id, final ImageView imageView){
        MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        String url ="http://pokeapi.co/api/v2/pokemon-form/"+id+"/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("response",response);
                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            JSONObject jsonObject1=new JSONObject(jsonObject.getString("sprites"));
                            String url=jsonObject1.getString("front_default").toString();
                            downloadimg(imageView,url);
                            Log.i("imagen: ", jsonObject1.getString("front_default").toString());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("","¡Error al Cargar!");
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
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
    //Metodo para cargar nombre de los pokemon's
    public void cargarname(int id, final TextView t){

        MySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();
        String url ="http://pokeapi.co/api/v2/pokemon/"+id+"/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("response",response);
                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            Log.i("nombre",jsonObject.getString("name"));
                            t.setText( jsonObject.getString("name"));
                            JSONArray jsonArray=jsonObject.getJSONArray("abilities");
                            for (int i=0;i<jsonArray.length();i++) {

                                Log.i("abilities: ", jsonArray.getJSONObject(i).toString());
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("","¡Error!");
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    @Override
    public void onClick(View v) {
        //Un id para implementar con un swith de 1 caso
        int id;
        id = v.getId();
        switch (id)
        {
            case R.id.btnataca:

                matar();

            break;
        }
    }

    public void matar(){
        int nume3 = (int) (Math.random() * 50);
        int num3 = nume3;
        int nume4 = (int) (Math.random() * 50);
        int num4 = nume4;

            hppc= hppc - num3;
            hp1.setText(String.valueOf(hppc));
            hpjp = hpjp - num4;
            hp2.setText(String.valueOf(hpjp));

            if (Integer.parseInt(hp1.getText().toString()) <= 0){
                btn2.setEnabled(false);
                ima1.setImageBitmap(null);
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Ganador: Jugador", Toast.LENGTH_SHORT);

                toast1.show();
            }else if(Integer.parseInt(hp2.getText().toString()) <= 0) {
                btn2.setEnabled(false);
                ima2.setImageBitmap(null);
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Ganador: PC", Toast.LENGTH_SHORT);

                toast1.show();
            }else {

            }

    }
}
