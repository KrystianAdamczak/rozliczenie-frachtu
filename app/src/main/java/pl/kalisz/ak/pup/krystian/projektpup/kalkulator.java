package pl.kalisz.ak.pup.krystian.projektpup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class kalkulator extends AppCompatActivity{

    EditText planNetto, wykonanieNetto, planKmSpedycyjne, wykonanieKmSpedycyjne,
    planKmSpedycyjneDzien, wykonanieKmSpedycyjneDzien, stawkaKmSpedycyjne,
    iloscDni, iloscDniRoboczych, do045, korektaPodstawy, premia1, premia2,
    premia3, wynagrodzenie;

    Button confirm, reset;


    double dPlanNetto, dWykNetto, dPlanKmSped, dWykKmSped, dStawkaKmSped, dIloscDniRoboczych,
    podstawaPrzekroczenieKmSped, premiaPrzekroczenieKmSped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);

        planNetto = findViewById(R.id.planNetto_editText);
        wykonanieNetto = findViewById(R.id.wykonanieNetto_editText);
        planKmSpedycyjne = findViewById(R.id.plankmSpedycyjne_editText);
        wykonanieKmSpedycyjne = findViewById(R.id.wykonaniekmSpedycyjne_editText);
        planKmSpedycyjneDzien = findViewById(R.id.plankmSpedycyjneDzien_editText);
        wykonanieKmSpedycyjneDzien = findViewById(R.id.wykonaniekmSpedycyjneDzien_editText);
        stawkaKmSpedycyjne = findViewById(R.id.stawkaKmSpedycyjny_editText);
        iloscDni = findViewById(R.id.iloscDni_editText);
        iloscDniRoboczych = findViewById(R.id.iloscDniRoboczych_editText);
        do045 = findViewById(R.id.do045_editText);
        korektaPodstawy = findViewById(R.id.korektaPodstawy_editText);
        premia1 = findViewById(R.id.premia1_editText);
        premia2 = findViewById(R.id.premia2_editText);
        premia3 = findViewById(R.id.premia3_editText);
        wynagrodzenie = findViewById(R.id.wynagrodzenie_editText);

        //reset = findViewById(R.id.reset_button);

        planNetto.setText("320.00");
        planKmSpedycyjne.setText("651.00");

        /*planNetto.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus && wykonanieNetto.getText().toString() != null)
                    planNetto.setText(wykonanieNetto.getText());
            }
        });*/

        iloscDniRoboczych.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                dPlanNetto = Double.parseDouble(iloscDniRoboczych.getText().toString())*320;
                dPlanKmSped = Double.parseDouble(iloscDniRoboczych.getText().toString())*651;


                planNetto.setText(Double.toString(dPlanNetto));
                planKmSpedycyjne.setText(Double.toString(dPlanKmSped));

            }
        });

        wykonanieKmSpedycyjne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                dWykKmSped = Double.parseDouble(wykonanieKmSpedycyjne.getText().toString());

                if(dWykKmSped > dPlanKmSped)
                    planKmSpedycyjneDzien.setText("TAK");
                else
                    planKmSpedycyjneDzien.setText("NIE");

                //wykonanieKmSpedycyjneDzien.setText(Double.toString(dWykKmSped / Double.parseDouble(iloscDniRoboczych.getText().toString())));
                wykonanieKmSpedycyjneDzien.setText(Double.toString(dWykKmSped));
                dStawkaKmSped = Double.parseDouble(wykonanieNetto.getText().toString()) / dWykKmSped;
                stawkaKmSpedycyjne.setText(String.format("%.2f", dStawkaKmSped));

                dIloscDniRoboczych = Double.parseDouble(iloscDniRoboczych.getText().toString());

                if(!stawkaKmSpedycyjne.getText().toString().isEmpty()) {
                    if (Double.parseDouble(stawkaKmSpedycyjne.getText().toString()) > 0.49)
                        do045.setText(String.format("%.2f",(Double.parseDouble(iloscDniRoboczych.getText().toString()) / 15) * 1000));
                    else
                        do045.setText(String.format("%.2f",((dStawkaKmSped-0.49)*1000)+((dIloscDniRoboczych/15)*1000)));
                }

                if(dStawkaKmSped > 0.44)
                    korektaPodstawy.setText(do045.getText());
                else
                    korektaPodstawy.setText(0);

                dWykNetto = Double.parseDouble(wykonanieNetto.getText().toString());

                if(dPlanNetto < dWykNetto)
                    premia2.setText(String.format("%.2f", (((dWykNetto-dPlanNetto)*Double.parseDouble(premia1.getText().toString())*0.4))));
                else
                    premia2.setText("0");

                if(dStawkaKmSped>=0.405)
                    premia3.setText(premia2.getText());
                else
                    premia3.setText("0");

                double podstawaKmSped = obliczPodstaweKmSped(dPlanKmSped, Double.parseDouble(wykonanieKmSpedycyjne.getText().toString()),
                        Double.parseDouble(korektaPodstawy.getText().toString()));
                double premiaKmSped = obliczPremieKmSped(dPlanKmSped,  Double.parseDouble(wykonanieKmSpedycyjne.getText().toString()),
                        Double.parseDouble(premia3.getText().toString()));
                double niewykonanieNormy = niewykonanieNormy(dWykNetto, dPlanNetto, Double.parseDouble(korektaPodstawy.getText().toString()));

                double dWynagrodzenie = obliczWynagrodzenie(Double.parseDouble(korektaPodstawy.getText().toString()), niewykonanieNormy, podstawaKmSped,
                        Double.parseDouble(premia3.getText().toString()), premiaKmSped);

                wynagrodzenie.setText(String.format("%.2f", dWynagrodzenie));
            }

        });


    }

    public double obliczPodstaweKmSped(double f12, double h12, double h22) {
        if(f12<h12)
            return (((((h12/f12)*-100)+100)*h22)/100);
        else
            return 0;
    }

    public double obliczPremieKmSped(double f12, double h12, double h28) {
        if(f12<h12)
            return ((((((h12/f12)*-100)+100)*h28)/100));
        else
            return 0;
    }

    public double niewykonanieNormy(double h8, double f8, double h22) {
        if(h8<f8)
            return (((h8/f8)-1)*h22);
        else
            return 0;
    }

    public double obliczWynagrodzenie(double h22, double h24, double h26, double h28, double h30) {
        return h22+h24+h26+h28+h30;
    }

    public void reset(View view) {
        planNetto.setText("");
        wykonanieNetto.setText("");
        planKmSpedycyjne.setText("");
        wykonanieKmSpedycyjne.setText("");
        planKmSpedycyjneDzien.setText("");
        wykonanieKmSpedycyjneDzien.setText("");
        stawkaKmSpedycyjne.setText("");
        iloscDni.setText("");
        iloscDniRoboczych.setText("");
        do045.setText("");
        korektaPodstawy.setText("");
        premia1.setText("");
        premia2.setText("");
        premia3.setText("");
        wynagrodzenie.setText("");


    }


    /*TODO
    1. PO WPISANIU I USUNIECIU WARTOSCI WYRZUCA APLIKACJE -> AFTERTEXTCHANGED;
    2. PRZYCISK RESET;
    3. KOSMETYCZNE ZMIANY
     */
}