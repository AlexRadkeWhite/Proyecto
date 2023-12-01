// Clase principal que contiene el método main para iniciar la aplicación y manejar interacciones.

package uabc.inc.proyectofinal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SopaDeLetras {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Generador de Sopas de Letras");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanelSopa panel = new JPanelSopa();
            frame.getContentPane().add(panel);

            frame.setJMenuBar(crearMenuBar(panel));  

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static JMenuBar crearMenuBar(JPanelSopa panel) {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");

        JMenuItem mostrarSopaItem = new JMenuItem("Mostrar Sopa de Letras");
        mostrarSopaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarSopaDesdeArchivo(panel);
            }
        });

        JMenuItem salirItem = new JMenuItem("Salir");
        salirItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarMensajeDespedida();
                System.exit(0);
            }
        });

        menu.add(mostrarSopaItem);
        menu.addSeparator(); 
        menu.add(salirItem);

        menuBar.add(menu);

        return menuBar;
    }

    private static void mostrarSopaDesdeArchivo(JPanelSopa panel) {
        String nombreArchivo = JOptionPane.showInputDialog(null, "Ingresa el nombre del archivo:");
        if (nombreArchivo != null && !nombreArchivo.isEmpty()) {
            panel.mostrarSopa(nombreArchivo);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo");
        }
    }

    private static void mostrarMensajeDespedida() {
        JOptionPane.showMessageDialog(null, "Vuelve pronto!");
    }
}
