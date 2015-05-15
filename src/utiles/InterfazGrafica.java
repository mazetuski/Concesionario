/**
 * Paquete utiles
 */
package utiles;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.KeyStroke;

import concesionarioCoches.Concesionario;
import concesionarioCoches.Fichero;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

/**
 * Clase InterfazGr&aacute;fica.
 * 
 * @author Miguel &Aacute;ngel Zamora Blanco
 */
public class InterfazGrafica extends JFrame {
	private static InterfazGrafica frame;
	private static JFrame nuevo = new JFrame();
	private JPanel contentPane;
	private Annadir annadirCoche;
	private Eliminar eliminarCoche;
	private FileNameExtensionFilter filtro = new FileNameExtensionFilter(
			"Archivos .obj", "obj");
	private File file;
	private static File selectedFile;

	public static File getSelectedFile() {
		return selectedFile;
	}

	public static void setSelectedFile(File selectedFile) {
		InterfazGrafica.selectedFile = selectedFile;
	}

	/**
	 * Ejecuta la aplicaci&oacute;n
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			// private InterfazGrafica frame;

			public void run() {
				try {
					frame = new InterfazGrafica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea el frame.
	 */
	public InterfazGrafica() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		/**
		 * Men&uacute; principal.
		 */
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		/**
		 * Men&uacute; de ficheros.
		 */
		JMenu mnFicheros = new JMenu("Ficheros");
		mnFicheros.setMnemonic('f');
		menuBar.add(mnFicheros);

		/**
		 * Submen&uacute; Abrir del men&uacute; Ficheros
		 */
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser abrir = new JFileChooser();
				abrir.setFileFilter(filtro);

				int opcion = abrir.showOpenDialog(frame);
				if (opcion == JFileChooser.APPROVE_OPTION) {
					file = abrir.getSelectedFile();
					try {
						Generar.concesionario = (Concesionario) Fichero
								.leer(file);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					Generar.concesionario.setModificado(false);
					setSelectedFile(file);
				}
			}
		});
		/**
		 * Submen&uacute; Nuevo del men&uacute; Ficheros.
		 */
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Generar.concesionario.isModificado()) {

					int opcion = JOptionPane.showConfirmDialog(nuevo,
							"Desea guardar el concesionario: ");
					if (opcion == 0) {
						JFileChooser guardar = new JFileChooser();
						guardar.setFileFilter(filtro);
						int opcionGuardar = guardar.showSaveDialog(frame);
						if (opcionGuardar == JFileChooser.APPROVE_OPTION) {
							file = guardar.getSelectedFile();
							try {
								Fichero.guardar(file, Generar.concesionario);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						Generar.concesionario = new Concesionario();
						Generar.concesionario.setModificado(false);
						setSelectedFile(null);
					} else if (opcion == 2)
						nuevo.setEnabled(false);
					else
						Generar.concesionario = new Concesionario();
					Generar.concesionario.setModificado(false);
					setSelectedFile(null);
				} else
					Generar.concesionario = new Concesionario();
				Generar.concesionario.setModificado(false);
				setSelectedFile(null);
			}
		});
		mnFicheros.add(mntmNuevo);
		mnFicheros.add(mntmAbrir);

		/**
		 * Submen&uacute; Guardar Como del men&uacute; Ficheros.
		 */
		JMenuItem mntmGuardar = new JMenuItem("Guardar Como");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser guardar = new JFileChooser();
				int opcionGuardar = guardar.showSaveDialog(frame);
				if (opcionGuardar == JFileChooser.APPROVE_OPTION) {
					file = guardar.getSelectedFile();
					try {
						Fichero.guardar(file, Generar.concesionario);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					Generar.concesionario.setModificado(false);
					setSelectedFile(file);
				}
			}
		});
		/**
		 * Submen&uacute; Guardar del men&uacute; Ficheros.
		 */
		JMenuItem mntmGuardar_1 = new JMenuItem("Guardar");
		mntmGuardar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getSelectedFile() == null) {
					JFileChooser guardar = new JFileChooser();
					int opcionGuardar = guardar.showSaveDialog(frame);
					if (opcionGuardar == JFileChooser.APPROVE_OPTION) {
						file = guardar.getSelectedFile();
						try {
							Fichero.guardar(file, Generar.concesionario);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Generar.concesionario.setModificado(false);
						setSelectedFile(file);
					}
				} else
					try {
						Fichero.guardar(file, Generar.concesionario);

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				Generar.concesionario.setModificado(false);
			}
		});
		mntmGuardar_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		mnFicheros.add(mntmGuardar_1);
		mnFicheros.add(mntmGuardar);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Generar.concesionario.isModificado()) {

					int opcion = JOptionPane.showConfirmDialog(nuevo,
							"Desea guardar el concesionario: ");
					if (opcion == 0) {
						JFileChooser guardar = new JFileChooser();
						guardar.setFileFilter(filtro);
						int opcionGuardar = guardar.showSaveDialog(frame);
						if (opcionGuardar == JFileChooser.APPROVE_OPTION) {
							file = guardar.getSelectedFile();
							try {
								Fichero.guardar(file, Generar.concesionario);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}

						System.exit(0);
					} else if (opcion == 2)
						nuevo.setEnabled(false);
					else {

						System.exit(0);
					}
				}
			}
		});
		mnFicheros.add(mntmSalir);

		/**
		 * Men&uacute; Concesionario.
		 */
		JMenu mnConcesionario = new JMenu("Concesionario");
		mnConcesionario.setMnemonic('c');
		menuBar.add(mnConcesionario);

		/**
		 * Submen&uacute; a&nacute;adirCoche del men&uacute; Concesionario.
		 */
		JMenuItem mntmAadirCoche = new JMenuItem("A\u00F1adir Coche");
		mntmAadirCoche.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.CTRL_MASK));
		mntmAadirCoche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				annadirCoche = new Annadir();
				annadirCoche.setVisible(true);
			}
		});
		mnConcesionario.add(mntmAadirCoche);

		/**
		 * Submen&uacute; EliminarCoche del men&uacute; Concesionario.
		 */
		JMenuItem mntmEliminarCoche = new JMenuItem("Eliminar Coche");
		mntmEliminarCoche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarCoche = new Eliminar();
				eliminarCoche.setVisible(true);
			}
		});
		mnConcesionario.add(mntmEliminarCoche);

		/**
		 * Submen&uacute; MostrarCoche del men&uacute; Concesionario.
		 */
		JMenuItem mntmMostrarCoche = new JMenuItem("Mostrar coche");
		mntmMostrarCoche.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
				InputEvent.CTRL_MASK));
		mntmMostrarCoche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mostrar mostrar = new Mostrar();
				mostrar.setVisible(true);
			}
		});
		mnConcesionario.add(mntmMostrarCoche);

		/**
		 * Submen&uacute; MostrarConcesionario del men&uacute; Concesionario.
		 */
		JMenuItem mntmMostrarConcesionario = new JMenuItem(
				"Mostrar concesionario");
		mntmMostrarConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarConcesionario mostrarC = new MostrarConcesionario();
				mostrarC.setVisible(true);
			}
		});
		mnConcesionario.add(mntmMostrarConcesionario);
		/**
		 * Submen&uacute; MostrarN&uacute;meroCoches del men&uacute;
		 * Concesionario.
		 */
		JMenuItem mntmMostrarNumeroCoches = new JMenuItem(
				"Mostrar n\u00FAmero coches");
		mntmMostrarNumeroCoches.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
						mntmMostrarNumeroCoches,
						"El número de coches es: "
								+ Generar.concesionario.size());
				;
			}
		});
		mnConcesionario.add(mntmMostrarNumeroCoches);
		/**
		 * Submen&uacute; MostrarCochesPorColor del men&uacute; Concesionario.
		 */
		JMenuItem mntmMostrarCochesPor = new JMenuItem(
				"Mostrar coches por color");
		mntmMostrarCochesPor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarPorColor mostrarPorColor = new MostrarPorColor();
				mostrarPorColor.setVisible(true);
			}
		});
		mnConcesionario.add(mntmMostrarCochesPor);

		/**
		 * Men&uacute; Ayuda.
		 */
		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setMnemonic('a');
		menuBar.add(mnAyuda);

		JMenuItem mntmVerAyuda = new JMenuItem("Ver ayuda");
		mntmVerAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ayuda ayuda = new Ayuda();
				ayuda.setVisible(true);
			}
		});
		mnAyuda.add(mntmVerAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AcercaDe acercaDe= new AcercaDe();
				acercaDe.setVisible(true);
			}
		});
		mnAyuda.add(mntmAcercaDe);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/imagen.PNG"))
				.getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(107, 23, 225, 177);
		contentPane.add(label);
	}
}
