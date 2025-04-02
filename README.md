# **Programa de Máquina Tragamonedas**

## **Estructura del Proyecto**
El proyecto está dividido en tres clases principales que implementan el patrón MVC. Estas clases son:

- **Modelo:** `SlotMachineModel`
- **Vista:** `SlotMachineView`
- **Controlador:** `SlotMachineController`

## **Clase SlotMachineModel**
`SlotMachineModel` es la clase que representa el modelo del juego de tragamonedas. Se encarga de manejar la lógica central del juego, incluyendo:

- Manejo del saldo del jugador.
- Generación aleatoria de símbolos en los rodillos.
- Cálculo de premios según las combinaciones obtenidas.

## **Clase SlotMachineView**
`SlotMachineView` es la clase que define la interfaz gráfica del juego de tragamonedas. Se encarga de:

- Mostrar los rodillos con los símbolos.
- Manejar la interacción con el usuario.
- Actualizar la interfaz según el resultado del juego.

## **Clase SlotMachineController**
`SlotMachineController` es la clase encargada de manejar la lógica del juego y la comunicación entre la vista (`SlotMachineView`) y el modelo (`SlotMachineModel`). Implementa concurrencia para animar los rodillos mientras se calcula el resultado del giro.

## **Clase TragaPerrasMain**
`TragaPerrasMain` es la clase principal del programa. Se encarga de:

- Inicializar la interfaz gráfica del usuario.
- Solicitar el saldo inicial al jugador.
- Conectar el modelo con el controlador para iniciar el juego.

## **Cómo jugar**
1. Inicia el programa.
2. Ingresa un saldo inicial.
3. Haz clic en el botón **"Girar"** para empezar a jugar.
4. Si los tres símbolos coinciden, recibirás un mensaje con el monto ganado.
5. Si no tienes saldo suficiente, podrás agregar más.
6. ¡Sigue jugando hasta que quieras detenerte!

---

**Autor:** pdiaz

