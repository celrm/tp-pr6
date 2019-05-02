//JFrame con 3 paneles, cada uno al darle al botón iniciar arranca
//una hebra distinta.
//El método SwingUtilities.isEventDispatchThread() me devuelve cierto si 
//estoy en el hilo de Swing, utilizarlo para entender los cambios de hebra

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Ejemplo2 extends JFrame {

	public Ejemplo2() {
		super("Ejemplo de múltiples hilos de ejecución");
		this.getContentPane().setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		MiContador contador1 = new MiContador();
		this.getContentPane().add(contador1);
		MiContador contador2 = new MiContador();
		this.getContentPane().add(contador2);
		MiContador contador3 = new MiContador();
		this.getContentPane().add(contador3);

		this.pack();
		setVisible(true);
	}

	private class MiContador extends JPanel implements Runnable {
		private JLabel lbl;
		private Thread hiloContador = null;

		public MiContador() {

			this.setBorder(BorderFactory.createTitledBorder("Contador"));
			this.setLayout(new BorderLayout());

			final JButton btnStart = new JButton("Iniciar");
			this.add(btnStart, BorderLayout.NORTH);

			lbl = new JLabel("0");
			this.add(lbl, BorderLayout.CENTER);

			final JButton btnStop = new JButton("Parar");
			this.add(btnStop, BorderLayout.SOUTH);

			btnStart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("click boton star: "
							+ SwingUtilities.isEventDispatchThread());
					if (hiloContador == null) {
						btnStart.setEnabled(false);
						hiloContador = new Thread(MiContador.this);
						hiloContador.start();
					}
				}
			});

			btnStop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("click boton parar: "
							+ SwingUtilities.isEventDispatchThread());
					if (hiloContador != null) {
						hiloContador.interrupt();
						hiloContador = null;
					}
					btnStart.setEnabled(true);
				}
			});
		}

		public void run() {
			System.out.println("desde run del nuevo hilo: "
					+ SwingUtilities.isEventDispatchThread());
			int desde = 1;
			while (!Thread.currentThread().isInterrupted()) {
				String strNum = Integer.toString(desde);
				// no estamos en el hilo de Swing, por eso obligatorio
				// actualizar la vista con invokelater
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						System.out
								.println("desde invokeLater del nuevo hilo actualizo la vista: "
										+ SwingUtilities
												.isEventDispatchThread());
						lbl.setText(strNum);
					}
				});
				// queremos una espera de 100 ms

				// Esta forma no es la correcta:
				// long espera = System.currentTimeMillis();
				// while (espera + 1000 > System.currentTimeMillis()) {...}
				// Este bucle hace espera activa, consume recursos (la CPU)
				// Es mejor utilizar espera pasiva con sleep(millis).

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// ojo!!!!! sin la siguiente línea no termina,
					// comprobadlo!!!!
					Thread.currentThread().interrupt();
					// también valdría return; para asegurar que termina
				}
				desde++;
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("En el main, antes del invokeLater: "
				+ SwingUtilities.isEventDispatchThread());

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				System.out.println("En el main, dentro de invokeLater: "
						+ SwingUtilities.isEventDispatchThread());
				new Ejemplo2();
			}
		});

	}
	// comprobad que si hago iniciar, parar y le doy otra vez a iniciar
	// comienza otra vez desde el principio, pues arranca otra hebra distinta.
}
