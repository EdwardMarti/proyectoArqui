package nc.apps.feedme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RegistreActivity extends AppCompatActivity {
    private static final String TAG = "RegistreActivity";
    private Button boton_registro;
    private TextInputEditText input_text_new_user;
    private TextInputEditText input_text_new_pass;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);
        this.boton_registro = findViewById(R.id.mi_button_registrer);
        this.input_text_new_user = findViewById(R.id.input_text_new_user);
        this.input_text_new_pass = findViewById(R.id.input_text_new_pass);
        this.boton_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarUsuario(input_text_new_user.getText().toString(), input_text_new_pass.getText().toString());
            }
        });
        mAuth = FirebaseAuth.getInstance();
    }

    private void RegistrarUsuario(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegistreActivity.this, "Usuario creado exitosamente.",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistreActivity.this, MainActivity.class));
                            finish();


                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegistreActivity.this, "Error al registrar usuario. Datos erroneos",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
}
