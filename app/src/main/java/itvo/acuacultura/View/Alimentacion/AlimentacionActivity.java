package itvo.acuacultura.View.Alimentacion;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import itvo.acuacultura.R;
import itvo.acuacultura.View.MenuActivity;

public class AlimentacionActivity extends AppCompatActivity {

    private Spinner spinner1, spinner2;
    ArrayList insumos= new ArrayList();
    String insumosProteinas[][]= new String[2][12];
    String SeleccionSpinner1="", SeleccionSpinner2="";
    private TextInputLayout tilz;
    private EditText z;
    int cont=0;
    String mensaje;
    boolean ter=true;
    boolean t=true;
    String res ="",re="";
    int numMezclas=0;
    float mezcla[][];
    String insumo [][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentacion);

        showToolbar("Alimentación", true);
        agregarInsumos();
        tilz = (TextInputLayout) findViewById(R.id.tilValorZ);
        z=(EditText)findViewById(R.id.txtZ) ;

        re = getIntent().getStringExtra("re");
        mensaje = getIntent().getStringExtra("num");

        spinner1 = (Spinner) findViewById(R.id.spinnerMezcla1);
        spinner2 = (Spinner) findViewById(R.id.SpinnerMescla2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AlimentacionActivity.this, android.R.layout.simple_list_item_1, insumos);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        //Adding setOnItemSelectedListener method on spinner.
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SeleccionSpinner1 = String.valueOf(spinner1.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SeleccionSpinner2=String.valueOf(spinner2.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        z.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                z.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private boolean esZValido(String zComp) {
        if (zComp.matches("")) {
            tilz.setError("Campo Vacio");
            return false;
        } else {
            tilz.setError(null);
        }

        return true;
    }

    public void add(){
        numMezclas=Integer.parseInt(mensaje);
        mezcla= new float [numMezclas][3];
        insumo= new String[numMezclas][2];
    }

    public void calcular(View v){
        if(t){
            add();
            t=false;
        }
        if (ter){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Importante");
            numMezclas=Integer.parseInt(mensaje);
            String z = tilz.getEditText().getText().toString();
            boolean a = esZValido(z);
            if (a){
                float z1=Float.parseFloat(z);
                if(cont<numMezclas-1){
                    builder.setMessage("Ingrese la Mezcla "+(cont+2));
                    builder.setPositiveButton("Aceptar",null);
                    builder.create();
                    builder.show();
                }
                if (cont<=numMezclas){
                    if(a){
                        try{
                            float x1=0;
                            float y2=0;
                            float rx1=0;
                            float ry1=0;

                            for(int i=0; i<insumos.size();i++){
                                if (SeleccionSpinner1==insumosProteinas[0][i]){
                                    x1= Float.parseFloat(insumosProteinas[1][i]);
                                    insumo[cont][0]=insumosProteinas[0][i];
                                }
                                if (SeleccionSpinner2==insumosProteinas[0][i]){
                                    y2= Float.parseFloat(insumosProteinas[1][i]);
                                    insumo[cont][1]=insumosProteinas[0][i];
                                }
                            }

                            rx1 = x1 - z1;
                            if (rx1 < 0) {
                                rx1 = rx1 * -1;
                            }

                            ry1 = y2 - z1;
                            if (ry1 < 0) {
                                ry1 = ry1 * -1;
                            }

                            float resSum=rx1+ry1;
                            float total1= (rx1/resSum)* 1000;
                            float total2= (ry1/resSum)* 1000;

                            mezcla[cont][0]=resSum;
                            mezcla[cont][1]=total1;
                            mezcla[cont][2]=total2;

                            /*if (numMezclas>1){

                                rx1 = mezcla[cont][0]-z1;
                                if (rx1<0){
                                    rx1=rx1*-1;
                                }
                                for(int i=0; i<cont-1;i++){
                                    y2+=mezcla[i][0];
                                }

                                ry1 = y2-z1;
                                if (ry1<0){
                                    ry1=ry1*-1;
                                }
                                resSum=rx1+ry1;

                                total1= (rx1/resSum) * 1000;
                                total2= (ry1/resSum) * 1000;

                                mezcla[cont][0]=resSum;
                                mezcla[cont][1]=total1;
                                mezcla[cont][2]=total2;

                            }*/

                            cont++;


                        }catch (Exception e){
                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                if (numMezclas==1){
                    res ="";
                    res = res +insumo[0][0]+": "+mezcla[0][1]+"\n";
                    res = res +insumo[0][1]+": "+mezcla[0][2]+"\n";
                    ter=false;
                    ArrayList array= new ArrayList();
                    array.add(mezcla[0][1]);
                    array.add(mezcla[0][2]);
                    Intent i = new Intent(this, GraficaAlimentacionActivity.class);
                    i.putStringArrayListExtra("insumos", array);
                    i.putExtra("res", res);
                    i.putExtra("re", re);
                    startActivity(i);

                }else{
                    if(cont==numMezclas){
                        float s=0;
                        res ="";
                        float suma=0;
                        for(int i=0; i<numMezclas; i++){
                            suma+=mezcla[i][1];
                            suma+=mezcla[i][2];
                        }

                        ArrayList m = new ArrayList();
                        for(int i=0; i<numMezclas; i++){
                            res = res +"Mezcla: "+(i+1)+"\n";
                            res = res +insumo[i][0]+": "+mezcla[i][1]+"\n";
                            res = res +insumo[i][1]+": "+mezcla[i][2]+"\n";
                            //res=res+insumo[i][0]+": "+((mezcla[i][1]/suma)*1000)+"\n";
                            //res=res+insumo[i][1]+": "+((mezcla[i][2]/suma)*1000)+"\n";
                            // m.add(((mezcla[i][1]/suma)*1000)+((mezcla[i][2]/suma)*1000));
                            m.add(mezcla[i][0]);
                        }

                        Intent i = new Intent(this, GraficaAlimentacionActivity.class);
                        i.putStringArrayListExtra("insumos", m);
                        i.putExtra("res", res);
                        i.putExtra("re", re);
                        startActivity(i);
                        ter=false;
                }
                }
            }
        }else{
            Toast.makeText(this, "Fin",Toast.LENGTH_SHORT).show();
           /* Intent intent = new Intent(this, graficaAlimentacion.class);
            intent.putStringArrayListExtra("miLista", );
            startActivity(intent);*/
        }

    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
    public void agregarInsumos() {
        insumos.add("Harina de Carne");
        insumos.add("Harina de Camaron");
        insumos.add("Harina de Pescado");
        insumos.add("Harina de Sangre");
        insumos.add("Harina de Hueso");
        insumos.add("Harina de Maiz");
        insumos.add("Harina de Trigo");
        insumos.add("Harina de Soya");
        insumos.add("Harina de Avena");
        insumos.add("Harina de Arroz");
        insumos.add("Harina de Centeno");
        insumos.add("Harina de Maicena");

        for(int i=0; i<insumos.size(); i++){
            insumosProteinas[0][i]=insumos.get(i).toString();
        }

        insumosProteinas[1][0]="55";
        insumosProteinas[1][1]="55";
        insumosProteinas[1][2]="65";
        insumosProteinas[1][3]="85";
        insumosProteinas[1][4]="55";
        insumosProteinas[1][5]="70";
        insumosProteinas[1][6]="10";
        insumosProteinas[1][7]="37";
        insumosProteinas[1][8]="24";
        insumosProteinas[1][9]="60";
        insumosProteinas[1][10]="11";
        insumosProteinas[1][11]="30";

    }
    /*public void dialogoPersonalizado(){
        numMezclas dialogoPersonalizado = new numMezclas();
        dialogoPersonalizado.show(getSupportFragmentManager(), "personalizado");

        android.app.Fragment frag = getFragmentManager().findFragmentByTag("personalizado");

        if (frag != null) {
            getFragmentManager().beginTransaction().remove(frag).commit();
        }
    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, MenuActivity.class);
                intent.putExtra("re", re);
                startActivity(intent);
                //Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();onBackPressed();
                return true;
        }
        // Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==4){
            Intent intent = new Intent(this, MenuActivity.class);
            intent.putExtra("re", re);
            startActivity(intent);
        }
        //onBackPressed();
        return super.onKeyDown(keyCode, event);
    }


}


