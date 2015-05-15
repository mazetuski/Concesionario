package concesionarioCoches;

//import pgn.examenMarzo.utiles.Menu;

/**
 * Se limitar�n los colores a tres: plata, rojo y azul. Para solicitar el color
 * al dar de alta al coche podr� implementarse un m�todo pedirColor que mediante
 * la gesti�n de un men�, devolver� el color indicado
 * 
 * @author Mar�aLourdes
 * 
 */
public enum Colores {
	PLATA, ROJO, AZUL;

	private static final Colores[] VALUES = Colores.values();

	public static String[] generarOpcionesMenu() {
		String[] opcionesMenu = new String[getValues().length + 1];
		int i = 0;
		for (Colores color : getValues()) {
			opcionesMenu[i++] = color.name();
		}
		opcionesMenu[i] = "Salir";
		return opcionesMenu;
	}

	public static Colores[] getValues() {
		return VALUES;
	}

}
