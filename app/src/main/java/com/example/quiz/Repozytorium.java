package com.example.quiz;

import android.widget.TextView;

import java.util.ArrayList;

public class Repozytorium {
    private static ArrayList<Pytanie> pytania = new ArrayList<>();
    public static int licznik = 0;
    public static ArrayList<Pytanie> zwrocWszystkiePytania(){
        if(licznik == 0){
            pytania.add(new Pytanie(R.drawable.knight, R.string.tp1 , R.string.podpowiedz1, false));
            pytania.add(new Pytanie(R.drawable.hornet, R.string.tp2 , R.string.podpowiedz2, false));
            pytania.add(new Pytanie(R.drawable.together, R.string.tp3, R.string.podpowiedz3, true));
        }
        licznik++;



        return pytania;
    }



}
