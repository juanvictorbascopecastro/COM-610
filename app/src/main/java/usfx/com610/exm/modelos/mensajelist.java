package usfx.com610.exm.modelos;

public class mensajelist {
    private String mensaje;
    private String nombre;
    private String id;
    private String key;

    public mensajelist(String mensaje, String nombre, String id, String key) {
        this.mensaje = mensaje;
        this.nombre = nombre;
        this.id = id;
        this.key = key;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
