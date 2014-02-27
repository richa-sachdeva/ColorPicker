package com.ColorPick;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorPick implements ActionListener{
	
	private static JFrame mainFrame;
	private static JPanel titlePanel, infoPanel;
	private static ColorGamePanel gamePanel;
	
	private static JLabel titleLabel, gameLabel, moveLabel, gameStatus, moveCount;

	private static JButton restart, pause;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ColorPick c = new ColorPick();
	//	initialiseGui();
		//addActionListener(start);
		// on start set the gamePanel -> make a seperate class 
		
		// Game loop
//		
//		while(true){
			
			
//		}
		
		

		
		
	}

	public ColorPick() {
		// TODO Auto-generated method stub
		mainFrame = new JFrame("Color Pick");
		mainFrame.getContentPane().setLayout(new BorderLayout());
		mainFrame.setSize(500, 700);
		mainFrame.setBackground(Color.black);
		
		titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout());
		titlePanel.setBackground(Color.white);
		
		titleLabel = new JLabel(" Game: Color Pick ");
		titleLabel.setFont(new Font("Snap ITC", Font.ITALIC, 18));
		
		titlePanel.add(titleLabel);
		
		mainFrame.add(titlePanel, BorderLayout.NORTH);
		
		gamePanel = new ColorGamePanel();
		
		mainFrame.add(gamePanel, BorderLayout.CENTER);
		
		infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(0, 2));
		infoPanel.setBackground(Color.black);
		
		
		gameLabel = new JLabel("Game Status : ");
		gameLabel.setFont(new Font("Sylfaen", Font.ITALIC, 14));
		gameLabel.setForeground(Color.white);
		infoPanel.add(gameLabel);
		
		gameStatus = new JLabel("Start the game");
		gameStatus.setFont(new Font("Sylfaen", Font.ITALIC, 14));
		gameStatus.setForeground(Color.white);
		infoPanel.add(gameStatus);
		
		moveLabel = new JLabel("Moves left : ");
		moveLabel.setFont(new Font("Sylfaen", Font.ITALIC, 14));
		moveLabel.setForeground(Color.white);
		infoPanel.add(moveLabel);
		
		moveCount = new JLabel(""+gamePanel.getLocalMoveCount());
		moveCount.setFont(new Font("Sylfaen", Font.ITALIC, 14));
		moveCount.setForeground(Color.white);
		infoPanel.add(moveCount);
		
		restart = new JButton("Start/Restart");
		restart.addActionListener(this);
		
		pause = new JButton("Pause");
		
		infoPanel.add(restart);
//		infoPanel.add(pause);
		
		mainFrame.add(infoPanel, BorderLayout.SOUTH);
		
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.pack();
	    mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("clicked " +arg0.getSource());
		 JButton button = (JButton)arg0.getSource();
		 if(button == restart){
			 gamePanel.initialiseGamePanel();
		 }
	}
	
	public static void setMoveLabel(int moveLabel) {
		ColorPick.moveCount.setText(""+moveLabel); 
	}

	public static void setGameStatus(String gameStatus) {
		ColorPick.gameStatus.setText(gameStatus);
	}

}
