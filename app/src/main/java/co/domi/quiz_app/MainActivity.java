package co.domi.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
    private Pregunta preguntaActual;
    private int timeLeft;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preguntaText = findViewById(R.id.preguntaTextV);
        timeCount = findViewById(R.id.timer);
        respuesta = findViewById(R.id.respuestaTextfield);
        puntaje = findViewById(R.id.puntajeText);
        responder = findViewById(R.id.responderBtn);

        score = 0;
        timeLeft = 30;
        preguntaActual = new Pregunta();
        preguntaText.setText(preguntaActual.getPregunta());

        timeCount.setText(""+timeLeft);

        new Thread(
                () -> {
                    while(timeLeft > 0){
                        try{
                            timeLeft--;
                            runOnUiThread(
                                    () -> {
                                        timeCount.setText(""+timeLeft);
                                    }
                            );
                            Thread.sleep(1000);
                        }catch(Exception e){

                        }
                    }
                }
        ).start();

        puntaje.setText("Puntaje: "+ score);

        responder.setOnClickListener(
                (view) -> {
                    verificarRespuesta();
                }
        );
    }

    private void verificarRespuesta() {
        String respuestaS = respuesta.getText().toString();
        int respuestaInt = (int) Integer.parseInt(respuestaS);

        if (respuestaInt == preguntaActual.getRespuesta()) {
            Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
            score+=10;
            puntaje.setText("Puntaje: "+score);
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        
        generarNuevaPregunta();
    }

    private void generarNuevaPregunta() {
        preguntaActual = new Pregunta();
        preguntaText.setText(preguntaActual.getPregunta());
    }


}