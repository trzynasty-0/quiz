package com.example.quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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
    private int numerPytanka = 0, iloscPytan = 3, wynik = 0;
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

        pytania.add(new Pytanie(R.drawable.wiewiora, "Czy wiewiórki zapadają w sen zimowy?", "Wiewiórki robią zapasy na zimę", true));
        pytania.add(new Pytanie(R.drawable.bociek, "huh??", "nwm", true));
        pytania.add(new Pytanie(R.drawable.krasnal, "z krakowa??", "nie", false));

        bNie = findViewById(R.id.button3);
        bTak = findViewById(R.id.button4);
        bNext = findViewById(R.id.button6);
        trescPytania = findViewById(R.id.trescPytanie);
        zdjecie = findViewById(R.id.obrazek1);
        wyswietlPytanie(numerPytanka);

        bNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(numerPytanka < pytania.size()) {
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
                        sprawdzeniePoprawności(numerPytanka, true);
                        numerPytanka++;
                        wyswietlPytanie(numerPytanka);
                    }
                }
        );

        bNie.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sprawdzeniePoprawności(numerPytanka, false);
                        numerPytanka++;
                        wyswietlPytanie(numerPytanka);
                    }
                }
        );


    }

    private void wyswietlPytanie(int ktorePytanie){
        trescPytania.setText(pytania.get(ktorePytanie).getTrescPytania());
        zdjecie.setImageResource(pytania.get(ktorePytanie).getIdObrazek());
    }
    private void sprawdzeniePoprawności(int ktorePytanie, boolean udzielonaOdpowniedz){
        if(pytania.get(ktorePytanie).isOdpowiedz() != udzielonaOdpowniedz){
            pytania.get(ktorePytanie).setCzyOdpowiedzOk(true);
            Toast.makeText(this, "Udzielono poprawną odpowiedź", Toast.LENGTH_SHORT).show();
        }
        else{
            pytania.get(ktorePytanie).setCzyOdpowiedzOk(false);
            Toast.makeText(this, "Udzielono złą odpowiedź", Toast.LENGTH_SHORT).show();
        }
    }

}