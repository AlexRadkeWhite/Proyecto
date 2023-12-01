// Implementa la interfaz Elemento y proporciona el método para obtener el carácter.

package uabc.inc.proyectofinal;

public class ElementoLetra implements Elemento {

    private final String valor;

    public ElementoLetra(String valor) {
        this.valor = valor;
    }

    @Override
    public char obtenerCaracter() {
        return valor.charAt(0);
    }
}
