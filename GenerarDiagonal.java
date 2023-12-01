// Implementa la generación de sopas de letras con palabras ocultas en direcciones diagonales.

package uabc.inc.proyectofinal;

import java.util.Random;

public class GenerarDiagonal extends GeneradorSopaDeLetras {
    @Override
    public void generarSopaDeLetras(String nombreArchivo, String[] palabras) {
        Elemento[][] cuadricula = new Elemento[filas][columnas];
        rellenarCuadricula(cuadricula, palabras);

        Random random = new Random();

        for (String palabra : palabras) {
            if (palabra.length() > 10) {
                System.out.println("Palabra omitida: " + palabra + " (más de 10 caracteres)");
                continue;
            }

            int fila, columna, direccion;
            do {
                fila = random.nextInt(filas);
                columna = random.nextInt(columnas);
                direccion = random.nextInt(3); 
            } while (!esconderPalabra(cuadricula, fila, columna, direccion, palabra));

            System.out.println("Palabra: " + palabra + ", Fila: " + (fila + 1) + ", Columna: " + (columna + 1) + ", Dirección: " + obtenerDireccionTexto(direccion));
        }

        guardarSopa(nombreArchivo, cuadricula);
    }

    private String obtenerDireccionTexto(int direccion) {
        switch (direccion) {
            case 0:
                return "Vertical";
            case 1:
                return "Horizontal";
            case 2:
                return "Diagonal";
            default:
                return "No hay mas direcciones ";
        }
    }
}
