
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EjemploJFileChooser1 extends JFrame implements ActionListener {
	JFileChooser fc;

	public EjemploJFileChooser1() {
		super("Cuadro de diálogo: selección de archivos");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 150);
		this.getContentPane().setLayout(new FlowLayout());

		JButton btnOpenFile = new JButton("Abrir archivo");
		btnOpenFile.setActionCommand("abrir");
		btnOpenFile.setIcon(new ImageIcon("icons/open.png"));
		btnOpenFile.addActionListener(this);
		this.getContentPane().add(btnOpenFile);

		JButton btnSaveFile = new JButton("Guardar archivo");
		btnSaveFile.setActionCommand("guardar");
		btnSaveFile.setIcon(new ImageIcon("icons/save.png"));
		btnSaveFile.addActionListener(this);
		this.getContentPane().add(btnSaveFile);

		fc = new JFileChooser();
		//fc.setMultiSelectionEnabled(true); //si se deseara
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("abrir")) {
			int ret = fc.showOpenDialog(this);
			//según salga yo de esta ventana, "ret" tomará un valor u otro
			if (ret == JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(
						this,
						"Se ha seleccionado abrir el archivo: "
								+ fc.getSelectedFile()); //me da el nombre del 
				                                         //archivo seleccionado    
			} else {
				//si cierro la ventana o doy al botón cancelar
				JOptionPane.showMessageDialog(this,
						"Se ha pulsado cancelar o ha ocurrido un error.");
			}
		} else if (e.getActionCommand().equals("guardar")) {
			int ret = fc.showSaveDialog(this);
			if (ret == JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(
						this,
						"Se ha seleccionado guardar el archivo: "
								+ fc.getSelectedFile());
			} else {
				//si cierro la ventana o doy al botón cancelar
				JOptionPane.showMessageDialog(this,
						"Se ha pulsado cancelar o ha ocurrido un error.");
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				 new EjemploJFileChooser1();
				
			}
		});
	}
}

