package com.example.boyraztalha.oxforddictionaryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    String url;
    EditText editText;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edittext);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textview);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url =  dictionaryEntries();
                DictionaryCallback dictionaryCallback = new DictionaryCallback(MainActivity.this,textView);
                dictionaryCallback.execute(url);
            }
        });
    }

    private String dictionaryEntries() {
        final String language = "en";
        final String word = editText.getText().toString();        //editText.getText().toString()
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }

}
