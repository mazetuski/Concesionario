package utiles;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import java.awt.Color;

import javax.swing.JComboBox;

import java.awt.Button;

import javax.swing.ImageIcon;

import concesionarioCoches.Coche;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase mostrar que muestra coches.
 * 
 * @author Miguel &Aacute;ngel Zamora Blanco
 *
 */
public class Mostrar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton azul;
	private JRadioButton plata;
	private JRadioButton rojo;
	private JComboBox comboBoxMarca;
	private JComboBox comboBoxModelo;
	private int indiceCoche = -1;
	private JButton anterior;
	private JButton siguiente;

	/**
	 * Ejecuta la aplicaci&oacute;n
	 */
	public static void main(String[] args) {
		try {
			Mostrar dialog = new Mostrar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el di&aacute;logo.
	 */
	public Mostrar() {
		setModal(true);
		setTitle("Mostrar Concesionario");
		setBounds(100, 100, 327, 281);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMatricula = new JLabel("Matr\u00EDcula");
			lblMatricula.setBounds(129, 11, 77, 14);
			contentPanel.add(lblMatricula);
		}

		textField = new JTextField();
		textField.setBounds(112, 36, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null), "Color", TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(0, 0, 0)));
		panel.setBounds(36, 67, 247, 45);
		contentPanel.add(panel);
		panel.setLayout(null);

		azul = new JRadioButton("Azul");
		azul.setBounds(6, 16, 77, 23);
		panel.add(azul);
		buttonGroup.add(azul);

		plata = new JRadioButton("Plata");
		plata.setBounds(89, 16, 77, 23);
		panel.add(plata);
		buttonGroup.add(plata);

		rojo = new JRadioButton("Rojo");
		rojo.setBounds(170, 16, 71, 23);
		panel.add(rojo);
		buttonGroup.add(rojo);

		azul.setEnabled(false);
		rojo.setEnabled(false);
		plata.setEnabled(false);

		comboBoxMarca = new JComboBox();
		comboBoxMarca.setBounds(35, 126, 98, 22);
		contentPanel.add(comboBoxMarca);

		comboBoxModelo = new JComboBox();
		comboBoxModelo.setBounds(170, 126, 111, 22);
		contentPanel.add(comboBoxModelo);

		comboBoxMarca.setEnabled(false);
		comboBoxModelo.setEnabled(false);

		anterior = new JButton("");
		anterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anterior();
			}
		});
		anterior.setIcon(new ImageIcon(
				Mostrar.class
						.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		anterior.setBounds(74, 187, 77, 23);
		contentPanel.add(anterior);

		siguiente = new JButton("");
		siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				siguiente();
			}
		});
		siguiente
				.setIcon(new ImageIcon(
						Mostrar.class
								.getResource("/com/sun/javafx/scene/web/skin/Redo_16x16_JFX.png")));
		siguiente.setBounds(161, 187, 77, 23);
		contentPanel.add(siguiente);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		empieza();
	}
	/**
	 * M&eacute;todo mostrarCoche que muestra un coche.
	 * 
	 * @param coche El coche.
	 */
	private void mostrarCoche(Coche coche) {
		textField.setText(coche.getMatricula());
		switch (coche.getColor()) {
		case PLATA:
			plata.setSelected(true);
			break;
		case ROJO:
			rojo.setSelected(true);
			break;
		case AZUL:
			azul.setSelected(true);
			
		}
		comboBoxMarca.addItem(coche.getModelo().getMarca());
		comboBoxMarca.setSelectedItem(coche.getModelo().getMarca());
		comboBoxModelo.addItem(coche.getModelo());
		comboBoxModelo.setSelectedItem(coche.getModelo());
	}
	/**
	 * M&eacute;todo anterior que permite mostrar el coche anterior.
	 */
	private void anterior() {
		mostrarCoche(Generar.concesionario.get(--indiceCoche));
		comprobarBoton();
	}
	/**
	 * M&eacute;todo siguiente que permite mostrar el coche siguiente.
	 */
	private void siguiente() {
		mostrarCoche(Generar.concesionario.get(++indiceCoche));
		comprobarBoton();
	}
	/**
	 * M&eacute;todo comprobarBoton que comprueba si hay un coche anterior y un coche siguiente.
	 */
	private void comprobarBoton() {
		if (Generar.concesionario.get(indiceCoche - 1) == null)
			anterior.setEnabled(false);
		else
			anterior.setEnabled(true);
		if (Generar.concesionario.get(indiceCoche + 1) == null)
			siguiente.setEnabled(false);
		else
			siguiente.setEnabled(true);
	}
	/**
	 * M&eacute;todo empieza que muestra el primer coche.
	 */
	private void empieza() {
		if (Generar.concesionario.size() == 0) {
			return;
		}
		indiceCoche = 0;
		mostrarCoche(Generar.concesionario.get(indiceCoche));
		comprobarBoton();
	}
}
