package nc.apps.feedme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nc.apps.feedme.modelo.Restaurante;

public class RestauranteAdapter extends RecyclerView.Adapter<RestauranteAdapter.RestauranteViewHolder> {
    private ArrayList<Restaurante> restaurantes;
    private Context context;
    private int recurso;

    public RestauranteAdapter(ArrayList<Restaurante> restaurantes, Context context, int recurso) {
        this.restaurantes = restaurantes;
        this.context = context;
        this.recurso = recurso;
    }

    @NonNull
    @Override
    public RestauranteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new RestauranteAdapter.RestauranteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestauranteViewHolder holder, int position) {
        Restaurante restaurante = restaurantes.get(position);
        holder.nombreRestaurante.setText(restaurante.getNombre());
        holder.descripcion.setText(restaurante.getDescripcion());
       Picasso.get().load(restaurante.getImg()).into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return restaurantes.size();
    }

    static class RestauranteViewHolder extends RecyclerView.ViewHolder {
        TextView nombreRestaurante;
        TextView descripcion;
        ImageView imagen;
        CheckBox meGusta;
        ImageView compartir;
        ImageView localizar;

        public RestauranteViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imagen_restaurante_principal);
            nombreRestaurante = itemView.findViewById(R.id.nombre_restaurante);
            descripcion = itemView.findViewById(R.id.descripcion_restaurante);
            compartir = itemView.findViewById(R.id.compartir);
            localizar = itemView.findViewById(R.id.localizar);
           // meGusta = itemView.findViewById(R.id.megusta);
        }
    }
}
