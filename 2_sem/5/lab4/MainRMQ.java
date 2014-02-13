import java.util.*;
import java.io.*;

public class MainRMQ {

	FastScanner in;
	PrintWriter out;
	
	public static final long MAX = 1000000000000000001;
	public static final long MIN = -1000000000000000001;
	
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
			in = new FastScanner(new File("rmq2.in"));
			out = new PrintWriter(new File("rmq2.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class RMQ {
		Seg[] tree;
		
		public RMQ(int n) {
			tree = new Seg[4 * n];
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
		
		public void addPush(int v) {
			if (tree[v].add != 0) {
				tree[v * 2].add += tree[v].add;
				tree[v * 2 + 1].add = tree[v].add;
				tree[v].rep = 0;
			}
		}
		
		public void setPush(int v) {
			if (tree[v].rep != MIN) {
				tree[v * 2].rep = tree[v].rep;
				tree[v * 2 + 1].rep = tree[v].rep;
				tree[v].rep = MIN;
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
		
		void add(int v, int tl, int tr, int l, int r, int add) {
			if (l >= r) {
				return;
			}
			if (l == tl && tr == r) {
				tree[v].add += add;
			}
			else {
				int tm = (tl + tr) / 2;
				add(v * 2 + 1, tl, tm, l, Math.min(r, tm), add);
				add(v * 2 + 2, tm, tr, Math.max(l, tm), r, add));
			}
		}
		
		// Replace all elements from l to r by "color"
		// At the first step: v = 0, tl = 0, tr = n
		public void set(int v, int tl, int tr, int l, int r, int color) {
			if (l >= r) {
				return;
			}
			if (tl == (tr - 1)) {
				tree[v].rep = color;
			} else {
				setPush(v);
				int tm = (tl + tr) / 2;
				set(v * 2 + 1, tl, tm, l, r, color);
				set(v * 2 + 2, tm, tr, l, r, color);
			}
		}
		
	}
	
	class Seg {
		public long val; // само значение, "минимум"
		public long	rep; // на что заменить
		public long add; // сколько прибавить
		
		
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
