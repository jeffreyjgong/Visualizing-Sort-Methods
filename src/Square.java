import java.awt.*;
//import java.util.*;

public class Square {
  private int value, x, y;
  private Color fillColor;

  public Square(int n, int x, int y){
    this.value = n;
    fillColor = new Color(169, 214, 249);
    this.x = x;
    this.y = y;
  }

	public void draw(Graphics g, boolean red){
		if (!red){
			g.setColor(fillColor);
			g.fillRect(x, y, 15, 15);
		} else if (red){
			g.setColor(new Color(251, 67, 67));
			g.fillRect(x, y, 15, 15);
		}
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 15, 15);
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.drawString(Integer.toString(value), x+4, y+12);
		
	}

	
	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void move(int newX){
		x += newX;
		return;
	}
	public int getValue() {
		return value;
	}
}
