package com.example.quiz;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class PodpowiedzActivity extends AppCompatActivity {
    private TextView podpowiedzText, tytulText;
    private ArrayList<Pytanie> pytania = new ArrayList<>();
    private  int visualNumer = 0;
    private ImageView podpowiedzZdj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_podpowiedz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pytania = Repozytorium.zwrocWszystkiePytania();

        podpowiedzZdj = findViewById(R.id.podpowiedzImg);

        int numerPytanka = getIntent().getIntExtra("NUMERPYTANKA", 0);
        podpowiedzText = findViewById(R.id.podpowiedzView);
        tytulText = findViewById(R.id.tytulView);
        visualNumer = numerPytanka + 1;
        tytulText.setText("Podpowiedź do pytania nr " + visualNumer + ": ");
        podpowiedzText.setText(pytania.get(numerPytanka).getPodpowiedz());
        podpowiedzZdj.setImageResource(pytania.get(numerPytanka).getIdObrazek());
    }
}