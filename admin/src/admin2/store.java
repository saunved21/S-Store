package admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class store {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					store window = new store();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public store() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * buttons and stuff
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 664, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("DISCOVER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				discover updateFrame = new discover();
	            updateFrame.frame.setVisible(true);
	            frame.dispose();
			}
		});
		btnNewButton.setBounds(123, 100, 156, 83);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnBilling = new JButton("CART");
		btnBilling.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        cart billingSystem = new cart();
		        billingSystem.setVisible(true);
		        frame.dispose();
		    }
		});

		btnBilling.setBounds(123, 200, 156, 83);
		frame.getContentPane().add(btnBilling);
		
		JButton btnViewBills = new JButton("INSTALLED");
		btnViewBills.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        installed bill = new installed();
		        bill.setVisible(true);
		        frame.dispose();
		    }
		});
		btnViewBills.setBounds(123, 300, 156, 83);
		frame.getContentPane().add(btnViewBills);
		
		
		
		
		JButton Try = new JButton("Try Snake Game ");
		Try.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        SnakeGame snake = new SnakeGame();
		        snake.setVisible(true);
		        frame.dispose();
		    }
		});
		Try.setBounds(400, 150, 156, 83);
		frame.getContentPane().add(Try);
		
				
		
		JButton Tac = new JButton("Try Tic Tac Toe");
		Tac.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        ticm tictactoe = new ticm();
		        tictactoe.setVisible(true);
		        frame.dispose();
		    }
		});
		Tac.setBounds(400, 250, 156, 83);
		frame.getContentPane().add(Tac);
		
		
	
		
		
		
		JLabel lblCategory = new JLabel("Try Demo Games");
        lblCategory.setBounds(422, 100, 200, 20);
        frame.getContentPane().add(lblCategory);
		

		
		//welcome
		JLabel lblNewLabel = new JLabel("Welcome To S Games");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(175, 18, 394, 68);
		frame.getContentPane().add(lblNewLabel);
	}
}

