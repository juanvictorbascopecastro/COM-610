package usfx.com610.exm.modelos;
public class Articulos {
    private String nombre;
    private String descripcion;
    private String costo;

    public Articulos(String nombre, String descripcion, String costo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }


}
