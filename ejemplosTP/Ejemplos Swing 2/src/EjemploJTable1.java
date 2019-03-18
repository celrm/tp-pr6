//Creación de JTable vacío, editable.
//Selecciono celda, coloco un dato en el campo de texto
//doy al botón modificar y se guarda el la celda que seleccioné.

//Selecciono celda con texto, doy al botón leer y sale por 
//el campo de texto

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;

public class EjemploJTable1 extends JFrame implements ActionListener {
	private JTable tbl;
	private JTextField txt;
	private JButton btnModificar;
	private JButton btnLeer;

	public EjemploJTable1() {
		super("Ejemplo sencillo de JTable vacío");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 300);
		this.getContentPane().setLayout(new BorderLayout());

		JPanel pnlControl = new JPanel();//para los botones y el campo de texto
		pnlControl.setLayout(new GridLayout(1, 3, 5, 5));

		txt = new JTextField("");
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnLeer = new JButton("Leer");
		btnLeer.addActionListener(this);

		pnlControl.add(txt);
		pnlControl.add(btnModificar);
		pnlControl.add(btnLeer);
		this.getContentPane().add(pnlControl, BorderLayout.NORTH);

		tbl = new JTable(30, 10);//30 filas y 10 columnas

		JScrollPane scb = new JScrollPane(tbl);//barras de desplazamiento
		
		//VAMOS A FIJAR EL TAMAÑO DE LAS COLUMNAS:
		//ver que sin esto no hay barra horizontal, las columnas 
		//se ajustan al ancho.
		//Si quiero fijar un tamaño
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//Así puedo a continuación 
													//poner el tamaño que yo quiera,
													//si quito esto, ese tamaño que 
													//fijo después queda anulado
		// Para modificar el tamaño de las columnas
		for (int i = 0; i < tbl.getColumnCount(); i++) {
			//con esto cojo la columna i:
			TableColumn col = tbl.getColumnModel().getColumn(i);
			//a cada columna le doy esta anchura
			col.setPreferredWidth(80);
		}
		//Chicos!!! probad todo esto:
		
		// SELECCION DE CELDAS

		// solo líneas únicas, no celdas sino líneas
		//que es la opcion por defecto
		//tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// intervalos de líneas
		//tbl.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		// selección de varios intervalos con la tecla Ctrl y el raton
		//tbl.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		// celdas únicas
		 tbl.setCellSelectionEnabled(true);

		 //permite seleccionar filas o columnas completas
		 //tbl.setRowSelectionAllowed(true);
		 //tbl.setColumnSelectionAllowed(true);
		
		this.getContentPane().add(scb, BorderLayout.CENTER);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//retorna el índice de la fila y columna seleccionadas
		int fila = tbl.getSelectedRow();
		int col = tbl.getSelectedColumn();

		if (e.getSource() == btnModificar) {
			if (fila >= 0 && col >= 0) {
				//colocamos dato en la tabla
				tbl.setValueAt(txt.getText(), fila, col);
			}
		} else if (e.getSource() == btnLeer) {
			if (fila >= 0 && col >= 0) {
				//leemos dato de la tabla
				Object s = tbl.getValueAt(fila, col);
				txt.setText((String) s);
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EjemploJTable1();
				
			}
		});
	}
}
