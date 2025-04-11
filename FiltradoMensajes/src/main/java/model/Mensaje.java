package model;

public class Mensaje {
    private String texto;
    private int contadorProhibidas;

    public Mensaje(String texto) {
        this.texto = texto;
        this.contadorProhibidas = 0;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getContadorProhibidas() {
        return contadorProhibidas;
    }

    public void setContadorProhibidas(int contadorProhibidas) {
        this.contadorProhibidas = contadorProhibidas;
    }
}