package utiles;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import java.awt.Color;

import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import concesionarioCoches.Coche;
import concesionarioCoches.Colores;
import concesionarioCoches.Concesionario;
import concesionarioCoches.Marca;
import concesionarioCoches.Modelo;

/**
 * Clase A&nacute;adir.
 * 
 * @author Miguel &Aacute;ngel Zamora Blanco
 *
 */
public class Annadir extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField matricula;
	private Coche coche;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private Colores colores;
	private String matri;
	private Modelo modelo;
	private Concesionario concesionario;
	private JRadioButton rojo;
	private JRadioButton plata;

	/**
	 * Ejecuta la aplicaci&oacute;n.
	 */
	public static void main(String[] args) {
		try {
			Annadir dialog = new Annadir();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el di&aacute;logo.
	 */
	public Annadir() {
		setModal(true);
		setBounds(100, 100, 346, 296);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			matricula = new JTextField();
			matricula.setBounds(68, 11, 139, 20);
			contentPanel.add(matricula);
			matricula.setColumns(10);
			matri = matricula.getText();
		}
		{
			JLabel lblMatrcula = new JLabel("Matr\u00EDcula");
			lblMatrcula.setBounds(10, 14, 66, 14);
			contentPanel.add(lblMatrcula);
		}

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null), "Color", TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(0, 0, 0)));
		panel.setBounds(10, 144, 215, 45);
		contentPanel.add(panel);
		panel.setLayout(null);

		JRadioButton azul = new JRadioButton("Azul");
		azul.setForeground(Color.BLUE);
		azul.setSelected(true);
		azul.setBounds(6, 16, 66, 23);
		panel.add(azul);
		buttonGroup.add(azul);
		

		plata = new JRadioButton("Plata");
		plata.setForeground(Color.GRAY);
		plata.setBounds(73, 16, 66, 23);
		panel.add(plata);
		buttonGroup.add(plata);
	
		rojo = new JRadioButton("Rojo");
		rojo.setForeground(Color.RED);
		rojo.setBounds(143, 16, 66, 23);
		panel.add(rojo);
		buttonGroup.add(rojo);
	
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(10, 101, 66, 14);
		contentPanel.add(lblModelo);

		JComboBox comboBoxModelo = new JComboBox();
		JComboBox comboBoxMarca = new JComboBox();
		comboBoxMarca.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBoxModelo.setModel(new DefaultComboBoxModel(
						getModelo(comboBoxMarca)));
			}
		});
		comboBoxMarca.setBounds(68, 51, 139, 22);
		contentPanel.add(comboBoxMarca);
		comboBoxMarca.setModel(new DefaultComboBoxModel(Marca.values()));
		comboBoxModelo.setModel(new DefaultComboBoxModel(getModelo(comboBoxMarca)));
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(10, 55, 46, 14);
		contentPanel.add(lblMarca);

		comboBoxModelo.setBounds(68, 97, 139, 22);
		contentPanel.add(comboBoxModelo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("A\u00F1adir");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							if (Generar.concesionario.annadir(matricula.getText(), getColor(),
									(Modelo) comboBoxModelo.getSelectedItem())) {
								JOptionPane.showMessageDialog(contentPanel,
										"Coche almacenado con éxito");
							} else
								JOptionPane.showMessageDialog(contentPanel,
										"El coche no ha podido almacenarse.",
										"Error", JOptionPane.ERROR_MESSAGE);
						} catch (HeadlessException e1) {
							e1.printStackTrace();
						}
					}
						
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	/**
	 * M&eacute;todo getModelo que obtiene el modelo del coche.
	 * @param comboBoxMarca comboBox de la marca el coche.
	 * @return Devuelve un array.
	 */
	private Object[] getModelo(JComboBox<Marca> comboBoxMarca) {
		Marca marca = (Marca) comboBoxMarca.getSelectedItem();
		ArrayList<Modelo> modelos = new ArrayList<Modelo>();
		for (Modelo m : Modelo.values()) {
			if (m.getMarca() == marca) {
				modelos.add(m);
			}
		}
		return modelos.toArray();
	}
	/**
	 * M&eacute;todo Colores
	 * @return Devuelve el color del coche.
	 */
	private Colores getColor(){
		if (rojo.isSelected()==true){
			return Colores.ROJO;
		}else if (plata.isSelected() == true) {
			return colores = Colores.PLATA;
		}
		return Colores.AZUL;
	}
}
