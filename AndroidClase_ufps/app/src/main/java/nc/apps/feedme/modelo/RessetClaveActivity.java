package nc.apps.feedme.modelo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.DOMConfiguration;

import nc.apps.feedme.R;
import nc.apps.feedme.RegistrarRestauranteActivity;

public class RessetClaveActivity extends AppCompatActivity {
    private static final String TAG = "RessetClaveActivity";

    private Button btn_recuperar;
    private TextInputEditText input_reset_user;
    private String email = "";

    private FirebaseAuth mAuth;

    private ProgressDialog mDialogog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resset_clave);
        input_reset_user = findViewById(R.id.reset_user);
        btn_recuperar = findViewById(R.id.btn_recuperar);

        mAuth = FirebaseAuth.getInstance();

        mDialogog = new ProgressDialog( this);

        btn_recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = input_reset_user.getText().toString();



                if(!email.isEmpty()){
                    mDialogog.setTitle("Subiendo foto....");
                    mDialogog.setMessage("Espere por Favor");
                    mDialogog.show();
                    resetPassword();
                }else {
                    Toast.makeText(RessetClaveActivity.this,"Debe Ingresar un correo", Toast.LENGTH_SHORT).show();
                }

            mDialogog.dismiss();

            }
        });


    }// final on crere

    private  void  resetPassword (){

        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(RessetClaveActivity.this,"Se ha enviado  un correo", Toast.LENGTH_SHORT).show();
                }else  {
                    Toast.makeText(RessetClaveActivity.this,"No se pudo enviar  un correo", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}