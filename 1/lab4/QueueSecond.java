import java.util.*;
import java.io.*;

public class QueueSecond {

	FastScanner in;
	PrintWriter out;

	public void solve() throws IOException {
		LinkedQueue queue = new LinkedQueue();
		int num = in.nextInt();
		for (int i = 0; i < num; ++i) {
			if("+".equals(in.next())) {
				queue.push(in.nextInt());
			} else {
				out.println(queue.pop());
			}
		}
	}

	public void run() {
		try {
			in = new FastScanner(new File("queue2.in"));
			out = new PrintWriter(new File("queue2.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class LinkedQueue {
		private int size;
		private Node head;
		private Node tail;
	

		public void push(Object element) {
			assert element != null;

			Node OldTail = new Node();
			OldTail = tail;
			tail = new Node();
			tail.setElement(element);
			if (size != 0) {
				OldTail.next = tail;
			} else {
				head = tail;
			}
			size++;
		}

		public Object pop() {
			assert size > 0;

			Node OldHead = new Node();
			OldHead = head;
			head = head.next;
			size--;
			return OldHead.getElement();
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
		new QueueSecond().run();
	}
}
