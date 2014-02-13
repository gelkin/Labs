import java.util.*;
import java.io.*;

public class Lab3 {

	FastScanner in;
	PrintWriter out;
	
	private final int QUEUE_SIZE = 1000000;
	
	public void solve() throws IOException {
		Priorityqueue queue = new Priorityqueue();
		
		String str;
		int x; // First arg
		int y; // Second arg
		
		int counter = 0; // Counts a number of operation
		while (!"".equals(str = in.next())) {
			if ("push".equals(str)) {
				x = in.nextInt();
				queue.push(x, counter);
			} else if ("extract-min".equals(str)) {
				if (queue.getSize() != 0) {
					out.print(queue.extractMin());
				} else {
					out.print("*");
				}
				out.print("\n");
			} else {
				x = in.nextInt();
				y = in.nextInt();
				queue.decreaseKey(x - 1, y);
			}
			counter++;
		}
	}

	public void run() {
		try {
			in = new FastScanner(new File("priorityqueue.in"));
			out = new PrintWriter(new File("priorityqueue.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class Priorityqueue {
		private int size;
		private int[] elements = new int[QUEUE_SIZE];
		private int[] numOfOp = new int[QUEUE_SIZE];
		private int[] opWithNum = new int[QUEUE_SIZE];
		
		
		public void push(int element, int n) {
			elements[size] = element;
			opWithNum[n] = size;
			numOfOp[size] = n;
			size++;
			siftUp(size - 1);
		}
		
		
		public int extractMin() {
			assert size > 0;
			
			int min = elements[0];
			elements[0] = elements[size - 1];
			size--;
			numOfOp[0] = numOfOp[size];
			opWithNum[numOfOp[0]] = 0;
			siftDown(0);
			return min;
		}
		
		public void decreaseKey(int n, int x) {
			elements[opWithNum[n]] = x;
			siftUp(opWithNum[n]);
		}
		
		public int getSize() {
			return size;
		}
		
		private void swapElements(int i, int j) {
			int x = elements[i];
			elements[i] = elements[j];
			elements[j] = x;
		}
		
		private void swapOpWithNum(int i, int j) {
			opWithNum[numOfOp[i]] = j;
			opWithNum[numOfOp[j]] = i;
		}
		
		private void swapNumOfOp(int i, int j) {
			int x = numOfOp[i];
			numOfOp[i] = numOfOp[j];
			numOfOp[j] = x;
		}
		
		private void siftUp(int i) {
			if(i == 0) {
				return;
			}
			if (elements[i] < elements[(i - 1)/2]){
				
				swapElements(i, (i - 1)/2);
				swapOpWithNum(i, (i - 1)/2);
				swapNumOfOp(i, (i - 1)/2);
				
				siftUp((i - 1)/2);
			}
		}
		
		private void siftDown(int i) {
			if ((size - 1) >= (2*i + 1)) {
				int indOfMin = 2*i + 1;
				if ((size - 1) >= (2*i + 2) && elements[indOfMin] > elements[2*i + 2]) {
					indOfMin = 2*i + 2;
				}
				if (elements[i] > elements[indOfMin]) {
					swapElements(i, indOfMin);
					swapOpWithNum(i, indOfMin);
					swapNumOfOp(i, indOfMin);
					
					siftDown(indOfMin);
				}
			} else {
				return;
			}
		}
	}
	
	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean Check;
		String line;

		FastScanner(File f) {
			try {
				br = new BufferedReader(new FileReader(f));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		boolean getCheck() {
			return Check;
		}

		void setCheck(boolean b) {
			Check = b;
		}

		String next() {
			try {
				if (st == null || !st.hasMoreTokens()) {
					if ((line = br.readLine()) != null) {
						st = new StringTokenizer(line);
						setCheck(true);
					} else {
						setCheck(false);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (getCheck()) {
				return st.nextToken();
			} else {
				return "";
			} 
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}

	public static void main(String[] args) {
		new Lab3().run();
	}
}
