package nc.apps.feedme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private TextInputEditText input_text_user;
    private TextInputEditText input_text_pass;
    private Button mi_button_sesion;
    private TextView label_bottom_new_registrer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        this.input_text_user = findViewById(R.id.input_text_user);
        this.input_text_pass = findViewById(R.id.input_text_pass);
        this.mi_button_sesion = findViewById(R.id.mi_button_login);
        this.label_bottom_new_registrer = findViewById(R.id.label_bottom_new_registrer);
        this.mi_button_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion(input_text_user.getText().toString(), input_text_pass.getText().toString());
            }
        });
       /* this.label_bottom_new_registrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistreActivity.class));
            }
        });*/
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
        if(currentUser != null) abrirContenedorActivity();
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    public void iniciarSesion(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Authentication exitosa.",
                                    Toast.LENGTH_SHORT).show();
                             abrirContenedorActivity();
                            //updateUI(user);
                            //System.out.println("login correcto");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                            //System.out.println("login incorrecto");
                        }

                        // ...
                    }
                });
    }

    public void abrirContenedorActivity(){
        Intent intent = new Intent(this, ContenedorActivity.class);
        startActivity(intent);
    }

    public void registrarUsuario(View view){
        startActivity(new Intent(MainActivity.this, RegistreActivity.class));
    }

}
