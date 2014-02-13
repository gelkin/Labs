import java.util.*;
import java.io.*;

public class Lab4 {

	FastScanner in;
	PrintWriter out;
	
	final int inf = 2000000000;
	int[] tree;
	int[] arr;
	
	public void build(int v, int tl, int tr) {
		if (tl == tr) {
			// System.out.println("XXX___XXX");
			tree[v] = arr[tl];
		} else {
			int tm = (tl + tr) / 2;
			build(v*2, tl, tm);
			build(v*2 + 1, tm + 1, tr);
			tree[v] = Math.min(tree[v*2], tree[v*2 + 1]);
		}
	}
	
	public int findPlace(int v, int tl, int tr, int l, int r) {
		if (l > r) {
			return inf;
		}
		if (l == tl && r == tr) {
			return tree[v];
		}
		int tm = (tl + tr) / 2;
		return Math.min(findPlace(v*2, tl, tm, l, Math.min(r,tm)),
						findPlace(v*2 + 1, tm + 1, tr, Math.max(l, tm + 1), r));
	}
	
	public void update(int v, int tl, int tr, int pos, int new_val) {
		if (tl == tr) {
			tree[v] = new_val;
		} else {
			int tm = (tl + tr) / 2;
			if (pos <= tm) {
				update(v*2, tl, tm, pos, new_val);
			} else {
				update(v*2 + 1, tm + 1, tr, pos, new_val);
			}
			tree[v] = Math.min(tree[v*2], tree[v*2 + 1]);
	}
}
	
	public void solve() throws IOException {
		int n = in.nextInt();
		int m = in.nextInt();
		arr = new int[n]; 		// Array which contains values.
		for (int i = 0; i < n; i++) {	// Next, these values fill in the "tree"
			arr[i] = i;
		}
		// System.out.println("XXX___XXX");
		tree = new int[4*n]; // Make a tree
		// System.out.println("XXX___XXX");
		build(1, 0, n - 1); // Fill it for the first time
		// System.out.println("XXX___XXX");
		String str;
		int x;
		int res;
		for (int i = 0; i < m; i++) {
			str = in.next();
			if ("enter".equals(str)) {
				x = in.nextInt() - 1;
				res = findPlace(1, 0, n - 1, x, n - 1);
				if (res < inf) {
					out.println(res + 1);
					update(1, 0, n - 1, res, inf);
				} else {
					res = findPlace(1, 0, n - 1, 0, x);
					out.println(res + 1);
					update(1, 0, n - 1, res, inf);
				}
			} else {
				x = in.nextInt() - 1;
				update(1, 0, n - 1, x, x);
			}
		}
	}

	public void run() {
		try {
			in = new FastScanner(new File("parking.in"));
			out = new PrintWriter(new File("parking.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
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
		new Lab4().run();
	}
}
