package com.example.estruturadethreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonIniciar, pararButton;
    private  int numero;
    private Handler handler = new Handler();
    private Boolean  pararExecução = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonIniciar = findViewById(R.id.buttonIniciar);
        pararButton   = findViewById(R.id.pararButton);
    }

    public void iniciarThread(View view) {

//        MyThread thread = new MyThread();
//        thread.start();
        pararExecução = false;
        MyRunnable runnable = new MyRunnable();
        new  Thread(runnable).start();
    }

    public void pararThread (View view){

        pararExecução = true;
    }



    class  MyRunnable implements  Runnable {

        @Override
        public void run() {

            //Handler handler = new Handler();

            for (int i=0; i<=15; i++){

                if (pararExecução)
                    return;

                numero = i;
                Log.d("Thread", "contador" + i );

                /*
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        buttonIniciar.setText("contador: " + numero);
                    }
                });

                 */

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        buttonIniciar.setText("contador: " + numero);
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {

            for (int i=0; i<=15; i++){
                Log.d("Thread", "contador" + i );
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            super.run();
        }
    }
}