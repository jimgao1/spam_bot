

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SpamBot extends JFrame{


	
	public SpamBot(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
		this.setTitle("Jim's Spam Bot  v0.1");
		this.setLayout(null);
		this.setSize(500, 400);
		this.setVisible(true);
		this.setResizable(false);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		
		
		
		final JTextArea text = new JTextArea("Type your text here...");
		text.setBounds(10, 10, 475, 200);
		this.add(text);
		
		
		JLabel l_mode = new JLabel("Mode: ");
		l_mode.setFont(new Font("Courier New", Font.PLAIN, 13));
		l_mode.setBounds(10, 220, 100, 30);
		this.add(l_mode);
		
		DefaultComboBoxModel<String> md_modes = new DefaultComboBoxModel<String>();
		
		md_modes.addElement("Count");
		
		final JComboBox<String> mode = new JComboBox<String>(md_modes);
		mode.setFont(new Font("Courier New", Font.PLAIN, 13));
		mode.setBounds(220, 220, 200, 30);
		this.add(mode);
		
		JLabel l_duration = new JLabel("Time/Count:");
		l_duration.setFont(new Font("Courier New", Font.PLAIN, 13));
		l_duration.setBounds(10, 260, 200, 30);
		this.add(l_duration);
		
		final JTextField duration = new JTextField();
		duration.setFont(new Font("Courier New", Font.PLAIN, 13));
		duration.setBounds(220, 260, 200, 30);
		this.add(duration);
		
		final JButton start = new JButton("Start Spam");
		start.setFont(new Font("Courier New", Font.BOLD, 15));
		start.setBounds(10, 300, 475, 40);
		start.addActionListener(new ActionListener(){
			
			KeyboardThread kt;
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (start.getText().equals("Start Spam")){
					
					kt = new KeyboardThread();
					
					String t = text.getText();
					int m = mode.getSelectedIndex();
					
					
					if (!t.equals("") &! duration.getText().equals("")){
						
						int d = Integer.parseInt(duration.getText());
						
						JOptionPane.showMessageDialog(getParent(),
								"Starting Spam...\n"
								+ "Text: " + t + "\n"
								+ "Mode: " + m + "\n"
								+ "Duration: " + d + "\n"
								+ "Please focus on the target window,\n"
								+ "spam is starting in 5 seconds..."
								+ ""
								);
						
						try{
							Thread.sleep(5000);
						} catch (InterruptedException ex){
							
						}
						
						
						kt.updateSettings(t, m, d);
						kt.running = true;
						kt.start();
						
						start.setText("Stop Spam");
						
					} else {
						JOptionPane.showMessageDialog(getParent(), "Invalid Settings");
					}
				
				} else {
					kt.running = false;
					kt = null;
					start.setText("Start Spam");
				}
				
				
			}
		});
		this.add(start);
		
		this.repaint();
		this.revalidate();
	}
	
	public static void main(String[] args) {
		new SpamBot();

	}

}
