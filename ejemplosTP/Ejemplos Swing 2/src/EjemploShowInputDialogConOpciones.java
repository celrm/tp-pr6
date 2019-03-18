//autor: Vicky
// Vamos a mostrar una ventana con 3 opciones
// para que el usuario elija una.

//Utilizamos este constructor para ello:
//showInputDialog(Component parentComponent, Object message, 
//String title, int messageType, Icon icon, Object[] selectionValues, 
//Object initialSelectionValue)
// Devuelve Object por lo que hay que hacer un casting

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class EjemploShowInputDialogConOpciones extends JFrame {
	public EjemploShowInputDialogConOpciones() {
		super("Cuadro de diálogo para seleccionar un dato:");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnInputDialog = new JButton("Mostrar Input Dialog");

		btnInputDialog.setIcon(new ImageIcon(this.getClass().getResource(
				"OK-icon.png")));
		btnInputDialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				String[] opciones = new String[3];
				for (int i = 0; i < opciones.length; i++) {
					opciones[i] = "texto de la opción: " + i;
				}
				// en "res" recogemos el String que metió el usuario
				// si no introduce ningún valor, res se queda cargado a null
				String res = (String) JOptionPane.showInputDialog(
						EjemploShowInputDialogConOpciones.this,
						"Selecciona una opción", "Título de mi ventana",
						JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
				System.out.println("El usuario metió: " + res);
			}
		});

		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnInputDialog);
		this.getContentPane().add(pnlSouth);
		this.setSize(800,200);
		this.setVisible(true);

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EjemploShowInputDialogConOpciones();

			}
		});
	}
}
