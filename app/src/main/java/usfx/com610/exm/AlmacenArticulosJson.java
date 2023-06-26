package usfx.com610.exm;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kotlin.jvm.internal.TypeReference;
import usfx.com610.exm.modelos.Articulos;

public class AlmacenArticulosJson  implements AlmacenArticulos {

    private String string; //Almacena puntuaciones en formato JSON
    private Gson gson = new Gson();
    private Type type = new TypeToken<List<Articulos>>() {}.getType();
    private String respuesta;

    public AlmacenArticulosJson() {
    }


    @Override
    public void guardarArticulos(String titulo, String detalle, String fecha, String imagen) {

    }

    @Override
    public List<String> listaArticulos(int cantidad, String respuesta) {
        this.respuesta = replaceFirstAndLastCharacter(respuesta, '[', ']');
        // string = leerString();
        ArrayList<Articulos> listaarticulos = convetArray(respuesta);
        /*if (respuesta == null) {
            listaarticulos = new ArrayList<>();
        } else {
            System.out.println(this.respuesta);
            listaarticulos = gson.fromJson(this.respuesta, type);
            Log.i("DATOS", respuesta);
            Log.i("Respondio gson",listaarticulos.toString());
        }*/


        List<String> salida = new ArrayList<>();
        for (Articulos articulo : listaarticulos) {
            if(articulo == null) {Log.i("Respondio","Es nullo");}else {
                salida.add(articulo.getNombre() + "," + articulo.getDescripcion()+","+articulo.getCosto());
                Log.i("Respondio SAlida", String.valueOf(salida));}
        }
        return salida;
    }
    public static String replaceFirstAndLastCharacter(String cadena, char primerCaracter, char ultimoCaracter) {
        if (cadena.length() <= 1) {
            // La cadena tiene menos de 2 caracteres, no se puede reemplazar
            return cadena;
        }

        StringBuilder sb = new StringBuilder(cadena);
        sb.setCharAt(0, primerCaracter);  // Reemplazar el primer carácter
        sb.setCharAt(sb.length() - 1, ultimoCaracter);  // Reemplazar el último carácter

        return sb.toString();
    }
    private ArrayList<Articulos> convetArray(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Articulos> lista = new ArrayList<>();
        try {
            // Convertir el string JSON en un objeto Map
            Map<String, Object> mapa = objectMapper.readValue(jsonString, Map.class);

            // Convertir el Map en un array
            Object[] array = mapa.values().toArray();

            // Iterar sobre los objetos del array
            for (Object objeto : array) {
                // Hacer algo con cada objeto
                System.out.println(objeto.toString());

                // Articulos model = objectMapper.readValue(objeto.toString(), Articulos.class);
                lista.add((Articulos) objeto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
