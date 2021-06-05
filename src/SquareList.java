//import java.awt.*;
import java.util.*;

public class SquareList {
	private Square[] list;
	//the moves needed to be done for selected sort method
	//format is every 3 elements is a group with 3*n being 0 or 1, 0 meaning swap, 1 meaning don't swap, 3*n+1 being the first thing to look at, and 3*n+2 being the second thing to look at
	private ArrayList<Integer> moves = new ArrayList<Integer>();
	private int[] copy;
  private int x, y, w, h, whichSeq;
	//sequences[0] is random, sequences[1] is nearly sorted, sequences[2] is reversed, sequences[3] is few unique
  private int[][] SEQUENCES = {{5, 1, 6, 0, 2, 7, 3, 8, 9, 4}, {0, 1, 3, 4, 2, 5, 7, 6, 8, 9}, {9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, {3, 2, 1, 3, 2, 1, 3, 2, 1, 3}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
	private final int[] pos = {20, 65, 110, 155, 200, 245, 290, 335, 380, 425};

  public SquareList(int select, int x, int y, int w, int h, int[] row5){
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
		
		this.whichSeq = select;
		for(int i = 0; i<10; i++){
			SEQUENCES[4][i] = row5[i];
		}
		this.copy = getIntList(select);
    list = new Square[10];
    for(int i = 0; i<10; i++){
      list[i] = new Square(SEQUENCES[select][i], 20 + i*45, 35);
    }
		
  }
	
	private void swap(int n1, int n2){
		Square temp = list[n1];
		list[n1] = list[n2];
		list[n2] = temp;
	}

	public void moveSwap(int n1, int n2){
		//dist each square will move when swapped
		int dist = Math.abs(pos[n1] - pos[n2]);
		if (n1 < n2) {
			list[n1].move(dist);
			list[n2].move(-1*dist);
			swap(n1, n2);
		} else {
			list[n1].move(-1*dist);
			list[n2].move(dist);
			swap(n1, n2);
		}
	}

	public Square[] getList(){
		return list;
	}
	private int[] getIntList(int row){
		return SEQUENCES[row];
	}
	//Sort methods
	public void selectionSort(){
		//0 is swap, 1 is no swap but only consideration
		//int[] copy = getIntList(whichSeq);
		//make copy of array being sorted and use it here
		for(int i = 0; i<9; i++){
			int min_idx = i;
			for(int j = i+1; j<10; j++){
				moves.add(1);
				moves.add(min_idx);
				moves.add(j);
				if (copy[j] < copy[min_idx]) {
					min_idx = j;
				}
				
			}

			moves.add(0);
			moves.add(min_idx);
			moves.add(i);
			int temp = copy[min_idx];
			copy[min_idx] = copy[i];
			copy[i] = temp;
		}
	}

	public void insertionSort(){
		//int[] copy = getIntList(whichSeq);
		for(int step = 1; step<10; step++){
			int key = copy[step];
			int j = step-1;
			moves.add(1);
			moves.add(step);
			moves.add(step-1);
			while(j >= 0 && key < copy[j]){
				copy[j+1] = copy[j];
				moves.add(0);
				moves.add(j);
				moves.add(j+1);
				--j;
			}

			copy[j+1] = key;
		}
	}

	public void combSort(){
		//int[] copy = getIntList(whichSeq);
		int gap = copy.length;
		int n = copy.length;
		boolean swapped = true;
		while (gap != 1 || swapped == true){
			gap = getNextGap(gap);
			swapped = false;
			for(int i = 0; i<n-gap; i++){
				if (copy[i] > copy[i+gap]){
					int temp = copy[i];
					copy[i] = copy[i+gap];
					copy[i+gap] = temp;
					moves.add(0);
					moves.add(i);
					moves.add(i+gap);
					swapped = true;
				} else {
					moves.add(1);
					moves.add(i);
					moves.add(i+gap);
				}
			}
		}
	}

	private int getNextGap(int gap){
		gap = (gap*10)/13;
		if (gap < 1) {
			return 1;
		}
		return gap;
	}

	public void heapSort(){
		int n = copy.length;

		for(int i = n/2 - 1; i>=0; i--){
			heapify(n, i);
		}

		for(int i = n-1; i>0; i--){
			int temp = copy[0];
			copy[0] = copy[i];
			copy[i] = temp;
			moves.add(0);
			moves.add(0);
			moves.add(i);

			heapify(i, 0);
		}
	}

	private void heapify(int n, int i){
		int largest = i;
		int l = 2*i+1;
		int r = 2*i+2;

		if (l < n && copy[l] > copy[largest]){
			largest = l;
		}

		if (r < n && copy[r] > copy[largest]){
			largest = r;
		}

		if (largest != i){
			int temp = copy[i];
      copy[i] = copy[largest];
      copy[largest] = temp;
			moves.add(0);
			moves.add(i);
			moves.add(largest);
			heapify(n, largest);
		} else if (largest == i){
			moves.add(1);
			moves.add(largest);
			moves.add(largest);
		}
	}

	public void bubbleSort(){
		//int[] copy = getIntList(whichSeq);
		int i, j, temp;
		int n = copy.length;
		boolean swapped;
		for(i = 0; i < n-1; i++){
			swapped = false;
			for(j = 0; j < n-i-1; j++){
				if (copy[j] > copy[j+1]) {
					temp = copy[j];
					copy[j] = copy[j+1];
					copy[j+1] = temp;
					swapped = true;
					moves.add(0);
					moves.add(j);
					moves.add(j+1);
				} else {
					moves.add(1);
					moves.add(j);
					moves.add(j+1);
				}
			}
			if (swapped == false) {
				break;
			}
		}
	}

	public void quickSort(int low, int high){
		if (low < high){
			int pi = partition(low, high);
			quickSort(low, pi-1);
			quickSort(pi+1, high);
		}
	}
	
	private int partition(int low, int high){
		int pivot = copy[high];
		int i = (low-1);
		for(int j = low; j <= high; j++){
			if (copy[j] < pivot){
				i++;
				if (i != j) {
					int temp = copy[i];
					copy[i] = copy[j];
					copy[j] = temp;
					moves.add(0);
					moves.add(i);
					moves.add(j);
				} else {
					moves.add(1);
					moves.add(j);
					moves.add(high);
				}
			}
		}
		if (i+1 != high) {
			int temp2 = copy[i+1];
			copy[i+1] = copy[high];
			copy[high] = temp2;
			moves.add(0);
			
		} else {
			moves.add(1);
		}
		moves.add(i+1);
		moves.add(high);
		return (i+1);
	}

	public void shellSort(){
		int n = copy.length;
		for(int gap = n/2; gap > 0; gap /= 2){
			for(int i = gap; i<n; i+=1){
				int temp = copy[i];
				int j;
				moves.add(1);
				moves.add(i);
				moves.add(i-gap);
				for(j = i; j >= gap && copy[j-gap] > temp; j-=gap){
					copy[j] = copy[j-gap];
					moves.add(0);
					moves.add(j);
					moves.add(j-gap);
				}
				copy[j] = temp;
			}
		}
	}

	//return moves ArrayList
	public ArrayList<Integer> getMoves(){
		return moves;
	}
}
