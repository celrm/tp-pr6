import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EjemploJSpinner extends JFrame {

	private JTextField tf;
	private JSpinner spinner;
	int Time0 = 1000;

	public EjemploJSpinner() {
		super("ejemplo: Mi primer Spinner");
		this.setSize(420, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Creacion del JTextField
		tf = new JTextField(20);

		// spinner = new JSpinner(); si utilizamos este constructor
		// comenzamos desde 0 e incrementamos uno a uno.
		// Para hacerlo a nuestro gusto utilizar el otro constructor
		// JSpinner(SpinnerModel model).
		// Constructor: SpinnerNumberModel(int value, int minimum, int maximum,
		// int stepSize)

		// 5000 es el máximo, 0 es el mímimo y el incremento es 500 y el valor
		// inicial lo cojo de Time0
		
		spinner = new JSpinner(new SpinnerNumberModel(Time0, 0, 5000, 500));
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// Ponemos el valor del JSpinner en el JTextField
				tf.setText("Has puesto en el spinner: " + spinner.getValue());
			}
		});

		// Creacion de la ventana con los componentes
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(spinner);
		getContentPane().add(tf);
		pack(); //probarlo con esto y sin esto
		setVisible(true);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EjemploJSpinner();
			}
		});

	}

}
