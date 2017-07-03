package itvo.acuacultura.View.CalidadDelAgua.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import itvo.acuacultura.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpCAFragment extends Fragment {


    public HelpCAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ayuda_ca, container, false);
        //showToolbar("Ayuda", true, view);

        return view;
    }
    public void showToolbar(String title, boolean upButton, View view){
        Toolbar toolbar =  (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}
