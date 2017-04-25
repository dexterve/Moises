package itvo.acuacultura.View.Crecimiento.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import itvo.acuacultura.Database.AdminBD;
import itvo.acuacultura.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GraficaCrecimientoFragment extends Fragment {


    public GraficaCrecimientoFragment() {
        // Required empty public constructor
    }

    String re="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grafica_crecimiento, container, false);
        //re = getIntent().getStringExtra("re");
        showToolbar("Grafica Crecimiento", true, view);
        Graficar(view);
        return view;
    }
    public void showToolbar(String title, boolean upButton, View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);


    }
    public void Graficar(View view){
        LineChart lineChart = (LineChart) view.findViewById(R.id.chartPeso);
        LineChart lineChartLongitud = (LineChart) view.findViewById(R.id.chartLongitud);
        ArrayList peso;
        ArrayList longitud;
        AdminBD bd = new AdminBD(getContext(), "AcuaCultura", null, 1);
        ArrayList<Entry> entries2 = new ArrayList();
        ArrayList<Entry> entries = new ArrayList();
        LineDataSet dataSet;
        LineDataSet dataSetLon;
        ArrayList<String> labels = new ArrayList<>();
        LineData data;
        LineData data2;

        switch (re) {
            case "Tilapia":
                peso = bd.GraficaPesoTilapia();
                longitud = bd.GraficaLongitudTilapia();


                for (int i = 0; i < peso.size(); i++) {
                    entries.add(new Entry(Float.parseFloat(peso.get(i).toString()), i));
                    entries2.add(new Entry(Float.parseFloat(longitud.get(i).toString()), i));
                }

                dataSet = new LineDataSet(entries, "Crecimiento en Peso(g)");
                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                dataSetLon = new LineDataSet(entries2, "Crecimiento en Longitud(Cm)");
                dataSetLon.setColors(ColorTemplate.COLORFUL_COLORS);

                for (int i = 1; i < peso.size() + 1; i++) {
                    labels.add("Rev " + i);
                }

                data = new LineData(labels, dataSet);
                lineChart.setData(data);
                lineChart.setDescription("Grafica Peso");
                lineChart.animateY(2500);

                data2 = new LineData(labels, dataSetLon);
                lineChartLongitud.setData(data2);
                lineChartLongitud.setDescription("Grafica Longitud");
                lineChartLongitud.animateY(2500);
                break;

            case "Trucha":
                peso = bd.GraficaPesoTrucha();
                longitud = bd.GraficaLongitudTrucha();

                for (int i = 0; i < peso.size(); i++) {
                    entries.add(new Entry(Float.parseFloat(peso.get(i).toString()), i));
                    entries2.add(new Entry(Float.parseFloat(longitud.get(i).toString()), i));
                }

                dataSet = new LineDataSet(entries, "Crecimiento en Peso(g)");
                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                dataSetLon = new LineDataSet(entries2, "Crecimiento en Longitud(Cm)");
                dataSetLon.setColors(ColorTemplate.COLORFUL_COLORS);


                for (int i = 1; i < peso.size() + 1; i++) {
                    labels.add("Rev " + i);
                }

                data = new LineData(labels, dataSet);
                lineChart.setData(data);
                lineChart.setDescription("Grafica Peso");
                lineChart.animateY(2000);

                data2 = new LineData(labels, dataSetLon);
                lineChartLongitud.setData(data2);
                lineChartLongitud.setDescription("Grafica Longitud");
                lineChartLongitud.animateY(2000);
                break;

        }
    }

    public void Re(String re) {
        this.re=re;
    }
}
