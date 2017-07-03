package itvo.acuacultura.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import itvo.acuacultura.Database.AdminBD;
import itvo.acuacultura.Model.ComunicadorRegistrosCA;
import itvo.acuacultura.R;
import itvo.acuacultura.View.CalidadDelAgua.CalidadDelAguaActivity;


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
    public void onBindViewHolder(final PictureViewHolder holder, final int position) {
        ComunicadorRegistrosCA reg = registros.get(position);
        holder.fecha.setText(reg.getFecha());
        holder.temp.setText(reg.getTemperatura());
        holder.oxi.setText(reg.getOxigeno());
        holder.ph.setText(reg.getPH());
        holder.turb.setText(reg.getTurbiez());

        final String pez = reg.getPez();

       holder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(activity, pez + position, Toast.LENGTH_SHORT).show();
                final AdminBD bd = new AdminBD(activity, "AcuaCultura", null, 1);

               switch (pez){
                    case "Tilapia":
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                        alertDialogBuilder.setTitle("Advertencia");

                        alertDialogBuilder
                                .setMessage("¿Seguro que desea Eliminar el registro?")
                                .setCancelable(false)
                                .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        ArrayList p = bd.ListarRegTilapia();
                                        ArrayList elemento = (ArrayList) p.get(position);
                                        id = Integer.parseInt(elemento.get(0).toString());
                                        bd.EliminarCATilapia(id);
                                        Intent intent = new Intent(activity, CalidadDelAguaActivity.class);
                                        intent.putExtra("re", pez);
                                        activity.startActivity(intent);
                                    }
                                })
                                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        break;

                   case "Trucha":

                       AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(activity);
                       alertDialogBuilder2.setTitle("Advertencia");

                       alertDialogBuilder2
                               .setMessage("¿Seguro que desea Eliminar el registro?")
                               .setCancelable(false)
                               .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                                   public void onClick(DialogInterface dialog,int id) {
                                       // if this button is clicked, close
                                       // current activity
                                       //activity.finish();
                                       Intent intent = new Intent(activity, CalidadDelAguaActivity.class);
                                       ArrayList p = bd.ListarRegTrucha();
                                       ArrayList elemento = (ArrayList) p.get(position);
                                       id = Integer.parseInt(elemento.get(0).toString());
                                       bd.EliminarCATrucha(id);
                                       intent.putExtra("re", pez);
                                       activity.startActivity(intent);
                                   }
                               })
                               .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                   public void onClick(DialogInterface dialog,int id) {
                                       dialog.cancel();
                                   }
                               });
                       AlertDialog alertDialog2 = alertDialogBuilder2.create();
                       alertDialog2.show();
                       break;

                }



            }
        });

    }

    @Override
    public int getItemCount() {
        return registros.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder{

        private ImageView pictureCard;
        private TextView fecha, temp, oxi, ph, turb;

        public PictureViewHolder(View itemView) {
            super(itemView);

            pictureCard=(ImageView)itemView.findViewById(R.id.imageViewTrashs);
            fecha   = (TextView) itemView.findViewById(R.id.lblFecha);
            temp    = (TextView)itemView.findViewById(R.id.lblTemperatura);
            oxi     = (TextView)itemView.findViewById(R.id.txtTemp);
            ph      = (TextView)itemView.findViewById(R.id.lblPH);
            turb    = (TextView)itemView.findViewById(R.id.lblturbidez);
        }
    }
}
