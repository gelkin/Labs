import java.util.*;
import java.io.*;

public class MainKth {

	FastScanner in;
	PrintWriter out;
	
	public void solve() throws IOException {
		String str;
		int n = in.nextInt();		
		int k = in.nextInt();
		int A = in.nextInt();
		int B = in.nextInt();
		int C = in.nextInt();
		int a1 = in.nextInt();
		int a2 = in.nextInt();
		Kth el = new Kth(n, A, B, C, a1, a2);
		out.println(el.kth(0, n - 1, k - 1));
	}

	public void run() {
		try {
			in = new FastScanner(new File("kth.in"));
			out = new PrintWriter(new File("kth.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Kth {
		int[] a;
		
		public Kth(int n, int A, int B, int C, int a1, int a2) {
			a = new int[n];
			a[0] = a1;
			a[1] = a2;
			for (int i = 2; i < n; i++) {
				a[i] = A * a[i - 2] + B * a[i - 1] + C;
			}
		}
		
		private int kth(int l, int r, int k) {
			int x = a[k];
			int i = l;
			int j = r;
			while (i <= j) {
				while (a[i] < x) {
					i++;
				}
				while (x < a[j]) {
					j--;
				}
				if (i <= j) {
					int tmp = a[i];
					a[i] = a[j];
					a[j] = tmp;
					i++;
					j--;
				}
			}
			
			if (l <= k && k <= j) {
    	        return kth(l, j, k);
	        }

	        if (i <= k && k <= r) {
	            return kth(i, r, k);
	        }
	        
	        return a[k];
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
		new MainKth().run();
	}
}
