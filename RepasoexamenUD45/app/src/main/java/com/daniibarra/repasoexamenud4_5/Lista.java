package com.daniibarra.repasoexamenud4_5;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class Lista  extends ListActivity {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);
        setListAdapter(new ArrayAdapter<>(this,

                android.R.layout.simple_list_item_1,
                MainActivity.scoreStorage.getListaStrings()));
    }
}

