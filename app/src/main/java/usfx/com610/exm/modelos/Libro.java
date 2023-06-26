package usfx.com610.exm.modelos;

import java.util.ArrayList;
import java.util.List;

public class Libro {
    private String titulo;
    private String autor;
    private String genero;

    public Libro(String titulo, String autor, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
    }

    public static List<Libro> ejemploLibros() {
        List<Libro> list = new ArrayList<>();
        list.add(new Libro("Kappa", "Akutagawa", "G_S_XIX"));
        list.add(new Libro("Avecilla", "Alas Clarin", "G_S_NOVEL"));
        list.add(new Libro("Divina Comedia", "Dante", "G_EPICO"));
        return list;
    }
}
