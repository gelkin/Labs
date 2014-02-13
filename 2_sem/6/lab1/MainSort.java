import java.util.*;
import java.io.*;

public class MainSort {

	FastScanner in;
	PrintWriter out;
	
	public void solve() throws IOException {
		String str;
		int n = in.nextInt();		
		
		Merge el = new Merge(n);
		
		el.mergeSort(0, n-1);
		el.show();
	}

	public void run() {
		try {
			in = new FastScanner(new File("sort.in"));
			out = new PrintWriter(new File("sort.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Merge {
		int[] a;
		
		public Merge(int n) {
			a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = in.nextInt();
			}
		}
		
		public void mergeSort(int lb, int ub) {
			int split;
			if (lb < ub) {
				split = (lb + ub) / 2;
				mergeSort(lb, split);
    			mergeSort(split + 1, ub);
   				merge(lb, split, ub);
			}
		}
		
		private void merge(int lb, int split, int ub) {
			int pos1 = lb;
			int pos2 = split + 1;
			int pos3 = 0;
			int[] temp = new int[ub - lb + 1];
			
			while (pos1 <= split && pos2 <= ub) {
			    if (a[pos1] < a[pos2])
      				temp[pos3++] = a[pos1++];
    			else
      				temp[pos3++] = a[pos2++];
  			}
  			
  			while (pos2 <= ub) {
    			temp[pos3++] = a[pos2++];
  			}
  			while (pos1 <= split) {
    			temp[pos3++] = a[pos1++];
			}
			for (pos3 = 0; pos3 < ub - lb + 1; pos3++) {
  				a[lb+pos3] = temp[pos3];
			}
		}
		
		public void show() {
			for (int i = 0; i < a.length; i++) {
				out.print(a[i]);
				out.print(" ");
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
		new MainSort().run();
	}
}
