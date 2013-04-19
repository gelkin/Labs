import java.util.*;
import java.io.*;

public class Postfix {

	FastScanner in;
	PrintWriter out;

	public void solve() throws IOException {
		ArrayStack stack = new ArrayStack();
		String str;
		int res = 0;
		while (in.getNumber() > 0) {
			str = in.next();
			if ("+".equals(str)) {
				res = (stack.pop() + stack.pop());
				stack.push(res);
			} else if ("-".equals(str)) {
				res = stack.pop();
				res = stack.pop() - res;
				stack.push(res);
			} else if ("*".equals(str)) {
				res = (stack.pop() * stack.pop());
				stack.push(res);
			} else {
				stack.push(Integer.parseInt(str));
			}
		}
		out.println(stack.pop());
	}

	public void run() {
		try {
			in = new FastScanner(new File("postfix.in"));
			out = new PrintWriter(new File("postfix.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class ArrayStack {
		private int size;
		private int[] elements = new int[5];

		public void push(int element) {
			//assert element != null;

			ensureCapacity(size + 1);
			elements[size++] = element;
		}


		private void ensureCapacity(int capacity) {
			if (capacity <= elements.length) {
				return;
			}
			int[] newElements = new int[2 * capacity];
			for (int i = 0; i < size; i++) {
				newElements[i] = elements[i];
			}
			elements = newElements;
		}

		public int pop() {
			assert size > 0;

			int value = peek();
			elements[--size] = 0;
			return value;
		}

		public int peek() {
			assert size > 0;

			return elements[size - 1];
		}

		public int size() {
			return size;
		}

		public boolean isEmpty() {
			return size == 0;
		}
	}
	
	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		int Number = 1;

		FastScanner(File f) {
			try {
				br = new BufferedReader(new FileReader(f));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		int getNumber() {
			return Number;
		}
		
		void setNumber(int x) {
			Number = x;
		}
		
		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			setNumber(st.countTokens() - 1);
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}

	public static void main(String[] args) {
		new Postfix().run();
	}
}
