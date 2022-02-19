package com.example.voicetext;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText writeEdt;
    Button checkBtn,checkNetbtn;
    TextToSpeech textToSpeech;
    TextView msgTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        writeEdt = findViewById(R.id.writeEdt);
        checkBtn = findViewById(R.id.checkbtn);
        checkNetbtn=findViewById(R.id.checkNetbtn);
        msgTxt=findViewById(R.id.msgTxt);

        textToSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

            }
        });

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (writeEdt.getText().toString().trim().length() > 0) {

                    textToSpeech.speak("" + writeEdt.getText().toString().trim(), TextToSpeech.QUEUE_FLUSH,
                            null, null);
                } else {
                    writeEdt.setError("কিছু লেখেন");
                }


            }
        });

        checkNetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           ConnectivityManager cmaneger= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
           NetworkInfo networkInfo=cmaneger.getActiveNetworkInfo();

           if (networkInfo!=null&&networkInfo.isConnected())
               msgTxt.setText("net ache");
           else 
               msgTxt.setText("no net");



            }
        });
    }


    @Override
    public void onBackPressed() {


        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Text to Voice App")
                .setMessage("Are you sure")
                .setCancelable(false)
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finishAndRemoveTask();
                    }
                })

                .show();

    }
}

