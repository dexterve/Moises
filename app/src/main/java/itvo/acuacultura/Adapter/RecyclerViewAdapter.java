package itvo.acuacultura.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import itvo.acuacultura.Model.ComunicadorRegistrosCA;
import itvo.acuacultura.R;


/**
 * Created by HP on 10/04/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PictureViewHolder> {

    private ArrayList <ComunicadorRegistrosCA>registros;
    private int resource;
    private Activity activity;

    public RecyclerViewAdapter(ArrayList<ComunicadorRegistrosCA> reg, int resource, Activity activity) {
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
        ComunicadorRegistrosCA reg = registros.get(position);
        holder.fecha.setText(reg.getFecha());
        holder.temp.setText(reg.getTemperatura());
        holder.oxi.setText(reg.getOxigeno());
        holder.ph.setText(reg.getPH());
        holder.turb.setText(reg.getTurbiez());

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
        private TextView fecha, temp, oxi, ph, turb;

        public PictureViewHolder(View itemView) {
            super(itemView);

            fecha     =(TextView) itemView.findViewById(R.id.lblFecha);
            temp    =(TextView)itemView.findViewById(R.id.lblTemperatura);
            oxi        =(TextView)itemView.findViewById(R.id.txtTemp);
            ph  =(TextView)itemView.findViewById(R.id.lblPH);
            turb=(TextView)itemView.findViewById(R.id.lblturbidez);
        }
    }
}
