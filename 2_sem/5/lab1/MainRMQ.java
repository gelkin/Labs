import java.util.*;
import java.io.*;

public class MainRMQ {

	FastScanner in;
	PrintWriter out;
	
	public static final int MAX = 1000000001;
	
	public void solve() throws IOException {
		String str;
		int n = in.nextInt();		
		int x;
		int y;
		
		int[] a = new int[n];
		
		for (int i = 0; i < n; i++) {
			a[i] = in.nextInt();
		}
		
		RMQ tree = new RMQ(n);
		tree.build(a, 0, 0, n);
		while (!"".equals(str = in.next())) {
			x = in.nextInt();
			y = in.nextInt();
			switch(str) {
			
			case "min":
				out.println(tree.min(0, 0, n, x - 1, y));
				break;
			case "set":
				tree.update(0, 0, n, x - 1, y);
				break;
			default:
				System.out.println("Ошибка!");
			}
		}
	}

	public void run() {
		try {
			in = new FastScanner(new File("rmq.in"));
			out = new PrintWriter(new File("rmq.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class RMQ {
		int[] tree;
		
		public RMQ(int n) {
			tree = new int[4 * n];
		}
		
		// At the first step: v = 0, tl = 0, tr = n
		public void build(int[] a, int v, int tl, int tr) {
			if (tl == (tr - 1)) {
				tree[v] = a[tl];
			} else {
				int tm = (tl + tr) / 2;
				build (a, v * 2 + 1, tl, tm);
				build (a, v * 2 + 2, tm, tr);
				tree[v] = Math.min(tree[v * 2 + 1], tree[v * 2 + 2]);
			}
		}
		
		// At the first step: v = 0, tl = 0, tr = n
		public int min(int v, int tl, int tr, int l, int r) {
			if (l >= r) {
				return MAX;
			}
			if (l == tl && r == tr) {
				return tree[v];
			}
			int tm = (tl + tr) / 2;
			return Math.min(min (v * 2 + 1, tl, tm, l, Math.min(r, tm)),
							min (v * 2 + 2, tm, tr, Math.max(l, tm), r));
		}
		
		// At the first step: v = 0, tl = 0, tr = n
		public void update(int v, int tl, int tr, int pos, int new_val) {
			if (tl == (tr - 1)) {
				tree[v] = new_val;
			} else {
				int tm = (tl + tr) / 2;
				if (pos <= (tm - 1)) {
					update (v * 2 + 1, tl, tm, pos, new_val);
				} else {
					update (v * 2 + 2, tm, tr, pos, new_val);
				}
				tree[v] = Math.min(tree[v * 2 + 1], tree[v * 2 + 2]);
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
		new MainRMQ().run();
	}
}
