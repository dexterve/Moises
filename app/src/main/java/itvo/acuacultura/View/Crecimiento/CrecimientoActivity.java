package itvo.acuacultura.View.Crecimiento;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import itvo.acuacultura.R;
import itvo.acuacultura.View.Crecimiento.Fragments.AyudaCrecimientoFragment;
import itvo.acuacultura.View.Crecimiento.Fragments.GraficaCrecimientoFragment;
import itvo.acuacultura.View.Crecimiento.Fragments.HomeCrecimientoFragment;
import itvo.acuacultura.View.Crecimiento.Fragments.RegistrosCrecimientoFragment;
import itvo.acuacultura.View.MenuActivity;

public class CrecimientoActivity extends AppCompatActivity {

    String re="";
    String num="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crecimiento);
        re  = getIntent().getStringExtra("re");
        num =  getIntent().getStringExtra("num");

        BottomBar buttombar=(BottomBar)findViewById(R.id.BottomBarCrecimiento);
        buttombar.setDefaultTab(R.id.calcular);

        buttombar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.calcular:
                        HomeCrecimientoFragment homeCrecimientoFragment = new HomeCrecimientoFragment();
                        homeCrecimientoFragment.Re(re, num);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerCrecimiento, homeCrecimientoFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.registro:
                        RegistrosCrecimientoFragment regFragment = new RegistrosCrecimientoFragment();
                        regFragment.Re(re);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerCrecimiento, regFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.helpc:

                        AyudaCrecimientoFragment helpCAFragment = new AyudaCrecimientoFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerCrecimiento, helpCAFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .addToBackStack(null).commit();

                        break;
                    case R.id.graficar:
                        GraficaCrecimientoFragment graf = new GraficaCrecimientoFragment();
                        graf.Re(re);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerCrecimiento, graf)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .addToBackStack(null).commit();
                        break;
                }
            }
        });
    }

    public void showToolbar(String title, boolean upButton) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    @Override
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

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==4){
            Intent intent = new Intent(this, MenuActivity.class);
            intent.putExtra("re", re);
            startActivity(intent);
        }

        //onBackPressed();
        return super.onKeyDown(keyCode, event);
    }
}
