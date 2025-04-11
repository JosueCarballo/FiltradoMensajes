package service;

import model.Mensaje;
import org.springframework.stereotype.Service;

@Service
public class FiltroService {
    // Lista de palabras prohibidas
    private final String[] PALABRAS_PROHIBIDAS = {"palabrota", "insulto", "groseria", "tonto", "grosero", "menso", "maldito", "desgraciado", "malo"};

    private final String REEMPLAZO = "!#?%@";

    // Límite de palabras prohibidas
    private final int LIMITE_PALABRAS = 3;

    public Mensaje filtrar(Mensaje mensaje) {
        return mensaje;
    }

    public Mensaje procesarMensaje(Mensaje mensaje) {
        String textoOriginal = mensaje.getTexto();
        String textoFiltrado = textoOriginal;
        int contador = 0;

        for (String palabra : PALABRAS_PROHIBIDAS) {
            String textoLower = textoFiltrado.toLowerCase();
            String palabraLower = palabra.toLowerCase();

            int indice = textoLower.indexOf(palabraLower);
            while (indice >= 0) {
                contador++;
                textoFiltrado = textoFiltrado.substring(0, indice) +
                        REEMPLAZO +
                        textoFiltrado.substring(indice + palabraLower.length());

                textoLower = textoFiltrado.toLowerCase();
                indice = textoLower.indexOf(palabraLower);
            }
        }

        mensaje.setContadorProhibidas(contador);

        if (contador > LIMITE_PALABRAS) {
            mensaje.setTexto("Mensaje bloqueado por contener demasiado contenido inapropiado.");
        } else {
            mensaje.setTexto(textoFiltrado);
        }

        return mensaje;
    }

    public String[] getPalabrasProhibidas() {
        return PALABRAS_PROHIBIDAS;
    }
}