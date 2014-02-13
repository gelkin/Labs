import java.util.*;
import java.io.*;

public class MainCrypto {

	FastScanner in;
	PrintWriter out;
	
	// public static final int MAX = 1000000000;
	
	public void solve() throws IOException {
		String str;
		int r = in.nextInt();
		int n = in.nextInt();		
		int m = in.nextInt();
		Matrix[] a = new Matrix[n];
		for (int i = 0; i < n; i++) {
			a[i] = new Matrix();
			a[i].init(r);
			str = in.next();
		}
		
		SegTree tree = new SegTree(n, r);
		tree.build(a, 0, 0, n);
		
		int x;
		int y;
		for (int i = 0; i < m; i++) {
			x = in.nextInt();
			y = in.nextInt();
			(tree.getMul(0, 0, n, x - 1, y)).show();
			out.println("");
		}
		
	}

	public void run() {
		try {
			in = new FastScanner(new File("crypto.in"));
			out = new PrintWriter(new File("crypto.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class SegTree {
		Matrix[] tree;
		Matrix UNITY = new Matrix();
		int r;
		
		
		public SegTree(int n, int r) {
			tree = new Matrix[4 * n];
			UNITY.a = 1;
			UNITY.b = 0;
			UNITY.c = 0;
			UNITY.d = 1;
			
			this.r = r;
		}
		
		// At the first step: v = 0, tl = 0, tr = n
		public void build(Matrix[] a, int v, int tl, int tr) {
			if (tl == (tr - 1)) {
				tree[v] = a[tl];
			} else {
				int tm = (tl + tr) / 2;
				build (a, v * 2 + 1, tl, tm);
				build (a, v * 2 + 2, tm, tr);
				tree[v] = mul(tree[v * 2 + 1], tree[v * 2 + 2]);
			}
		}
		
		// At the first step: v = 0, tl = 0, tr = n
		public Matrix getMul(int v, int tl, int tr, int l, int r) {
			if (l >= r) {
				return UNITY;
			}
			if (l == tl && r == tr) {
				return tree[v];
			}
			int tm = (tl + tr) / 2;
			return mul(getMul(v * 2 + 1, tl, tm, l, Math.min(r, tm)),
							getMul(v * 2 + 2, tm, tr, Math.max(l, tm), r));
		}
		
		private Matrix mul(Matrix L, Matrix R) {
			Matrix ans = new Matrix();
			ans.a = (L.a * R.a + L.b * R.c) % r;
			ans.b = (L.a * R.b + L.b * R.d) % r;
			ans.c = (L.c * R.a + L.d * R.c) % r;
			ans.d = (L.c * R.b + L.d * R.d) % r;
			return ans;
		}
	}
	
	class Matrix {
		int a;
		int b;
		int c;
		int d;
		
		public void init(int r) {
			a = (int) (in.nextLong()) % r;
			b = (int) (in.nextLong()) % r;
			c = (int) (in.nextLong()) % r;
			d = (int) (in.nextLong()) % r;
		}
		
		public void show() {
			out.print(a);
			out.print(" ");
			out.println(b);
			out.print(c);
			out.print(" ");
			out.println(d);		
		}
		
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean Check;
		String line;
		String enter = "%n";

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
					if (((line = br.readLine()) != null) && (!line.isEmpty())) {
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
		new MainCrypto().run();
	}
}
