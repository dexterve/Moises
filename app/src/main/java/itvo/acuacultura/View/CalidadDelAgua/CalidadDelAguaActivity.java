package itvo.acuacultura.View.CalidadDelAgua;

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
import itvo.acuacultura.View.CalidadDelAgua.Fragments.RegistrosCAFragment;
import itvo.acuacultura.View.CalidadDelAgua.Fragments.HelpCAFragment;
import itvo.acuacultura.View.CalidadDelAgua.Fragments.HomeCAFragment;
import itvo.acuacultura.View.MenuActivity;

public class CalidadDelAguaActivity extends AppCompatActivity {

    String re="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calidad_del_agua);
        re = getIntent().getStringExtra("re");

        BottomBar buttombar=(BottomBar)findViewById(R.id.BottomBarCA);
        buttombar.setDefaultTab(R.id.home);

        buttombar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.home:
                        HomeCAFragment homeCAFragment = new HomeCAFragment();
                        homeCAFragment.Re(re);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeCAFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.registros:
                        RegistrosCAFragment regFragment = new RegistrosCAFragment();
                        regFragment.Re(re);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, regFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.help:
                        HelpCAFragment helpCAFragment = new HelpCAFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, helpCAFragment)
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
