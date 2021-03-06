package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class Viewer extends JComponent implements SimulatorObserver {
	// ...
	private int _centerX;
	private int _centerY;	
	private double _scale;
	private List<Body> _bodies;
	private boolean _showHelp;
	
	Viewer(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	}
	private void initGUI() {
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black, 2),
				"Viewer", TitledBorder.LEFT, TitledBorder.TOP));
		this.setSize(200,200);

		_bodies = new ArrayList<>();
		_scale = 1.0;
		_showHelp = true;
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
				case '-':
					_scale = _scale * 1.1; break;
				case '+':
					_scale = Math.max(1000.0, _scale / 1.1); break;
				case '=':
					autoScale(); break;
				case 'h':
					_showHelp = !_showHelp; break;
				default:
				}				
				repaint();
			}
			@Override
			public void keyReleased(KeyEvent arg0) {}
			@Override
			public void keyTyped(KeyEvent arg0) {}
		});
		
		addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent e) {
				requestFocus();
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
		RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		gr.setColor(Color.WHITE);
		Rectangle rec = getVisibleRect();
		rec.setBounds(5, 7, getWidth()-10, getHeight()-12);
		gr.fill(rec);
		
		// use 'gr' to draw not 'g'
		// calculate the center
		_centerX = getWidth() / 2;
		_centerY = getHeight() / 2;
		
		// draw a cross at center
		gr.setColor(Color.BLACK);
		gr.drawLine(-3+_centerX -((int) (getHeight() / 70)), _centerY, 3+_centerX +((int) (getHeight() / 70)), _centerY);
		gr.drawLine(_centerX, -3+_centerY-((int) (getHeight() / 70)), _centerX,3+ _centerY+((int) (getHeight() / 70)));
		
		// draw bodies
		for (Body b: _bodies){
			gr.setColor(Color.BLUE);
			gr.fillOval(-5+_centerX + (int)(b.getPosition().coordinate(0)/_scale), -5+_centerY - (int)(b.getPosition().coordinate(1)/_scale), 10, 10);
			gr.drawString(b.getId(), -8+_centerX + (int)(b.getPosition().coordinate(0)/_scale),  -10+_centerY - (int)(b.getPosition().coordinate(1)/_scale));
		}
		if (_showHelp){
			String m = "h: toggle help, +: zoom-in, -:zoom-out, =:fit ";
			StringBuilder message = new StringBuilder("Scaling ratio: ");
			message.append(_scale);
			String s = message.toString();
			gr.setColor(Color.RED);
			gr.drawString(m , 10, 25);
			gr.setColor(Color.RED);
			gr.drawString(s, 10, 40);
		}

	}

	private void autoScale() {
		double max = 1.0;
		for (Body b : _bodies) {
			Vector p = b.getPosition();
			for (int i = 0; i < p.dim(); i++)
			max = Math.max(max,
				Math.abs(b.getPosition().coordinate(i)));
		}
		double size = Math.max(1.0, Math.min((double) getWidth(),
											(double) getHeight()));
		_scale = max > size ? 4.0 * max / size : 1.0;
	}
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_bodies = bodies;
				autoScale();
				repaint();
			}
		});
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_bodies = bodies;
				autoScale();
				repaint();
			}
		});
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_bodies = bodies;
				autoScale();
				repaint();
			}
		});
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_bodies = bodies;
//				autoScale(); // Lo quitamos para que los botones sean efectivos
				repaint();
			}
		});
	}
	@Override
	public void onDeltaTimeChanged(double dt) {}
	@Override
	public void onGravityLawChanged(String gLawsDesc) {}
}
