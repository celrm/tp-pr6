package ejemploJTablePR5;

public class EventsPrueba {

	private Integer _time;//tiempo en el que se produce el evento
	private Integer _prioridad;//respecto de otros eventos
	
	public EventsPrueba(Integer time, Integer prioridad) {
		_time = time;
		_prioridad = prioridad;
		
	}

	public int getTime() {
		return _time;

	}

	public int getPrioridad() {
		return _prioridad;

	}
	
}
