package concesionarioCoches;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import utiles.Menu;
import utiles.Teclado;
import concesionarioCoches.Colores;
import concesionarioCoches.Modelo;

/**
 * Queremos modelar un concesionario de coches en Java. Nos limitaremos a las
 * siguientes opciones: Añadir un coche (se pedirá matricula, color y modelo),
 * Eliminar un coche (por matrícula), mostrar un coche (por matrícula), mostrar
 * coches (todo el concesionario)
 * 
 * @author MaríaLourdes
 * 
 */
public class TestConcesionario extends Concesionario implements Serializable {
	static Menu menu = new Menu("Concesionario de coches", new String[] {
			"Alta Coche", "Baja Coche", "Mostrar Coche",
			"Mostrar concesionario", "Contar coches del concesionario",
			"Mostrar coches de un color", "Ficheros", "Salir" });
	private static Menu menuColores = new Menu("Colores de los coches",
			Colores.generarOpcionesMenu());
	private static Menu menuModelos = new Menu("Modelos de los coches",
			Modelo.generarOpcionesMenu());
	private static Menu menuFicheros = new Menu("Ficheros", new String[] {
			"Nuevo", "Abrir", "GuardarComo", "Guardar", "Salir" });
	static Concesionario concesionario = new Concesionario();
	private static boolean modificado;
	private static File selectedFile;

	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		do {
			switch (menu.gestionar()) {
			case 1:// "Añadir Coche
				annadirCoche();
				break;
			case 2:// Eliminar Coche
				eliminarCoche();
				break;
			case 3:// Obtener Coche
				getCoche();
				break;
			case 4:// Mostrar lista
				System.out.println(concesionario);
				break;
			case 5:// Contar coches
				System.out.println("Número de coches en el concesionario: "
						+ concesionario.size());
				break;
			case 6:// Mostrar coches de un color
				System.out.println(concesionario.getCochesColor(pedirColor()));
				break;
			case 7:
				gestionarFicheros();
				break;
			default:// Salir
				System.out.println("Adios");
				return;
			}
		} while (true);
	}

	private static void getCoche() {
		Coche coche = concesionario.get(Teclado
				.leerCadena("Introduce la matrícula"));
		if (coche == null)
			System.out.println("No existe el coche en el concesionario.");
		else
			System.out.println(coche);
	}

	private static void eliminarCoche() {
		if (concesionario
				.eliminar(Teclado.leerCadena("Introduce la matrícula"))) {
			System.out.println("Coche eliminado");
			setModificado(true);
		} else
			System.out.println("No se ha podido eliminar");
	}

	private static void annadirCoche() {
		if (concesionario.annadir(Teclado.leerCadena("Introduce la matrícula"),
				pedirColor(), pedirModelo())) {
			System.out.println("Coche añadido con éxito");
			setModificado(true);
		} else
			System.out.println("No se ha podido añadir");
	}

	private static void setModificado(boolean b) {
		modificado = b;

	}

	private static Modelo pedirModelo() {
		int opcion = menuModelos.gestionar();
		Modelo[] arrModelos = Modelo.getValues();
		if (opcion == arrModelos.length + 1)
			return null;
		return arrModelos[opcion - 1];
	}

	private static Colores pedirColor() {
		int opcion = menuColores.gestionar();
		Colores[] arrColores = Colores.getValues();
		if (opcion == arrColores.length + 1)
			return null;
		return arrColores[opcion - 1];
	}

	static void gestionarFicheros() throws FileNotFoundException, IOException,
			ClassNotFoundException {
		switch (menuFicheros.gestionar()) {
		case 1:
			nuevo();
			break;
		case 2:
			abrir();
			break;
		case 3:
			guardarComo();
			break;
		case 4:
			guardar();
			break;
		default:
			System.out.println("Adios");
			return;
		}
	}

	private static void abrir() throws FileNotFoundException,
			ClassNotFoundException, IOException {
		try {
			File fichero = new File(
					Teclado.leerCadena("Dame el nombre del archivo: "));
			fichero = Fichero.annadirExtension(fichero);
			concesionario = (Concesionario) Fichero.leer(fichero);
		} catch (ClassNotFoundException e) {
			System.out.println("Fichero con información distinta.");
		} catch (IOException e) {
			System.out.println("No se puede abrir el fichero.");
		}
	}

	   private static void guardarComo() {
	        try {
	            File file = new File(
	                    Teclado.leerCadena("Introduce el nombre del fichero a guardar: "));
	            file = Fichero.annadirExtension(file);
	            if (Fichero.confirmarSiExiste(file)) {
	                char caracter = Teclado
	                        .leerCaracter("El fichero ya existe. ¿Desea sobreescribirlo? (s/n)");
	                switch (caracter) {
	                case 'n':
	                case 'N':
	                    return;
	                }
	            }
	            Fichero.guardar(file, concesionario);
	            setModificado(false);
	            setSelectedFile(file);
	        } catch (IOException e) {
	            System.out.println("El sistema no puede guardar el fichero.");
	        }
	    }

	   private static void guardar() {
	        if (getSelectedFile() == null)
	            guardarComo();
	        else {
	            try {
	                Fichero.guardar(getSelectedFile(), concesionario);
	                setModificado(false);
	            } catch (IOException e) {
	                System.out.println("El sistema no puede guardar el fichero.");
	            }
	        }
	    }


	private static void nuevo() throws FileNotFoundException, IOException {
		if (getModificado() == true)
			guardarSiConfirmacion();
		setModificado(false);
		concesionario = new Concesionario();
		setSelectedFile(null);
	}

	private static boolean guardarSiConfirmacion()
			throws FileNotFoundException, IOException {
		if (isModificado() == true) {
			char respuesta = Teclado.leerCaracter("Desea guardarlo? s/n : ");
			switch (respuesta) {
			case 's':
			case 'S':
				guardar();
				return true;
			case 'n':
			case 'N':
				return false;
			}
		}
		return false;
	}

	private static boolean isModificado() {
		return modificado;
	}

	private static boolean getModificado() {

		return modificado;
	}

	private static File getSelectedFile() {
		return selectedFile;
	}

	private static void setSelectedFile(File selectedFile) {
		TestConcesionario.selectedFile = selectedFile;
	}
}
