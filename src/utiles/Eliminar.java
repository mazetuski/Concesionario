package utiles;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase Eliminar que elimina un coche.
 * 
 * @author  Miguel &Aacute;ngel Zamora Blanco
 * 
 */
public class Eliminar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Ejecuta la aplicaci&oacute;n
	 */
	public static void main(String[] args) {
		try {
			Eliminar dialog = new Eliminar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el di&aacute;logo.
	 */
	public Eliminar() {
		setModal(true);
		setBounds(100, 100, 214, 151);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(83, 11, 67, 14);
		contentPanel.add(lblMatricula);

		textField = new JTextField();
		textField.setBounds(49, 36, 115, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Eliminar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (Generar.concesionario.eliminar(textField.getText())) {
							JOptionPane.showMessageDialog(contentPanel,
									"El coche se ha eliminado correctamente");
						} else
							JOptionPane.showMessageDialog(contentPanel,
									"El coche no se ha podido eliminar",
									"Error", JOptionPane.ERROR_MESSAGE);
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
}
