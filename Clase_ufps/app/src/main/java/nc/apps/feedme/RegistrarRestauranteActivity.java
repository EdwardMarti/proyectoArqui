package nc.apps.feedme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import nc.apps.feedme.modelo.Restaurante;

public class RegistrarRestauranteActivity extends AppCompatActivity {
    ImageView imagen_new_Resturante;
    TextInputEditText nombre;
    TextInputEditText descripcion;
    TextView ubicacion;
    Button guardar;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_restaurante);
        imagen_new_Resturante = findViewById(R.id.imagen_new_restaurante);
        nombre = findViewById(R.id.nombre_restaurante_new);
        descripcion = findViewById(R.id.descripcion_restaurante_new);
        ubicacion = findViewById(R.id.ubicacion);
        guardar = findViewById(R.id.insertar_new_restaurant);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               guardarRestaurante();
            }
         });
    }

    private void guardarRestaurante(){
       // String nombre, String descripcion, double longitud, double latitud, int like
        Restaurante restaurante = new Restaurante(
                myRef.push().getKey(),
                nombre.getText().toString(),
                descripcion.getText().toString(),
                0.0,
                0.0,
                0
        );
        Task<Void> task = myRef.child("restaurante").child(restaurante.getId()).setValue(restaurante);
        Toast.makeText(RegistrarRestauranteActivity.this, "Authentication exitosa.", Toast.LENGTH_SHORT).show();
    }

    private double[] obtenerUbicacion(){
        double[] aux = {
                Double.parseDouble(ubicacion.getText().toString().split(";")[0]),
                Double.parseDouble(ubicacion.getText().toString().split(";")[1])
        };
        return aux;
    }
}
