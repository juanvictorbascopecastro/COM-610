package usfx.com610.exm.modelos;

public class mensajelist {
    private String texto;
    private String idUsuario;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public mensajelist(String texto, String idUsuario, String key) {
        this.texto = texto;
        this.idUsuario = idUsuario;
        this.key = key;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
