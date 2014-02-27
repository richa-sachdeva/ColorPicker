package com.ColorPick;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Rectangle;

import javax.swing.JPanel;

class ColorGamePanel extends JPanel implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ColorRectangle> playableGrid;
	private List<ColorRectangle> colorBar;
	private double barLength = 60, gridLength = 35;
	private List<Integer> colorGrid;
	private final int MOVES = 17;
	private int moveCount;
	private boolean paintGame = false;

	public ColorGamePanel(){
		
		this.setLayout(new GridLayout(0, 1));
		this.setSize(500, 400);
		this.setBackground(Color.white);
		this.setBounds(new Rectangle(500, 400));
		
		drawColorBar();
		
		this.addMouseListener(this);
		
		moveCount = MOVES;
	}
	
	private void drawColorBar() {
		colorBar = new ArrayList<ColorRectangle>();
		double x = 75;
		double y = 450;
		double spacing = 10;
		
		for(ColorEnum color : ColorEnum.values()){
	//		System.out.println(color);
			colorBar.add(new ColorRectangle(x, y, color.getValue(), barLength, false));
			x += barLength + spacing;
		}
	}
	
	enum ColorEnum{
		RED(Color.red),
		PINK(Color.pink),
		BLUE(Color.blue),
		MAGNETA(Color.magenta),
		ORANGE(Color.orange);
		
		private final Color value;
		ColorEnum(Color value){
			this.value = value;
		}
		
		public Color getValue(){
			return this.value;
		}
	};
	
	enum GameStatus{
		INIT("Start the game"),
		PROG("Game in progress"),
		OVER("Game over"),
		WIN("You win"),
		LOSE("Oops. Try again!");
		
		private String value;
		GameStatus(String v){
			this.value = v;
		}
		
		public String getValue(){
			return this.value;
		}
	}
	
	public void initialiseGamePanel(){
//		playableGrid = new ColorRectangle[10][10];	
		paintGame = true;
		playableGrid = new ArrayList<ColorRectangle>();
		double x;
		double y = 50;
		ColorEnum[] c = ColorEnum.values();
		int colorIndex;
		int Min = 0;
		int Max = 4;
		
		for(int i=0; i<10; i++){
			x = 70;			
			for(int j=0; j<10; j++){
				colorIndex = (int) (Min + (int)(Math.random() * ((Max - Min) + 1)));
	//			System.out.println(" ci: "+colorIndex);
				playableGrid.add(new ColorRectangle(x, y, c[colorIndex].getValue(), gridLength, false));
	//			playableGrid[i][j] = new ColorRectangle(x, y, c[colorIndex].getValue(), gridLength, false);
				x+=gridLength;
			}
			y += gridLength;
		}
		repaint();
		colorGrid = new ArrayList<Integer>();
		colorGrid.add(0);

		SetPlayableList();	
	}

	private void SetPlayableList() {
		
		boolean fwd = true, bkwd = true;
		boolean down = true, upwd = true;
		
		for(ColorRectangle cr : playableGrid){
			int curr = playableGrid.indexOf(cr);
			if (colorGrid.contains(curr)){
				System.out.println("in curr : "+curr);
			
				int right = curr + 1; right = checkRange(right);
				int bottom = curr + 10; bottom = checkRange(bottom);
				int left = curr - 1; left = checkRange(left);
				int top = curr - 10; top = checkRange(top);
				
				boolean r = false,b = false,l = false, t=false;
				
				if(fwd && curr%10 != 9 && right != -1)r = checkNeighborColor(right, curr);
				if(down && bottom != -1)b = checkNeighborColor(bottom, curr);
				if(bkwd && curr%10 != 0 && left != -1)l = checkNeighborColor(left, curr);
				if(upwd && top != -1)t = checkNeighborColor(top, curr);
				
				if( r || b || l || t){
					fwd = true;
					down = true;
					bkwd = true;
					upwd = true;
				}
//				System.out.println("after: "+fwd + " " +down + " "+bkwd+ " "+upwd);
			}
		}
		System.out.println("init grid count" + colorGrid.size());		
	}

	private int checkRange(int right) {
		if( (right < 0) || (right > 100) || (right == 100))
			return -1;
		return right;
	}

	private boolean checkNeighborColor(int right, int curr) {
		if(right != -1 && (playableGrid.get(right).getColor() == playableGrid.get(curr).getColor())){
			if(!colorGrid.contains(right))
				colorGrid.add(right);
			return true;
		}
		return false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(ColorRectangle l : colorBar)
			l.paint(g);
		if(paintGame){
		for(ColorRectangle l : playableGrid)
			l.paint(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		for(ColorRectangle cr : colorBar){
			double x = cr.getX();
			double y = cr.getY();
			double size = cr.getLength();
			
			Rectangle r = new Rectangle((int)x, (int)y, (int)size, (int)size);
			
			double mouseX = arg0.getX();
			double mouseY = arg0.getY();
			
			Point p = new Point((int)mouseX, (int)mouseY);
			
			if(r.contains(p)){
				System.out.println("inside: "+ cr.getColor().toString());
				SetMoveLabel(cr.getColor());
				SetGameStatusLabel();
				SetColor(cr.getColor());
				SetPlayableList();
				
				repaint();	
			}
		}
	}

	private void SetMoveLabel(Color color) {
		// TODO Auto-generated method stub
		if(playableGrid.get(0).getColor() != color){
			moveCount--;
			ColorPick.setMoveLabel(moveCount);
		}
	}
	
	private void SetGameStatusLabel() {
		// check is some moves are left and colorGrid size != 100
		if(moveCount == MOVES)
			ColorPick.setGameStatus(GameStatus.INIT.getValue());
		if(moveCount != 0){
			if(colorGrid.size() != playableGrid.size())
				ColorPick.setGameStatus(GameStatus.PROG.getValue());
			if(colorGrid.size() == playableGrid.size())
				ColorPick.setGameStatus(GameStatus.OVER.getValue() + ": " + GameStatus.WIN.getValue());
		}	
		if(moveCount == 0){
			if(colorGrid.size() != playableGrid.size())
				ColorPick.setGameStatus(GameStatus.OVER.getValue() + ": " + GameStatus.LOSE.getValue());
			if(colorGrid.size() == playableGrid.size())
				ColorPick.setGameStatus(GameStatus.OVER.getValue() + ": " + GameStatus.WIN.getValue());
		}	
	}

	private void SetColor(Color color) {	
		for(int curr : colorGrid){
		//	if(playableGrid.get(curr).getColor() != color){
				playableGrid.get(curr).setColor(color);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public int getMovesCount() {
		return MOVES;
	}

	public int getLocalMoveCount() {
		return moveCount;
	}
}
