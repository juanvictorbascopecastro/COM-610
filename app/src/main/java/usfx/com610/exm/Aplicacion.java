package usfx.com610.exm;

import android.app.Application;

import java.util.List;

import usfx.com610.exm.adapters.AdaptadorLibros;
import usfx.com610.exm.modelos.Libro;

public class Aplicacion extends Application {
    private List<Libro> listaLibros;
    private AdaptadorLibros adaptador;

    @Override
    public void onCreate() {
        super.onCreate();
        listaLibros = Libro.ejemploLibros();
        // adaptador = new AdaptadorLibros(this, listaLibros);
    }
    public AdaptadorLibros getAdapter() {
        return adaptador;
    }
    public List<Libro> getListaLibros() {
        return listaLibros;
    }
}
