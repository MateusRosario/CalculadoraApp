package com.example.calculadora;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class Calculadora {
    TextView display = null;

    public Calculadora(TextView display){
        this.display = display;
    }

    public void  botaoClicado(View a){
        if(display.getText() == "no result"){
            display.setText("");
        }
        Button botao = (Button) a;
        String botaoT = (String) botao.getText();
        if(botaoT.equals("")) {
            display.setText("");
        }else if(botaoT.equals(">")){
            if(display.getText().length() > 0){
                display.setText(display.getText().toString().substring(0,display.getText().length()-1));
            }
        }else if(botaoT.equals("=")) {
            if(display.getText().length() == 0){
                display.setText("No expr.");
            }else {
                lerExprecao((String) display.getText());
            }
        }else if(display.getText().length() < 8){
            display.setText(display.getText() + botaoT);
        }
    }

    public boolean lerExprecao(String exprecao){
        ArrayList<String> list = new ArrayList<String>();
        int elemento = 0;
        char car = exprecao.charAt(0);
        if(car == '/' || car == 'X'){
            display.setText("Inv. Ope.");
            return false;
        }else{
            list.add(car + "");
        }
        for(int i = 1; i < exprecao.length(); i++){
            car = exprecao.charAt(i);
            if(car == '/' || car == 'X') {
                list.add(car + "");
                elemento = list.size();
            }else if(car == '+' || car == '-'){
                list.add(car + "");
                elemento++;
            }else{
                if(elemento == list.size()){
                    list.add(car + "");
                }else{
                    list.add(elemento,list.get(elemento) + car);
                    list.remove(list.size()-1);
                }
            }
        }
        if(((String)list.get(list.size()-1)).charAt(((String)list.get(list.size()-1)).length()-1) == '+' || ((String)list.get(list.size()-1)).charAt(((String)list.get(list.size()-1)).length()-1) == '-' || ((String)list.get(list.size()-1)).charAt(((String)list.get(list.size()-1)).length()-1) == '/' || ((String)list.get(list.size()-1)).charAt(((String)list.get(list.size()-1)).length()-1) == 'X'){
            list.remove(list.size()-1);
        }
        Calcular(list);
        return true;
    }

    public void Calcular(ArrayList exprecao){
        ArrayList nums = new ArrayList();
        boolean resultFloat = false;
        int i;
        for(i =0; i < exprecao.size()-1; i++){
            if(((String)exprecao.get(i+1)).equals("X")){
                nums.add(Integer.parseInt((String) exprecao.get(i)) * Integer.parseInt((String) exprecao.get(i+2)));
                i = i+3;
            }else if(((String)exprecao.get(i+1)).equals("/")){
                nums.add(Float.parseFloat((String) exprecao.get(i)) / Float.parseFloat((String) exprecao.get(i+2)));
                resultFloat = true;
                i = i+3;
            }else{
                nums.add(Integer.parseInt((String) exprecao.get(i)));
            }
        }
        if(!(exprecao.get(exprecao.size()-2).equals("X")) && !(exprecao.get(exprecao.size()-2).equals("/"))){
            nums.add(Integer.parseInt((String) exprecao.get(exprecao.size()-1)));
        }
        if(resultFloat){
            float result = 0;
            for(i =0; i< nums.size(); i++){
                result += nums.get(i) instanceof Integer?(int) nums.get(i): (float) nums.get(i);
            }
            display.setText(String.valueOf(result));
        }else{
            int result = 0;
            for(i =0; i< nums.size(); i++){
                result += (int) nums.get(i);
            }
            display.setText(String.valueOf(result));
        }

    }
}
