package itvo.acuacultura.View.Enfermedades;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.github.chrisbanes.photoview.PhotoView;

import itvo.acuacultura.Model.Comunicador;
import itvo.acuacultura.Model.drawImage;
import itvo.acuacultura.R;

public class ImagenEnfermedadActivity extends AppCompatActivity {
    //ImageView imgEnfermedad;
    //PhotoViewAttacher photoViewAttacher;
    Object obj;
    drawImage objIm;
    Drawable imgEnf;
    String nom, re;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_enfermedad);
        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        //imgEnfermedad =(ImageView) findViewById(R.id.imgPeces);
        //photoViewAttacher = new PhotoViewAttacher(imgEnfermedad);
        nom=getIntent().getStringExtra("nom");
        re=getIntent().getStringExtra("re");
        showToolbar(nom,true);
        obj = Comunicador.getObj();
        objIm = (drawImage) obj;
        imgEnf = objIm.getEnfermedad();
        //Drawable imgEnfe=getResources().getDrawable(objIm.getEnfermedad());
       // Toast.makeText(this, nom,Toast.LENGTH_SHORT).show();
        photoView.setImageDrawable(imgEnf);
        //photoView.setImageResource(R.drawable.ataque_bacteriano_aeromonas);
    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar =  (Toolbar) findViewById(R.id.toolbarBlack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsinToolbar);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, DescripcionActivity.class);
                intent.putExtra("re", re);
                intent.putExtra("nom", nom);
                /*View imageView = findViewById(R.id.imageHeader);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    getWindow().setExitTransition(explode);
                    startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation
                            (this,imageView  , this.getString(R.string.transitionName)).toBundle());
                }else {

                } */
                startActivity(intent);
                return true;
        }
        // Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);

    }

}
