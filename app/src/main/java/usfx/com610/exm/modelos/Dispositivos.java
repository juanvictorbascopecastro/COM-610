package usfx.com610.exm.modelos;

public class Dispositivos {
    private String email;
    private String token;
    private String nombre;
    private String key;

    public Dispositivos(String email, String token, String nombre, String key) {
        this.email = email;
        this.token = token;
        this.nombre = nombre;
        this.key = key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
