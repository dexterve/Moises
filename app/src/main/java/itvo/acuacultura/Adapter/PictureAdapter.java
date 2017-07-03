package itvo.acuacultura.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import itvo.acuacultura.Model.Comunicador;
import itvo.acuacultura.Model.Picture;
import itvo.acuacultura.Model.drawImage;
import itvo.acuacultura.R;
import itvo.acuacultura.View.Enfermedades.DescripcionActivity;


public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {

    private ArrayList<Picture> pictures;
    private int resource;
    private Activity activity;
    private String re;

    public PictureAdapter(ArrayList<Picture> pictures, int resource, Activity activity, String re) {
        this.pictures = pictures;
        this.resource = resource;
        this.activity = activity;
        this.re=re;
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        final Picture picture = pictures.get(position);

        holder.enfermedad.setText(picture.getEnfermedad());
        holder.pictureCard.setImageDrawable(picture.getImage());
        holder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DescripcionActivity.class);
                intent.putExtra("nom", picture.getEnfermedad());
                intent.putExtra("re",re);
                drawImage objImage = new drawImage(picture.getImage());
                Comunicador.setObj(objImage);

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
        });
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder{

        private ImageView pictureCard;
        private TextView enfermedad;

        public PictureViewHolder(View itemView) {
            super(itemView);

            pictureCard   =(ImageView)itemView.findViewById(R.id.imgpictureCard);
            enfermedad    =(TextView)itemView.findViewById(R.id.lblnameEnfermedad);

        }
    }
}
