package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;

public class VelTable extends JPanel {
	VelTable(Controller ctrl) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(
		BorderFactory.createLineBorder(Color.black, 2),
		"Bodies",
		TitledBorder.LEFT, TitledBorder.TOP));
		
		VelTableModel model = new VelTableModel(ctrl); 	// (1) creando una instancia de BodiesTableModel que se le pase a la JTable;
		JTable tabla = new JTable(model);
		
		JScrollPane area = new JScrollPane(tabla);		// (2) añadiendo la JTable al panel (es decir, a this) con un JScrollPane.
		area.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		area.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()/2));
		this.add(area);
	}
}
