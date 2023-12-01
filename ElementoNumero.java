// Implementa la interfaz Elemento y proporciona el método para obtener el carácter.
package uabc.inc.proyectofinal;

public class ElementoNumero implements Elemento {

    private final int valor;

    public ElementoNumero(int valor) {
        this.valor = valor;
    }

    @Override
    public char obtenerCaracter() {
        return Character.forDigit(valor, 10);
    }
}
