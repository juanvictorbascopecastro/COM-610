package usfx.com610.exm.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import usfx.com610.exm.R;
import usfx.com610.exm.modelos.mensajelist;

public
class AdaptadorArticulos extends RecyclerView.Adapter<AdaptadorArticulos.ViewHolder> {
    private LayoutInflater inflador; //Crea Layouts a partir del XML protecte
    private ArrayList<mensajelist> lista;
    private Context contexto;
    private OnItemClickListener listener;
    private FirebaseUser user;



    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
        user  = FirebaseAuth.getInstance().getCurrentUser();
    }
    public interface OnItemClickListener {
        void onItemClick(mensajelist item);
    }
    public AdaptadorArticulos(Context contexto, ArrayList<mensajelist> listaLibros) {
        inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.lista = listaLibros;
        this.contexto = contexto;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre, detalle;
        public TextView costo;
        public RelativeLayout itemLayout;

        ViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            detalle = (TextView) itemView.findViewById(R.id.detalle);
            costo = (TextView) itemView.findViewById(R.id.costo);
            itemLayout = itemView.findViewById(R.id.itemLayout);
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
        holder.nombre.setText(articulos.getMensaje());
        holder.detalle.setText(articulos.getNombre());
        holder.costo.setText(articulos.getKey());
        // Configurar clic en el elemento de la vista
        if(articulos.getId().equals(user.getUid())) {
            holder.itemLayout.setBackgroundColor(Color.parseColor("#FFC570"));
        }else {
            holder.itemLayout.setBackgroundColor(Color.parseColor("#9789C0EC"));
        }
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