package nc.apps.feedme.modelo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import nc.apps.feedme.R;

public class ListaRestauranteAdapter extends RecyclerView.Adapter<ListaRestauranteAdapter.ViewHolder> {
private  int resourse;

private  ArrayList<Restaurante>  restauranteList;

public  ListaRestauranteAdapter(ArrayList<Restaurante> restauranteList, int resourse){
    this.restauranteList =restauranteList;
    this.resourse = resourse;
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resourse , viewGroup , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    Restaurante restaurante = restauranteList.get(position);

    holder.nombre_restaurante.setText(restaurante.getNombre());
    holder.descripcion_restaurante.setText(restaurante.getDescripcion());
//    holder.imagen_restaurante_principal.setImageURI(restaurante.getImagen());

    }

    @Override
    public int getItemCount()
    {

        return restauranteList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

       private TextView nombre_restaurante;
        private TextView descripcion_restaurante;
        private ImageView imagen_restaurante_principal;
        public View view;

        public  ViewHolder(View view){
            super(view);

            this.view = view;
            this.nombre_restaurante = (TextView) view.findViewById(R.id.nombre_restaurante);
            this.descripcion_restaurante = (TextView) view.findViewById(R.id.descripcion_restaurante);
          //  this.imagen_restaurante_principal = (ImageView) view.findViewById(R.id.imagen_restaurante_principal);
        }
    }
}
