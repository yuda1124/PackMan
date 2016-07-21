package packman;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.plaf.SliderUI;

public class GameUI extends JFrame {
	Timer timer;
	Map map;
	Character character;
	ghost ghost;
	ghost ghost1;
	Image img;
	Image enemy;
	Graphics buffer = null;
	Graphics graphic;
	int [][]food;
	public GameUI() {
		map = new Map();
		map.makeWall();
		food = new int [20][20];
		for(int i = 0; i < 20; i ++){
			for(int j = 0; j < 20; j++){
				if(map.isWall(i, j)) food[i][j] = -1;
				else food[i][j] = 1;
			}
		}
		map.printWall();
		ghost = new ghost(18,18, map);
		ghost1 = new ghost(7,7, map);
		character = new Character(map);
		TListener tListener = new TListener();
		timer = new Timer(500,tListener);
		timer.start();
		
		setVisible(true);
		setBounds(0, 0, 700, 800);
		//img = Toolkit.getDefaultToolkit().getImage("pacman.png");
		
				
		addKeyListener(new KeyListener() {
			public void keyPerformed(KeyEvent e) { // 
				// e.getActionCommand()는 클릭된 버튼의 문자열 정보 리턴
						
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				operate(e.getKeyCode());
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
			//@Override
			/*public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				

			}
			
		});
		
*/	
		
	}
	
	class TListener implements ActionListener {   //timer클래스 ActionListener
		public void actionPerformed(ActionEvent event)
		{
			int choice = (int)(Math.random() * 3);
			
			ghost.moveGhost(character.getCx(), character.getCy());
			ghost1.moveGhost(character.getCx(), character.getCy());
			repaint();
			if(lose()){
				System.exit(0);
			}
		}
	}
	public boolean lose(){
		if(ghost.getGx() == character.getCx() && ghost.getGy() == character.getCy()) return true;
		if(ghost1.getGx() == character.getCx() && ghost1.getGy() == character.getCy()) return true;
		return false;
	}
	public void operate(int key){
		
		switch (key) {
		case KeyEvent.VK_UP:
			if (character.getCy() <= 0)
				return;
			else
				character.moveUp();
			break;

		case KeyEvent.VK_DOWN:
			if (character.getCy() >= 19)
				return;
			else
				character.moveDown();
			break;
		case KeyEvent.VK_LEFT:
			if (character.getCx() <= 0)
				return;
			else
				character.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			if (character.getCx() >= 19)
				return;
			else
				character.moveRight();
			break;
		}
		food[character.getCx()][character.getCy()] = 0;
		if(wincheck()){
			JOptionPane.showMessageDialog(null, "You Win!");
			timer.stop();
		}
		
		repaint();
		
	}
	public boolean wincheck(){
		for(int i = 0; i < 20; i++){
			for(int j = 0; j < 20; j++){
				if(food[i][j] == 1) return false;
			}
		}
		return true;
	}
	public void paint(Graphics g) {
		if(img == null) img = createImage(700,800);
		if(buffer == null) buffer = img.getGraphics();
		
		super.paintComponents(g);
		// LIGHT_GRAY 색으로 남아 있는 양을 보여 주기위해 그린다.
		buffer.setColor(Color.black);
		buffer.fillRect(30, 60, 600, 600);

		// GRAY 색으로 통의 외곽선을 그린다.
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (map.isWall(j, i)) {
					buffer.setColor(Color.ORANGE);
					buffer.fillRect(30 + (30 * j), 60 + (30 * i), 30, 30);
					buffer.setColor(Color.black);
					buffer.drawRect(30 + (30 * j), 60 + (30 * i), 30, 30);
					//g.drawImage(img, (character.getCx() * 30)+ 30, (character.getCy() * 30) + 60, this);
					
				}
				else {
					if(food[j][i] == 1) buffer.drawImage(Toolkit.getDefaultToolkit().getImage("smallDot.png"),(j * 30)+ 30, (i * 30) + 60, this);
				}
			}
		}
		
		
			
		
		buffer.drawImage(Toolkit.getDefaultToolkit().getImage("pacman.png"),(character.getCx() * 30)+ 30, (character.getCy() * 30) + 60, this);
		buffer.drawImage(Toolkit.getDefaultToolkit().getImage("enemy.png"),(ghost.getGx() * 30)+ 30, (ghost.getGy() * 30) + 60, this);
		buffer.drawImage(Toolkit.getDefaultToolkit().getImage("enemy.png"),(ghost1.getGx() * 30)+ 30, (ghost1.getGy() * 30) + 60, this);
		g.drawImage(img,0,0,this);

	}
	public void update(Graphics g) {
		paint(g);
	}
}	
	