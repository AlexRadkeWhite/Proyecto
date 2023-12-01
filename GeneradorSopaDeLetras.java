// Clase base abstracta con funcionalidades comunes para generar sopas de letras.
package uabc.inc.proyectofinal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public abstract class GeneradorSopaDeLetras {

    protected static final int filas = 15;
    protected static final int columnas = 15;

    public abstract void generarSopaDeLetras(String nombreArchivo, String[] palabras);

    protected boolean esconderPalabra(Elemento[][] cuadricula, int fila, int columna, int direccion, String palabra) {
        int longitud = palabra.length();

        switch (direccion) {
            case 0: // Vertical
                if (fila + longitud <= filas) {
                    for (int i = 0; i < longitud; i++) {
                        cuadricula[fila + i][columna] = new ElementoLetra(String.valueOf(palabra.charAt(i)));
                    }
                    return true;
                }
                break;
            case 1: // Horizontal
                if (columna - longitud + 1 >= 0) {
                    for (int i = 0; i < longitud; i++) {
                        cuadricula[fila][columna - i] = new ElementoLetra(String.valueOf(palabra.charAt(i)));
                    }
                    return true;
                }
                break;
            case 2: // Diagonal 
                if (fila - longitud + 1 >= 0 && columna - longitud + 1 >= 0) {
                    for (int i = 0; i < longitud; i++) {
                        cuadricula[fila - i][columna - i] = new ElementoLetra(String.valueOf(palabra.charAt(i)));
                    }
                    return true;
                }
                break;
        }

        return false;
    }

    protected void rellenarCuadricula(Elemento[][] cuadricula, String[] palabras) {
        Random random = new Random();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (palabras.length > 0 && palabras[0].matches("[0-9]+")) {
                    // Si se proporcionan palabras y son combinaciones de números, llenar con números
                    cuadricula[i][j] = new ElementoNumero(random.nextInt(10));
                } else {
                    // Llenar con letras aleatorias
                    cuadricula[i][j] = new ElementoLetra(String.valueOf((char) (random.nextInt(26) + 'a')));
                }
            }
        }
    }

    protected void guardarSopa(String nombreArchivo, Elemento[][] cuadricula) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo + ".txt"))) {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    char elemento = cuadricula[i][j].obtenerCaracter();
                    writer.write(elemento + " ");
                    System.out.print(elemento + " ");
                }
                writer.newLine();
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
