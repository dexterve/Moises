package itvo.acuacultura.View.CalidadDelAgua.Fragments;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import itvo.acuacultura.Database.AdminBD;
import itvo.acuacultura.Model.CalculoCalidadAgua;
import itvo.acuacultura.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeCAFragment extends Fragment {

    TextInputLayout TITemperatura;
    TextInputLayout TIPH;
    TextInputLayout TIOxigeno;
    private TextInputLayout TITurbidez;
    EditText temp, ph, oxigen, turb;

    String re="";
    Button revisar, nuevo;
    TextView temperatura, ph2, oxigeno, turbidez;
    CalculoCalidadAgua objCAgua;
    MediaPlayer alarma, bien;

    public HomeCAFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_ca, container, false);
        showToolbar("Calidad del Agua", true, view);
        vistas(view);
        objCAgua = new CalculoCalidadAgua();
        temp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ph.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ph.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        oxigen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                oxigen.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        turb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                turb.setError(null);;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        revisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDatos();
            }
        });
        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nuevo();
            }
        });

        return view;

    }
    public void Re(String re){
        this.re=re;
    }

    public void vistas(View view) {
        TITemperatura = (TextInputLayout) view.findViewById(R.id.tilTemp);
        TIOxigeno = (TextInputLayout) view.findViewById(R.id.TilOxi);
        TIPH = (TextInputLayout) view.findViewById(R.id.TilPH);
        TITurbidez = (TextInputLayout) view.findViewById(R.id.TilTurb);

        temp = (EditText) view.findViewById(R.id.txtTemp);
        ph = (EditText) view.findViewById(R.id.txtPH);
        oxigen = (EditText) view.findViewById(R.id.txtOxigeno);
        turb = (EditText) view.findViewById(R.id.txtTurbidez);

        temperatura = (TextView) view.findViewById(R.id.txtReTemperatura);
        ph2 = (TextView) view.findViewById(R.id.txtRePH);
        oxigeno = (TextView) view.findViewById(R.id.txtReOxigeno);
        turbidez = (TextView) view.findViewById(R.id.txtReTurbidez);

        revisar = (Button) view.findViewById(R.id.btnRevisar);
        nuevo = (Button) view.findViewById(R.id.btnNuevoCalculo);

        alarma = MediaPlayer.create(getActivity(), R.raw.alarma);
        bien = MediaPlayer.create(getActivity(), R.raw.good);
    }

    public void revisarCalidad(String t, String o, String p, String tu){
        switch (re){
            case "Tilapia":
                Tilapia(t, o, p, tu);
                break;
            case "Trucha":
                Trucha(t, o, p, tu);
                break;
        }
    }

    public void Tilapia(String t, String o, String p, String tu){

        this.objCAgua.setTemperatura(Float.parseFloat(temp.getText().toString()));
        objCAgua.calcularTemperatura();
        temperatura.setText(objCAgua.getRTemperatura());

        this.objCAgua.setPh(Float.parseFloat(ph.getText().toString()));
        objCAgua.calcularPH();
        ph2.setText(objCAgua.getrPH());

        this.objCAgua.setOxigeno(Float.parseFloat(oxigen.getText().toString()));
        objCAgua.calcularOxigeno();
        oxigeno.setText(objCAgua.getROxigeno());

        this.objCAgua.setTurbidez(Float.parseFloat(turb.getText().toString()));
        objCAgua.calcularTurbidez();
        turbidez.setText(objCAgua.getrTurbidez());

        if(objCAgua.issTur()|| objCAgua.issTemp()|| objCAgua.issPH()|| objCAgua.issOxi()){
            alarma.start();
        }else{
            bien.start();
        }

        AdminBD db = new AdminBD(getContext(), "AcuaCultura", null, 1);
        db.AltaRegTilapia(t, p, o, tu);
        Toast.makeText(getActivity(),"Guardando...",Toast.LENGTH_SHORT).show();


        }


    public void Trucha(String t, String o, String p, String tu){

        this.objCAgua.setTemperatura(Float.parseFloat(temp.getText().toString()));
        objCAgua.calcularTemperaturaTrucha();
        temperatura.setText(objCAgua.getRTemperatura());

        this.objCAgua.setPh(Float.parseFloat(ph.getText().toString()));
        objCAgua.calcularPHTrucha();
        ph2.setText(objCAgua.getrPH());

        this.objCAgua.setOxigeno(Float.parseFloat(oxigen.getText().toString()));
        objCAgua.calcularOxigenoTrucha();
        oxigeno.setText(objCAgua.getROxigeno());

        this.objCAgua.setTurbidez(Float.parseFloat(turb.getText().toString()));
        objCAgua.calcularTurbidezTrucha();
        turbidez.setText(objCAgua.getrTurbidez());

        if(objCAgua.issTur()|| objCAgua.issTemp()|| objCAgua.issPH()|| objCAgua.issOxi()){
            alarma.start();
        }else{
            bien.start();
        }

        AdminBD db = new AdminBD(getActivity(), "AcuaCultura", null, 1);
        db.AltaRegTrucha(t, p, o, tu);

    }

    public void Nuevo() {
        temp.setText("");
        ph.setText("");
        oxigen.setText("");
        turb.setText("");
        temperatura.setText("");
        ph2.setText("");
        oxigeno.setText("");
        turbidez.setText("");
        temp.requestFocus();
        alarma = MediaPlayer.create(getActivity(), R.raw.alarma);
        bien = MediaPlayer.create(getActivity(), R.raw.good);
    }

    private void validarDatos() {
        String t = TITemperatura.getEditText().getText().toString();
        String o = TIOxigeno.getEditText().getText().toString();
        String p = TIPH.getEditText().getText().toString();
        String tu = TITurbidez.getEditText().getText().toString();

        boolean a = esTemp(t);
        boolean b = esOxigeno(o);
        boolean c = esPH(p);
        boolean d = esTurb(tu);

        if (a && b && c && d) {
            revisarCalidad(t, o, p, tu);

        }

    }

    private boolean esTemp(String temp) {
        if (temp.matches("")) {
            TITemperatura.setError("Campo Vacio o Datos Invalidos");
            return false;
        } else {
            TITemperatura.setError(null);
        }

        return true;
    }

    private boolean esPH(String ph) {
        if (ph.matches("")) {
            TIPH.setError("Campo Vacio o Datos Invalidos");
            return false;
        } else {
            TIPH.setError(null);
        }

        return true;
    }

    private boolean esOxigeno(String oxi) {
        if (oxi.matches("")) {
            TIOxigeno.setError("Campo Vacio o Datos Invalidos");
            return false;
        } else {
            TIOxigeno.setError(null);
        }

        return true;
    }

    private boolean esTurb(String tur) {
        if (tur.matches("")) {
            TITurbidez.setError("Campo Vacio o Datos Invalidos");
            return false;
        } else {
            TITurbidez.setError(null);
        }

        return true;
    }

    public void showToolbar(String title, boolean upButton, View view){
        Toolbar toolbar =  (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }


}
