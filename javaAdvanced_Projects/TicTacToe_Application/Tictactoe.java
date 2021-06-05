/*
 * This project implements the Tic Tac Toe game. 
 */

import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
//import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Tictactoe {

	private JFrame frame;
	private JTextField aiCount;
	private JTextField humanCount;
	private int HUMAN = 0;
	private int AI = 0;
	private String userSymbol = "X";
	private String aiSymbol = "O";
	private int lastMove;				// Determines who made the last move, the user, or the computer
	private String[][] STATE = {{"0","0","0"},{"0","0","0"},{"0","0","0"}};
	private JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnReset;
	private TictactoeFunctionality tttF = new TictactoeFunctionality();

	// Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tictactoe window = new Tictactoe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the application
	public Tictactoe() {
		// Show information dialog
		frame = new JFrame("Information");
		JOptionPane.showMessageDialog(frame, " User has X Player.\n A.I has O Player.\n\nPlayers swap each round. X Player plays first.");
		initialize();
	}
	
	
	// Initialise the contents of the frame
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 5, 2, 2));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		btn1 = new JButton("");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(STATE[0][0].equals("0")) {
					btn1.setText(userSymbol);
					btn1.setForeground(Color.RED);
					STATE[0][0] = userSymbol;
					lastMove = 1;
					isGAMEOVER();
					aiPlays();
				}
				
			}
		});
		btn1.setFont(new Font("Dialog", Font.BOLD, 35));
		panel_1.add(btn1, BorderLayout.CENTER);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		btn2 = new JButton("");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(STATE[0][1].equals("0")) {
					btn2.setText(userSymbol);
					btn2.setForeground(Color.RED);
					STATE[0][1] = userSymbol;
					lastMove = 1;
					isGAMEOVER();
					aiPlays();
				}
			}
		});
		btn2.setFont(new Font("Dialog", Font.BOLD, 35));
		panel_2.add(btn2, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		btn3 = new JButton("");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(STATE[0][2].equals("0")) {
					btn3.setText(userSymbol);
					btn3.setForeground(Color.RED);
					STATE[0][2] = userSymbol;
					lastMove = 1;
					isGAMEOVER();
					aiPlays();
				}
			}
		});
		btn3.setFont(new Font("Dialog", Font.BOLD, 35));
		panel_3.add(btn3, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("A.I");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_4.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		aiCount = new JTextField(); // -------------------------------
		aiCount.setFont(new Font("Tahoma", Font.BOLD, 20));
		aiCount.setHorizontalAlignment(SwingConstants.CENTER);
		aiCount.setText("0");
		panel_5.add(aiCount, BorderLayout.CENTER);
		aiCount.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		btn4 = new JButton("");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(STATE[1][0].equals("0")) {
					btn4.setText(userSymbol);
					btn4.setForeground(Color.RED);
					STATE[1][0] = userSymbol;
					lastMove = 1;
					isGAMEOVER();
					aiPlays();
				}
			}
		});
		btn4.setFont(new Font("Dialog", Font.BOLD, 35));
		panel_6.add(btn4, BorderLayout.CENTER);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		btn5 = new JButton("");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(STATE[1][1].equals("0")) {
					btn5.setText(userSymbol);
					btn5.setForeground(Color.RED);
					STATE[1][1] = userSymbol;
					lastMove = 1;
					isGAMEOVER();
					aiPlays();
				}
			}
		});
		btn5.setFont(new Font("Dialog", Font.BOLD, 35));
		panel_7.add(btn5, BorderLayout.CENTER);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		btn6 = new JButton("");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(STATE[1][2].equals("0")) {
					btn6.setText(userSymbol);
					btn6.setForeground(Color.RED);
					STATE[1][2] = userSymbol;
					lastMove = 1;
					isGAMEOVER();
					aiPlays();
				}
			}
		});
		btn6.setFont(new Font("Dialog", Font.BOLD, 35));
		panel_8.add(btn6, BorderLayout.CENTER);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Human");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_9.add(lblNewLabel_1, BorderLayout.CENTER);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		humanCount = new JTextField();
		humanCount.setFont(new Font("Tahoma", Font.BOLD, 20));
		humanCount.setHorizontalAlignment(SwingConstants.CENTER);
		humanCount.setText("0"); 
		panel_10.add(humanCount, BorderLayout.CENTER);
		humanCount.setColumns(10);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		btn7 = new JButton("");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(STATE[2][0].equals("0")) {
					btn7.setText(userSymbol);
					btn7.setForeground(Color.RED);
					STATE[2][0] = userSymbol;
					lastMove = 1;
					isGAMEOVER();
					aiPlays();
				}
			}
		});
		btn7.setFont(new Font("Dialog", Font.BOLD, 35));
		panel_11.add(btn7, BorderLayout.CENTER);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		btn8 = new JButton("");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(STATE[2][1].equals("0")) {
					btn8.setText(userSymbol);
					btn8.setForeground(Color.RED);
					STATE[2][1] = userSymbol;
					lastMove = 1;
					isGAMEOVER();
					aiPlays();
				}
			}
		});
		btn8.setFont(new Font("Dialog", Font.BOLD, 35));
		panel_12.add(btn8, BorderLayout.CENTER);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		btn9 = new JButton("");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(STATE[2][2].equals("0")) {
					btn9.setText(userSymbol);
					btn9.setForeground(Color.RED);
					STATE[2][2] = userSymbol;
					lastMove = 1;
					isGAMEOVER();
					aiPlays();
				}
			}
		});
		btn9.setFont(new Font("Dialog", Font.BOLD, 35));
		panel_13.add(btn9, BorderLayout.CENTER);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_14);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn1.setText(null);
				btn2.setText(null);
				btn3.setText(null);
				btn4.setText(null);
				btn5.setText(null);
				btn6.setText(null);
				btn7.setText(null);
				btn8.setText(null);
				btn9.setText(null);
				String[][] newState = {{"0","0","0"},{"0","0","0"},{"0","0","0"}};
				STATE = newState;
				if(aiSymbol.equals("X")) aiPlays();
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_14.add(btnReset, BorderLayout.CENTER);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_15);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame, "Confirm You Want To Exit","Tic Tac Toe",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_15.add(btnExit, BorderLayout.CENTER);
		
	}
	
	private void aiPlays() {
		
		tttF.setGlobalPlayer(aiSymbol);				
		int button = tttF.MINIMAX(STATE);		// Return which button to choose
		lastMove = -1;
		switch(button) {
		case 1:
			btn1.setText(aiSymbol);
			btn1.setForeground(Color.BLUE);
			STATE[0][0] = aiSymbol;
			isGAMEOVER();
			break;
		case 2:
			btn2.setText(aiSymbol);
			btn2.setForeground(Color.BLUE);
			STATE[0][1] = aiSymbol;
			isGAMEOVER();			
			break;
		case 3:
			btn3.setText(aiSymbol);
			btn3.setForeground(Color.BLUE);
			STATE[0][2] = aiSymbol;
			isGAMEOVER();
			break;
		case 4:
			btn4.setText(aiSymbol);
			btn4.setForeground(Color.BLUE);
			STATE[1][0] = aiSymbol;
			isGAMEOVER();
			break;
		case 5:
			btn5.setText(aiSymbol);
			btn5.setForeground(Color.BLUE);
			STATE[1][1] = aiSymbol;
			isGAMEOVER();
			break;
		case 6:
			btn6.setText(aiSymbol);
			btn6.setForeground(Color.BLUE);
			STATE[1][2] = aiSymbol;
			isGAMEOVER();
			break;
		case 7:
			btn7.setText(aiSymbol);
			btn7.setForeground(Color.BLUE);
			STATE[2][0] = aiSymbol;
			isGAMEOVER();
			break;
		case 8:
			btn8.setText(aiSymbol);
			btn8.setForeground(Color.BLUE);
			STATE[2][1] = aiSymbol;
			isGAMEOVER();
			break;
		case 9:
			btn9.setText(aiSymbol);
			btn9.setForeground(Color.BLUE);
			STATE[2][2] = aiSymbol;
			isGAMEOVER();
			break;
		}
	}
	
	private void RESET() {
		btn1.setText(null);
		btn2.setText(null);
		btn3.setText(null);
		btn4.setText(null);
		btn5.setText(null);
		btn6.setText(null);
		btn7.setText(null);
		btn8.setText(null);
		btn9.setText(null);
		String[][] newState = {{"0","0","0"},{"0","0","0"},{"0","0","0"}};
		STATE = newState;
	}
	
	private void isGAMEOVER() {
		if(tttF.TERMINAL(STATE)) {
			int n = tttF.UTILITY(STATE);			// Return the winner
			if(n == 1) {
				if(userSymbol.equals("X")) {
					HUMAN += 1;
					humanCount.setText(Integer.toString(HUMAN));
					frame = new JFrame("Information");
					JOptionPane.showMessageDialog(frame, "Human wins!");
				}
				else if(aiSymbol.equals("X")) {
					AI += 1;
					aiCount.setText(Integer.toString(AI));
					frame = new JFrame("Information");
					JOptionPane.showMessageDialog(frame, "AI wins!");
				}
				
				// Swap players
				String temp = aiSymbol;
				aiSymbol = userSymbol;
				userSymbol = temp;
				
				RESET();
				if(aiSymbol.equals("X") && lastMove == -1) aiPlays();
			}
			else if(n == -1) {
				if(userSymbol.equals("O")) {
					HUMAN += 1;
					humanCount.setText(Integer.toString(HUMAN));
					frame = new JFrame("Information");
					JOptionPane.showMessageDialog(frame, "Human wins!");
				}
				else if(aiSymbol.equals("O")) {
					AI += 1;
					aiCount.setText(Integer.toString(AI));
					frame = new JFrame("Information");
					JOptionPane.showMessageDialog(frame, "A.I wins!");
				}
				
				// Swap players
				String temp = aiSymbol;
				aiSymbol = userSymbol;
				userSymbol = temp;
				
				RESET();
				if(aiSymbol.equals("X") && lastMove == -1) aiPlays();
			}
			else {
				frame = new JFrame("Information");
				JOptionPane.showMessageDialog(frame, "It's a Draw");
				
				// Swap players
				String temp = aiSymbol;
				aiSymbol = userSymbol;
				userSymbol = temp;
				
				RESET();
				if(aiSymbol.equals("X") && lastMove == -1) aiPlays();
			}
		}
	}
	

}
