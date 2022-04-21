package com.example.icp11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btn;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.text);
        btn =  findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Log.e("message", "clicked");
               tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
                   @Override
                   public void onInit(int i) {
                       String text = editText.getText().toString();
                       tts.setSpeechRate(0.5f);
                       tts.speak(text,TextToSpeech.QUEUE_ADD,null);
                   }
               });
            }
        });

    }
}