package itvo.acuacultura.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import itvo.acuacultura.Model.ComRegistrosCrecimiento;
import itvo.acuacultura.R;


/**
 * Created by HP on 10/04/2017.
 */

public class RecyclerViewAdapterCrecimiento extends RecyclerView.Adapter<RecyclerViewAdapterCrecimiento.PictureViewHolder> {

    private ArrayList <ComRegistrosCrecimiento>registros;
    private int resource;
    private Activity activity;

    public RecyclerViewAdapterCrecimiento(ArrayList<ComRegistrosCrecimiento> reg, int resource, Activity activity) {
        this.registros = reg;
        this.resource = resource;
        this.activity = activity;
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        ComRegistrosCrecimiento reg = registros.get(position);
        holder.fecha.setText(reg.getFecha());
        holder.peso.setText(reg.getPesoProm());
        holder.lon.setText(reg.getLongProm());
        holder.pesoA.setText(reg.getPesoAu());
        holder.lonA.setText(reg.getLongAu());

        if(reg.getPorPeso()=="Infinity"){
            holder.PesoPor.setText("Porcentaje Peso: 0%");
            holder.lonPor.setText("Porcentaje Longitud: 0%");
        }else{
            holder.PesoPor.setText("Porcentaje Peso: "+reg.getPorPeso()+"%");
            holder.lonPor.setText("Porcentaje Longitud: "+reg.getPorLong()+"%");
        }





       /* holder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PictureDatailActivity.class);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation
                            (activity, v, activity.getString(R.string.transitionName)).toBundle());
                }else {
                    activity.startActivity(intent);
                }


            }
        });*/
    }

    @Override
    public int getItemCount() {
        return registros.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder{

        //private ImageView pictureCard;
        private TextView fecha,peso, lon, pesoA, lonA, PesoPor, lonPor;

        public PictureViewHolder(View itemView) {
            super(itemView);

            fecha       =(TextView) itemView.findViewById(R.id.lblFechaCre);
            peso        =(TextView) itemView.findViewById(R.id.lblPesoProm);
            lon         =(TextView) itemView.findViewById(R.id.lblLongitudProm);
            pesoA       =(TextView) itemView.findViewById(R.id.lblPesoAumentado);
            lonA        =(TextView) itemView.findViewById(R.id.lbllongitudAumen);
            PesoPor    =(TextView) itemView.findViewById(R.id.lblPorcentajePeso);
            lonPor     =(TextView) itemView.findViewById(R.id.lblPorcentajeLong);

        }
    }
}
