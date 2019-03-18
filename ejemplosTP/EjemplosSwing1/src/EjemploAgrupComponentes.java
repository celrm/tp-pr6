import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EjemploAgrupComponentes extends JFrame {
	private JLabel lsouth;
	private static final String TXT_LSOUTH = "Lo que has tecleado es: ";

	public EjemploAgrupComponentes() {
		super("Calculadora:");
		this.setSize(450, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());//el de las regiones
		
		JLabel lnorth = new JLabel("Mi calculadora.");
		this.getContentPane().add(lnorth, BorderLayout.NORTH);

		lsouth = new JLabel(TXT_LSOUTH);
		lsouth.setBackground(Color.yellow);
		lsouth.setOpaque(true);
		this.getContentPane().add(lsouth, BorderLayout.SOUTH);

		JPanel pnlCentro = new JPanel();
		pnlCentro.setLayout(new GridLayout(4, 3, 4, 4));
		for (int i = 1; i < 10; i++) {
			JButton btnI = creaBotonI(i);
			pnlCentro.add(btnI);
		}
		pnlCentro.add(new JLabel(""));
		pnlCentro.add(creaBotonI(0));

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lsouth.setText(TXT_LSOUTH);
			}
		});

		pnlCentro.add(btnLimpiar);
		this.getContentPane().add(pnlCentro, BorderLayout.CENTER);
		this.setVisible(true);
	}

	private JButton creaBotonI(int i) {
		String strI = Integer.toString(i);
		JButton btnI = new JButton(strI);
		btnI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lsouth.setText(lsouth.getText() + strI);
			}
		});
		return btnI;
	}

	public static void main(String[] args) {	
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EjemploAgrupComponentes();
			}
		});
	}
}

