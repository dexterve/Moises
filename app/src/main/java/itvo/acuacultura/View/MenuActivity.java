package itvo.acuacultura.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

import itvo.acuacultura.MainActivity;
import itvo.acuacultura.R;
import itvo.acuacultura.View.Alimentacion.Fragments.DialogoListaAlimentacion;
import itvo.acuacultura.View.CalidadDelAgua.CalidadDelAguaActivity;
import itvo.acuacultura.View.Crecimiento.Fragments.DialogoListaCrecimiento;
import itvo.acuacultura.View.Enfermedades.EnfermedadesActivity;

public class MenuActivity extends AppCompatActivity {

    String re = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        re = getIntent().getStringExtra("re");

        showToolbar("Menu " + re, true);
    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }


    public void calidadAgua(View v) {
        Intent i = new Intent(getApplicationContext(), CalidadDelAguaActivity.class);
        i.putExtra("re", re);
        startActivity(i);
    }

    public void Crecimiento(View v) {
       /* Intent i = new Intent(getApplicationContext(), CrecimientoActivity.class);
        i.putExtra("re", re);
        startActivity(i);*/

        DialogoListaCrecimiento lista = new DialogoListaCrecimiento();
        lista.Re(re);
        lista.show(getSupportFragmentManager(), "Lista");


    }

    public void Alimentacion(View v) {
        DialogoListaAlimentacion lista = new DialogoListaAlimentacion();
        lista.Re(re);
        lista.show(getSupportFragmentManager(), "Lista");
    }
    public void Enfermedades(View v) {
        Intent i = new Intent(getApplicationContext(), EnfermedadesActivity.class);
        i.putExtra("re", re);
        startActivity(i);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Log.i("Ejemplo", "Se dio clic en la tecla: " + keyCode + " y se ejecutara onBackPressed()");
        if (keyCode==4){
            Intent intent = new Intent(this, MainActivity.class);
            //Toast.makeText(this,"Se dio clic en la tecla: " + keyCode, Toast.LENGTH_LONG).show();
            startActivity(intent);
            //onBackPressed();

        }
        return super.onKeyDown(keyCode, event);
    }

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                //onBackPressed();
                //Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
                return true;
        }
       // Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);

    }*/
}
