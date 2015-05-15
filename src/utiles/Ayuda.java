package utiles;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ayuda extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Ayuda dialog = new Ayuda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Ayuda() {
		//Esto es una modificacion..
		setModal(true);
		setBounds(100, 100, 379, 570);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextPane txtpnhola = new JTextPane();
		txtpnhola.setEditable(false);
		txtpnhola.setContentType("text/html");
		txtpnhola.setText("<h2>Ayuda para el concesionario</h2>\r\n<h3><b>Este programa es un concesionario de coche que tiene distintas utilidades:</b></h3>\r\n<ol>\r\n<li>Men\u00FA Ficheros:</li>\r\n<ul>\r\n<li><b>Nuevo: </b>crea un nuevo archivo.</li>\r\n<li><b>Abrir: </b>abre un archivo</li>\r\n<li><b>Guardar: </b>guarda un archivo</li>\r\n<li><b>Guardar Como: </b>guarda un archivo especificando la ruta</li>\r\n<li><b>Salir: </b>se sale del programa</li> \r\n</ul>\r\n<li>Men\u00FA Concesionario:</li>\r\n<ul>\r\n<li><b>A\u00F1adir:</b> a\u00F1ade un coche al concesionario.</li>\r\n<li><b>Eliminar:</b> elimina un coche del concesionario.</li>\r\n<li><b>Mostrar:</b> muestra los coches del concesionario uno por uno.</li>\r\n<li><b>Mostrar Concesionario:</b> muestra el concesionario de coches.</li>\r\n<li><b>Mostrar por color:</b> muestra los coches de un determinado color</li>\r\n</ul>\r\n</ol>\r\n</html>");
		txtpnhola.setBounds(10, 11, 349, 499);
		contentPanel.add(txtpnhola);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
