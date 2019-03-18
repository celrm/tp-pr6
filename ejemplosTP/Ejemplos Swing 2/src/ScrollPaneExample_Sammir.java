import java.awt.Dimension;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
//JScrollPane a partir de un JPanel

public class ScrollPaneExample_Sammir extends JFrame {

	public ScrollPaneExample_Sammir() {
		super("Scroll Pane with JPanel");
		initGUI();
	}

	private void initGUI() {
		
		this.setSize(620, 200); //con este tamaño sólo sale la barra vertical
		//this.setSize(200, 200); //con este tamaño salen las 2 barras
		
		//primero creo el panel y después el JScrollPane
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		addButton("Holaa", p);
		addButton("Holaaa", p);
		addButton("Holaaaa", p);
		addButton("Holaaaaa", p);
		addButton("Holaaaaaaaaaaaaaaaaaaaaaa", p);
		addButton("Holaaaaaaaaaaaaaaaaaaaaaa", p);
		addButton("Holaaaaaaaaaaaaaaaaaaaaaa", p);
		addButton("Holaaaaaaaaaaaaaaaaaaaaaa", p);

		
		JScrollPane area = new JScrollPane(p);
		this.getContentPane().add(area);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void addButton(String text, JPanel container) {
		JButton button = new JButton(text);
		
		//probarlo con esto y sin esto
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(button);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ScrollPaneExample_Sammir();
			}
		});
	}
}
