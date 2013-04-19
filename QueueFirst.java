import java.util.*;
import java.io.*;

public class QueueFirst {

	FastScanner in;
	PrintWriter out;

	public void solve() throws IOException {
		ArrayQueue queue = new ArrayQueue();
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
			in = new FastScanner(new File("queue1.in"));
			out = new PrintWriter(new File("queue1.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class ArrayQueue {
		private int size;
		private Object[] elements = new Object[10];
		private int tail = 0;
		private int head = 0;
	

		public void push(Object element) {
			assert element != null;
				
			ensureCapacity(size + 1);
			elements[tail] = element;
			tail = (tail + 1) % elements.length;
			size++;
		}

		private void ensureCapacity(int capacity) {
			if (capacity <= elements.length) {
				return;
			}
			Object[] newElements = new Object[2 * capacity];
			for (int i = 0; i < elements.length; i++) {
				if (i < head) {
					newElements[elements.length - head + i] = elements[i];
				} else {
					newElements[i - head] = elements[i];
				}
			}
			tail = elements.length;
			head = 0;
			elements = newElements;
		}
	
		public Object pop() {
			assert size > 0;
		
			Object value = elements[head];
			elements[head] = 0;
			head = (head + 1) % elements.length;
			size--;
			return value;
		}
	
		public int size() {
			return size;
		}

		public boolean isEmpty() {
			return (size == 0);
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
		new QueueFirst().run();
	}
}
