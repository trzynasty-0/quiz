package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Pytanie> pytania = new ArrayList<>();
    private Button bTak, bNie, bPodpowiedz, bNext;
    private TextView trescPytania;
    private ImageView zdjecie;
    private Boolean czyBylaPodpowiedz = false;
    private int numerPytanka = 0, wynik = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pytania = Repozytorium.zwrocWszystkiePytania();

        if(savedInstanceState != null){
            wynik = savedInstanceState.getInt("WYNIK");
            numerPytanka = savedInstanceState.getInt("NUMERPYTANKA");
            czyBylaPodpowiedz = savedInstanceState.getBoolean("CZYBYLAPODPOWIEDZ");
        }

        bNie = findViewById(R.id.button4);
        bTak = findViewById(R.id.button3);
        bNext = findViewById(R.id.button6);
        bPodpowiedz = findViewById(R.id.button5);
        trescPytania = findViewById(R.id.trescPytanie);
        zdjecie = findViewById(R.id.obrazek1);
        wyswietlPytanie(numerPytanka);



        bNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(numerPytanka <= pytania.size()) {
                            numerPytanka++;
                            wyswietlPytanie(numerPytanka);
                        }
//

                    }
                }
        );

        bTak.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sprawdzeniePoprawnosci(numerPytanka, true);
                        numerPytanka++;
                        wyswietlPytanie(numerPytanka);
                    }
                }
        );

        bNie.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sprawdzeniePoprawnosci(numerPytanka, false);
                        numerPytanka++;
                        wyswietlPytanie(numerPytanka);
                    }
                }
        );
        bPodpowiedz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, PodpowiedzActivity.class);
                        intent.putExtra("NUMERPYTANKA", numerPytanka);
                        startActivity(intent);
                        czyBylaPodpowiedz = true;
                    }
                }
        );


    }

    private void wyswietlPytanie(int ktorePytanie){
        if(numerPytanka != pytania.size()){
            trescPytania.setText(pytania.get(ktorePytanie).getTrescPytania());
            zdjecie.setImageResource(pytania.get(ktorePytanie).getIdObrazek());
        }
        else{
            trescPytania.setText("Zdobyłeś " + wynik + " punktów");
            zdjecie.setVisibility(View.INVISIBLE);
            bTak.setVisibility(View.INVISIBLE);
            bNie.setVisibility(View.INVISIBLE);
            bNext.setVisibility(View.INVISIBLE);
            bPodpowiedz.setVisibility(View.INVISIBLE);

        }
    }
    private void sprawdzeniePoprawnosci(int ktorePytanie, boolean udzielonaOdpowniedz){
        if(pytania.get(ktorePytanie).isOdpowiedz() == udzielonaOdpowniedz){
            pytania.get(ktorePytanie).setCzyOdpowiedzOk(true);
            Toast.makeText(this, "Udzielono poprawną odpowiedź", Toast.LENGTH_SHORT).show();
            if(czyBylaPodpowiedz){
                wynik += 2;
                czyBylaPodpowiedz = false;
            }
            else{
                wynik += 5;
            }
        }
        else{
            pytania.get(ktorePytanie).setCzyOdpowiedzOk(false);
            Toast.makeText(this, "Udzielono złą odpowiedź", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("NUMERPYTANKA", numerPytanka);
        outState.putInt("WYNIK", wynik);
        outState.putBoolean("CZYBYLAPODPOWIEDZ", czyBylaPodpowiedz);
    }
}