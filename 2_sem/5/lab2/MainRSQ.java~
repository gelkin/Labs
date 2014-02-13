import java.util.*;
import java.io.*;

public class MainRSQ {

	FastScanner in;
	PrintWriter out;
	
	// public static final int MAX = 1000000000;
	
	public void solve() throws IOException {
		String str;
		int n = in.nextInt();		
		int x;
		int y;
		long z;
		
		long[] a = new long[n];
		
		for (int i = 0; i < n; i++) {
			a[i] = in.nextLong();
		}
		
		RSQ tree = new RSQ(n);
		tree.build(a, 0, 0, n);
		while (!"".equals(str = in.next())) {
			x = in.nextInt();
			
			switch(str) {
			
			case "sum":
				y = in.nextInt();
				out.println(tree.sum(0, 0, n, x - 1, y));
				break;
			case "set":
				z = in.nextLong();
				tree.update(0, 0, n, x - 1, z);
				break;
			default:
				System.out.println("Ошибка!");
			}
		}
	}

	public void run() {
		try {
			in = new FastScanner(new File("rsq.in"));
			out = new PrintWriter(new File("rsq.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class RSQ {
		long[] tree;
		
		public RSQ(int n) {
			this.tree = new long[4 * n];
		}
		
		// At the first step: v = 0, tl = 0, tr = n
		public void build(long[] a, int v, int tl, int tr) {
			if (tl == (tr - 1)) {
				tree[v] = a[tl];
			} else {
				int tm = (tl + tr) / 2;
				build (a, v * 2 + 1, tl, tm);
				build (a, v * 2 + 2, tm, tr);
				tree[v] = tree[v * 2 + 1] + tree[v * 2 + 2];
			}
		}
		
		// At the first step: v = 0, tl = 0, tr = n
		public long sum(int v, int tl, int tr, int l, int r) {
			if (l >= r) {
				return 0;
			}
			if (l == tl && r == tr) {
				return tree[v];
			}
			int tm = (tl + tr) / 2;
			return sum(v * 2 + 1, tl, tm, l, Math.min(r, tm)) +
						sum(v * 2 + 2, tm, tr, Math.max(l, tm), r);
		}
		
		// At the first step: v = 0, tl = 0, tr = n
		public void update(int v, int tl, int tr, int pos, long new_val) {
			if (tl == (tr - 1)) {
				tree[v] = new_val;
			} else {
				int tm = (tl + tr) / 2;
				if (pos <= (tm - 1)) {
					update (v * 2 + 1, tl, tm, pos, new_val);
				} else {
					update (v * 2 + 2, tm, tr, pos, new_val);
				}
				tree[v] = tree[v * 2 + 1] + tree[v * 2 + 2];
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
		
		long nextLong() {
			return Long.parseLong(next());
		}
	}

	public static void main(String[] args) {
		new MainRSQ().run();
	}
}
