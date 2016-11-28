package com.example.labsoftware.pokemonapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(   ){
	/*llamada de los id de la activity*/
        btn = (Button)findViewById(R.id.boton1);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
		//Un id para implementar con un swith de 1 caso
        int id;
        id = view.getId();
        switch (id)
        {
            case R.id.boton1:
                Intent pag2 = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(pag2);
        }
    }
}
