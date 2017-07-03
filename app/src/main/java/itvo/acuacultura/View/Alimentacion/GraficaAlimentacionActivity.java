package itvo.acuacultura.View.Alimentacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import itvo.acuacultura.MainActivity;
import itvo.acuacultura.R;

public class GraficaAlimentacionActivity extends AppCompatActivity {

    String re="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica_alimentacion);

        showToolbar("Resultados Alimentación", true);

        PieChart pieChart = (PieChart) findViewById(R.id.chartAlimentacion);
        TextView resultado=(TextView)findViewById(R.id.lblResultados);

        ArrayList lista = getIntent().getStringArrayListExtra("insumos");
        String res = getIntent().getStringExtra("res");
        re = getIntent().getStringExtra("re");
        resultado.setText(res);

        ArrayList <Entry> entries = new ArrayList();
        float m=0;
        for(int i =0; i<lista.size();i++){
            m +=Float.parseFloat(lista.get(i).toString());
        }

        for(int i =0; i<lista.size();i++){
            float mm =(Float.parseFloat(lista.get(i).toString())/m)*1000;
            entries.add(new Entry(mm,i));
        }

        PieDataSet dataSet =new PieDataSet(entries, "");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<String>labels=new ArrayList<>();

        for(int i=0; i<lista.size(); i++){
            labels.add("Mez. "+(i+1));
        }

        PieData data = new PieData(labels, dataSet);
        pieChart.setData(data);
        pieChart.setDescription("Grafica Alimentacion");
        pieChart.animateY(3000);
    }
    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                /*DialogoListaAlimentacion lista = new DialogoListaAlimentacion();
                lista.Re(re);
                lista.show(getSupportFragmentManager(), "Lista");
                //Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();onBackPressed();*/
                return true;
        }
        // Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==4){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        /*DialogoListaAlimentacion lista = new DialogoListaAlimentacion();
        lista.Re(re);
        lista.show(getSupportFragmentManager(), "Lista");*/
        //onBackPressed();
        return super.onKeyDown(keyCode, event);
    }
}
