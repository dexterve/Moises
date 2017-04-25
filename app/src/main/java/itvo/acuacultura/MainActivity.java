package itvo.acuacultura;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import itvo.acuacultura.View.MenuActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showToolbar(getResources().getString(R.string.toolbar_title),false);
    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar =  (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public void goToMenuTrucha(View v){
        String re="Trucha";
        Intent i = new Intent(getApplicationContext(),MenuActivity.class);
        i.putExtra("re",re);
        startActivity(i);
    }

    public void goToMenuTilapia(View v){
        String re="Tilapia";
        Intent i = new Intent(getApplicationContext(),MenuActivity.class);
        i.putExtra("re",re);
        startActivity(i);
    }
}
