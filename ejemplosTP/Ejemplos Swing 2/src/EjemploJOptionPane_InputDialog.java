import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class EjemploJOptionPane_InputDialog extends JFrame {
	public EjemploJOptionPane_InputDialog() {
		super("Cuadro de diálogo con entrada de datos:");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnInputDialog = new JButton("Mostrar Input Dialog");

		// Con los siguientes métodos, busca en el proyecto ese fichero 
		//con ese nombre para formar la imagen, sin preocuparnos de poner la ruta
		btnInputDialog.setIcon(new ImageIcon(this.getClass().getResource(
				"OK-icon.png")));
		btnInputDialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// vamos a mostrar una ventana con un campo de texto para que
				// el usuario de la GUI introduzca datos y dos botones: aceptar
				// y cancelar.
				// en "res" recogemos el String que metió el usuario
				String res = JOptionPane.showInputDialog(
						EjemploJOptionPane_InputDialog.this,
						"Introduce un texto");
				System.out.println("El usuario metió: " + res);
			}
		});

		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnInputDialog);
		this.getContentPane().add(pnlSouth);
		this.setSize(700,200);
		this.setVisible(true);

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EjemploJOptionPane_InputDialog();

			}
		});
	}
}
