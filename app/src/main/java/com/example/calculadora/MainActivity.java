package com.example.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Calculadora calc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView display = (TextView) findViewById(R.id.display);
        calc = new Calculadora(display);
    }

    public void botaoClicado(View a){
        calc.botaoClicado(a);
    }

    public void apagarDisplay(View a){
        ((Button) a).setText("");
    }
}
