package ejemploJTablePR5;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//Vamos a sacar los datos de un ArrayList en un JTable
//Para esto necesitamos un modelo de tabla.
//Pues no siempre los datos van a venir en un array bidimensional

public class EjJTablePR5 extends JFrame implements ActionListener {
	private Border _defaultBorder = BorderFactory
			.createLineBorder(Color.red, 1);
	private EventsTableModel _modelo;
	private JTable _eventsTable;
	private JButton btnMostrar;
	private List<EventsPrueba> _events; // esto es lo que tengo que
										// sacar en la tabla

	public EjJTablePR5() {

		super("Ejemplo de Jtable con los datos en un ArrayList");
		initGUI();
		inicializaEvents();

	}

	public void initGUI() {
		this.getContentPane().setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 300);

		btnMostrar = new JButton("Mostrar eventos");
		btnMostrar.addActionListener(this);

		JPanel eventsPanel = new JPanel(new BorderLayout());
		eventsPanel.setBorder(BorderFactory.createTitledBorder(_defaultBorder,
				"Listado de Eventos", TitledBorder.LEFT, TitledBorder.TOP));

		_modelo = new EventsTableModel();
		_eventsTable = new JTable(_modelo);

		// modo de selección: celdas únicas
		_eventsTable.setCellSelectionEnabled(true);

		eventsPanel.add(new JScrollPane(_eventsTable,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

		this.getContentPane().add(btnMostrar);
		this.getContentPane().add(eventsPanel);
		_events = new ArrayList();
		pack();
		setVisible(true);
	}

	public void inicializaEvents() {

		EventsPrueba e1 = new EventsPrueba(0, 3);
		EventsPrueba e2 = new EventsPrueba(1, 5);
		EventsPrueba e3 = new EventsPrueba(1, 2);
		EventsPrueba e4 = new EventsPrueba(2, 1);
		EventsPrueba e5 = new EventsPrueba(5, 4);
		_events.add(e1);
		_events.add(e2);
		_events.add(e3);
		_events.add(e4);
		_events.add(e5);

	}

	public void actionPerformed(ActionEvent e) {
		// le paso al modelo el arrayList de eventos
		_modelo.setEventsList(_events);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EjJTablePR5();
			}
		});
	}

}
