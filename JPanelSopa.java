// Interfaz gráfica para ingresar datos y generar sopas de letras.
package uabc.inc.proyectofinal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JPanelSopa extends JPanel {

    private JTextField archivoTextField;
    private JTextField palabrasTextField;
    private JButton generarButton;

    public JPanelSopa() {
        archivoTextField = new JTextField(20);
        palabrasTextField = new JTextField(20);
        generarButton = new JButton("Generar Sopa de Letras");

        generarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarSopaDeLetras();
            }
        });

        add(new JLabel("Nombre del archivo:"));
        add(archivoTextField);
        add(new JLabel("Palabras o combinaciones de números:"));
        add(palabrasTextField);
        add(generarButton);
    }

    public void generarSopaDeLetras() {
        String nombreArchivo = archivoTextField.getText();
        String palabrasInput = palabrasTextField.getText();

        if (nombreArchivo.isEmpty() || palabrasInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un nombre de archivo y palabras a ocultar.");
            return;
        }

        String[] palabras = palabrasInput.split(",\\s*");

        GeneradorSopaDeLetras generador = new GenerarVertical();

        generador.generarSopaDeLetras(nombreArchivo, palabras);

        JOptionPane.showMessageDialog(this, "Sopa de letras generada.");
    }

    public void mostrarSopa(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int fila = 0;
            Elemento[][] cuadricula = new Elemento[GeneradorSopaDeLetras.filas][GeneradorSopaDeLetras.columnas];

            while ((linea = reader.readLine()) != null && fila < GeneradorSopaDeLetras.filas) {
                String[] elementos = linea.split("\\s+");
                for (int columna = 0; columna < Math.min(elementos.length, GeneradorSopaDeLetras.columnas); columna++) {
                    if (elementos[columna].matches("[0-9]+")) {
                        cuadricula[fila][columna] = new ElementoNumero(Integer.parseInt(elementos[columna]));
                    } else {
                        cuadricula[fila][columna] = new ElementoLetra(elementos[columna]);
                    }
                }
                fila++;
            }

            if (fila == GeneradorSopaDeLetras.filas) {
                SopaGUI sopaDeLetrasGUI = new SopaGUI(cuadricula);
                sopaDeLetrasGUI.mostrar();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "El archivo no se encontró", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
