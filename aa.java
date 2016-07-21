package packman;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class aa extends JFrame {
	CoffeeMachinePanel coffeeMachinePanel; // Ŀ�� �ùķ��̼� �г�

	public aa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		coffeeMachinePanel = new CoffeeMachinePanel();

		add(new TitlePanel(), BorderLayout.NORTH); // "Welcome, Hot Coffee !!" ����ϴ� �г�
		add(coffeeMachinePanel, BorderLayout.CENTER); // Ŀ�� ���Ǳ� �ùķ��̼�, ��� ���� �����ִ� �г�
		add(new ButtonPanel(), BorderLayout.SOUTH); //3 ������ Ŀ�� ���� ��ư�� "Reset" ��ư�� ���� �г�
		
		setSize(450,400);
		setResizable(false); // ����ڰ� �������� ũ�� ������ �� ������ ����
		setVisible(true);
	}

	// ����Ʈ���� NORTH�� �����Ǵ� �гημ� "Welcome, Hot Coffee !!"�� ����ϱ� ���� ����
	class TitlePanel extends JPanel {
		JLabel titleMsg = new JLabel("Welcome, Hot Coffee !!");
		public TitlePanel() {
			titleMsg.setHorizontalAlignment(JLabel.CENTER); // ���̺��� ���ڿ��� �߾ӿ� ����
			setBackground(Color.MAGENTA);			
			add(titleMsg);
		}
	}
	
	// ����Ʈ���� SOUTH�� �����Ǵ� �гημ� 3 ������ Ŀ�� ���� ��ư�� "Reset" ��ư�� �ޱ� ���� ����
	class ButtonPanel extends JPanel {
		JButton [] coffeeButtons = new JButton[4]; // 3 ���� Ŀ�� ���� ��ư�� "Reset" ��ư
		String [] names = {"Black Coffee", "Sugar Coffee", "Dabang Coffee", "Reset"}; //��ư ���ڿ� �迭
		
		public ButtonPanel() {
			for(int i=0; i<coffeeButtons.length; i++) { // ��ư �迭�� ����(4)��ŭ ����
				coffeeButtons[i] = new JButton(names[i]); // ��ư ������Ʈ ����
				add(coffeeButtons[i]); // ��ư �ޱ�
				
				// ��� ��ư�� Action ������ ���
				coffeeButtons[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) { // 
							// e.getActionCommand()�� Ŭ���� ��ư�� ���ڿ� ���� ����
							coffeeMachinePanel.operate(e.getActionCommand());				
						}
				});
			}
		}
	}
	
	
	// Ŀ�� ���ǱⰡ �ùķ��̼��ϰ� ��� ������� �����ִ�  �г�. ��ġ�����ڸ� null�� ����
	class CoffeeMachinePanel extends JPanel {
		BoxLabel [] boxes = new BoxLabel[5]; // 5 ���� ������� ǥ���ϴ� ���̺� ������Ʈ �迭
		JLabel coffeeImageLabel; // Ŀ�ǰ� ������ �� �̸� �����ִ�Ŀ���� �̹��� ���̺�
		
		 // BoxLabel[]�� ���� �ε���
		final int CUP = 0;
		final int COFFEE = 1;
		final int WATER = 2;
		final int SUGAR = 3;
		final int CREAM = 4;
		
		// �� ������� �̸� ���ڿ�, ���̺�� ����� �� �ؿ� �����
		String [] boxNames = {"Cup", "Coffee", "Water", "Sugar", "Cream"};
		
		// Ŀ������ �̹��� ��ü
		ImageIcon coffeeIcon = new ImageIcon("images/coffee.jpg");
		
		public CoffeeMachinePanel() {
			setLayout(null); // ������Ʈ�� ��ġ�� ������� ������ �� �ֵ��� null�� ����
			
			for(int i=0; i<boxes.length; i++) {
				boxes[i] = new BoxLabel(); // ������� ǥ���ϴ� ���̺� ������Ʈ ����
				boxes[i].setLocation(30+80*i, 10); // ���̺��� ��ġ ����
				boxes[i].setSize(40, 100); // ���̺��� ũ�� ����
				
				JLabel text = new JLabel(boxNames[i]); // ����� �ؿ� ����� ���ڿ�
				text.setLocation(30+80*i, 120);
				text.setSize(50, 30);
				
				add(boxes[i]);
				add(text);
			}
			
			coffeeImageLabel = new JLabel(); // Ŀ������ �̹����� ����� �̹��� ���̺�			
			coffeeImageLabel.setLocation(180, 200);
			coffeeImageLabel.setSize(coffeeIcon.getIconWidth(), coffeeIcon.getIconHeight());
			
			add(coffeeImageLabel);
		}
		
		public void operate(String cmd) { // ���Ǳ� �ùķ������� �ٽ� ó�� ��ƾ
			if(cmd.equals("Black Coffee")) { // ������ ��ư�� "Black Coffee" �̸�
				if(boxes[CUP].isEmpty() || boxes[COFFEE].isEmpty() || boxes[WATER].isEmpty()) {
					error("������ ���� �ֽ��ϴ�. ä���ּ���."); // ���â�� ����Ѵ�.
					return;
				}
				else {
					boxes[CUP].consume(); // ���� �ϳ� ���δ�.
					boxes[COFFEE].consume(); // Ŀ�� ���� �ϳ� ���δ�.
					boxes[WATER].consume(); // �� ���� �ϳ� ���δ�.
				}
			}
			else if(cmd.equals("Sugar Coffee")) { // ������ ��ư�� "Sugar Coffee" �̸�
				if(boxes[CUP].isEmpty() || boxes[COFFEE].isEmpty() || boxes[WATER].isEmpty() ||
						boxes[SUGAR].isEmpty()) {
					error("������ ���� �ֽ��ϴ�. ä���ּ���."); // ���â�� ����Ѵ�.
					return;
				}		
				else {
					boxes[CUP].consume();  // ���� �ϳ� ���δ�.
					boxes[COFFEE].consume(); // Ŀ�� ���� �ϳ� ���δ�.
					boxes[WATER].consume(); // �� ���� �ϳ� ���δ�.
					boxes[SUGAR].consume();	// ���� ���� �ϳ� ���δ�.				
				}
			}
			else if(cmd.equals("Dabang Coffee")) { // ������ ��ư�� "Dabang Coffee" �̸�
				if(boxes[CUP].isEmpty() || boxes[COFFEE].isEmpty() || boxes[WATER].isEmpty() ||
						boxes[SUGAR].isEmpty() || boxes[CREAM].isEmpty()) {
					error("������ ���� �ֽ��ϴ�. ä���ּ���."); // ���â�� ����Ѵ�.
					return;
				}
				else {
					boxes[CUP].consume(); // ���� �ϳ� ���δ�.
					boxes[COFFEE].consume(); // Ŀ�� ���� �ϳ� ���δ�.
					boxes[WATER].consume(); // �� ���� �ϳ� ���δ�.
					boxes[SUGAR].consume();	// ���� ���� �ϳ� ���δ�.	
					boxes[CREAM].consume();	// ũ�� ���� �ϳ� ���δ�.	
				}
			}
			else { // // ������ ��ư�� "reset" �̸�
				boxes[CUP].reset(); // ���� �ʱ� ���¿� ���� ���� ä���.
				boxes[COFFEE].reset(); // Ŀ�Ǹ� �ʱ� ���¿� ���� ���� ä���.
				boxes[WATER].reset(); // ���� �ʱ� ���¿� ���� ���� ä���.
				boxes[SUGAR].reset(); // ������ �ʱ� ���¿� ���� ���� ä���.	
				boxes[CREAM].reset(); // ũ���� �ʱ� ���¿� ���� ���� ä���.
				repaint(); // boxes[]�� ���̺� ������Ʈ�� ��ȭ�� �������Ƿ� �θ𿡰� �ٽ� �׸����� �Ѵ�.
				return;
			}

			repaint(); // boxes[]�� ���̺� ������Ʈ�� ��ȭ�� �������Ƿ� �θ𿡰� �ٽ� �׸����� �Ѵ�.
			
			// ���������� Ŀ�ǰ� ������ �Ǿ����Ƿ� Ŀ������ ����Ѵ�.
			this.coffeeImageLabel.setIcon(coffeeIcon);
			
			// Ŀ�ǰ� �������� �˷��ִ� �޽��� â�� ����Ѵ�.
			JOptionPane.showMessageDialog(null, "�߰ſ���, ��ſ� �Ϸ�~~", "Ŀ�ǳ��Խ��ϴ�.", 
					JOptionPane.INFORMATION_MESSAGE);
			
			// Ŀ���� �̹����� �����.
			coffeeImageLabel.setIcon(null);

		}
			
		public void error(String msg) { // ���â�� ����ϴ� �޼ҵ�
			JOptionPane.showMessageDialog(null, msg, "Ŀ�� ���Ǳ� ���", 
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}

	// ������� �����ϴ� ���̺� Ŭ����
	class BoxLabel extends JLabel {
		final int MAXSIZE = 10; // �� ũ��
		int currentSize; // ���� �뿡 ��� �ִ� ����� ��
		
		public BoxLabel() {
			currentSize = MAXSIZE; // �ʱ⿡�� �뿡 ��ᰡ ����ä���� �ֵ��� ����				
		}
		boolean consume() { // ��Ḧ �ϳ� �Һ��Ѵ�.
			if(currentSize > 0) {
				currentSize--;
				return true;
			}
			else
				return false;
		}
		
		void reset() { // ���� �ʱ� ������ ������ �����Ѵ�. 
			currentSize = MAXSIZE; 
		}				
	
		boolean isEmpty() { return currentSize == 0; }
		
		// �뿡 ���� �ִ� ����� ���� �����ֱ� ���� �ۼ���
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			// LIGHT_GRAY ������ ���� �ִ� ���� ���� �ֱ����� �׸���.
			g.setColor(Color.LIGHT_GRAY);
			int y = this.getHeight() - (currentSize*this.getHeight() / MAXSIZE);
			g.fillRect(0, y, this.getWidth(), this.getHeight() - y);
			
			// GRAY ������ ���� �ܰ����� �׸���.			
			g.setColor(Color.GRAY);
			g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);			
		}
	}
	
	static public void main(String [] args) {
		new aa();
	}
}
 
