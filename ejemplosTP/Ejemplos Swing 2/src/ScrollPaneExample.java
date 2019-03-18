import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
//JScrollPane a partir de un JTextArea

public class ScrollPaneExample extends JFrame {

	public ScrollPaneExample() {
		super("Ejemplo ScrollPane----------");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel(new BorderLayout());

		// This is the story we took from Wikipedia.
		String story = "The Internet Foundation Classes (IFC) were a graphics "
				+ "library for Java originally developed by Netscape Communications "
				+ "Corporation and first released on December 16, 1996.\n\n"
				+ "On April 2, 1997, Sun Microsystems and Netscape Communications"
				+ " Corporation announced their intention to combine IFC with other"
				+ " technologies to form the Java Foundation Classes. In addition "
				+ "to the components originally provided by IFC, Swing introduced "
				+ "a mechanism that allowed the look and feel of every component "
				+ "in an application to be altered without making substantial "
				+ "changes to the application code. The introduction of support "
				+ "for a pluggable look and feel allowed Swing components to "
				+ "emulate the appearance of native components while still "
				+ "retaining the benefits of platform independence. This feature "
				+ "also makes it easy to have an individual application's appearance "
				+ "look very different from other native programs.\n\n"
				+ "Originally distributed as a separately downloadable library, "
				+ "Swing has been included as part of the Java Standard Edition "
				+ "since release 1.2. The Swing classes are contained in the "
				+ "javax.swing package hierarchy.\n\n";

		
		JTextArea storyArea = new JTextArea(story);
		//probarlo con esto a true y a false
		storyArea.setEditable(false);
		//Para establecer ajuste automático de líneas
		storyArea.setLineWrap(true);
		//Así no parte palabras
		storyArea.setWrapStyleWord(true);
		
		JScrollPane area = new JScrollPane(storyArea);
		area.setPreferredSize(new Dimension(300, 200));
		mainPanel.add(area);
		
		this.setContentPane(mainPanel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ScrollPaneExample();
			}
		});
	}
}

