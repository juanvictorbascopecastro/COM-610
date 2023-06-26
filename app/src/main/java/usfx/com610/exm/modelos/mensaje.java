package usfx.com610.exm.modelos;

public class mensaje {
    private String texto;
    private String idUsuario;

    public mensaje(String texto, String idUsuario) {
        this.texto = texto;
        this.idUsuario = idUsuario;
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
