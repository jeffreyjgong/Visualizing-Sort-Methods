import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.util.*;

public class FPanel extends JPanel implements ActionListener {
  private javax.swing.Timer timer;
	private SquareList s;
	private int moveNum = 0;
	private int swapsC = 0;
	private String selectedSeq;
	private String selectedSortM;

  public FPanel(int x, int y, int w, int h, int seq, String selectSeq, int[] row5){
    setBounds(x, y, w, h);
		selectedSeq = selectSeq;
    setBorder( BorderFactory.createLineBorder(Color.BLACK ) );
		timer = new javax.swing.Timer(750, this);
		s = new SquareList(seq, x + 5, y + 30, w - 10, h - 30, row5);
  }
  
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
		g.drawString("Sort Method: " + selectedSortM + ", Sequence: " + selectedSeq + "", 15, 15);
		g.drawString("Steps: " + moveNum + ", Swaps: " + swapCount(moveNum), 15, 74);
		for(int i = 0; i<10; i++) {
			//System.out.println("x: " + s.getList()[i].getX() + ", y: " + s.getList()[i].getY() + ", val:" + s.getList()[i].getValue());
			s.getList()[i].draw(g, false);
			if (!s.getMoves().isEmpty() && (3*(moveNum-1) < s.getMoves().size())){
				if (i == s.getMoves().get(3*(moveNum-1)+1) || i == s.getMoves().get(3*(moveNum-1)+2)) {
					s.getList()[i].draw(g, true);
					//System.out.println("paint movenum " + moveNum);
				}
			}
			
		}
  }
 
	//start and stop panel timer
	public void startTimer() {
		timer.start();
	}
	public void stopTimer() {
		timer.stop();
	}

	//mutator for selected sort method
	public void selectSort(String str) {
		selectedSortM = str;
	}

  public void actionPerformed(ActionEvent e){ 
		//so the selected sort string updates
		//repaint();
		if (moveNum == s.getMoves().size()/3) {
			this.stopTimer();
			// for(int x : s.getMoves()) {
			// 	System.out.print(x + " ");
			// }
			System.out.println("Stopped timer: " + selectedSortM);
		}
		if(moveNum < s.getMoves().size()/3){
			if (s.getMoves().get(3*moveNum) == 0) {
				s.moveSwap(s.getMoves().get(3*moveNum+1), s.getMoves().get(3*moveNum+2));
			}
			moveNum++;
			//System.out.println("action moveNum: " + moveNum);
		}
		repaint();
	}
	
	//count swaps
	public int swapCount(int moveNumCurr){
		if (s.getMoves().isEmpty()) {
			return 0;
		}
		if (s.getMoves().get((moveNumCurr-1)*3) == 0) {
			swapsC = Math.min(moveNumCurr, swapsC+1);
			//System.out.println(swapsC + " " + moveNumCurr);
		}
		return swapsC;
	}
	//panel calling the squarelist sort method

	public void SS(){
		s.selectionSort();
	}

	public void IS(){
		s.insertionSort();
	}

	public void CS(){
		s.combSort();
	}

	public void HS(){
		s.heapSort();
	}

	public void BS(){
		s.bubbleSort();
	}

	public void QS(){
		s.quickSort(0, 9);
	}

	public void SHS(){
		s.shellSort();
	}
}
