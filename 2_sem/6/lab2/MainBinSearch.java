import java.util.*;
import java.io.*;

public class MainBinSearch {

	FastScanner in;
	PrintWriter out;
	
	public void solve() throws IOException {
		String str;
		int n = in.nextInt();		
		
		BinSearch el = new BinSearch(n);
		
		n = in.nextInt();
		for (int i = 0; i < n; i++) { 
			el.show(in.nextInt());
		}
	}

	public void run() {
		try {
			in = new FastScanner(new File("binsearch.in"));
			out = new PrintWriter(new File("binsearch.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class BinSearch {
		int[] a;
		
		public BinSearch(int n) {
			a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = in.nextInt();
			}
		}
		
		private int binSearchFirst(int k) {
			int l = 0;
			int u = a.length - 1;
			int m;
			
			while (l <= u) {
				m = l + (u - l) / 2;
				if (a[m] < k) {
					l = m + 1;
				} else {
					u = m - 1;
				}
			}
            
            if((l >= 0) && (l < a.length) && (a[l] == k)) {
                return l;
            }
            
            return -1;
		}
		
		private int binSearchLast(int k) {
			int l = 0;
			int u = a.length - 1;
			int m;
			
			while (l <= u) {
				m = l + (u - l) / 2;
				if (a[m] <= k) {
					l = m + 1;
				} else {
					u = m - 1;
				}
			}
            
            if((u >= 0) && (u < a.length) && (a[u] == k)) {
                return u;
            }
            
            return -1;
		}
		
		public void show(int k) {
			int i = binSearchFirst(k);
			if (i != -1) {
				int j = binSearchLast(k);
				out.println((i + 1) + " " + (j + 1));
				return;
			}
			out.println("-1 -1");
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
		new MainBinSearch().run();
	}
}
