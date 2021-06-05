import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class FinalProject implements ActionListener {
  private JFrame jf = new JFrame();
  private JPanel jp = new JPanel();
	//to count how many times user has pressed button
	private static int numPress = 0;
  //two panels, one for each sort method
  private FPanel p1;
  private FPanel p2;

  //buttons at top of screen
  private JButton selection = new JButton("Select");
  private JButton insertion = new JButton("Insert");
  private JButton comb = new JButton("Comb");
  private JButton heap = new JButton("Heap");
  private JButton bubble = new JButton("Bubble");
  private JButton quick = new JButton("Quick");
  private JButton shell = new JButton("Shell");

	//reset button
	//private JButton reset = new JButton("Reset");

	//int to store sequence
	private int selectSeq;

  public FinalProject(){
		//to prompt user on which sequence they want
		Scanner sc = new Scanner(System.in);
		System.out.println("Select a sequence to sort: Type 0 for a random sequence, Type 1 for a nearly sorted sequence, Type 2 for a reversed sequence, Type 3 for a sequence with only a few unique elements, or Type 4 if you want to type your own sequence of 10 single-digit numbers.");
		selectSeq = sc.nextInt();
		int[] userGen = new int[10];
		String which = "";
		if (selectSeq == 0) {
			which = "Random";
		} else if (selectSeq == 1){
			which = "Nearly Sorted";
		} else if (selectSeq == 2){
			which = "Reversed";
		} else if (selectSeq == 3){
			which = "Few Unique";
		} else if (selectSeq == 4) {
			which = "User Gen";
			System.out.println("Please type in 10 SINGLE-DIGIT numbers in any order you want separated by a single space. E.g - 0 1 2 3 4 5 6 7 8 9");
			for(int i = 0; i<10; i++){
				userGen[i] = sc.nextInt();
			}
		}
      sc.close();
		System.out.println("You've selected a " + which + " sequence.");
		System.out.println("Now, you will choose two sorting methods. I recommend trying either a Select/Heap comparison, Insert/Shell comparison, or Bubble/Comb comparison, as the latter of each of these pairs is seen as the \"optimized\" version of the former.");
		p1 = new FPanel(20, 50, 464, 80, selectSeq, which, userGen);
		p2 = new FPanel(20, 150, 464, 80, selectSeq, which, userGen);
    jf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    jf.setTitle( "Visualizing Sort" );

    jp.setBackground(Color.WHITE);
    jp.setPreferredSize(new Dimension(504, 250));
    jp.setLayout(null);

    //setting bounds for buttons
    selection.setBounds(7, 5, 64, 30);
    insertion.setBounds(78, 5, 64, 30);
    comb.setBounds(149, 5, 64, 30);
    heap.setBounds(220, 5, 64, 30);
    bubble.setBounds(291, 5, 64, 30);
    quick.setBounds(362, 5, 64, 30);
    shell.setBounds(433, 5, 64, 30);
		
		//reset button
		//reset.setBounds(220, 250, 64, 30);

    //setting font size for buttons and alignment
    selection.setFont(new Font("Arial", Font.PLAIN, 10));
		selection.setMargin(new Insets(0, -8, 0, -8));
		selection.setHorizontalAlignment(SwingConstants.CENTER);
		insertion.setFont(new Font("Arial", Font.PLAIN, 10));
		insertion.setMargin(new Insets(0, -8, 0, -8));
		insertion.setHorizontalAlignment(SwingConstants.CENTER);
		comb.setFont(new Font("Arial", Font.PLAIN, 10));
		comb.setMargin(new Insets(0, -8, 0, -8));
		comb.setHorizontalAlignment(SwingConstants.CENTER);
		heap.setFont(new Font("Arial", Font.PLAIN, 10));
		heap.setMargin(new Insets(0, -8, 0, -8));
		heap.setHorizontalAlignment(SwingConstants.CENTER);
		bubble.setFont(new Font("Arial", Font.PLAIN, 10));
		bubble.setMargin(new Insets(0, -8, 0, -8));
		bubble.setHorizontalAlignment(SwingConstants.CENTER);
		quick.setFont(new Font("Arial", Font.PLAIN, 10));
		quick.setMargin(new Insets(0, -8, 0, -8));
		quick.setHorizontalAlignment(SwingConstants.CENTER);
		shell.setFont(new Font("Arial", Font.PLAIN, 10));
		shell.setMargin(new Insets(0, -8, 0, -8));
		shell.setHorizontalAlignment(SwingConstants.CENTER);
    // reset.setFont(new Font("Arial", Font.PLAIN, 10));
		// reset.setMargin(new Insets(0, -8, 0, -8));
		// reset.setHorizontalAlignment(SwingConstants.CENTER);
    //adding
    jp.add(selection);
    jp.add(insertion);
    jp.add(comb);
    jp.add(heap);
    jp.add(bubble);
    jp.add(quick);
    jp.add(shell);
		//jp.add(reset);
    jp.add(p1);
    jp.add(p2);

		//adding action listeners
		selection.addActionListener(this);
		insertion.addActionListener(this);
		comb.addActionListener(this);
		heap.addActionListener(this);
		bubble.addActionListener(this);
		quick.addActionListener(this);
		shell.addActionListener(this);
		//reset.addActionListener(this);

    jf.getContentPane().add(jp);
    jf.pack();
  }

	public void actionPerformed(ActionEvent e){
		if (e.getSource().equals(selection)) {
			if (numPress == 0) {
				numPress++;
				//set selected button as unclickable
				selection.setEnabled(false);
				p1.selectSort("Selection");
				p1.SS();
			} else {
				p2.selectSort("Selection");
				p2.SS();
				//set all buttons to unclickable
				setAllButtons(false);
				//start timer in each panel to begin sort
				p1.startTimer();
				p2.startTimer();
			}
		} else if (e.getSource().equals(insertion)) {
			if (numPress == 0) {
				numPress++;
				insertion.setEnabled(false);
				p1.selectSort("Insertion");
				p1.IS();
			} else {
				p2.selectSort("Insertion");
				p2.IS();
				setAllButtons(false);
				p1.startTimer();
				p2.startTimer();
			}
		} else if (e.getSource().equals(comb)) {
			if (numPress == 0) {
				numPress++;
				comb.setEnabled(false);
				p1.selectSort("Comb");
				p1.CS();
			} else {
				p2.selectSort("Comb");
				p2.CS();
				setAllButtons(false);
				p1.startTimer();
				p2.startTimer();
			}
		} else if (e.getSource().equals(heap)) {
			if (numPress == 0) {
				numPress++;
				heap.setEnabled(false);
				p1.selectSort("Heap");
				p1.HS();
			} else {
				p2.selectSort("Heap");
				p2.HS();
				setAllButtons(false);
				p1.startTimer();
				p2.startTimer();
			}
		} else if (e.getSource().equals(bubble)) {
			if (numPress == 0) {
				numPress++;
				bubble.setEnabled(false);
				p1.selectSort("Bubble");
				p1.BS();
			} else {
				p2.selectSort("Bubble");
				p2.BS();
				setAllButtons(false);
				p1.startTimer();
				p2.startTimer();
			}
		} else if (e.getSource().equals(quick)) {
			if (numPress == 0) {
				numPress++;
				quick.setEnabled(false);
				p1.selectSort("Quick");
				p1.QS();
			} else {
				p2.selectSort("Quick");
				p2.QS();
				setAllButtons(false);
				p1.startTimer();
				p2.startTimer();
			}
		} else if (e.getSource().equals(shell)) {
			if (numPress == 0) {
				numPress++;
				shell.setEnabled(false);
				p1.selectSort("Shell");
				p1.SHS();
			} else {
				p2.selectSort("Shell");
				p2.SHS();
				setAllButtons(false);
				p1.startTimer();
				p2.startTimer();
			}
		}
	}

	private void setAllButtons(boolean click){
		selection.setEnabled(click);
		insertion.setEnabled(click);
		comb.setEnabled(click);
		heap.setEnabled(click);
		bubble.setEnabled(click);
		quick.setEnabled(click);
		shell.setEnabled(click);
	}
  public void display() {
        EventQueue.invokeLater(new Runnable() {
                public void run() {
									jf.setVisible(true);
									//jf.toFront();
									//jp.setVisible(true);
									//jf.repaint();
                }
            });
    }
}
