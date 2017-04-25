package itvo.acuacultura.View.CalidadDelAgua.Fragments;


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

import itvo.acuacultura.Adapter.RecyclerViewAdapter;
import itvo.acuacultura.Database.AdminBD;
import itvo.acuacultura.Model.ComunicadorRegistrosCA;
import itvo.acuacultura.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrosCAFragment extends Fragment {


    String re;
    public RegistrosCAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_registros_ca, container, false);

        showToolbar("Registros", true, view);
        RecyclerView picturerecycler=(RecyclerView)view.findViewById(R.id.pictureRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturerecycler.setLayoutManager(linearLayoutManager);

        RecyclerViewAdapter pictureAdapter = new RecyclerViewAdapter(builPicture(),R.layout.registros_calidad_agua,getActivity());

        picturerecycler.setAdapter(pictureAdapter);
        return view;
    }
    public ArrayList<ComunicadorRegistrosCA> builPicture(){
        ArrayList <ComunicadorRegistrosCA> registros = new ArrayList<>();
        AdminBD bd = new AdminBD(getActivity(), "AcuaCultura", null, 1);
        ArrayList reg= new ArrayList();
        switch (re){
            case "Tilapia":
                reg = bd.ListarRegTilapia();
                break;
            case "Trucha":
                reg = bd.ListarRegTrucha();
                break;
        }

        ArrayList elemento;
        for(int position=0; position<reg.size();position++){
            elemento = (ArrayList) reg.get(position);

            registros.add(new ComunicadorRegistrosCA(elemento.get(0).toString(), "Fecha: "+elemento.get(1).toString(),
                    "Temperatura: "+elemento.get(2).toString(),"Oxigeno: "+elemento.get(3).toString(),
                    "PH: "+elemento.get(4).toString(), "Turbidez: "+elemento.get(5).toString()));

        }

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
