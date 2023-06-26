package usfx.com610.exm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import usfx.com610.exm.R;
import usfx.com610.exm.modelos.mensajelist;

public
class AdaptadorArticulos extends RecyclerView.Adapter<AdaptadorArticulos.ViewHolder> {
    private LayoutInflater inflador; //Crea Layouts a partir del XML protecte
    private ArrayList<mensajelist> lista;
    private Context contexto;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(mensajelist item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public AdaptadorArticulos(Context contexto, ArrayList<mensajelist> listaLibros) {
        inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.lista = listaLibros;
        this.contexto = contexto;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre, detalle;
        public TextView costo;

        ViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            detalle = (TextView) itemView.findViewById(R.id.detalle);
            costo = (TextView) itemView.findViewById(R.id.costo);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.elemento_lista, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {

        final mensajelist articulos = lista.get(i);
        holder.nombre.setText(articulos.getTexto());
        holder.detalle.setText(articulos.getIdUsuario());
        holder.costo.setText(articulos.getKey());
        // Configurar clic en el elemento de la vista
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(articulos);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return lista.size();

    }
}