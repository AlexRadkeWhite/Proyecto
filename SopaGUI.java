// Interfaz gráfica para mostrar la sopa de letras generada.
package uabc.inc.proyectofinal;

import javax.swing.*;
import java.awt.*;

public class SopaGUI extends JFrame {

    public SopaGUI(Elemento[][] cuadricula) {

        setTitle("Sopa de Letras");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(cuadricula.length + 1, cuadricula[0].length + 1));

        panel.add(new JLabel(""));
        for (int j = 0; j < cuadricula[0].length; j++) {
            panel.add(new JLabel(String.valueOf(j + 1), SwingConstants.CENTER));
        }

        for (int i = 0; i < cuadricula.length; i++) {
            panel.add(new JLabel(String.valueOf(i + 1), SwingConstants.CENTER));
            for (int j = 0; j < cuadricula[i].length; j++) {
                JLabel label = new JLabel(String.valueOf(cuadricula[i][j].obtenerCaracter()), SwingConstants.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(label);
            }
        }

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    public void mostrar() {
        setVisible(true);
    }
}
