package pl.kalisz.ak.pup.krystian.projektpup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonRozlicz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRozlicz = findViewById(R.id.rozlicz_button);
    }

    public void navigateToKalkulator(View v) {
        Intent intent = new Intent(this, kalkulator.class);
        startActivity(intent);
    }

}