package ejemploJTablePR5;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class EventsTableModel extends AbstractTableModel {

	private List<EventsPrueba> _events;
	private String[] _colNames = { "número", "tiempo", "prioridad" };

	public void setEventsList(List<EventsPrueba> events) {
		_events = events;
		// observar que si no refresco la tabla no se carga
		// La tabla es la represantación visual de una estructura de datos,
		// en este caso de un ArrayList, hay que notificar los cambios.
		fireTableStructureChanged();
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public String getColumnName(int col) {
		return _colNames[col];
	}

	@Override
	//método obligatorio, probad a quitarlo, no compilan
	public int getColumnCount() {
		return _colNames.length;
	}

	@Override
	//método obligatorio
	public int getRowCount() {
		return _events == null ? 0 : _events.size();
	}

	@Override
	//método obligatorio
	//así es como se va a cargar la tabla desde el ArrayList
	//el índice del arrayList es el número de fila pq en este ejemplo
	//quiero enumerarlos.
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = null;
		switch (columnIndex) {
		case 0:
			s = rowIndex; 
			break;
		case 1:
			s = _events.get(rowIndex).getTime();
			break;
		case 2:
			s = _events.get(rowIndex).getPrioridad();
			break;
		}
		return s;
	}
}
