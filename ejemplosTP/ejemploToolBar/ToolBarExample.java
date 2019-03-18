import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ToolBarExample extends JFrame implements ActionListener {

	private JTextArea textArea;

	public ToolBarExample() {
		super("Mi primer ToolBar JToolBar");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
		
		textArea = new JTextArea(5, 30);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		mainPanel.add(scrollPane);  //observar que cuando se llena la zona, 
									//salen las barras

		JToolBar barra = new JToolBar();
		mainPanel.add(barra, BorderLayout.NORTH);

		JButton load = new JButton();
		load.setActionCommand("load");
		load.setToolTipText("Load a file");
		load.addActionListener(this);
		load.setIcon(new ImageIcon("icons/open.png"));
		barra.add(load);
		barra.addSeparator();

		JButton save = new JButton();
		save.setActionCommand("save");
		save.setToolTipText("Save a file");
		save.addActionListener(this);
		save.setIcon(new ImageIcon("icons/save.png"));
		barra.add(save);
		barra.addSeparator();

		String[] names = { "Times Roman", "Arial", "Comic" };
		JComboBox<String> list = new JComboBox<String>(names);
		list.setSelectedIndex(0);
		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				textArea.append("Has seleccionado la opci�n: " + list.getSelectedItem() + "\n");;
			} 
		});
		list.setMaximumSize(new Dimension(150, 60));
		barra.add(list);
		barra.addSeparator();
		
		//con lo que metemos aqu�, en este ejmplo no hacemos nada en concreto
		JTextField text = new JTextField(8);
		text.setMaximumSize(new Dimension(150, 60));
		barra.add(text);

		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		textArea.append("Clicked on " + e.getActionCommand() + "\n");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ToolBarExample();
			}
		});
	}

}

