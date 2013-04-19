import java.util.*;
import java.io.*;

public class Brackets {

	FastScanner in;
	PrintWriter out;

	public boolean isPair(char c1, char c2) {
		if ((c1 == '[') && (c2 == ']')) {
			return true;
		}
		if ((c1 == '(') && (c2 == ')')) {
			return true;
		}
		return false;
	}
	
	public boolean isClosed(char c) {
		if ((c == ')') || (c == ']')) {
			return true;
		}
		return false;
	}
	
	public boolean isRigthBrackets(String str) {
		ArrayStack stack = new ArrayStack();
		for (int i = 0; i < str.length(); i++) {
			if (stack.isEmpty()) {
				stack.push(str.charAt(i));
			} else if (isPair(stack.peek(), str.charAt(i))) {
				stack.pop();
			} else if (isClosed(str.charAt(i))) {
				return false;
			} else {
				stack.push(str.charAt(i));
			}
		}
		if (stack.size() > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public void solve() throws IOException {
		String str;
		while (!"".equals(str = in.next())) {
			if (isRigthBrackets(str)) {
				out.println("YES");
			} else {
				out.println("NO");
			}
		}
	}

	public void run() {
		try {
			in = new FastScanner(new File("brackets.in"));
			out = new PrintWriter(new File("brackets.out"));
			
			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class ArrayStack {
		private int size;
		private char[] elements = new char[5];

		public void push(char element) {
			//assert element != null;

			ensureCapacity(size + 1);
			elements[size++] = element;
		}


		private void ensureCapacity(int capacity) {
			if (capacity <= elements.length) {
				return;
			}
			char[] newElements = new char[2 * capacity];
			for (int i = 0; i < size; i++) {
				newElements[i] = elements[i];
			}
			elements = newElements;
		}

		public char pop() {
			assert size > 0;

			char value = peek();
			elements[--size] = 0;
			return value;
		}

		public char peek() {
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
		boolean Check;

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
			String line;
			try {
				if ((line = br.readLine()) == null) {
					setCheck(false);
				} else {
					while (st == null || !st.hasMoreTokens()) {
						st = new StringTokenizer(line);
					}
					setCheck(true);
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
		new Brackets().run();
	}
}
