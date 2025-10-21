package com.example.quiz;

import java.util.ArrayList;

public class Repozytorium {
    private static ArrayList<Pytanie> pytania;
    public static ArrayList<Pytanie> zwrocWszystkiePytania(){
        pytania.add(new Pytanie(R.drawable.wiewiora, "Czy wiewiórki są rude?", "Obrazek", true));
        pytania.add(new Pytanie(R.drawable.bociek, "czy bociany odlatują na zimę", "czasem na niebie widać ich klucze", true));
        pytania.add(new Pytanie(R.drawable.krasnal, "z krasnal na obrazku jest z krakowa??", "jest wiele miast z krasnalami w Polsce", false));

        return pytania;
    }



}
