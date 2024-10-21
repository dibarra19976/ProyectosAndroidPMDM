package com.daniibarra.ast3roides;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class Scores extends ListActivity {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);
        setListAdapter(new MyAdapter(this,
                MainActivity.scoreStorage.getScoreList(10)));
    }
}