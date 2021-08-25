package co.domi.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView preguntaText;
    private TextView timeCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preguntaText = findViewById(R.id.preguntaTextV);
        timeCount = findViewById(R.id.timer);

    }
}