package nc.apps.feedme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ContenedorActivity extends AppCompatActivity {
    private TabItem perfil;
    private TabItem restaurante;
    private TabItem favoritos;
    private TabLayout tabLayout;
    private ViewPager contenedor;
    private PagerAdapter pagerAdapter;
    private int contador=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor);
        this.tabLayout = findViewById(R.id.my_tabLayout);
        this.perfil = findViewById(R.id.verPerfil);
        this.restaurante = findViewById(R.id.verRestaurante);
        this.favoritos = findViewById(R.id.verFavoritos);
        this.contenedor = findViewById(R.id.contenedor_fragment);
        this.pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        contenedor.setAdapter(this.pagerAdapter);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(contador==0){
            Toast.makeText(getApplicationContext() , "Presione Nuevamente Para Salir", Toast.LENGTH_SHORT).show();
            contador++;
        }else {
            super.onBackPressed();
        }

        new CountDownTimer(3000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                contador=0;
            }
        }.start();
    }


}
