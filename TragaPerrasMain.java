/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pablo.com.app.mvc.view;

/**
 *
 * @author pdiaz
 */

import pablo.com.app.mvc.view.SlotMachineController;

/**
 * Clase principal que inicia el juego de tragamonedas.
 * Se encarga de inicializar la vista, solicitar el saldo inicial al usuario,
 * crear el modelo con el saldo ingresado y establecer el controlador para gestionar el juego.
 */
public class TragaPerrasMain {
    public static void main(String[] args) {
        // Crear la vista de la máquina tragamonedas
        SlotMachineView view = new SlotMachineView();
        
        // Solicitar al usuario que ingrese un saldo inicial
        int initialBalance = view.promptInitialBalance(); 
        
        // Crear el modelo con el saldo inicial ingresado por el usuario
        SlotMachineModel model = new SlotMachineModel(initialBalance);
        
        // Crear el controlador y conectar el modelo con la vista
        new SlotMachineController(model, view);
    }
}
