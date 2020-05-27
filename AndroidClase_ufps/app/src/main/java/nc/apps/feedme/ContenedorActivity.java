package nc.apps.feedme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ContenedorActivity extends AppCompatActivity {
    private TabItem perfil;
    private TabItem restaurante;
    private TabItem favoritos;
    private TabLayout tabLayout;
    private ViewPager contenedor;
    private PagerAdapter pagerAdapter;
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



}
