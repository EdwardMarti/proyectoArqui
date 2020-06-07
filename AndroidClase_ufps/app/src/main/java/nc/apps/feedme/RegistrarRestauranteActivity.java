package nc.apps.feedme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import id.zelory.compressor.Compressor;
import nc.apps.feedme.modelo.Restaurante;

public class RegistrarRestauranteActivity extends AppCompatActivity {
    ImageView foto;
    TextInputEditText nombre;
    TextInputEditText descripcion;
    TextView ubicacion;
    Button guardar;
    String img;
    FirebaseDatabase database;
    DatabaseReference myRef;

    //***************************************//
    Button seleccionar;
    Bitmap thumb_bitmap = null;
    ProgressDialog cargando;
    StorageReference storageReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_restaurante);
 //       foto = findViewById(R.id.imagen_new_restaurante);
        nombre = findViewById(R.id.nombre_restaurante_new);
        descripcion = findViewById(R.id.descripcion_restaurante_new);
        ubicacion = findViewById(R.id.ubicacion);
        guardar = findViewById(R.id.insertar_new_restaurant);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        //*************************************************//
        cargando = new ProgressDialog( this);
        seleccionar =findViewById(R.id.seleccionar);
        storageReference = FirebaseStorage.getInstance().getReference().child("img_comprimido");


        seleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.startPickImageActivity(RegistrarRestauranteActivity.this);
            }
        });




    //    guardar.setOnClickListener(new View.OnClickListener() {
      //      @Override
      //      public void onClick(View view) {
      //         guardarRestaurante();
     //       }
     //    });
    }//fin oncreate



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Uri imguri =CropImage.getPickImageResultUri(this,data);



            CropImage.activity(imguri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setRequestedSize(640,480)
                    .setAspectRatio(2,1).start(RegistrarRestauranteActivity.this);

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result  = CropImage.getActivityResult(data);

            if(resultCode == RESULT_OK){
                Uri resultUri = result.getUri();

                File url = new File(resultUri.getPath());
                //comprime imeagen

                foto = findViewById(R.id.imagen_new_restaurante);

             Picasso.get().load(url).into(foto);




                try {
                    thumb_bitmap = new Compressor(this)
                            .setMaxWidth(640)
                            .setMaxHeight(480)
                            .setQuality(90)
                            .compressToBitmap(url);
                }catch (IOException e){
                    e.printStackTrace();
                }

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                thumb_bitmap.compress(Bitmap.CompressFormat.JPEG ,90,byteArrayOutputStream);
                final byte []  thuBytes = byteArrayOutputStream.toByteArray();

                //find  del compresor....

                int p = (int) (Math.random() * 25 + 1); int s =(int) (Math.random() * 25 + 1);
                int t = (int) (Math.random() * 25 + 1); int c =(int) (Math.random() * 25 + 1);
                int numero1 = (int) (Math.random() * 1012 + 2111);
                int numero2 = (int) (Math.random() * 1012 + 2111);

                String[] elementos = {"a", "b","c","d","e","f","g","h","i","j","k","l","m",
                        "n","o","p","q","r","s","t","u","v","w","y","z"};

                final  String aleatorio = elementos [p] + elementos [s] +
                        numero1 + elementos [t] +  elementos [c] + numero2 + "comprimido.jpg";

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //guardarRestaurante();

                        cargando.setTitle("Subiendo foto....");
                        cargando.setMessage("Espere por Favor");
                        cargando.show();

                        final StorageReference ref =storageReference.child(aleatorio);

                        UploadTask uploadTask = ref.putBytes(thuBytes);

                        //subir imagen



                    //    Task<Void> task = myRef.child("restaurante").child(restaurante.getId()).setValue(restaurante);
                      //  Toast.makeText(RegistrarRestauranteActivity.this, "Registro  exitoso.", Toast.LENGTH_SHORT).show();




                        Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if(!task.isSuccessful()){
                                    throw Objects.requireNonNull(task.getException());
                                }
                                return ref.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                Uri downloaduri = task.getResult();

                                Restaurante restaurante = new Restaurante(
                                        myRef.push().getKey(),
                                        nombre.getText().toString(),
                                        descripcion.getText().toString(),
                                         0.0,
                                        0.0,
                                        0,
                                        img=downloaduri.toString()

                                );
                                  myRef.child("restaurante").child(restaurante.getId()).setValue(restaurante);
                                cargando.dismiss();
                                Toast.makeText(RegistrarRestauranteActivity.this,"Imagen Cargada", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(RegistrarRestauranteActivity.this, MainActivity.class));
                                finish();
                            }
                        });

                    }
                });

            }
        }

    }



  //  private void guardarRestaurante(){
       // String nombre, String descripcion, double longitud, double latitud, int like
    //    Restaurante restaurante = new Restaurante(
     //           myRef.push().getKey(),
     //           nombre.getText().toString(),
      //          descripcion.getText().toString(),
        //        0.0,
      //          0.0,
     //           0
    //    );
   //     Task<Void> task = myRef.child("restaurante").child(restaurante.getId()).setValue(restaurante);
   //     Toast.makeText(RegistrarRestauranteActivity.this, "Authentication exitosa.", Toast.LENGTH_SHORT).show();
 //  }

    private double[] obtenerUbicacion(){
        double[] aux = {
                Double.parseDouble(ubicacion.getText().toString().split(";")[0]),
                Double.parseDouble(ubicacion.getText().toString().split(";")[1])
        };
        return aux;
    }
}
