package com.example.quiz;

public class Pytanie {
    private int idObrazek, trescPytania, podpowiedz;
    private boolean odpowiedz, czyOdpowiedzOk;

    public Pytanie(int idObrazek, int trescPytania, int podpowiedz, boolean odpowiedz) {
        this.idObrazek = idObrazek;
        this.trescPytania = trescPytania;
        this.podpowiedz = podpowiedz;
        this.odpowiedz = odpowiedz;
        czyOdpowiedzOk = false;
    }

    public boolean isOdpowiedz() {
        return odpowiedz;
    }

    public boolean isCzyOdpowiedzOk() {
        return czyOdpowiedzOk;
    }

    public int getIdObrazek() {
        return idObrazek;
    }

    public int getTrescPytania() {
        return trescPytania;
    }

    public int getPodpowiedz() {
        return podpowiedz;
    }

    public void setCzyOdpowiedzOk(boolean czyOdpowiedzOk) {
        this.czyOdpowiedzOk = czyOdpowiedzOk;
    }
}
