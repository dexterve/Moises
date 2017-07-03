package itvo.acuacultura.View.Enfermedades;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import itvo.acuacultura.Adapter.PictureAdapter;
import itvo.acuacultura.MainActivity;
import itvo.acuacultura.Model.Picture;
import itvo.acuacultura.R;
import itvo.acuacultura.View.Alimentacion.AlimentacionActivity;

public class EnfermedadesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    String re="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades);
        re = getIntent().getStringExtra("re");

       // showToolbar("Enfermedades " + re, true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setTitle("Enfermedades");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        RecyclerView picturerecycler=(RecyclerView) findViewById(R.id.pictureRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturerecycler.setLayoutManager(linearLayoutManager);

        PictureAdapter pictureAdapter = new PictureAdapter(builPicture(),R.layout.card_view, this, re);

        picturerecycler.setAdapter(pictureAdapter);

    }
    public ArrayList<Picture> builPicture(){
        ArrayList <Picture> pictures = new ArrayList<>();
        Drawable columnaris = getResources().getDrawable(R.drawable.columnaris);
        switch (re){
            case "Trucha":
                Drawable vibriosisTrucha = getResources().getDrawable(R.drawable.vidriosis);
                Drawable atBacAertrucha = getResources().getDrawable(R.drawable.ataque_bacteriano_aeromonas1);
                Drawable puntoBlancoTrucha = getResources().getDrawable(R.drawable.punto_blanco);
                Drawable saprolegniaTrucha = getResources().getDrawable(R.drawable.saprolegnia1);
                Drawable FurunculosisTrucha = getResources().getDrawable(R.drawable.furunculosis1);
                pictures.add(new Picture(puntoBlancoTrucha, "Ichtofoniasis (Punto Blanco)"));
                pictures.add(new Picture(columnaris, "Columnaris"));
                pictures.add(new Picture(vibriosisTrucha, "Vibriosis"));
                pictures.add(new Picture(atBacAertrucha, "Ataque Bacteriano Aeromonas"));
                pictures.add(new Picture(saprolegniaTrucha, "Saprolegnia"));
                pictures.add(new Picture(FurunculosisTrucha, "Furunculosis"));
                break;
            case "Tilapia":
                Drawable vibriosisTilapia = getResources().getDrawable(R.drawable.vibriosis);
                Drawable atBacAerTilapia = getResources().getDrawable(R.drawable.ataque_bacteriano_aeromonas);
                Drawable puntoBlancoTilapia = getResources().getDrawable(R.drawable.punto_blanco_1);
                Drawable saprolegniaTilapia = getResources().getDrawable(R.drawable.saprolegnia);
                Drawable FurunculosisTilapia = getResources().getDrawable(R.drawable.furunculosis);
                pictures.add(new Picture(puntoBlancoTilapia, "Ichtofoniasis (Punto Blanco)"));
                pictures.add(new Picture(columnaris, "Columnaris"));
                pictures.add(new Picture(vibriosisTilapia, "Vibriosis"));
                pictures.add(new Picture(atBacAerTilapia, "Ataque Bacteriano Aeromonas"));
                pictures.add(new Picture(saprolegniaTilapia, "Saprolegnia"));
                pictures.add(new Picture(FurunculosisTilapia, "Furunculosis"));
                break;
        }


        return pictures;
    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, MenuActivity.class);
                intent.putExtra("re", re);
                startActivity(intent);
                //Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();onBackPressed();
                return true;
        }
        // Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);

    }*/
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==4){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        //onBackPressed();
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.Tilapia){
            Intent searchIntent = new Intent(this, EnfermedadesActivity.class);
            String re="Tilapia";
            searchIntent.putExtra("re",re);
            startActivity(searchIntent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }else if(id == R.id.Trucha){
            Intent searchIntent = new Intent(this, EnfermedadesActivity.class);
            startActivity(searchIntent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }else if(id == R.id.Alimentacion){
            Intent searchIntent = new Intent(this, AlimentacionActivity.class);
            startActivity(searchIntent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
