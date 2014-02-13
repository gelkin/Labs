import java.util.*;
import java.io.*;

public class MainSet {

	FastScanner in;
	PrintWriter out;
	
	public final int MIL = 1000000;
	
	public void solve() throws IOException {
		String str;
		int num;
		// int x; // First arg
		//int y; // Second arg
		// while "".equals(str = in.next()) == true
		
		Set set = new Set();
		while (!"".equals(str = in.next())) {
			num = in.nextInt();
			switch(str) {
			
			case "insert":
				set.insert(num);
				break;
			case "delete":
				set.delete(num);
				break;
			case "exists":
				out.println(set.exists(num));
				break;
			default:
				System.out.println("Ошибка!");
			}
		}
	}

	public void run() {
		try {
			in = new FastScanner(new File("set.in"));
			out = new PrintWriter(new File("set.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Set {
		private ArrayList<ArrayList<Integer>> set = new ArrayList<ArrayList<Integer>>(MIL);
		
		public Set() {
			for (int i = 0; i < MIL; i++) {
				set.add(i, new ArrayList<Integer>());
			}
		}
		
		int hash(int x) {
			return Math.abs(x % MIL);
		}
		
		boolean exists(int x) {
			int y = hash(x);
			for (int i = 0; i < set.get(y).size(); i++) {
				if (set.get(y).get(i) == x) {
					return true;
				}
			}
			return false;
		}
		
		void insert(int x) {
			int y = hash(x);
			for (int i = 0; i < set.get(y).size(); i++) {
				if (set.get(y).get(i) == x) {
					return;
				}
			}
			set.get(y).add(x);
		}
		
		void delete(int x) {
			int y = hash(x);
			for (int i = 0; i < set.get(y).size(); i++) {
				if (set.get(y).get(i) == x) {
					set.get(y).remove(i);
					return;
				}
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
		new MainSet().run();
	}
}
