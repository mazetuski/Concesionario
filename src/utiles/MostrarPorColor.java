package utiles;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import java.awt.Color;

import javax.swing.JTextArea;

import concesionarioCoches.Colores;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;
/**
 * Clase MostrarPorColor
 * 
 * @author  Miguel &Aacute;ngel Zamora Blanco
 * @version 1.0
 */
public class MostrarPorColor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JRadioButton azul;
	private JRadioButton plata;
	private JRadioButton rojo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextArea textArea;
	private Object objt;

	/**
	 * Ejecuta la aplicaci&oacute;n
	 */
	public static void main(String[] args) {
		try {
			MostrarPorColor dialog = new MostrarPorColor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el di&aacute;logo.
	 */
	public MostrarPorColor() {
		setModal(true);
		setBounds(100, 100, 369, 298);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "B\u00FAsqueda por Color", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(79, 11, 193, 54);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				azul = new JRadioButton("Azul");
				azul.setSelected(true);
				azul.setForeground(Color.BLUE);
				azul.setBounds(6, 16, 59, 32);
				panel.add(azul);
				buttonGroup.add(azul);
			}
			{
				plata = new JRadioButton("Plata");
				plata.setForeground(Color.GRAY);
				plata.setBounds(67, 16, 59, 32);
				panel.add(plata);
				buttonGroup.add(plata);
			}
			{
				rojo = new JRadioButton("Rojo");
				rojo.setForeground(Color.RED);
				rojo.setBounds(128, 16, 59, 32);
				panel.add(rojo);
				buttonGroup.add(rojo);
			}
		}
		
		textArea = new JTextArea();
		textArea.setBounds(10, 87, 341, 151);
		contentPanel.add(textArea);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Mostrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textArea.setText(String.valueOf(CochePorColor()));
					}
				});
				okButton.setActionCommand("OK");
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
	 * M&eacute;todo CochePorColor que devuelve un arraylist.
	 * @return objt Devuelve un arraylist.
	 */
	private  Object CochePorColor(){
		if(azul.isSelected()){
			objt=Generar.concesionario.getCochesColor(Colores.AZUL);
			return objt;
		}else if(plata.isSelected()){
			objt=Generar.concesionario.getCochesColor(Colores.PLATA);
			return objt;
		}else{
			objt=Generar.concesionario.getCochesColor(Colores.ROJO);
			return objt;
		}
	}
}
