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
    ArrayList<Restaurante> restaurantes_aux_p;
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
        obtenerRedCloud();


        this.agregar_nuevo_restaurante = view.findViewById(R.id.agregar_nuevo_restaurante);
        this.agregar_nuevo_restaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFormulario();
            }
        });
        return view;
    }

    private void obtenerRedCloud(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("restaurante");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Restaurante> restaurantes_aux = new ArrayList<>();
                if (dataSnapshot.exists()){
                    for (DataSnapshot ds: dataSnapshot.getChildren()) {
                       // String itm = ds.getValue(Actividades.class).toString();
                        Restaurante r = ds.getValue(Restaurante.class);
                        restaurantes_aux.add(r);
                    }
                    RestauranteAdapter restauranteAdapter = new RestauranteAdapter(restaurantes_aux, getActivity(), R.layout.cardview);
                    visualizar_restaurante.setAdapter(restauranteAdapter);
                }else{
                    Log.d(TAG, "NO HAY NADA" );
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        //Restaurante r_1 = new Restaurante("001", "DulceAroma", "CafeMolido y freso", 0.0, 0.0, 5, "https://www.perfectdailygrind.com/wp-content/uploads/2016/11/Catuai.jpg");
       // Restaurante r_2 = new Restaurante("002", "Boobagump", "Camarones y frutos del mar", 0.0, 0.0, 5, "https://www.bubbagump.com/img/locations/BGAN_bigpic2.jpg");
        //restaurantes_aux.add(r_1);
        //restaurantes_aux.add(r_2);
       /* Log.d(TAG, "TAMANO: " + restaurantes_aux.size());
        for (int i = 0; i < restaurantes_aux.size(); i++) {
            Log.d(TAG, "SUPER");
            String a = restaurantes_aux.get(i).toString();
            System.out.println(a);
            Log.d(TAG, "Value is: " + a);
        }
*/
    }

    private void abrirFormulario() {
        Intent intent = new Intent(getActivity(), RegistrarRestauranteActivity.class);
        startActivity(intent);
    }

}
