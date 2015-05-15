package concesionarioCoches;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * El nombre del concesionario es "IES Gran Capitán".
 * 
 * No podrá añadirse un coche inválido al almacén del concesinario
 * 
 * @author Miguel &Aacute;ngel Zamora Blanco
 * 
 */
public class Concesionario implements Serializable {
	/**
	 * colección de coches. No puede tener null
	 */
	private ArrayList<Coche> almacen = new ArrayList<Coche>();
	private final String nombre = "IES Gran Capitán";
	private boolean modificado;

	public boolean isModificado() {
		return modificado;
	}

	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	public boolean annadir(String matricula, Colores color, Modelo modelo) {
		Coche coche = Coche.instanciarCoche(matricula, color, modelo);
		if (coche == null || almacen.contains(coche))
			return false;
		setModificado(true);
		return almacen.add(coche);
	}

	public boolean eliminar(String matricula) {
		setModificado(true);
		return almacen.remove(Coche.instanciarCoche(matricula));
	}

	
	public int size() {
		return almacen.size();
	}

	/**
	 * Devuelve el Coche del almacén indicado por la matrícula
	 * 
	 * @param matricula
	 *            Matrícula a buscar
	 * @return Coche contenido en el almacén. null si no existe
	 */
	Coche get(String matricula) {
		Coche coche = Coche.instanciarCoche(matricula);
		int index = almacen.indexOf(coche);
		if (index != -1) {
			return almacen.get(index);
		}
		return null;
	}
	public Coche get(int index) {
		if(almacen.isEmpty())
			return null;
		if(index < 0 | index > almacen.size()-1)
			return null;
		return almacen.get(index);
	}

	@Override
	public String toString() {
		return "Concesionario " + nombre + "almacen=" + almacen + "";
	}

	public ArrayList<Coche> getCochesColor(Colores color) {
		ArrayList<Coche> arrCochesColor = new ArrayList<Coche>();
		for (Coche coche : almacen) {
			if(coche.getColor()== color)
				arrCochesColor.add(coche);
		}
		return arrCochesColor;
	}

}
