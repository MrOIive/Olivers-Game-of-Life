import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;

@SuppressWarnings("serial")
public class MyFrame extends JFrame implements KeyListener, MouseListener{
  public static OutlineLabel gen = new OutlineLabel("Generation: 1");
  public static JLabel[][] labelArr = new JLabel[28][28];
  boolean yes = true;

  MyFrame(){
    this.setTitle("Oliver's Game of Life");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(700,700);
    this.setLayout(null);
    this.setResizable(false);
    this.addKeyListener(this);
    this.setVisible(false);

    gen.setFont(new Font("Comic Sans", Font.BOLD, 12));
    gen.setOutlineColor(Color.BLACK);
    gen.setForeground(Color.WHITE);
    gen.setBounds(0,0,700,25);

    this.add(gen);
    
    int addX = 0;
    int addY = 0;
    for (int i = 0; i < Main.arrX; i++) {
      for (int j = 0; j < Main.arrY; j++) {
       labelArr[i][j] = new JLabel();
      // first two move second two make size
      labelArr[i][j].setBounds(addY, addX, 25, 25);
      updateColor(i,j);
      labelArr[i][j].addMouseListener(this);
      labelArr[i][j].putClientProperty("firstIndex", i);
      labelArr[i][j].putClientProperty("secondIndex", j);
      this.add(labelArr[i][j]);
      labelArr[i][j].setBackground(Color.white);
      labelArr[i][j].setOpaque(true);
      labelArr[i][j].setVisible(true);
        addX += 25;
      }
      addX = 0;
      addY += 25;
    }
    this.setVisible(true);
  }
  
  @Override
 public void keyTyped(KeyEvent e) {
 }

 @Override
 public void keyReleased(KeyEvent e) {
 }
  public void labelWhite(int i, int j) {
  labelArr[i][j].setBackground(Color.white);

  }
  public void labelBlack(int i, int j) {
    labelArr[i][j].setBackground(Color.black);

  }
  @Override
  public void mousePressed(MouseEvent e) {
    JLabel label = (JLabel) e.getSource();
    Integer firstIndex = (Integer) label.getClientProperty("firstIndex");
    Integer secondIndex = (Integer) label.getClientProperty("secondIndex" );
    if (labelArr[firstIndex][secondIndex].getBackground() == Color.WHITE) {
      updateColor(firstIndex, secondIndex);
      Main.arr[firstIndex][secondIndex] = Main.CELL;
      Main.endArr[firstIndex][secondIndex] = Main.CELL;
      addBack(firstIndex, secondIndex);
      labelArr[firstIndex][secondIndex].setBackground(Color.BLACK); 
    } else {
      updateColor(firstIndex, secondIndex);
      Main.arr[firstIndex][secondIndex] = Main.CELL;
      Main.endArr[firstIndex][secondIndex] = Main.CELL;
      addBack(firstIndex, secondIndex);
      labelArr[firstIndex][secondIndex].setBackground(Color.WHITE);
    }
  }
  public void updateColor(int i, int j) {
    this.remove(labelArr[i][j]);
    gen.setVisible(false);
    gen.setOutlineColor(Color.white);
    gen.setForeground(Color.black);
    gen.setOutlineColor(Color.BLACK);
    gen.setForeground(Color.WHITE);
    gen.setVisible(true);
  }
  public void addBack(int i, int j) {
    this.add(labelArr[i][j]);
  }
  @Override
    public void keyPressed(KeyEvent e) {
      switch(e.getKeyCode()) {
        case KeyEvent.VK_SPACE: Main.stop();
          break;
        case KeyEvent.VK_DOWN: Main.waitTime =+ 10;
          break;
         case KeyEvent.VK_UP:if (Main.waitTime != 50) {Main.waitTime =- 10;}
          break;
      }
    }
  @Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}