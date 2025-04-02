/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablo.com.app.mvc.view;
/**
 *
 * @author pdiaz
 */
import java.util.ArrayList;
import java.util.List;
import pablo.com.app.mvc.view.SlotMachineModel;
import pablo.com.app.mvc.view.SlotMachineView;

/**
 * Clase controladora del juego de tragamonedas.
 * Se encarga de gestionar la lógica del juego y la comunicación entre la vista y el modelo.
 * Implementa concurrencia para manejar la animación de los rodillos mientras se calcula el resultado.
 */
public class SlotMachineController {
    private SlotMachineModel model; // Modelo que maneja la lógica del juego
    private SlotMachineView view; // Vista que maneja la interfaz gráfica
    private final Object lock = new Object(); // Objeto de sincronización para manejar hilos

    /**
     * Constructor de la clase controlador.
     * @param model Instancia del modelo del juego.
     * @param view Instancia de la vista del juego.
     */
    public SlotMachineController(SlotMachineModel model, SlotMachineView view) {
        this.model = model;
        this.view = view;

        // Agrega un listener al botón para iniciar el juego cuando se presiona
        view.getSpinButton().addActionListener(e -> startGame());
    }

    /**
     * Método para iniciar el juego. Controla la apuesta, la lógica del giro y la actualización de la vista.
     */
    private void startGame() {
        int bet = 10; // Monto fijo de apuesta

        // Hilo que maneja la lógica del juego
        Thread gameLogicThread = new Thread(() -> {
            synchronized (lock) { // Bloque de sincronización para evitar problemas de concurrencia
                // Verifica si el jugador tiene saldo suficiente para apostar
                if (model.getPlayerBalance() < bet) {
                    int newBalance = view.promptNewBalance(); // Solicita un nuevo saldo al usuario
                    model.addBalance(newBalance); // Actualiza el saldo en el modelo
                    view.updateResult("Nuevo saldo agregado.", model.getPlayerBalance()); // Muestra el nuevo saldo
                }

                // Genera tres símbolos aleatorios para la tirada
                String symbol1 = model.spinReel();
                String symbol2 = model.spinReel();
                String symbol3 = model.spinReel();

                // Calcula la recompensa basada en los símbolos obtenidos
                int reward = model.calculateReward(symbol1, symbol2, symbol3, bet);
                model.updateBalance(reward, bet); // Actualiza el saldo del jugador

                // Almacena los símbolos finales en una lista
                List<String> finalSymbols = List.of(symbol1, symbol2, symbol3);

                synchronized (lock) { // Bloque sincronizado para actualizar la interfaz gráfica
                    view.updateReels(finalSymbols); // Muestra los símbolos en la interfaz

                    // Muestra un mensaje dependiendo de si el jugador ganó o perdió
                    if (reward > 0) {
                        view.updateResult("¡Ganaste $" + reward + "!", model.getPlayerBalance());
                    } else {
                        view.updateResult("¡Inténtalo de nuevo!", model.getPlayerBalance());
                    }
                }
            }
        });

        // Hilo que maneja la animación de los rodillos
        Thread uiAnimationThread = new Thread(() -> {
            synchronized (lock) { // Bloque sincronizado para evitar conflictos
                try {
                    // Lista de símbolos para la animación de los rodillos
                    List<String> spinningSymbols = List.of("🍒", "🍋", "🔔", "🍉", "⭐", "7️⃣");
                    for (int i = 0; i < 15; i++) { // Repite la animación 15 veces
                        List<String> currentSymbols = new ArrayList<>();
                        for (int j = 0; j < 3; j++) {
                            int index = (i + j) % spinningSymbols.size(); // Selecciona un símbolo aleatorio
                            currentSymbols.add(spinningSymbols.get(index));
                        }

                        view.updateReels(currentSymbols); // Actualiza la interfaz con los símbolos en movimiento
                        Thread.sleep(50); // Controla la velocidad de la animación
                    }
                } catch (InterruptedException ex) { // Captura errores si el hilo es interrumpido
                    Thread.currentThread().interrupt();
                    System.err.println("Hilo de animación interrumpido: " + ex.getMessage());
                }
            }
        });

        // Inicia los hilos de animación y de lógica del juego
        uiAnimationThread.start();
        gameLogicThread.start();
    }
}
