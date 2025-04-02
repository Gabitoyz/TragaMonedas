/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablo.com.app.mvc.view;

/**
 *
 * @author pdiaz
 */

/**
 * Clase SlotMachineModel que representa el modelo de la máquina tragamonedas.
 * Se encarga de gestionar el saldo del jugador, generar símbolos aleatorios y
 * calcular las recompensas en función de los resultados.
 * 
 */
public class SlotMachineModel {
    
    /** Saldo del jugador. */
    private int playerBalance;
    
    /** Lista de símbolos posibles en los rodillos. */
    private String[] symbols = {"🍒", "🍋", "🔔", "🍉", "⭐", "7️⃣"};

    /**
     * Constructor de la clase SlotMachineModel.
     * Inicializa el saldo del jugador.
     * 
     * @param initialBalance Saldo inicial del jugador.
     */
    public SlotMachineModel(int initialBalance) {
        this.playerBalance = initialBalance;
    }

    /**
     * Método que simula el giro de un rodillo.
     * Selecciona un símbolo aleatorio de la lista de símbolos disponibles.
     * 
     * @return Un símbolo aleatorio de la máquina tragamonedas.
     */
    public String spinReel() {
        return symbols[(int) (Math.random() * symbols.length)];
    }

    /**
     * Calcula la recompensa en función de los símbolos obtenidos.
     * Si los tres símbolos son iguales, se multiplica la apuesta según el tipo de símbolo.
     * 
     * @param symbol1 Primer símbolo.
     * @param symbol2 Segundo símbolo.
     * @param symbol3 Tercer símbolo.
     * @param bet Cantidad apostada.
     * @return Recompensa calculada en función de los símbolos obtenidos.
     */
    public int calculateReward(String symbol1, String symbol2, String symbol3, int bet) {
        if (symbol1.equals(symbol2) && symbol2.equals(symbol3)) {
            switch (symbol1) {
                case "🍒": return bet * 2;
                case "🍋": return bet * 3;
                case "🔔": return bet * 5;
                case "🍉": return bet * 6;
                case "⭐": return bet * 10;
                case "7️⃣": return bet * 20;
                default: return 0;
            }
        }
        return 0;
    }

    /**
     * Actualiza el saldo del jugador restando la apuesta y sumando la recompensa obtenida.
     * 
     * @param reward Cantidad ganada en el giro.
     * @param bet Cantidad apostada.
     */
    public void updateBalance(int reward, int bet) {
        playerBalance += (reward - bet);
    }

    /**
     * Agrega una cantidad de saldo al jugador.
     * 
     * @param amount Cantidad a agregar al saldo.
     */
    public void addBalance(int amount) {
        playerBalance += amount;
    }

    /**
     * Obtiene el saldo actual del jugador.
     * 
     * @return Saldo del jugador.
     */
    public int getPlayerBalance() {
        return playerBalance;
    }
}
