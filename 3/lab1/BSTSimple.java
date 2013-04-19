import java.util.*;
import java.io.*;

public class BSTSimple {

	FastScanner in;
	PrintWriter out;
	
	public void solve() throws IOException {
		Priorityqueue queue = new Priorityqueue();
		
		String str;
		int x; // First arg
		int y; // Second arg
		// while "".equals(str = in.next()) == true
		
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
	
	class BST {
		Node left;
		Node right;
		Node parent;
		int key;
		
		void insert(int x) {
		
		}
		
		void delete(int x) {
		
		}
		
		boolean exists(int x) {
		
		}
		
		Node next(int x) { // if (exists): show key: show "none"
		
		}
		
		Node prev(int x) { // if (exists): show key: show "none"
		
		}
	}
	
	class Node {
		private Object element;
		private Node next;

		public Object getElement(){
			return element;
		}

		public void setElement(Object e){
			element = e;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node n) {
			next = n;
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
		new BSTSimple.run();
	}
}
