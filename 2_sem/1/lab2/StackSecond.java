import java.util.*;
import java.io.*;

public class StackSecond {

	FastScanner in;
	PrintWriter out;

	public void solve() throws IOException {
		LinkedStack stack = new LinkedStack();
		int num = in.nextInt();
		for (int i = 0; i < num; ++i) {
			if("+".equals(in.next())) {
				stack.push(in.nextInt());
			} else {
				out.println(stack.pop());
			}
		}
	}

	public void run() {
		try {
			in = new FastScanner(new File("stack2.in"));
			out = new PrintWriter(new File("stack2.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class LinkedStack {
		private Node head;
		private int size;

		public void push(Object element) {
			assert element != null;

			Node OldHead = new Node();
			Node NewHead = new Node();
			OldHead = head;
			NewHead.setElement(element);
			NewHead.setNext(OldHead);
			head = NewHead;
			size++;
		}

		public Object pop() {
			assert size > 0;

			Node OldHead = new Node();
			OldHead = head;
			Object element = OldHead.getElement();
			head = OldHead.getNext();
			return element;
		}

		public Object peek() {
			assert size > 0;

			return head.getElement();
		}

		public int size() {
			return size;
		}

		public boolean isEmpty() {
			return size == 0;
		}
	}
	
	public class Node {
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

		FastScanner(File f) {
			try {
				br = new BufferedReader(new FileReader(f));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}

	public static void main(String[] args) {
		new StackSecond().run();
	}
}
