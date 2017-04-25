package itvo.acuacultura.View.Crecimiento.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import itvo.acuacultura.Adapter.RecyclerViewAdapterCrecimiento;
import itvo.acuacultura.Database.AdminBD;
import itvo.acuacultura.Model.ComRegistrosCrecimiento;
import itvo.acuacultura.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrosCrecimientoFragment extends Fragment {

    String re;


    public RegistrosCrecimientoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registros_crecimiento, container, false);

        showToolbar("Registros", true, view);
        RecyclerView picturerecycler=(RecyclerView)view.findViewById(R.id.RegistroCrecimientoRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturerecycler.setLayoutManager(linearLayoutManager);

        RecyclerViewAdapterCrecimiento pictureAdapter = new RecyclerViewAdapterCrecimiento(builArray(),R.layout.registros_crecimiento,getActivity());

        picturerecycler.setAdapter(pictureAdapter);
        return view;
    }
    public ArrayList<ComRegistrosCrecimiento> builArray(){
        ArrayList <ComRegistrosCrecimiento> registros = new ArrayList<>();
        AdminBD bd = new AdminBD(getActivity(), "AcuaCultura", null, 1);
        ArrayList reg = new ArrayList();
        ArrayList elemento;
        switch (re){
            case "Tilapia":
                reg = bd.ListarCrecimientoTilapia();
                break;
            case "Trucha":
                reg =  bd.ListarCrecimientoTrucha();
                break;
        }
        for(int position=0; position<reg.size();position++){
            elemento = (ArrayList) reg.get(position);

            registros.add(new ComRegistrosCrecimiento(elemento.get(0).toString(), "Fecha: "+elemento.get(1).toString(), "Peso Promedio: "+
                    elemento.get(2).toString(),"Longitud Promedio: "+elemento.get(3).toString(),"Peso Aumentado: "+elemento.get(4).toString(),
                    "Longitud Aumentada: "+elemento.get(5).toString(),elemento.get(6).toString(),elemento.get(7).toString()));

        }
        //registros.add(new ComunicadorRegistrosCA(reg));

        //registros.add(new ComunicadorRegistrosCA(reg.get(0).toString(), reg.get(1).toString(), reg.get(2).toString(),"hola","fg","ew"));
        //Toast.makeText(getActivity(), registros.get(0)+""+registros.get(1),Toast.LENGTH_SHORT).show();

        return registros;
    }

    public void showToolbar(String title, boolean upButton, View view){
        Toolbar toolbar =  (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public void Re(String re) {
        this.re=re;
    }
}
