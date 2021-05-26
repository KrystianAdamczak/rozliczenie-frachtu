package pl.kalisz.ak.pup.krystian.projektpup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.os.Bundle;
import android.text.Spanned;
import android.widget.TextView;

public class license extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);

        TextView textView = findViewById(R.id.textLicencji);
        String htmlText = "/*<br>" +
                " * ----------------------------------------------------------------------------<br>" +
                " * \"THE BEER-WARE LICENSE\" (Revision 42):<br>" +
                " * <b>Krystian Adamczak</b> wrote this application. As long as you retain this notice you<br>" +
                " * can do whatever you want with this stuff. If we meet some day, and you think<br>" +
                " * this stuff is worth it, you can buy me a beer.<br>" +
                " * ----------------------------------------------------------------------------<br>" +
                " */";
        Spanned spanned = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_COMPACT);
        textView.setText(spanned);

        TextView textView1 = findViewById(R.id.textLicencji2);
        String htmlText2 = "<b>Beerware</b> – licencja oprogramowania pozwalająca użytkownikowi" +
                " końcowemu na dowolne korzystanie z oprogramowania pod warunkiem, że w przypadku spotkania autora użytkownik postawi mu piwo";
        Spanned spanned2 = HtmlCompat.fromHtml(htmlText2, HtmlCompat.FROM_HTML_MODE_COMPACT);
        textView1.setText(spanned2);
    }
}