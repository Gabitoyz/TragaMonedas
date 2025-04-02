/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablo.com.app.mvc.view;
/**
 *
 * @author pdiaz
 */

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Clase SlotMachineView
 * Representa la interfaz gráfica de la máquina tragamonedas.
 * Se encarga de mostrar los rodillos, manejar la interacción con el usuario y actualizar el estado visual del juego.
 */
public class SlotMachineView extends JFrame {
    private JLabel reel1Label, reel2Label, reel3Label;
    private JButton spinButton;
    private JLabel resultLabel, balanceLabel;

    /**
     * Constructor de la vista de la máquina tragamonedas.
     * Inicializa la interfaz gráfica, crea los componentes y los organiza en la ventana.
     */
    public SlotMachineView() {
        setTitle("Juego de Tragamonedas"); // Título de la ventana
        setLayout(new BorderLayout()); // Configuración del layout principal

        // Panel de rodillos
        JPanel reelsPanel = new JPanel(new GridLayout(1, 3));
        reel1Label = new JLabel("🍒", SwingConstants.CENTER);
        reel2Label = new JLabel("🍋", SwingConstants.CENTER);
        reel3Label = new JLabel("🔔", SwingConstants.CENTER);

        // Establecer el tamaño de la fuente para los símbolos
        reel1Label.setFont(new Font("Serif", Font.PLAIN, 48));
        reel2Label.setFont(new Font("Serif", Font.PLAIN, 48));
        reel3Label.setFont(new Font("Serif", Font.PLAIN, 48));

        // Asignar colores a cada símbolo
        reel1Label.setForeground(Color.RED); // Cereza 🍒
        reel2Label.setForeground(Color.YELLOW); // Limón 🍋
        reel3Label.setForeground(Color.GREEN); // Campana 🔔

        // Agregar los símbolos al panel de rodillos
        reelsPanel.add(reel1Label);
        reelsPanel.add(reel2Label);
        reelsPanel.add(reel3Label);

        add(reelsPanel, BorderLayout.CENTER); // Agregar el panel a la ventana

        // Botón para girar los rodillos
        spinButton = new JButton("Girar");
        add(spinButton, BorderLayout.SOUTH); // Posicionar el botón en la parte inferior

        // Etiquetas para mostrar el resultado y el saldo del jugador
        resultLabel = new JLabel("¡Haz tu apuesta!", SwingConstants.CENTER);
        balanceLabel = new JLabel("Saldo: $0", SwingConstants.CENTER); // Saldo inicial predeterminado
        add(resultLabel, BorderLayout.NORTH);
        add(balanceLabel, BorderLayout.EAST);

        // Configuración de la ventana
        setSize(500, 300); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierre seguro de la aplicación
        setVisible(true); // Hacer visible la ventana
    }

    /**
     * Muestra un cuadro de diálogo para que el usuario ingrese el saldo inicial.
     * @return El saldo ingresado por el usuario.
     */
    public int promptInitialBalance() {
        String input = JOptionPane.showInputDialog(this, "Introduce tu saldo inicial:", "Saldo Inicial", JOptionPane.PLAIN_MESSAGE);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return promptInitialBalance(); // Volver a solicitar el saldo en caso de error
        }
    }

    /**
     * Muestra un cuadro de diálogo cuando el saldo es insuficiente, permitiendo al usuario agregar más dinero.
     * @return El nuevo saldo ingresado por el usuario.
     */
    public int promptNewBalance() {
        String input = JOptionPane.showInputDialog(this, "Saldo insuficiente. Introduce un nuevo saldo:", "Nuevo Saldo", JOptionPane.PLAIN_MESSAGE);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return promptNewBalance(); // Volver a solicitar el saldo en caso de error
        }
    }

    /**
     * Devuelve el botón de girar, permitiendo al controlador agregar un ActionListener.
     * @return El botón de girar.
     */
    public JButton getSpinButton() {
        return spinButton;
    }

    /**
     * Actualiza los rodillos con nuevos símbolos después de un giro.
     * @param symbols Lista de tres símbolos a mostrar en los rodillos.
     */
    public void updateReels(List<String> symbols) {
        reel1Label.setText(symbols.get(0));
        reel2Label.setText(symbols.get(1));
        reel3Label.setText(symbols.get(2));

        // Reasignar colores a cada símbolo después de actualizar los rodillos
        reel1Label.setForeground(Color.RED); // Cereza 🍒
        reel2Label.setForeground(Color.YELLOW); // Limón 🍋
        reel3Label.setForeground(Color.GREEN); // Campana 🔔
    }

    /**
     * Actualiza el resultado del giro y el saldo del jugador.
     * @param result Mensaje con el resultado del giro.
     * @param balance Saldo actual del jugador.
     */
    public void updateResult(String result, int balance) {
        resultLabel.setText(result);
        balanceLabel.setText("Saldo: $" + balance);
    }
}
