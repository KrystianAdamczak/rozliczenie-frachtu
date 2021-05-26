package pl.kalisz.ak.pup.krystian.projektpup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button buttonRozlicz;
    String wersjaApki = "Alfa 1.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonRozlicz = findViewById(R.id.rozlicz_button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.version:
                Toast.makeText(this, "Wersja aplikacji to: " + wersjaApki, Toast.LENGTH_LONG).show();
                return true;
            case R.id.about:
                Intent intent = new Intent(this, about.class);
                startActivity(intent);
                return true;
            case R.id.license:
                Intent intent1 = new Intent(this, license.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void navigateToKalkulator(View v) {
        Intent intent = new Intent(this, kalkulator.class);
        startActivity(intent);
    }

}