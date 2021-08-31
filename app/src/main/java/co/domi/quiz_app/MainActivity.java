package co.domi.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView preguntaText;
    private TextView timeCount;
    private EditText respuesta;
    private TextView puntaje;
    private Button responder;
    private Button resetBtn;
    private Pregunta preguntaActual;
    private int timeLeft;
    private int score;
    private int pressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preguntaText = findViewById(R.id.preguntaTextV);
        timeCount = findViewById(R.id.timer);
        respuesta = findViewById(R.id.respuestaTextfield);
        puntaje = findViewById(R.id.puntajeText);
        responder = findViewById(R.id.responderBtn);
        resetBtn = findViewById(R.id.reintentoBtn);

        resetBtn.setVisibility(View.GONE);
        score = 0;
        timeLeft = 30;
        preguntaActual = new Pregunta();
        preguntaText.setText(preguntaActual.getPregunta());

        timeCount.setText("Time left: "+timeLeft);

        counterThread();

        puntaje.setText("Puntaje: "+ score);

        responder.setOnClickListener(
                (view) -> {
                    verificarRespuesta();
                }
        );

        resetBtn.setOnClickListener(
                (view) -> {
                    resetGame();
                }
        );
    }

    private void counterThread(){
        new Thread(
                () -> {
                    while(timeLeft > 0){
                        try{
                            timeLeft--;
                            runOnUiThread(
                                    () -> {
                                        timeCount.setText("Time left: "+timeLeft);
                                    }
                            );
                            Thread.sleep(1000);
                        }catch(Exception e){

                        }
                    }
                    if(timeLeft == 0){
                        runOnUiThread(
                                () -> {
                                    resetBtn.setVisibility(View.VISIBLE);
                                    responder.setVisibility(View.GONE);
                                    preguntaText.setText("Tiempo agotado");
                                    respuesta.setEnabled(false);
                                }
                        );

                    }
                }
        ).start();
    }


    private void resetGame() {
        timeLeft = 30;
        score = 0;
        resetBtn.setVisibility(View.GONE);
        responder.setVisibility(View.VISIBLE);
        respuesta.setEnabled(true);
        preguntaActual = new Pregunta();
        preguntaText.setText(preguntaActual.getPregunta());
       counterThread();
    }

    private void verificarRespuesta() {
        String respuestaS;
        int respuestaInt;

        if(respuesta.getText().equals(null)){
            respuestaInt = 0;
        }else{
            respuestaS = respuesta.getText().toString();
            respuestaInt = (int) Integer.parseInt(respuestaS);
        }
        respuesta.setText("");


        if (respuestaInt == preguntaActual.getRespuesta()) {
            Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
            score+=5;
            puntaje.setText("Puntaje: "+score);
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            if(score<=4){
                score = 0;
            }else {
                score -= 4;
            }
            puntaje.setText("Puntaje: "+score);
        }
        
        generarNuevaPregunta();
    }

    private void generarNuevaPregunta() {
        preguntaActual = new Pregunta();
        preguntaText.setText(preguntaActual.getPregunta());
    }


}