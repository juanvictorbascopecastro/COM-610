package usfx.com610.exm.modelos;

public class mensaje {
    private String mensaje;
    private String nombre;
    private String id;

    public mensaje(String mensaje, String nombre, String id) {
        this.mensaje = mensaje;
        this.nombre = nombre;
        this.id = id;
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
}
