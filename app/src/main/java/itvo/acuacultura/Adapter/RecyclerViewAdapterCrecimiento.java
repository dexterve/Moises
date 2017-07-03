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
import itvo.acuacultura.Model.ComRegistrosCrecimiento;
import itvo.acuacultura.R;
import itvo.acuacultura.View.Crecimiento.CrecimientoActivity;


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
    public void onBindViewHolder(PictureViewHolder holder, final int position) {
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

        final String pez = reg.getPez();
        final String num = reg.getNum();

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
                                        ArrayList p = bd.ListarCrecimientoTilapia();
                                        ArrayList elemento = (ArrayList) p.get(position);
                                        id = Integer.parseInt(elemento.get(0).toString());
                                        bd.EliminarCreTilapia(id);
                                        Intent intent = new Intent(activity, CrecimientoActivity.class);
                                        intent.putExtra("re", pez);
                                        intent.putExtra("num", num);
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
                                        ArrayList p = bd.ListarCrecimientoTrucha();
                                        ArrayList elemento = (ArrayList) p.get(position);
                                        id = Integer.parseInt(elemento.get(0).toString());
                                        bd.EliminarCreTrucha(id);
                                        Intent intent = new Intent(activity, CrecimientoActivity.class);
                                        intent.putExtra("re", pez);
                                        intent.putExtra("num", num);
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
        private TextView fecha,peso, lon, pesoA, lonA, PesoPor, lonPor;

        public PictureViewHolder(View itemView) {
            super(itemView);
            pictureCard =(ImageView)    itemView.findViewById(R.id.imageViewTrash);
            fecha       =(TextView)     itemView.findViewById(R.id.lblFechaCre);
            peso        =(TextView)     itemView.findViewById(R.id.lblPesoProm);
            lon         =(TextView)     itemView.findViewById(R.id.lblLongitudProm);
            pesoA       =(TextView)     itemView.findViewById(R.id.lblPesoAumentado);
            lonA        =(TextView)     itemView.findViewById(R.id.lbllongitudAumen);
            PesoPor     =(TextView)     itemView.findViewById(R.id.lblPorcentajePeso);
            lonPor      =(TextView)     itemView.findViewById(R.id.lblPorcentajeLong);

        }
    }
}
