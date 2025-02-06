package com.daniibarra.ast3roides;

import java.util.ArrayList;
import java.util.List;

public class ScoreStorageList implements ScoreStorage{
    private List<String> scores;
    public ScoreStorageList() {
        scores= new ArrayList<>();
        scores.add("123000 Pepe Domínguez");
        scores.add("111000 Pedro Martínez");
        scores.add("011000 Paco Pérez");
    }
    @Override public void storeScore(int score, String name, long date) {
        scores.add(0, score + " "+ name);
    }
    @Override public List<String> getScoreList(int maxNo) {
        return scores;
    }
}