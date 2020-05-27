package nc.apps.feedme;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import nc.apps.feedme.modelo.Restaurante;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantFragment extends Fragment {

    private FloatingActionButton agregar_nuevo_restaurante;
    private RecyclerView visualizar_restaurante;
    private String TAG = "ResturanteFragment";

    public RestaurantFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);

        //parte1
        visualizar_restaurante = view.findViewById(R.id.visualizar_restaurante);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //perte2
        visualizar_restaurante.setLayoutManager(linearLayoutManager);
       RestauranteAdapter restauranteAdapter = new RestauranteAdapter(obtenerRedCloud(), getActivity(), R.layout.cardview);
       visualizar_restaurante.setAdapter(restauranteAdapter);

        this.agregar_nuevo_restaurante = view.findViewById(R.id.agregar_nuevo_restaurante);
        this.agregar_nuevo_restaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFormulario();
            }
        });
        return view;
    }

    private ArrayList<Restaurante> obtenerRedCloud(){
        ArrayList<Restaurante> restaurantes_aux = new ArrayList<>();
        Restaurante r_1 = new Restaurante("001", "DulceAroma", "CafeMolido y freso", 0.0, 0.0, 5, "https://www.perfectdailygrind.com/wp-content/uploads/2016/11/Catuai.jpg");
        Restaurante r_2 = new Restaurante("002", "Boobagump", "Camarones y frutos del mar", 0.0, 0.0, 5, "https://www.bubbagump.com/img/locations/BGAN_bigpic2.jpg");
        restaurantes_aux.add(r_1);
        restaurantes_aux.add(r_2);
        return restaurantes_aux;
    }

    private void abrirFormulario() {
        Intent intent = new Intent(getActivity(), RegistrarRestauranteActivity.class);
        startActivity(intent);
    }

}
